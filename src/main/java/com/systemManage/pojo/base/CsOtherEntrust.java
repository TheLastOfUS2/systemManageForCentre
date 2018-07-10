package com.systemManage.pojo.base;

import java.io.Serializable;
import java.util.Date;

public class CsOtherEntrust implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String entrustId;

    /**
     * 承担时间
     */
    private String entrustTime;

    /**
     * 承担人
     */
    private String entrustUndertaker;
    
    /**
     * 承担人ID
     */
    private String entrustUndertakerId;

	/**
     * 委托单位
     */
    private String entrustCompany;

    /**
     * 委托任务
     */
    private String entrustTask;

    /**
     * 简要说明
     */
    private String entrustSynopsis;

    /**
     * 备注
     */
    private String entrustRemarks;

    /**
     * 是否删除（0.未删除1.删除--回收站）
     */
    private String entrustDel;

    private Date entrustCreateTime;

    /**
     * @return ID
     */
    public String getEntrustId() {
        return entrustId;
    }

    /**
     * @param entrustId 
	 *            ID
     */
    public void setEntrustId(String entrustId) {
        this.entrustId = entrustId;
    }

    /**
     * @return 承担时间
     */
    public String getEntrustTime() {
        return entrustTime;
    }

    /**
     * @param entrustTime 
	 *            承担时间
     */
    public void setEntrustTime(String entrustTime) {
        this.entrustTime = entrustTime;
    }

    /**
     * @return 承担人
     */
    public String getEntrustUndertaker() {
        return entrustUndertaker;
    }

    /**
     * @param entrustUndertaker 
	 *            承担人
     */
    public void setEntrustUndertaker(String entrustUndertaker) {
        this.entrustUndertaker = entrustUndertaker;
    }

    /**
     * @return 委托单位
     */
    public String getEntrustCompany() {
        return entrustCompany;
    }

    /**
     * @param entrustCompany 
	 *            委托单位
     */
    public void setEntrustCompany(String entrustCompany) {
        this.entrustCompany = entrustCompany;
    }

    /**
     * @return 委托任务
     */
    public String getEntrustTask() {
        return entrustTask;
    }

    /**
     * @param entrustTask 
	 *            委托任务
     */
    public void setEntrustTask(String entrustTask) {
        this.entrustTask = entrustTask;
    }

    /**
     * @return 简要说明
     */
    public String getEntrustSynopsis() {
        return entrustSynopsis;
    }

    /**
     * @param entrustSynopsis 
	 *            简要说明
     */
    public void setEntrustSynopsis(String entrustSynopsis) {
        this.entrustSynopsis = entrustSynopsis;
    }

    /**
     * @return 备注
     */
    public String getEntrustRemarks() {
        return entrustRemarks;
    }

    /**
     * @param entrustRemarks 
	 *            备注
     */
    public void setEntrustRemarks(String entrustRemarks) {
        this.entrustRemarks = entrustRemarks;
    }

    /**
     * @return 是否删除（0.未删除1.删除--回收站）
     */
    public String getEntrustDel() {
        return entrustDel;
    }

    /**
     * @param entrustDel 
	 * 是否删除（0.未删除1.删除--回收站）
     */
    public void setEntrustDel(String entrustDel) {
        this.entrustDel = entrustDel;
    }

    public Date getEntrustCreateTime() {
        return entrustCreateTime;
    }

    public void setEntrustCreateTime(Date entrustCreateTime) {
        this.entrustCreateTime = entrustCreateTime;
    }
    

    public String getEntrustUndertakerId() {
		return entrustUndertakerId;
	}

	public void setEntrustUndertakerId(String entrustUndertakerId) {
		this.entrustUndertakerId = entrustUndertakerId;
	}
}