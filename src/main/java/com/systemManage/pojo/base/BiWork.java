package com.systemManage.pojo.base;

import java.io.Serializable;
import java.util.Date;

public class BiWork implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String workId;

    /**
     * 基本信息ID
     */
    private String baseInfoId;

    /**
     * 工作开始时间
     */
    private String workStartTime;

    /**
     * 工作结束时间
     */
    private String workEndTime;

    /**
     * 工作单位
     */
    private String workCompany;

    /**
     * 职务
     */
    private String workPost;

    /**
     * 录入时间
     */
    private Date workCreateTime;

    /**
     * @return ID
     */
    public String getWorkId() {
        return workId;
    }

    /**
     * @param workId 
	 *            ID
     */
    public void setWorkId(String workId) {
        this.workId = workId;
    }

    /**
     * @return 基本信息ID
     */
    public String getBaseInfoId() {
        return baseInfoId;
    }

    /**
     * @param baseInfoId 
	 *            基本信息ID
     */
    public void setBaseInfoId(String baseInfoId) {
        this.baseInfoId = baseInfoId;
    }

    /**
     * @return 工作开始时间
     */
    public String getWorkStartTime() {
        return workStartTime;
    }

    /**
     * @param workStartTime 
	 *            工作开始时间
     */
    public void setWorkStartTime(String workStartTime) {
        this.workStartTime = workStartTime;
    }

    /**
     * @return 工作结束时间
     */
    public String getWorkEndTime() {
        return workEndTime;
    }

    /**
     * @param workEndTime 
	 *            工作结束时间
     */
    public void setWorkEndTime(String workEndTime) {
        this.workEndTime = workEndTime;
    }

    /**
     * @return 工作单位
     */
    public String getWorkCompany() {
        return workCompany;
    }

    /**
     * @param workCompany 
	 *            工作单位
     */
    public void setWorkCompany(String workCompany) {
        this.workCompany = workCompany;
    }

    /**
     * @return 职务
     */
    public String getWorkPost() {
        return workPost;
    }

    /**
     * @param workPost 
	 *            职务
     */
    public void setWorkPost(String workPost) {
        this.workPost = workPost;
    }

    /**
     * @return 录入时间
     */
    public Date getWorkCreateTime() {
        return workCreateTime;
    }

    /**
     * @param workCreateTime 
	 *            录入时间
     */
    public void setWorkCreateTime(Date workCreateTime) {
        this.workCreateTime = workCreateTime;
    }
}