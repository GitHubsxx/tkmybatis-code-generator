package com.sxx.generator;

import com.sxx.generator.constant.Constant;
import com.sxx.generator.entity.GeneratorParam;
import com.sxx.generator.util.FileUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileWriter;

/**
 * Author: sxx
 * Date: 2021/1/5
 * @description: 模板代码生成器
 */
public abstract class Creator {

    public static void create(GeneratorParam params, String srcPath, boolean isJavaFileSuffix) throws Exception {
        FileWriter writer = null;
        try {
            Configuration cfg = new Configuration();
            cfg.setDirectoryForTemplateLoading(FileUtil.getTemplateFileDir());
            //类
            String className = params.getDomainClassName() +
                    (StringUtils.isNoneBlank(params.getClassSuffix()) ? params.getClassSuffix() : "") +
                    (isJavaFileSuffix ? Constant.JAVA_FILE_SUFFIX : Constant.XML_FILE_SUFFIX);
            File file = new File(srcPath + "/" + className);
            File parentFile = file.getParentFile();
            //创建文件夹
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            writer = new FileWriter(file);
            Template template = cfg.getTemplate(params.getTemplateName());
            template.process(params, writer);
        } catch (Exception e) {
            throw e;
        } finally {
            if (writer != null) {
                writer.flush();
                writer.close();
            }
        }
    }
}
