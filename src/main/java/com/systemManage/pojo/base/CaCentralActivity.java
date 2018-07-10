package com.systemManage.pojo.base;

import java.io.Serializable;
import java.util.Date;

public class CaCentralActivity implements Serializable {
    private static final long serialVersionUID = 1L;

    private String activityId;

    private String activityType;

    private String activityTime;

    private String activityAddress;

    private String activityTheme;

    private String activityContent;

    private String activityHost;

    private String activitySpeaker;

    private String activityParticipant;

    private String activityRemarks;

    private String activityDel;

    /**
     * 录入时间
     */
    private Date activityCreateTime;

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(String activityTime) {
        this.activityTime = activityTime;
    }

    public String getActivityAddress() {
        return activityAddress;
    }

    public void setActivityAddress(String activityAddress) {
        this.activityAddress = activityAddress;
    }

    public String getActivityTheme() {
        return activityTheme;
    }

    public void setActivityTheme(String activityTheme) {
        this.activityTheme = activityTheme;
    }

    public String getActivityContent() {
        return activityContent;
    }

    public void setActivityContent(String activityContent) {
        this.activityContent = activityContent;
    }

    public String getActivityHost() {
        return activityHost;
    }

    public void setActivityHost(String activityHost) {
        this.activityHost = activityHost;
    }

    public String getActivitySpeaker() {
        return activitySpeaker;
    }

    public void setActivitySpeaker(String activitySpeaker) {
        this.activitySpeaker = activitySpeaker;
    }

    public String getActivityParticipant() {
        return activityParticipant;
    }

    public void setActivityParticipant(String activityParticipant) {
        this.activityParticipant = activityParticipant;
    }

    public String getActivityRemarks() {
        return activityRemarks;
    }

    public void setActivityRemarks(String activityRemarks) {
        this.activityRemarks = activityRemarks;
    }

    public String getActivityDel() {
        return activityDel;
    }

    public void setActivityDel(String activityDel) {
        this.activityDel = activityDel;
    }

    /**
     * @return 录入时间
     */
    public Date getActivityCreateTime() {
        return activityCreateTime;
    }

    /**
     * @param activityCreateTime 
	 *            录入时间
     */
    public void setActivityCreateTime(Date activityCreateTime) {
        this.activityCreateTime = activityCreateTime;
    }
}