package ${daoPackagePath};

<#list importPackages as packagePath>
import ${packagePath};
</#list>

/**
* @description: 数据访问扩展接口
* @date: ${nowDateTime}
*/
public interface ${domainClassName}ExtendMapper extends ${domainClassName}Mapper<${domainClassName}>{
}