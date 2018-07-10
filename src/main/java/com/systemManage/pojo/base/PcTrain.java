package com.systemManage.pojo.base;

import java.io.Serializable;
import java.util.Date;

public class PcTrain implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String trainId;

    /**
     * 培训人
     */
    private String trainName;
    
    /**
     * 培训人ID
     */
    private String trainNameId;

	/**
     * 培训时间
     */
    private String trainTime;

    /**
     * 培训地点
     */
    private String trainAddress;

    /**
     * 内容
     */
    private String trainContent;

    /**
     * 组织单位
     */
    private String trainCompany;

    /**
     * 备注
     */
    private String trainRemarks;

    /**
     * 是否删除（0.未删除1.删除--回收站）
     */
    private String trainDel;

    /**
     * 录入时间
     */
    private Date trainCreateTime;

    /**
     * @return ID
     */
    public String getTrainId() {
        return trainId;
    }

    /**
     * @param trainId 
	 *            ID
     */
    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    /**
     * @return 姓名
     */
    public String getTrainName() {
        return trainName;
    }

    /**
     * @param trainName 
	 *            姓名
     */
    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getTrainTime() {
        return trainTime;
    }

    public void setTrainTime(String trainTime) {
        this.trainTime = trainTime;
    }

    /**
     * @return 姓名
     */
    public String getTrainAddress() {
        return trainAddress;
    }

    /**
     * @param trainAddress 
	 *            姓名
     */
    public void setTrainAddress(String trainAddress) {
        this.trainAddress = trainAddress;
    }

    /**
     * @return 姓名
     */
    public String getTrainContent() {
        return trainContent;
    }

    /**
     * @param trainContent 
	 *            姓名
     */
    public void setTrainContent(String trainContent) {
        this.trainContent = trainContent;
    }

    /**
     * @return 组织单位
     */
    public String getTrainCompany() {
        return trainCompany;
    }

    /**
     * @param trainCompany 
	 *            组织单位
     */
    public void setTrainCompany(String trainCompany) {
        this.trainCompany = trainCompany;
    }

    /**
     * @return 备注
     */
    public String getTrainRemarks() {
        return trainRemarks;
    }

    /**
     * @param trainRemarks 
	 *            备注
     */
    public void setTrainRemarks(String trainRemarks) {
        this.trainRemarks = trainRemarks;
    }

    /**
     * @return 是否删除（0.未删除1.删除--回收站）
     */
    public String getTrainDel() {
        return trainDel;
    }

    /**
     * @param trainDel 
	 *            是否删除（0.未删除1.删除--回收站）
     */
    public void setTrainDel(String trainDel) {
        this.trainDel = trainDel;
    }

    /**
     * @return 录入时间
     */
    public Date getTrainCreateTime() {
        return trainCreateTime;
    }

    /**
     * @param trainCreateTime 
	 *            录入时间
     */
    public void setTrainCreateTime(Date trainCreateTime) {
        this.trainCreateTime = trainCreateTime;
    }
    
    public String getTrainNameId() {
		return trainNameId;
	}

	public void setTrainNameId(String trainNameId) {
		this.trainNameId = trainNameId;
	}
}