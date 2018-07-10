package com.systemManage.pojo.base;

import java.io.Serializable;
import java.util.Date;

public class AmActivity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String activityId;

    /**
     * 活动名称
     */
    private String activityName;

    /**
     * 时间
     */
    private String activityTime;

    /**
     * 主题
     */
    private String activityTheme;
    
    /**
     * 主讲人/主持人
     */
    private String activitySpeaker;
    /**
     * 主讲人/主持人ID
     */
    private String activitySpeakerId;

	/**
     * 主要内容
     */
    private String activityContent;

    /**
     * 备注
     */
    private String activityRemarks;

    /**
     * 是否删除（0.未删除1.删除--回收站）
     */
    private String activityDel;

    private Date activityCreateTime;

    /**
     * @return ID
     */
    public String getActivityId() {
        return activityId;
    }

    /**
     * @param activityId 
	 *            ID
     */
    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    /**
     * @return 活动名称
     */
    public String getActivityName() {
        return activityName;
    }

    /**
     * @param activityName 
	 *            活动名称
     */
    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    /**
     * @return 时间
     */
    public String getActivityTime() {
        return activityTime;
    }

    /**
     * @param activityTime 
	 *            时间
     */
    public void setActivityTime(String activityTime) {
        this.activityTime = activityTime;
    }

    /**
     * @return 主题
     */
    public String getActivityTheme() {
        return activityTheme;
    }

    /**
     * @param activityTheme 
	 *            主题
     */
    public void setActivityTheme(String activityTheme) {
        this.activityTheme = activityTheme;
    }

    public String getActivitySpeaker() {
        return activitySpeaker;
    }

    public void setActivitySpeaker(String activitySpeaker) {
        this.activitySpeaker = activitySpeaker;
    }

    /**
     * @return 主要内容
     */
    public String getActivityContent() {
        return activityContent;
    }

    /**
     * @param activityContent 
	 *            主要内容
     */
    public void setActivityContent(String activityContent) {
        this.activityContent = activityContent;
    }

    /**
     * @return 备注
     */
    public String getActivityRemarks() {
        return activityRemarks;
    }

    /**
     * @param activityRemarks 
	 *            备注
     */
    public void setActivityRemarks(String activityRemarks) {
        this.activityRemarks = activityRemarks;
    }

    /**
     * @return 是否删除（0.未删除1.删除--回收站）
     */
    public String getActivityDel() {
        return activityDel;
    }

    /**
     * @param activityDel 
	 *            是否删除（0.未删除1.删除--回收站）
     */
    public void setActivityDel(String activityDel) {
        this.activityDel = activityDel;
    }

    public Date getActivityCreateTime() {
        return activityCreateTime;
    }

    public void setActivityCreateTime(Date activityCreateTime) {
        this.activityCreateTime = activityCreateTime;
    }
    
    public String getActivitySpeakerId() {
		return activitySpeakerId;
	}

	public void setActivitySpeakerId(String activitySpeakerId) {
		this.activitySpeakerId = activitySpeakerId;
	}
}