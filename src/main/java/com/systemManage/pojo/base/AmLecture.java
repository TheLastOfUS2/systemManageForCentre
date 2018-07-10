package com.systemManage.pojo.base;

import java.io.Serializable;
import java.util.Date;

public class AmLecture implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String lectureId;
    
    /**
     * 主讲人
     */
    private String lectureSpeaker;
    
    /**
     * 主讲人ID
     */
    private String lectureSpeakerId;

    /**
     * 主题
     */
    private String lectureTheme;

	/**
     * 时间
     */
    private String lectureTime;

    /**
     * 地点
     */
    private String lectureAddress;

    /**
     * 主要内容
     */
    private String lectureContent;

    /**
     * 备注
     */
    private String lectureRemarks;

    /**
     * 是否删除（0.未删除1.删除--回收站）
     */
    private String lectureDel;

    private Date lectureCreateTime;

    /**
     * @return ID
     */
    public String getLectureId() {
        return lectureId;
    }

    /**
     * @param lectureId 
	 *            ID
     */
    public void setLectureId(String lectureId) {
        this.lectureId = lectureId;
    }

    public String getLectureSpeaker() {
        return lectureSpeaker;
    }

    public void setLectureSpeaker(String lectureSpeaker) {
        this.lectureSpeaker = lectureSpeaker;
    }

    /**
     * @return 主题
     */
    public String getLectureTheme() {
        return lectureTheme;
    }

    /**
     * @param lectureTheme 
	 *            主题
     */
    public void setLectureTheme(String lectureTheme) {
        this.lectureTheme = lectureTheme;
    }

    /**
     * @return 时间
     */
    public String getLectureTime() {
        return lectureTime;
    }

    /**
     * @param lectureTime 
	 *            时间
     */
    public void setLectureTime(String lectureTime) {
        this.lectureTime = lectureTime;
    }

    /**
     * @return 地点
     */
    public String getLectureAddress() {
        return lectureAddress;
    }

    /**
     * @param lectureAddress 
	 *            地点
     */
    public void setLectureAddress(String lectureAddress) {
        this.lectureAddress = lectureAddress;
    }

    /**
     * @return 主要内容
     */
    public String getLectureContent() {
        return lectureContent;
    }

    /**
     * @param lectureContent 
	 *            主要内容
     */
    public void setLectureContent(String lectureContent) {
        this.lectureContent = lectureContent;
    }

    /**
     * @return 备注
     */
    public String getLectureRemarks() {
        return lectureRemarks;
    }

    /**
     * @param lectureRemarks 
	 *            备注
     */
    public void setLectureRemarks(String lectureRemarks) {
        this.lectureRemarks = lectureRemarks;
    }

    /**
     * @return 是否删除（0.未删除1.删除--回收站）
     */
    public String getLectureDel() {
        return lectureDel;
    }

    /**
     * @param lectureDel 
	 *            是否删除（0.未删除1.删除--回收站）
     */
    public void setLectureDel(String lectureDel) {
        this.lectureDel = lectureDel;
    }

    public Date getLectureCreateTime() {
        return lectureCreateTime;
    }

    public void setLectureCreateTime(Date lectureCreateTime) {
        this.lectureCreateTime = lectureCreateTime;
    }
    
    public String getLectureSpeakerId() {
  		return lectureSpeakerId;
  	}

  	public void setLectureSpeakerId(String lectureSpeakerId) {
  		this.lectureSpeakerId = lectureSpeakerId;
  	}
}