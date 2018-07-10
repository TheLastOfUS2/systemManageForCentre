package com.systemManage.pojo.base;

import java.io.Serializable;
import java.util.Date;

public class MwRule implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String ruleId;

    /**
     * 制度名称
     */
    private String ruleTitle;

    /**
     * 颁布时间
     */
    private String ruleTime;

    /**
     * 简要内容
     */
    private String ruleContent;

    /**
     * 备注
     */
    private String ruleRemarks;

    /**
     * 是否删除（0.未删除1.删除--回收站）
     */
    private String ruleDel;

    /**
     * 录入时间
     */
    private Date ruleCreateTime;

    /**
     * @return ID
     */
    public String getRuleId() {
        return ruleId;
    }

    /**
     * @param ruleId 
	 *            ID
     */
    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    /**
     * @return 制度名称
     */
    public String getRuleTitle() {
        return ruleTitle;
    }

    /**
     * @param ruleTitle 
	 *            制度名称
     */
    public void setRuleTitle(String ruleTitle) {
        this.ruleTitle = ruleTitle;
    }

    /**
     * @return 制度名称
     */
    public String getRuleTime() {
        return ruleTime;
    }

    /**
     * @param ruleTime 
	 *            制度名称
     */
    public void setRuleTime(String ruleTime) {
        this.ruleTime = ruleTime;
    }

    /**
     * @return 简要内容
     */
    public String getRuleContent() {
        return ruleContent;
    }

    /**
     * @param ruleContent 
	 *            简要内容
     */
    public void setRuleContent(String ruleContent) {
        this.ruleContent = ruleContent;
    }

    /**
     * @return 备注
     */
    public String getRuleRemarks() {
        return ruleRemarks;
    }

    /**
     * @param ruleRemarks 
	 *            备注
     */
    public void setRuleRemarks(String ruleRemarks) {
        this.ruleRemarks = ruleRemarks;
    }

    /**
     * @return 是否删除（0.未删除1.删除--回收站）
     */
    public String getRuleDel() {
        return ruleDel;
    }

    /**
     * @param ruleDel 
	 *            是否删除（0.未删除1.删除--回收站）
     */
    public void setRuleDel(String ruleDel) {
        this.ruleDel = ruleDel;
    }

    /**
     * @return 录入时间
     */
    public Date getRuleCreateTime() {
        return ruleCreateTime;
    }

    /**
     * @param ruleCreateTime 
	 *            录入时间
     */
    public void setRuleCreateTime(Date ruleCreateTime) {
        this.ruleCreateTime = ruleCreateTime;
    }
}