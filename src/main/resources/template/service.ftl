package ${packagePath};

import com.sxx.common.service.base.BaseService;
<#list importPackages as packagePath>
import ${packagePath};
</#list>

/**
* @description: Service业务接口
* @date: ${nowDateTime}
*/
public interface ${domainClassName}Service extends BaseService<${domainClassName}, ${primaryKeyPropertyType}>{

}