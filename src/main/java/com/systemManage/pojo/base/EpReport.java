package com.systemManage.pojo.base;

import java.io.Serializable;
import java.util.Date;

public class EpReport implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String reportId;

    /**
     * 报表类型（0.个人1.中心）
     */
    private String reportType;

    /**
     * 上报时间/提交时间
     */
    private String reportTime;

    /**
     * 材料名称
     */
    private String reportTitle;

    /**
     * 提交部门
     */
    private String reportDept;

    /**
     * 提交人
     */
    private String reportSubmitter;
    
    /**
     * 提交人ID
     */
    private String reportSubmitterId;

	/**
     * 备注
     */
    private String reportRemarks;

    /**
     * 是否删除（0.未删除1.删除--回收站）
     */
    private String reportDel;

    private Date reportCreateTime;

    /**
     * @return ID
     */
    public String getReportId() {
        return reportId;
    }

    /**
     * @param reportId 
	 *            ID
     */
    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    /**
     * @return 报表类型（0.个人1.中心）
     */
    public String getReportType() {
        return reportType;
    }

    /**
     * @param reportType 
	 *            报表类型（0.个人1.中心）
     */
    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    /**
     * @return 上报时间/提交时间
     */
    public String getReportTime() {
        return reportTime;
    }

    /**
     * @param reportTime 
	 *            上报时间/提交时间
     */
    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }

    /**
     * @return 材料名称
     */
    public String getReportTitle() {
        return reportTitle;
    }

    /**
     * @param reportTitle 
	 *            材料名称
     */
    public void setReportTitle(String reportTitle) {
        this.reportTitle = reportTitle;
    }

    /**
     * @return 提交部门
     */
    public String getReportDept() {
        return reportDept;
    }

    /**
     * @param reportDept 
	 *            提交部门
     */
    public void setReportDept(String reportDept) {
        this.reportDept = reportDept;
    }

    /**
     * @return 提交人
     */
    public String getReportSubmitter() {
        return reportSubmitter;
    }

    /**
     * @param reportSubmitter 
	 *            提交人
     */
    public void setReportSubmitter(String reportSubmitter) {
        this.reportSubmitter = reportSubmitter;
    }

    /**
     * @return 备注
     */
    public String getReportRemarks() {
        return reportRemarks;
    }

    /**
     * @param reportRemarks 
	 *            备注
     */
    public void setReportRemarks(String reportRemarks) {
        this.reportRemarks = reportRemarks;
    }

    /**
     * @return 是否删除（0.未删除1.删除--回收站）
     */
    public String getReportDel() {
        return reportDel;
    }

    /**
     * @param reportDel 
	 *            是否删除（0.未删除1.删除--回收站）
     */
    public void setReportDel(String reportDel) {
        this.reportDel = reportDel;
    }

    public Date getReportCreateTime() {
        return reportCreateTime;
    }

    public void setReportCreateTime(Date reportCreateTime) {
        this.reportCreateTime = reportCreateTime;
    }
    
    public String getReportSubmitterId() {
  		return reportSubmitterId;
  	}

  	public void setReportSubmitterId(String reportSubmitterId) {
  		this.reportSubmitterId = reportSubmitterId;
  	}
}