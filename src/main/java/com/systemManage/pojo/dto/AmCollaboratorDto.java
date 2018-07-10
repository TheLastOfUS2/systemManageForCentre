package com.systemManage.pojo.dto;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.systemManage.pojo.base.AmCollaborator;

//查询关联关系表需要用到该注解
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AmCollaboratorDto extends AmCollaborator {
    private static final long serialVersionUID = 1L;
    
    /**
     * 合作者名称
     */
    private String BaseInfoName;

    public String getBaseInfoName() {
        return BaseInfoName;
    }

    public void setBaseInfoName(String baseInfoName) {
        BaseInfoName = baseInfoName;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}