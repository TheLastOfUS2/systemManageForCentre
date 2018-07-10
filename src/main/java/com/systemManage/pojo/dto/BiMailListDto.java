package com.systemManage.pojo.dto;

import lombok.Getter;
import lombok.Setter;

import com.systemManage.pojo.base.BiMailList;

public class BiMailListDto extends BiMailList {
    private static final long serialVersionUID = 1L;

    /**
     * 类型(0.科研人员1.行政人员3.博士后4.博士5.硕士6.其他人员)
     */
    @Getter@Setter
    private String baseInfoType;

    /**
     * 姓名
     */
    @Getter@Setter
    private String baseInfoName;
    
    /**
     * 职称（0.研究员1.副研究员2.助理研究员3.教授4.副教授5.讲师6.助教）
     */
    @Getter@Setter
    private String baseInfoPositionalTitles;
    
    /**
     * 职务
     */
    @Getter@Setter
    private String baseInfoPost;
    
    /**
     * 进站时间/入学时间
     */
    @Getter@Setter
    private String baseInfoStartTime;

    /**
     * 出站时间/毕业时间
     */
    @Getter@Setter
    private String baseInfoEndTime;

}