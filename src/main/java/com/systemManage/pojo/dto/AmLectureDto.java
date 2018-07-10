package com.systemManage.pojo.dto;

import lombok.Getter;
import lombok.Setter;

import com.systemManage.pojo.base.AmAdopt;
import com.systemManage.pojo.base.AmLecture;

public class AmLectureDto extends AmLecture{
    private static final long serialVersionUID = 1L;
    
    /**
     * 基本信息集合
     */
    @Getter@Setter
    private String baseInfoName;// 第一负责人

}