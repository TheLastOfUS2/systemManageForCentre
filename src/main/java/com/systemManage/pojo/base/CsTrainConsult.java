package com.systemManage.pojo.base;

import java.io.Serializable;
import java.util.Date;

public class CsTrainConsult implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String consultId;

    /**
     * 课程名称
     */
    private String consultTitle;
    
    /**
     * 授课人(科研人员)
     */
    private String consultName;
    
    /**
     * 授课人ID(科研人员)
     */
    private String consultNameId;
    
	/**
     * 授课对象(单位)
     */
    private String consultTarget;
  
	/**
     * 开始时间
     */
    private String consultStartTime;

    /**
     * 结束时间
     */
    private String consultEndTime;

    /**
     * 人数
     */
    private String consultCount;

    /**
     * 类别（0.自办1.委托）
     */
    private String consultType;

    /**
     * 合作单位/委托单位
     */
    private String consultEntrustCompany;

    /**
     * 简介
     */
    private String consultSynopsis;

    /**
     * 备注
     */
    private String consultRemarks;

    /**
     * 是否删除（0.未删除1.删除--回收站）
     */
    private String consultDel;

    private Date consultCreateTime;

    /**
     * @return ID
     */
    public String getConsultId() {
        return consultId;
    }

    /**
     * @param consultId 
	 *            ID
     */
    public void setConsultId(String consultId) {
        this.consultId = consultId;
    }

    /**
     * @return 课程名称
     */
    public String getConsultTitle() {
        return consultTitle;
    }

    /**
     * @param consultTitle 
	 *            课程名称
     */
    public void setConsultTitle(String consultTitle) {
        this.consultTitle = consultTitle;
    }

    /**
     * @return 授课对象
     */
    public String getConsultName() {
        return consultName;
    }

    /**
     * @param consultName 
	 *            授课对象
     */
    public void setConsultName(String consultName) {
        this.consultName = consultName;
    }

    /**
     * @return 开始时间
     */
    public String getConsultStartTime() {
        return consultStartTime;
    }

    /**
     * @param consultStartTime 
	 *            开始时间
     */
    public void setConsultStartTime(String consultStartTime) {
        this.consultStartTime = consultStartTime;
    }

    /**
     * @return 结束时间
     */
    public String getConsultEndTime() {
        return consultEndTime;
    }

    /**
     * @param consultEndTime 
	 *            结束时间
     */
    public void setConsultEndTime(String consultEndTime) {
        this.consultEndTime = consultEndTime;
    }

    /**
     * @return 人数
     */
    public String getConsultCount() {
        return consultCount;
    }

    /**
     * @param consultCount 
	 *            人数
     */
    public void setConsultCount(String consultCount) {
        this.consultCount = consultCount;
    }

    /**
     * @return 类别（0.自办1.委托）
     */
    public String getConsultType() {
        return consultType;
    }

    /**
     * @param consultType 
	 *            类别（0.自办1.委托）
     */
    public void setConsultType(String consultType) {
        this.consultType = consultType;
    }

    /**
     * @return 合作单位/委托单位
     */
    public String getConsultEntrustCompany() {
        return consultEntrustCompany;
    }

    /**
     * @param consultEntrustCompany 
	 *            合作单位/委托单位
     */
    public void setConsultEntrustCompany(String consultEntrustCompany) {
        this.consultEntrustCompany = consultEntrustCompany;
    }

    /**
     * @return 简介
     */
    public String getConsultSynopsis() {
        return consultSynopsis;
    }

    /**
     * @param consultSynopsis 
	 *            简介
     */
    public void setConsultSynopsis(String consultSynopsis) {
        this.consultSynopsis = consultSynopsis;
    }

    /**
     * @return 备注
     */
    public String getConsultRemarks() {
        return consultRemarks;
    }

    /**
     * @param consultRemarks 
	 *            备注
     */
    public void setConsultRemarks(String consultRemarks) {
        this.consultRemarks = consultRemarks;
    }

    /**
     * @return 是否删除（0.未删除1.删除--回收站）
     */
    public String getConsultDel() {
        return consultDel;
    }

    /**
     * @param consultDel 
	 *            是否删除（0.未删除1.删除--回收站）
     */
    public void setConsultDel(String consultDel) {
        this.consultDel = consultDel;
    }

    public Date getConsultCreateTime() {
        return consultCreateTime;
    }

    public void setConsultCreateTime(Date consultCreateTime) {
        this.consultCreateTime = consultCreateTime;
    }
    
    public String getConsultTarget() {
  		return consultTarget;
  	}

  	public void setConsultTarget(String consultTarget) {
  		this.consultTarget = consultTarget;
  	}
  	
  	public String getConsultNameId() {
 		return consultNameId;
 	}

 	public void setConsultNameId(String consultNameId) {
 		this.consultNameId = consultNameId;
 	}
}