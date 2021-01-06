package ${domainPackagePath};

import lombok.*;
import lombok.experimental.Accessors;
import javax.persistence.*;
import java.io.Serializable;
<#list domainImportPackages as packagePath>
import ${packagePath};
</#list>

/**
* @description: 实体类
* @date: ${nowDateTime}
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "${tableName}")
public class ${domainClassName} implements Serializable{
<#list columnInfoList as columnInfo>
    <#if columnInfo.colName==primaryKeyColumn>
        /**
        * ${columnInfo.colDesc}
        */
        @Id
        @Column(name = "${columnInfo.colName}")
        private ${columnInfo.propertyType} ${columnInfo.propertyName};
    <#else>
        /**
        * ${columnInfo.colDesc}
        */
        @Column(name = "${columnInfo.colName}")
        private ${columnInfo.propertyType} ${columnInfo.propertyName};
    </#if>

</#list>
}