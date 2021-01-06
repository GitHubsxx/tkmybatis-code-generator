package com.sxx.generator.entity;


import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * Author: sxx
 * Date: 2021/1/5
 * @description: 表数据
 */
@Data
public class TableInfo {

    /**
     * 生成的Java文件的包路径
     */
    private String domainPackagePath;

    /**
     * 数据库表名称
     */
    private String tableName;
    /**
     * 主键所在的列
     */
    private String primaryKeyColumn;
    /**
     * 主键所在的列对应的java数据类型
     */
    private String primaryKeyPropertyType;

    /**
     * domain名称
     */
    private String domainClassName;

    /**
     * 表描述，对应类的注释
     */
    private String desc;

    /**
     * 需要导入的包
     */
    private Set<String> domainImportPackages;

    /**
     * 列信息
     */
    private List<ColumnInfo> columnInfoList;
    /**
     * 现在时间
     */
    private String nowDateTime;

}
