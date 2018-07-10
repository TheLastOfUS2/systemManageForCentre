package com.systemManage.pojo.base;

import java.io.Serializable;
import java.util.Date;

public class AmCollaborator implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String collaboratorId;

    /**
     * 成果ID（著作/论文/获奖/采纳批示）
     */
    private String achievementId;

    /**
     * 合作者ID（就是选择的人员的基本信息ID）
     */
    private String baseInfoId;

    /**
     * 类型（0.著作1.论文2.获奖3.采纳批示）
     */
    private String collaboratorType;

    /**
     * 主要工作
     */
    private String collaboratorContent;

    private Date collaboratorCreateTime;

    /**
     * @return ID
     */
    public String getCollaboratorId() {
        return collaboratorId;
    }

    /**
     * @param collaboratorId 
	 *            ID
     */
    public void setCollaboratorId(String collaboratorId) {
        this.collaboratorId = collaboratorId;
    }

    /**
     * @return 成果ID（著作/论文/获奖/采纳批示）
     */
    public String getAchievementId() {
        return achievementId;
    }

    /**
     * @param achievementId 
	 *            成果ID（著作/论文/获奖/采纳批示）
     */
    public void setAchievementId(String achievementId) {
        this.achievementId = achievementId;
    }

    /**
     * @return 合作者ID（就是选择的人员的基本信息ID）
     */
    public String getBaseInfoId() {
        return baseInfoId;
    }

    /**
     * @param baseInfoId 
	 *            合作者ID（就是选择的人员的基本信息ID）
     */
    public void setBaseInfoId(String baseInfoId) {
        this.baseInfoId = baseInfoId;
    }

    /**
     * @return 类型（0.著作1.论文2.获奖3.采纳批示）
     */
    public String getCollaboratorType() {
        return collaboratorType;
    }

    /**
     * @param collaboratorType 
	 *            类型（0.著作1.论文2.获奖3.采纳批示）
     */
    public void setCollaboratorType(String collaboratorType) {
        this.collaboratorType = collaboratorType;
    }

    /**
     * @return 主要工作
     */
    public String getCollaboratorContent() {
        return collaboratorContent;
    }

    /**
     * @param collaboratorContent 
	 *            主要工作
     */
    public void setCollaboratorContent(String collaboratorContent) {
        this.collaboratorContent = collaboratorContent;
    }

    public Date getCollaboratorCreateTime() {
        return collaboratorCreateTime;
    }

    public void setCollaboratorCreateTime(Date collaboratorCreateTime) {
        this.collaboratorCreateTime = collaboratorCreateTime;
    }
}