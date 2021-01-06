package com.sxx.generator.util;

import java.sql.*;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import com.sxx.generator.constant.Constant;
import com.sxx.generator.entity.GeneratorParam;
import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.sxx.generator.entity.ColumnInfo;
import com.sxx.generator.entity.TableInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author: sxx
 * Date: 2021/1/5
 * @description: DB工具
 */
public class DBUtil {
    public static final Logger logger = LoggerFactory.getLogger(DBUtil.class);

    public static Connection getConnection(DbInfo dbInfo) throws Exception {
        Class.forName(dbInfo.getDriverClass());
        Properties props = new Properties();
        props.put("user", dbInfo.getUserName());
        props.put("password", dbInfo.getPassword());
        props.put("remarksReporting", "true");
        return DriverManager.getConnection(dbInfo.getConnectionURL(), props);
    }

    public static TableInfo getTableInfo(String tableName, String domainName, DbInfo dbInfo) throws Exception {
        TableInfo tableInfo = new GeneratorParam();
        tableInfo.setTableName(tableName);
        tableInfo.setDomainClassName(StringUtils.isNotBlank(domainName) ? domainName : StringUtil.formatDomainName(tableName));
        DatabaseMetaData metaData = getConnection(dbInfo).getMetaData();
        //获取主键所在的列
        ResultSet pks = metaData.getPrimaryKeys(null, null, tableName);
        String pkColumnName = "";
        while (pks.next()) {
            pkColumnName = pks.getObject(4) == null ? "" : pks.getObject(4).toString();
            break;
        }
        tableInfo.setPrimaryKeyColumn(pkColumnName);

        ResultSet columns = metaData.getColumns(null, null, tableName, null);
        List<ColumnInfo> colInfos = Lists.newArrayList();
        Set<String> importPackages = Sets.newHashSet();

        while (columns.next()) {
            ColumnInfo columnInfo = new ColumnInfo();
            String columnName = columns.getString("COLUMN_NAME");
            //去除重复
            if (colInfos.stream().anyMatch(item -> item.getColName().equals(columnName))) {
                continue;
            }
            columnInfo.setColName(columnName);
            columnInfo.setPropertyName(StringUtil.formatFieldName(columnName));
//            columnInfo.setFieldMethodName(methodName);
//            String methodName = StringUtil.formatGetSetMethod(columns.getString("COLUMN_NAME"));

            String desc = columns.getString("REMARKS") == null ? "" : columns.getString("REMARKS");
            columnInfo.setColDesc(desc);
            String dataBaseType = columns.getString("TYPE_NAME");
            String javaType = Constant.DATA_TYPE_MAP.get(dataBaseType);
            //处理数据库number类型
            if (StringUtils.equals("NUMBER", dataBaseType)) {
                int columnSize = Integer.valueOf(columns.getString("COLUMN_SIZE"));
                if (columnSize == 1) {
                    javaType = "Boolean";
                    dataBaseType = "BIT";
                } else if (columnSize == 2) {
                    javaType = "Byte";
                    dataBaseType = "TINYINT";
                } else if (columnSize == 3 || columnSize == 4) {
                    javaType = "Short";
                    dataBaseType = "SMALLINT";
                } else if (columnSize >= 5 && columnSize <= 9) {
                    javaType = "Integer";
                    dataBaseType = "INTEGER";
                } else if (columnSize >= 10 && columnSize < 18) {
                    javaType = "Long";
                    dataBaseType = "BIGINT";
                } else {
                    dataBaseType = "DECIMAL";
                }
            }

            if (StringUtils.equals(tableInfo.getPrimaryKeyColumn(), columnInfo.getColName())) {
                tableInfo.setPrimaryKeyPropertyType(javaType);
            }
            dataBaseType = dataBaseType.replace("VARCHAR2", "VARCHAR");
            columnInfo.setColType(dataBaseType);
            columnInfo.setPropertyType(javaType);

            String packagePath = Constant.IMPORT_PACKAGE_MAP.get(javaType);
            if (StringUtils.isNotEmpty(packagePath)) {
                importPackages.add(packagePath);
            }
            if (StringUtils.equals(columnInfo.getColName(), tableInfo.getPrimaryKeyColumn())) {
                colInfos.add(0, columnInfo);
            } else {
                colInfos.add(columnInfo);
            }
        }
        tableInfo.setDomainImportPackages(importPackages);
        tableInfo.setColumnInfoList(colInfos);
        return tableInfo;
    }
}
