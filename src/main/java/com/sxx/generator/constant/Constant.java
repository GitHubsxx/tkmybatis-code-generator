package com.sxx.generator.constant;

import com.google.common.collect.ImmutableMap;

/**
 * Author: sxx
 * Date: 2021/1/5
 * @description: 常量
 */
public class Constant {

    private Constant() {

    }

    public static final String JAVA_FILE_SUFFIX = ".java";
    public static final String XML_FILE_SUFFIX = ".xml";
    //	public static final String TARGET_SRC = "D:/SpringCloud/sxx-mybatis-codeGenerator/code/";	//local
    public static final String TARGET_SRC = "/app/generator/code/";    //test


    public static final ImmutableMap<String, String> DATA_TYPE_MAP = ImmutableMap.<String, String>builder()
            .put("CHAR", "String")
            .put("VARCHAR", "String")
            .put("VARCHAR2", "String")
            .put("NVARCHAR2", "String")
            .put("TEXT", "String")
            .put("CLOB", "String")
            .put("LONG", "String")
            .put("INTEGER", "Long")
            .put("TINYINT", "Integer")
            .put("SMALLINT", "Integer")
            .put("BIT", "Boolean")
            .put("BIGINT", "BigInteger")
            .put("FLOAT", "Double")
            .put("NUMBER", "BigDecimal")
            .put("DECIMAL", "BigDecimal")
            .put("TIMESTAMP", "Timestamp")
            .put("DATETIME", "Timestamp")
            .put("TIME", "Time")
            .put("DATE", "Date")
            .put("INT", "Integer")
            .build();

    public static final ImmutableMap<String, String> IMPORT_PACKAGE_MAP = ImmutableMap.<String, String>builder()
            .put("BigDecimal", "java.math.BigDecimal")
            .put("Date", "java.util.Date")
            .put("Timestamp", "java.sql.Timestamp")
            .build();
}
