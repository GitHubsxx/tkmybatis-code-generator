package com.sxx.generator.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Author: sxx
 * Date: 2021/1/5
 * @description: 字符串工具
 */
public class StringUtil {
    private static Pattern linePattern = Pattern.compile("_(\\w)");

    private StringUtil() {

    }

    /**
     * 格式化domain类名
     *
     * @param tableName
     * @return
     */
    public static String formatDomainName(String tableName) {
        return formatName(tableName);
    }

    /**
     * 格式化get、set方法后半部分
     *
     * @param methodName
     * @return
     */
    public static String formatGetSetMethod(String methodName) {
        return formatName(methodName);
    }

    /**
     * a:首字母大写
     * b:若含有下划线，则去掉下划线，下划线后第一位字母大写
     *
     * @param str
     * @return
     */
    private static String formatName(String str) {
        //首先全部转小写
        str = str.toLowerCase();
        char[] cs = str.toCharArray();
        cs[0] -= 32;
        str = String.valueOf(cs);
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();

    }

    /**
     * a:首字母小写
     * b:若含有下划线，则去掉下划线，下划线后第一位字母大写
     *
     * @param columnName
     * @return
     */
    public static String formatFieldName(String columnName) {
        //首先全部转小写
        columnName = columnName.toLowerCase();

        char[] cs = columnName.toCharArray();
        int position = 0;
        if ((position = columnName.indexOf("_")) > 0) {
            cs[position + 1] -= 32;
        }
        String newName = String.valueOf(cs);
        return newName.replace("_", "");
    }
}
