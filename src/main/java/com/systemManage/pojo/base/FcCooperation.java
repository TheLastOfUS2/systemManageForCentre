package com.systemManage.pojo.base;

import java.io.Serializable;
import java.util.Date;

public class FcCooperation implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String cooperationId;

    /**
     * 合作类型（0.国内1.国际）
     */
    private String cooperationType;

    /**
     * 合作项目名称
     */
    private String cooperationProjectName;

    /**
     * 合作内容
     */
    private String cooperationContent;

    /**
     * 合作单位
     */
    private String cooperationCompany;

    /**
     * 合作时间
     */
    private String cooperationTime;

    /**
     * 备注
     */
    private String cooperationRemarks;

    /**
     * 是否删除（0.未删除1.删除--回收站）
     */
    private String cooperationDel;

    private Date cooperationCreateTime;

    private String cooperationPerson;

    private String cooperationPersonCompany;

    private String cooperationPersonLiable;

    private String cooperationPersonLiableId;

    /**
     * @return ID
     */
    public String getCooperationId() {
        return cooperationId;
    }

    /**
     * @param cooperationId 
	 *            ID
     */
    public void setCooperationId(String cooperationId) {
        this.cooperationId = cooperationId;
    }

    /**
     * @return 合作类型（0.国内1.国际）
     */
    public String getCooperationType() {
        return cooperationType;
    }

    /**
     * @param cooperationType 
	 *            合作类型（0.国内1.国际）
     */
    public void setCooperationType(String cooperationType) {
        this.cooperationType = cooperationType;
    }

    /**
     * @return 合作项目名称
     */
    public String getCooperationProjectName() {
        return cooperationProjectName;
    }

    /**
     * @param cooperationProjectName 
	 *            合作项目名称
     */
    public void setCooperationProjectName(String cooperationProjectName) {
        this.cooperationProjectName = cooperationProjectName;
    }

    /**
     * @return 合作内容
     */
    public String getCooperationContent() {
        return cooperationContent;
    }

    /**
     * @param cooperationContent 
	 *            合作内容
     */
    public void setCooperationContent(String cooperationContent) {
        this.cooperationContent = cooperationContent;
    }

    /**
     * @return 合作单位
     */
    public String getCooperationCompany() {
        return cooperationCompany;
    }

    /**
     * @param cooperationCompany 
	 *            合作单位
     */
    public void setCooperationCompany(String cooperationCompany) {
        this.cooperationCompany = cooperationCompany;
    }

    /**
     * @return 合作时间
     */
    public String getCooperationTime() {
        return cooperationTime;
    }

    /**
     * @param cooperationTime 
	 *            合作时间
     */
    public void setCooperationTime(String cooperationTime) {
        this.cooperationTime = cooperationTime;
    }

    /**
     * @return 备注
     */
    public String getCooperationRemarks() {
        return cooperationRemarks;
    }

    /**
     * @param cooperationRemarks 
	 *            备注
     */
    public void setCooperationRemarks(String cooperationRemarks) {
        this.cooperationRemarks = cooperationRemarks;
    }

    /**
     * @return 是否删除（0.未删除1.删除--回收站）
     */
    public String getCooperationDel() {
        return cooperationDel;
    }

    /**
     * @param cooperationDel 
	 *            是否删除（0.未删除1.删除--回收站）
     */
    public void setCooperationDel(String cooperationDel) {
        this.cooperationDel = cooperationDel;
    }

    public Date getCooperationCreateTime() {
        return cooperationCreateTime;
    }

    public void setCooperationCreateTime(Date cooperationCreateTime) {
        this.cooperationCreateTime = cooperationCreateTime;
    }

    public String getCooperationPerson() {
        return cooperationPerson;
    }

    public void setCooperationPerson(String cooperationPerson) {
        this.cooperationPerson = cooperationPerson;
    }

    public String getCooperationPersonCompany() {
        return cooperationPersonCompany;
    }

    public void setCooperationPersonCompany(String cooperationPersonCompany) {
        this.cooperationPersonCompany = cooperationPersonCompany;
    }

    public String getCooperationPersonLiable() {
        return cooperationPersonLiable;
    }

    public void setCooperationPersonLiable(String cooperationPersonLiable) {
        this.cooperationPersonLiable = cooperationPersonLiable;
    }

    public String getCooperationPersonLiableId() {
        return cooperationPersonLiableId;
    }

    public void setCooperationPersonLiableId(String cooperationPersonLiableId) {
        this.cooperationPersonLiableId = cooperationPersonLiableId;
    }
}