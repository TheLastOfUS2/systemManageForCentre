package com.systemManage.pojo.base;

import java.io.Serializable;
import java.util.Date;

public class EpPublicity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String publicityId;

    /**
     * 宣传类型（0.报纸1.期刊2.电视台3.网络）
     */
    private String publicityType;

    /**
     * 宣传主题
     */
    private String publicityTheme;

    /**
     * 电视频道名称
     */
    private String publicityChannel;

    /**
     * 报纸名称/期刊名称/栏目名称/网站名称
     */
    private String publicityTitle;

    /**
     * 卷/期
     */
    private String publicityVolume;

    /**
     * 发表时间
     */
    private String publicityTime;

    /**
     * 受访人
     */
    private String publicityInterviewee;
    
    /**
     * 受访人ID
     */
    private String publicityIntervieweeId;
    
	/**
     * 受访单位
     */
    private String publicityCompany;

    /**
     * 版面/位置(封1、2、3、4)/视频网址/宣传页网址
     */
    private String publicityPosition;

    /**
     * 备注
     */
    private String publicityRemarks;

    /**
     * 备注
     */
    private String publicityDel;

    private Date publicityCreateTime;

    /**
     * @return ID
     */
    public String getPublicityId() {
        return publicityId;
    }

    /**
     * @param publicityId 
	 *            ID
     */
    public void setPublicityId(String publicityId) {
        this.publicityId = publicityId;
    }

    /**
     * @return 宣传类型（0.报纸1.期刊2.电视台3.网络）
     */
    public String getPublicityType() {
        return publicityType;
    }

    /**
     * @param publicityType 
	 *            宣传类型（0.报纸1.期刊2.电视台3.网络）
     */
    public void setPublicityType(String publicityType) {
        this.publicityType = publicityType;
    }

    /**
     * @return 宣传主题
     */
    public String getPublicityTheme() {
        return publicityTheme;
    }

    /**
     * @param publicityTheme 
	 *            宣传主题
     */
    public void setPublicityTheme(String publicityTheme) {
        this.publicityTheme = publicityTheme;
    }

    /**
     * @return 电视频道名称
     */
    public String getPublicityChannel() {
        return publicityChannel;
    }

    /**
     * @param publicityChannel 
	 *            电视频道名称
     */
    public void setPublicityChannel(String publicityChannel) {
        this.publicityChannel = publicityChannel;
    }

    /**
     * @return 报纸名称/期刊名称/栏目名称/网站名称
     */
    public String getPublicityTitle() {
        return publicityTitle;
    }

    /**
     * @param publicityTitle 
	 *            报纸名称/期刊名称/栏目名称/网站名称
     */
    public void setPublicityTitle(String publicityTitle) {
        this.publicityTitle = publicityTitle;
    }

    /**
     * @return 卷/期
     */
    public String getPublicityVolume() {
        return publicityVolume;
    }

    /**
     * @param publicityVolume 
	 *            卷/期
     */
    public void setPublicityVolume(String publicityVolume) {
        this.publicityVolume = publicityVolume;
    }

    /**
     * @return 发表时间
     */
    public String getPublicityTime() {
        return publicityTime;
    }

    /**
     * @param publicityTime 
	 *            发表时间
     */
    public void setPublicityTime(String publicityTime) {
        this.publicityTime = publicityTime;
    }

    /**
     * @return 受访人/单位
     */
    public String getPublicityInterviewee() {
        return publicityInterviewee;
    }

    /**
     * @param publicityInterviewee 
	 *            受访人/单位
     */
    public void setPublicityInterviewee(String publicityInterviewee) {
        this.publicityInterviewee = publicityInterviewee;
    }

    public String getPublicityCompany() {
        return publicityCompany;
    }

    public void setPublicityCompany(String publicityCompany) {
        this.publicityCompany = publicityCompany;
    }

    /**
     * @return 版面/位置(封1、2、3、4)/视频网址/宣传页网址
     */
    public String getPublicityPosition() {
        return publicityPosition;
    }

    /**
     * @param publicityPosition 
	 *            版面/位置(封1、2、3、4)/视频网址/宣传页网址
     */
    public void setPublicityPosition(String publicityPosition) {
        this.publicityPosition = publicityPosition;
    }

    /**
     * @return 备注
     */
    public String getPublicityRemarks() {
        return publicityRemarks;
    }

    /**
     * @param publicityRemarks 
	 *            备注
     */
    public void setPublicityRemarks(String publicityRemarks) {
        this.publicityRemarks = publicityRemarks;
    }

    /**
     * @return 备注
     */
    public String getPublicityDel() {
        return publicityDel;
    }

    /**
     * @param publicityDel 
	 *            备注
     */
    public void setPublicityDel(String publicityDel) {
        this.publicityDel = publicityDel;
    }

    public Date getPublicityCreateTime() {
        return publicityCreateTime;
    }

    public void setPublicityCreateTime(Date publicityCreateTime) {
        this.publicityCreateTime = publicityCreateTime;
    }
    
    public String getPublicityIntervieweeId() {
 		return publicityIntervieweeId;
 	}

 	public void setPublicityIntervieweeId(String publicityIntervieweeId) {
 		this.publicityIntervieweeId = publicityIntervieweeId;
 	}
}