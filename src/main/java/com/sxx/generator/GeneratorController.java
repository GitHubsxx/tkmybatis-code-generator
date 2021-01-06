package com.sxx.generator;

import com.sxx.generator.constant.Constant;
import com.sxx.generator.entity.GeneratorParam;
import com.sxx.generator.util.*;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: sxx
 * Date: 2021/1/5
 * @description: 代码逆向生成入口控制器
 */
@Controller
public class GeneratorController {
    public static final Logger logger = LoggerFactory.getLogger(GeneratorController.class);

    private DbInfo dbInfo = new DbInfo();
    private String zipName = "test";
    private String fileTargetSrc = "";

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @PostMapping(value = "/toGenerator", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Map<String, Object> toGenerator(@Valid @ModelAttribute GeneratorParam generatorParam, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        try {
            generatorParam.setDriverClass(generatorParam.getDriverClass().trim());
            if (generatorParam.getDriverClass() != null && generatorParam.getDriverClass().contains("mysql.cj")) {
                generatorParam.setConnectionURL(generatorParam.getConnectionURL().trim() + "&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai");
            } else {
                generatorParam.setConnectionURL(generatorParam.getConnectionURL().trim());
            }
            generatorParam.setUserName(generatorParam.getUserName().trim());
            generatorParam.setPassword(generatorParam.getPassword().trim());

            String tables = generatorParam.getTables().replace(" ", "");
            String domains = generatorParam.getDomains().replace(" ", "");
            String packagePrefix = generatorParam.getPackagePrefix().replace(" ", "");

            //是否首次生成
            String isFirst = generatorParam.getIsFirst();
            boolean isFirstBoolean = StringUtils.equals("on", isFirst) ? true : false;

            org.springframework.beans.BeanUtils.copyProperties(generatorParam, this.dbInfo);

            String[] tableArray = {};
            String[] domainsArray = {};
            if (StringUtils.isNotBlank(tables)) {
                tableArray = tables.split(",");
            }
            if (StringUtils.isNotBlank(domains)) {
                domainsArray = domains.split(",");
            }

            zipName = tableArray[0];
            fileTargetSrc = Constant.TARGET_SRC + zipName + "(" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyy-MM-dd")) + ")";

            for (int i = 0; i < tableArray.length; i++) {
                String domainName = "";
                if (domainsArray.length == tableArray.length) {
                    domainName = domainsArray[i];
                }
                create(tableArray[i].toUpperCase(), domainName, fileTargetSrc, packagePrefix, isFirstBoolean);
            }

        } catch (Exception e) {
            if (StringUtils.isNotBlank(fileTargetSrc)) {
                FileUtil.delFile(new File(fileTargetSrc));
            }
            logger.error("生成失败，原因:", e);
            map.put("error", e.getMessage());
            return map;
        }
        map.put("success", "java基础代码导出成功");
        logger.info("java基础代码导出成功");
        return map;
    }

    public static List<StackTraceElement> getExceptionInfo(Throwable e) {
        //只打印10行的错误堆栈
        int count = 0;
        List<StackTraceElement> list = Lists.newArrayList();
        for (StackTraceElement stackTraceElement : e.getStackTrace()) {
            String className = stackTraceElement.getClassName();
            if (className.indexOf("org.") > -1 || className.indexOf("sun.") > -1 || className.indexOf("java.") > -1 || className.indexOf("javax.") > -1 ||
                    className.indexOf("com.alibaba") > -1 || className.indexOf("CGLIB$$") > -1) {
                continue;
            }
            if (className.indexOf("com.sxx") > -1) {
                list.add(0, stackTraceElement);
            } else {
                list.add(stackTraceElement);
            }
            if (count++ > 15) {
                break;
            }
        }
        return list;
    }

    public void create(String tableName, String domainName, String targetSrc, String packagePrefix, boolean isFirstBoolean) throws Exception {
        GeneratorParam param = (GeneratorParam) DBUtil.getTableInfo(tableName, domainName, this.dbInfo);
        //当前时间
        String nowDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
        param.setNowDateTime(nowDateTime);
        //定义包名
        String domainPackagePath = packagePrefix + ".entity";
        String daoPackagePath = packagePrefix + ".dao";
        String daoExtendPackagePath = packagePrefix + ".dao.extend";
        String servicePackagePath = packagePrefix + ".service";
        String serviceImplPackagePath = packagePrefix + ".service.impl";

        //生成domain
        param.setDomainPackagePath(domainPackagePath);
        param.setTemplateName("domain.ftl");
        Creator.create(param, targetSrc, true);

        //生成xml
        param.setDaoPackagePath(daoPackagePath + "." + param.getDomainClassName() + "Mapper");
        param.setDomainPackagePath(domainPackagePath + "." + param.getDomainClassName());
        param.setPrimaryKeyColumn(param.getPrimaryKeyColumn());
        param.setClassSuffix("Mapper");
        param.setTemplateName("mapper.ftl");
        Creator.create(param, targetSrc, false);

        if (isFirstBoolean) {
            //生成dao
            param.setDaoPackagePath(daoPackagePath);
            List<String> packageList = Lists.newArrayList(domainPackagePath + "." + param.getDomainClassName());
            param.setImportPackages(packageList);
            param.setClassSuffix("Mapper");
            param.setTemplateName("dao.ftl");
            Creator.create(param, targetSrc, true);

            //生成dao extend
            param.setDaoExtendPackagePath(daoExtendPackagePath);
            packageList.add(daoPackagePath + "." + param.getDomainClassName() + "Mapper");
            param.setClassSuffix("ExtendMapper");
            param.setTemplateName("daoExtend.ftl");
            Creator.create(param, targetSrc, true);

            //生成extend xml
            param.setTemplateName("mapperExtend.ftl");
            Creator.create(param, targetSrc, false);

            //生成service
            param.setPackagePath(servicePackagePath);
            param.setMethodParamName(StringUtil.formatFieldName(StringUtils.isNoneBlank(domainName) ? domainName : tableName));
            param.setClassSuffix("Service");
            param.setTemplateName("service.ftl");
            Creator.create(param, targetSrc, true);

            //生成serviceImpl
            packageList.add(servicePackagePath + "." + param.getDomainClassName() + "Service");
            packageList.add(daoPackagePath + "." + param.getDomainClassName() + "Mapper");
            param.setPackagePath(serviceImplPackagePath);
            param.setClassSuffix("ServiceImpl");
            param.setTemplateName("serviceImpl.ftl");
            Creator.create(param, targetSrc, true);
        }
    }

    @RequestMapping("/downloadZip")
    @ResponseBody
    public void downloadZip(HttpServletResponse response) {
        ZipUtil.downLoad(response, Constant.TARGET_SRC, fileTargetSrc, zipName);
    }
}
