<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >

<mapper namespace="${daoPackagePath}">
    <resultMap id="BaseResultMap" type="${domainPackagePath}">
        <constructor>
        <#list columnInfoList as column>
            <#if column.colName==primaryKeyColumn>
                <idArg column="${column.colName}" jdbcType="${column.colType}" javaType="${column.propertyType}"/>
            <#else>
                <arg column="${column.colName}" jdbcType="${column.colType}" javaType="${column.propertyType}"/>
            </#if>
        </#list>
        </constructor>
    </resultMap>
</mapper>

