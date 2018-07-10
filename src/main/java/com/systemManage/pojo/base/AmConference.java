package com.systemManage.pojo.base;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

public class AmConference implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String conferenceId;
    
    /**
     * 姓名ID
     */
    private String conferenceNameId;

	/**
     * 姓名
     */
    private String conferenceName;

    /**
     * 会议名称
     */
    @Getter@Setter
    private String conferenceTitle;

    /**
     * 举办地点
     */
    private String conferenceAddress;

    /**
     * 会议性质（0.自办1.参会）
     */
    private String conferenceNature;

    /**
     * 会议开始时间
     */
    private String conferenceStartTime;

    /**
     * 会议结束时间
     */
    private String conferenceEndTime;

    /**
     * 会议类型（0.国内1.国际）
     */
    private String conferenceType;

    /**
     * 主席(自定义)
     */
    private String conferenceChairman;
    /**
     * 主席ID
     */
    private String conferenceChairmanId;

    /**
     * 执行主席(自定义)
     */
    private String conferenceImplementChairman;
    
    /**
     * 执行主席ID
     */
    private String conferenceImplementChairmanId;

    /**
     * 国内参会人数
     */
    private String conferenceDomesticCount;

    /**
     * 国际参会人数
     */
    private String conferenceInternationCount;

    /**
     * 收录论文数
     */
    private String conferencePaperCount;

    /**
     * 会议综述或简介（描述或新闻网址）
     */
    private String conferenceSynopsis;

    /**
     * 主要活动内容
     */
    private String conferenceContent;

    /**
     * 交流论文名称
     */
    private String conferencePaperName;

    /**
     * 是否大会宣读(0.否1.是)
     */
    private String conferenceIfRead;

    /**
     * 是否收录会议论文集（0.否1.是）
     */
    private String conferenceIfInclude;

    /**
     * 备注
     */
    private String conferenceRemarks;

    /**
     * 是否删除（0.未删除1.删除--回收站）
     */
    private String conferenceDel;

    /**
     * 录入时间
     */
    private Date conferenceCreateTime;

    /**
     * @return ID
     */
    public String getConferenceId() {
        return conferenceId;
    }

    /**
     * @param conferenceId 
	 *            ID
     */
    public void setConferenceId(String conferenceId) {
        this.conferenceId = conferenceId;
    }

    /**
     * @return 姓名
     */
    public String getConferenceName() {
        return conferenceName;
    }

    /**
     * @param conferenceName 
	 *            姓名
     */
    public void setConferenceName(String conferenceName) {
        this.conferenceName = conferenceName;
    }


    /**
     * @return 举办地点
     */
    public String getConferenceAddress() {
        return conferenceAddress;
    }

    /**
     * @param conferenceAddress 
	 *            举办地点
     */
    public void setConferenceAddress(String conferenceAddress) {
        this.conferenceAddress = conferenceAddress;
    }

    /**
     * @return 会议性质（0.自办1.参会）
     */
    public String getConferenceNature() {
        return conferenceNature;
    }

    /**
     * @param conferenceNature 
	 *            会议性质（0.自办1.参会）
     */
    public void setConferenceNature(String conferenceNature) {
        this.conferenceNature = conferenceNature;
    }

    /**
     * @return 会议开始时间
     */
    public String getConferenceStartTime() {
        return conferenceStartTime;
    }

    /**
     * @param conferenceStartTime 
	 *            会议开始时间
     */
    public void setConferenceStartTime(String conferenceStartTime) {
        this.conferenceStartTime = conferenceStartTime;
    }

    /**
     * @return 会议开始时间
     */
    public String getConferenceEndTime() {
        return conferenceEndTime;
    }

    /**
     * @param conferenceEndTime 
	 *            会议开始时间
     */
    public void setConferenceEndTime(String conferenceEndTime) {
        this.conferenceEndTime = conferenceEndTime;
    }

    /**
     * @return 会议类型（0.国内1.国际）
     */
    public String getConferenceType() {
        return conferenceType;
    }

    /**
     * @param conferenceType 
	 *            会议类型（0.国内1.国际）
     */
    public void setConferenceType(String conferenceType) {
        this.conferenceType = conferenceType;
    }

    /**
     * @return 主席(自定义)
     */
    public String getConferenceChairman() {
        return conferenceChairman;
    }

    /**
     * @param conferenceChairman 
	 *            主席(自定义)
     */
    public void setConferenceChairman(String conferenceChairman) {
        this.conferenceChairman = conferenceChairman;
    }

    /**
     * @return 执行主席(自定义)
     */
    public String getConferenceImplementChairman() {
        return conferenceImplementChairman;
    }

    /**
     * @param conferenceImplementChairman 
	 *            执行主席(自定义)
     */
    public void setConferenceImplementChairman(String conferenceImplementChairman) {
        this.conferenceImplementChairman = conferenceImplementChairman;
    }

    /**
     * @return 国内参会人数
     */
    public String getConferenceDomesticCount() {
        return conferenceDomesticCount;
    }

    /**
     * @param conferenceDomesticCount 
	 *            国内参会人数
     */
    public void setConferenceDomesticCount(String conferenceDomesticCount) {
        this.conferenceDomesticCount = conferenceDomesticCount;
    }

    /**
     * @return 国际参会人数
     */
    public String getConferenceInternationCount() {
        return conferenceInternationCount;
    }

    /**
     * @param conferenceInternationCount 
	 *            国际参会人数
     */
    public void setConferenceInternationCount(String conferenceInternationCount) {
        this.conferenceInternationCount = conferenceInternationCount;
    }

    /**
     * @return 收录论文数
     */
    public String getConferencePaperCount() {
        return conferencePaperCount;
    }

    /**
     * @param conferencePaperCount 
	 *            收录论文数
     */
    public void setConferencePaperCount(String conferencePaperCount) {
        this.conferencePaperCount = conferencePaperCount;
    }

    /**
     * @return 会议综述或简介（描述或新闻网址）
     */
    public String getConferenceSynopsis() {
        return conferenceSynopsis;
    }

    /**
     * @param conferenceSynopsis 
	 *            会议综述或简介（描述或新闻网址）
     */
    public void setConferenceSynopsis(String conferenceSynopsis) {
        this.conferenceSynopsis = conferenceSynopsis;
    }

    /**
     * @return 主要活动内容
     */
    public String getConferenceContent() {
        return conferenceContent;
    }

    /**
     * @param conferenceContent 
	 *            主要活动内容
     */
    public void setConferenceContent(String conferenceContent) {
        this.conferenceContent = conferenceContent;
    }

    /**
     * @return 交流论文名称
     */
    public String getConferencePaperName() {
        return conferencePaperName;
    }

    /**
     * @param conferencePaperName 
	 *            交流论文名称
     */
    public void setConferencePaperName(String conferencePaperName) {
        this.conferencePaperName = conferencePaperName;
    }

    /**
     * @return 交流论文名称
     */
    public String getConferenceIfRead() {
        return conferenceIfRead;
    }

    /**
     * @param conferenceIfRead 
	 *            交流论文名称
     */
    public void setConferenceIfRead(String conferenceIfRead) {
        this.conferenceIfRead = conferenceIfRead;
    }

    /**
     * @return 是否收录会议论文集（0.否1.是）
     */
    public String getConferenceIfInclude() {
        return conferenceIfInclude;
    }

    /**
     * @param conferenceIfInclude 
	 *            是否收录会议论文集（0.否1.是）
     */
    public void setConferenceIfInclude(String conferenceIfInclude) {
        this.conferenceIfInclude = conferenceIfInclude;
    }

    /**
     * @return 备注
     */
    public String getConferenceRemarks() {
        return conferenceRemarks;
    }

    /**
     * @param conferenceRemarks 
	 *            备注
     */
    public void setConferenceRemarks(String conferenceRemarks) {
        this.conferenceRemarks = conferenceRemarks;
    }

    /**
     * @return 是否删除（0.未删除1.删除--回收站）
     */
    public String getConferenceDel() {
        return conferenceDel;
    }

    /**
     * @param conferenceDel 
	 *            是否删除（0.未删除1.删除--回收站）
     */
    public void setConferenceDel(String conferenceDel) {
        this.conferenceDel = conferenceDel;
    }

    /**
     * @return 录入时间
     */
    public Date getConferenceCreateTime() {
        return conferenceCreateTime;
    }

    /**
     * @param conferenceCreateTime 
	 *            录入时间
     */
    public void setConferenceCreateTime(Date conferenceCreateTime) {
        this.conferenceCreateTime = conferenceCreateTime;
    }

	public String getConferenceNameId() {
		return conferenceNameId;
	}

	public void setConferenceNameId(String conferenceNameId) {
		this.conferenceNameId = conferenceNameId;
	}

	public String getConferenceChairmanId() {
		return conferenceChairmanId;
	}

	public void setConferenceChairmanId(String conferenceChairmanId) {
		this.conferenceChairmanId = conferenceChairmanId;
	}

	public String getConferenceImplementChairmanId() {
		return conferenceImplementChairmanId;
	}

	public void setConferenceImplementChairmanId(
			String conferenceImplementChairmanId) {
		this.conferenceImplementChairmanId = conferenceImplementChairmanId;
	}
}