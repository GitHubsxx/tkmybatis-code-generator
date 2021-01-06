package ${daoPackagePath};

<#list importPackages as packagePath>
import ${packagePath};
</#list>

/**
* @description: 数据访问接口
* @date: ${nowDateTime}
*/
public interface ${domainClassName}Mapper extends BaseMapper<${domainClassName}>{
}