package com.systemManage.pojo.dto;

import lombok.Getter;
import lombok.Setter;

import com.systemManage.pojo.base.AmActivity;
import com.systemManage.pojo.base.AmAdopt;

public class AmActivityDto extends AmActivity{
    private static final long serialVersionUID = 1L;
    
    /**
     * 基本信息集合
     */
    @Getter@Setter
    private String baseInfoName;// 第一负责人

}