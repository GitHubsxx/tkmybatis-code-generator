package com.sxx.generator.util;

import lombok.Data;

/**
 * Author: sxx
 * Date: 2021/1/5
 * @description: DB连接数据
 */
@Data
public class DbInfo {
    private String driverClass;
    private String connectionURL;
    private String userName;
    private String password;
}
