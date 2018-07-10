package com.systemManage.pojo.base;

import java.io.Serializable;
import java.util.Date;

public class AmProject implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 项目ID
     */
    private String projectId;

    /**
     * 项目第一负责人（管理员选择；科研人员默认自己）
     */
    private String baseInfoId;

    /**
     * 项目类型（0.横向/1.纵向/2.国际合作项目）
     */
    private String projectType;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 批准编号
     */
    private String projectNumber;

    /**
     * 项目来源
     */
    private String projectSource;

    /**
     * 项目级别(0.国家/1.部/2.省/3.市/4.企事业单位)
     */
    private String projectLevel;

    /**
     * 立项时间
     */
    private String projectTime;

    /**
     * 结项时间
     */
    private String projectKnotTime;

    /**
     * 项目状态(0.在研1.结项2.延期（已申请）3.延期（未申请）)
     */
    private String projectStatus;

    /**
     * 批准经费(万元)
     */
    private Double projectApprovedOutlay;

    /**
     * 合作单位
     */
    private String projectCooperativeUnit;

    /**
     * 备注
     */
    private String projectRemarks;

    /**
     * 是否删除（0.未删除1.删除--回收站）
     */
    private String projectDel;

    private Date projectCreateTime;

    /**
     * @return 项目ID
     */
    public String getProjectId() {
        return projectId;
    }

    /**
     * @param projectId 
	 *            项目ID
     */
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    /**
     * @return 项目第一负责人（管理员选择；科研人员默认自己）
     */
    public String getBaseInfoId() {
        return baseInfoId;
    }

    /**
     * @param baseInfoId 
	 *            项目第一负责人（管理员选择；科研人员默认自己）
     */
    public void setBaseInfoId(String baseInfoId) {
        this.baseInfoId = baseInfoId;
    }

    /**
     * @return 项目类型（0.横向/1.纵向/2.国际合作项目）
     */
    public String getProjectType() {
        return projectType;
    }

    /**
     * @param projectType 
	 *            项目类型（0.横向/1.纵向/2.国际合作项目）
     */
    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    /**
     * @return 项目名称
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * @param projectName 
	 *            项目名称
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * @return 批准编号
     */
    public String getProjectNumber() {
        return projectNumber;
    }

    /**
     * @param projectNumber 
	 *            批准编号
     */
    public void setProjectNumber(String projectNumber) {
        this.projectNumber = projectNumber;
    }

    /**
     * @return 项目来源
     */
    public String getProjectSource() {
        return projectSource;
    }

    /**
     * @param projectSource 
	 *            项目来源
     */
    public void setProjectSource(String projectSource) {
        this.projectSource = projectSource;
    }

    /**
     * @return 项目级别(0.国家/1.部/2.省/3.市/4.企事业单位)
     */
    public String getProjectLevel() {
        return projectLevel;
    }

    /**
     * @param projectLevel 
	 *            项目级别(0.国家/1.部/2.省/3.市/4.企事业单位)
     */
    public void setProjectLevel(String projectLevel) {
        this.projectLevel = projectLevel;
    }

    /**
     * @return 立项时间
     */
    public String getProjectTime() {
        return projectTime;
    }

    /**
     * @param projectTime 
	 *            立项时间
     */
    public void setProjectTime(String projectTime) {
        this.projectTime = projectTime;
    }

    /**
     * @return 结项时间
     */
    public String getProjectKnotTime() {
        return projectKnotTime;
    }

    /**
     * @param projectKnotTime 
	 *            结项时间
     */
    public void setProjectKnotTime(String projectKnotTime) {
        this.projectKnotTime = projectKnotTime;
    }

    /**
     * @return 项目状态(0.在研1.结项2.延期（已申请）3.延期（未申请）)
     */
    public String getProjectStatus() {
        return projectStatus;
    }

    /**
     * @param projectStatus 
	 *            项目状态(0.在研1.结项2.延期（已申请）3.延期（未申请）)
     */
    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
    }

    /**
     * @return 批准经费(万元)
     */
    public Double getProjectApprovedOutlay() {
        return projectApprovedOutlay;
    }

    /**
     * @param projectApprovedOutlay 
	 *            批准经费(万元)
     */
    public void setProjectApprovedOutlay(Double projectApprovedOutlay) {
        this.projectApprovedOutlay = projectApprovedOutlay;
    }

    /**
     * @return 合作单位
     */
    public String getProjectCooperativeUnit() {
        return projectCooperativeUnit;
    }

    /**
     * @param projectCooperativeUnit 
	 *            合作单位
     */
    public void setProjectCooperativeUnit(String projectCooperativeUnit) {
        this.projectCooperativeUnit = projectCooperativeUnit;
    }

    /**
     * @return 备注
     */
    public String getProjectRemarks() {
        return projectRemarks;
    }

    /**
     * @param projectRemarks 
	 *            备注
     */
    public void setProjectRemarks(String projectRemarks) {
        this.projectRemarks = projectRemarks;
    }

    /**
     * @return 是否删除（0.未删除1.删除--回收站）
     */
    public String getProjectDel() {
        return projectDel;
    }

    /**
     * @param projectDel 
	 *            是否删除（0.未删除1.删除--回收站）
     */
    public void setProjectDel(String projectDel) {
        this.projectDel = projectDel;
    }

    public Date getProjectCreateTime() {
        return projectCreateTime;
    }

    public void setProjectCreateTime(Date projectCreateTime) {
        this.projectCreateTime = projectCreateTime;
    }
}