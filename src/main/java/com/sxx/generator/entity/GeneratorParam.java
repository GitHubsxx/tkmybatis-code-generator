package com.sxx.generator.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * Author: sxx
 * Date: 2021/1/5
 * @description: 代码生成相关参数
 */
@Data
public class GeneratorParam extends TableInfo {

    /**
     * 包路径
     */
    private String packagePath;
    /**
     * dao包路径
     */
    private String daoPackagePath;
    /**
     * dao extend包路径
     */
    private String daoExtendPackagePath;
    /**
     * 需要导入的所有包路径
     */
    private List<String> importPackages;
    /**
     * 方法参数
     */
    private String methodParamName;

    /**
     * 驱动类
     */
    private String driverClass;
    /**
     * connectionURL
     */
    private String connectionURL;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 多表名
     */
    private String tables;
    /**
     * 多实体类名
     */
    @NotBlank(message = "自定义实体类名不能空")
    private String domains;
    /**
     * 包名前缀
     */
    private String packagePrefix;

    /**
     * 类名后缀
     */
    private String classSuffix;
    /**
     * 是否首次生成
     */
    private String isFirst;

    /**
     * 模板文件名
     */
    private String templateName;

}
