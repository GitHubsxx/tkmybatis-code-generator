package com.sxx.generator.util;

import java.io.File;
import java.net.URL;

/**
 * Author: sxx
 * Date: 2021/1/5
 * @description: 文件工具
 */
public class FileUtil {

    public static File getTemplateFileDir() {
        ClassLoader classLoader = FileUtil.class.getClassLoader();
        URL url = classLoader.getResource("template");
        //String templatePath = System.getProperty("user.dir") + File.separator + "template";
        String templatePath = url.getPath();
        File file;
        try {
            file = new File(url.getFile());
        } catch (Exception e) {
            file = new File(templatePath);
        }
        return file;
    }

    public static boolean delFile(File file) {
        if (!file.exists()) {
            return false;
        }

        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                delFile(f);
            }
        }
        return file.delete();
    }
}
