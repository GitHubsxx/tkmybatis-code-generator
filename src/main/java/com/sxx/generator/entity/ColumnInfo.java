package com.sxx.generator.entity;

import lombok.Data;

/**
 * Author: sxx
 * Date: 2021/1/5
 * @description: 列数据
 */
@Data
public class ColumnInfo {
    /**
     * 列名称
     */
    private String colName;
    /**
     * domain中的字段属性名称，需要经过转换，首字母小写，去掉下划线
     */
    private String propertyName;

    /**
     * 列类型
     */
    private String colType;

    /**
     * domain中的字段属性类型
     */
    private String propertyType;

    /**
     * 列描述
     */
    private String colDesc;

    /**
     * 列对应的get、set方法的后半部分
     */
    private String fieldMethodName;

}
