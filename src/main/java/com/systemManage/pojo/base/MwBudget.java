package com.systemManage.pojo.base;

import java.io.Serializable;
import java.util.Date;

public class MwBudget implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String budgetId;

    /**
     * 预算年度
     */
    private String budgetYear;

    /**
     * 制定时间
     */
    private String budgetTime;

    /**
     * 支出类目(0.学术会议1.数据库建设2.学术期刊3.网站建设4.图书资料5.其他)
     */
    private String budgetCategory;

    /**
     * 分项名称
     */
    private String budgetTitle;

    /**
     * 经费来源（0.教育部1.省部委2.学校拨款3.其他来源）
     */
    private String budgetFundSource;

    /**
     * 金额(万)
     */
    private Double budgetAmounts;

    /**
     * 备注
     */
    private String budgetRemarks;

    /**
     * 是否删除（0.未删除1.删除--回收站）
     */
    private String budgetDel;

    /**
     * 录入时间
     */
    private Date budgetCreateTime;

    /**
     * @return ID
     */
    public String getBudgetId() {
        return budgetId;
    }

    /**
     * @param budgetId 
	 *            ID
     */
    public void setBudgetId(String budgetId) {
        this.budgetId = budgetId;
    }

    /**
     * @return 预算年度
     */
    public String getBudgetYear() {
        return budgetYear;
    }

    /**
     * @param budgetYear 
	 *            预算年度
     */
    public void setBudgetYear(String budgetYear) {
        this.budgetYear = budgetYear;
    }

    /**
     * @return 制定时间
     */
    public String getBudgetTime() {
        return budgetTime;
    }

    /**
     * @param budgetTime 
	 *            制定时间
     */
    public void setBudgetTime(String budgetTime) {
        this.budgetTime = budgetTime;
    }

    /**
     * @return 支出类目
     */
    public String getBudgetCategory() {
        return budgetCategory;
    }

    /**
     * @param budgetCategory 
	 *            支出类目
     */
    public void setBudgetCategory(String budgetCategory) {
        this.budgetCategory = budgetCategory;
    }

    /**
     * @return 分项名称
     */
    public String getBudgetTitle() {
        return budgetTitle;
    }

    /**
     * @param budgetTitle 
	 *            分项名称
     */
    public void setBudgetTitle(String budgetTitle) {
        this.budgetTitle = budgetTitle;
    }

    /**
     * @return 经费来源（0.教育部1.省部委2.学校拨款3.其他来源）
     */
    public String getBudgetFundSource() {
        return budgetFundSource;
    }

    /**
     * @param budgetFundSource 
	 *            经费来源（0.教育部1.省部委2.学校拨款3.其他来源）
     */
    public void setBudgetFundSource(String budgetFundSource) {
        this.budgetFundSource = budgetFundSource;
    }

    /**
     * @return 金额(万)
     */
    public Double getBudgetAmounts() {
        return budgetAmounts;
    }

    /**
     * @param budgetAmounts 
	 *            金额(万)
     */
    public void setBudgetAmounts(Double budgetAmounts) {
        this.budgetAmounts = budgetAmounts;
    }

    /**
     * @return 备注
     */
    public String getBudgetRemarks() {
        return budgetRemarks;
    }

    /**
     * @param budgetRemarks 
	 *            备注
     */
    public void setBudgetRemarks(String budgetRemarks) {
        this.budgetRemarks = budgetRemarks;
    }

    /**
     * @return 是否删除（0.未删除1.删除--回收站）
     */
    public String getBudgetDel() {
        return budgetDel;
    }

    /**
     * @param budgetDel 
	 *            是否删除（0.未删除1.删除--回收站）
     */
    public void setBudgetDel(String budgetDel) {
        this.budgetDel = budgetDel;
    }

    /**
     * @return 录入时间
     */
    public Date getBudgetCreateTime() {
        return budgetCreateTime;
    }

    /**
     * @param budgetCreateTime 
	 *            录入时间
     */
    public void setBudgetCreateTime(Date budgetCreateTime) {
        this.budgetCreateTime = budgetCreateTime;
    }
}