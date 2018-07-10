package com.systemManage.pojo.dto;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.systemManage.pojo.base.AmCollaborator;
import com.systemManage.pojo.base.AmPrize;

//查询关联关系表需要用到该注解
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AmPrizeDto extends AmPrize{
    private static final long serialVersionUID = 1L;
    
    /**
     * 作者名称
     */
    private String baseInfoName;
    
    /**
     * 合作者
     */
    private List<AmCollaboratorDto> amCollaborators;
    
    public List<AmCollaboratorDto> getAmCollaborators() {
        return amCollaborators;
    }

    public void setAmCollaborators(List<AmCollaboratorDto> amCollaborators) {
        this.amCollaborators = amCollaborators;
    }
    
    public String getBaseInfoName() {
        return baseInfoName;
    }

    public void setBaseInfoName(String baseInfoName) {
        this.baseInfoName = baseInfoName;
    }
}