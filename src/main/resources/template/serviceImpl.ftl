package ${packagePath};

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.sxx.common.service.base.CrudBaseService;
<#list importPackages as packagePath>
import ${packagePath};
</#list>

/**
* @description: Service业务接口实现
* @date: ${nowDateTime}
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class ${domainClassName}ServiceImpl extends CrudBaseService<${domainClassName}, ${primaryKeyPropertyType}> implements ${domainClassName}Service {
    @Autowired
    private ${domainClassName}Mapper ${methodParamName}Mapper;

}