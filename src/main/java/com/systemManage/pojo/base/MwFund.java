package com.systemManage.pojo.base;

import java.io.Serializable;
import java.util.Date;

public class MwFund implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String fundId;

    /**
     * 经费名称
     */
    private String fundTitle;

    /**
     * 金额(万)
     */
    private Double fundAmounts;

    /**
     * 划拨时间
     */
    private String fundTime;

    /**
     * 经费类别（0.设备1.会议2.图书3.项目4.办公5.其他）
     */
    private String fundType;

    /**
     * 经费来源（0.教育部1.其他部委2.省市政府3.学校4.企事业单位5.社会捐助）
     */
    private String fundSource;

    /**
     * 备注
     */
    private String fundRemarks;

    /**
     * 是否删除（0.未删除1.删除--回收站）
     */
    private String fundDel;

    /**
     * 录入时间
     */
    private Date fundCreateTime;

    /**
     * @return ID
     */
    public String getFundId() {
        return fundId;
    }

    /**
     * @param fundId 
	 *            ID
     */
    public void setFundId(String fundId) {
        this.fundId = fundId;
    }

    /**
     * @return 经费名称
     */
    public String getFundTitle() {
        return fundTitle;
    }

    /**
     * @param fundTitle 
	 *            经费名称
     */
    public void setFundTitle(String fundTitle) {
        this.fundTitle = fundTitle;
    }

    /**
     * @return 金额(万)
     */
    public Double getFundAmounts() {
        return fundAmounts;
    }

    /**
     * @param fundAmounts 
	 *            金额(万)
     */
    public void setFundAmounts(Double fundAmounts) {
        this.fundAmounts = fundAmounts;
    }

    /**
     * @return 划拨时间
     */
    public String getFundTime() {
        return fundTime;
    }

    /**
     * @param fundTime 
	 *            划拨时间
     */
    public void setFundTime(String fundTime) {
        this.fundTime = fundTime;
    }

    /**
     * @return 经费类别（0.设备1.会议2.图书3.项目4.办公5.其他）
     */
    public String getFundType() {
        return fundType;
    }

    /**
     * @param fundType 
	 *            经费类别（0.设备1.会议2.图书3.项目4.办公5.其他）
     */
    public void setFundType(String fundType) {
        this.fundType = fundType;
    }

    /**
     * @return 经费来源（0.教育部1.其他部委2.省市政府3.学校4.企事业单位5.社会捐助）
     */
    public String getFundSource() {
        return fundSource;
    }

    /**
     * @param fundSource 
	 *            经费来源（0.教育部1.其他部委2.省市政府3.学校4.企事业单位5.社会捐助）
     */
    public void setFundSource(String fundSource) {
        this.fundSource = fundSource;
    }

    /**
     * @return 备注
     */
    public String getFundRemarks() {
        return fundRemarks;
    }

    /**
     * @param fundRemarks 
	 *            备注
     */
    public void setFundRemarks(String fundRemarks) {
        this.fundRemarks = fundRemarks;
    }

    /**
     * @return 是否删除（0.未删除1.删除--回收站）
     */
    public String getFundDel() {
        return fundDel;
    }

    /**
     * @param fundDel 
	 *            是否删除（0.未删除1.删除--回收站）
     */
    public void setFundDel(String fundDel) {
        this.fundDel = fundDel;
    }

    /**
     * @return 录入时间
     */
    public Date getFundCreateTime() {
        return fundCreateTime;
    }

    /**
     * @param fundCreateTime 
	 *            录入时间
     */
    public void setFundCreateTime(Date fundCreateTime) {
        this.fundCreateTime = fundCreateTime;
    }
}