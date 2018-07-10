package com.systemManage.pojo.base;

import java.io.Serializable;
import java.util.Date;

public class MwPlan implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String planId;

    /**
     * 规划标题
     */
    private String planTitle;

    /**
     * 制定时间
     */
    private String planTime;

    /**
     * 规划周期（0.年度规划1.十三五规划2.十二五规划3.十一五规划4.十五规划）
     */
    private String planCycle;

    /**
     * 规划大纲
     */
    private String planContent;

    /**
     * 备注
     */
    private String planRemarks;

    /**
     * 是否删除（0.未删除1.删除--回收站）
     */
    private String planDel;

    /**
     * 录入时间
     */
    private Date planCreateTime;

    /**
     * @return ID
     */
    public String getPlanId() {
        return planId;
    }

    /**
     * @param planId 
	 *            ID
     */
    public void setPlanId(String planId) {
        this.planId = planId;
    }

    /**
     * @return 规划标题
     */
    public String getPlanTitle() {
        return planTitle;
    }

    /**
     * @param planTitle 
	 *            规划标题
     */
    public void setPlanTitle(String planTitle) {
        this.planTitle = planTitle;
    }

    /**
     * @return 制定时间
     */
    public String getPlanTime() {
        return planTime;
    }

    /**
     * @param planTime 
	 *            制定时间
     */
    public void setPlanTime(String planTime) {
        this.planTime = planTime;
    }

    /**
     * @return 规划周期（0.十三五1.年度规划...）
     */
    public String getPlanCycle() {
        return planCycle;
    }

    /**
     * @param planCycle 
	 *            规划周期（0.十三五1.年度规划...）
     */
    public void setPlanCycle(String planCycle) {
        this.planCycle = planCycle;
    }

    /**
     * @return 规划大纲
     */
    public String getPlanContent() {
        return planContent;
    }

    /**
     * @param planContent 
	 *            规划大纲
     */
    public void setPlanContent(String planContent) {
        this.planContent = planContent;
    }

    /**
     * @return 备注
     */
    public String getPlanRemarks() {
        return planRemarks;
    }

    /**
     * @param planRemarks 
	 *            备注
     */
    public void setPlanRemarks(String planRemarks) {
        this.planRemarks = planRemarks;
    }

    /**
     * @return 是否删除（0.未删除1.删除--回收站）
     */
    public String getPlanDel() {
        return planDel;
    }

    /**
     * @param planDel 
	 *            是否删除（0.未删除1.删除--回收站）
     */
    public void setPlanDel(String planDel) {
        this.planDel = planDel;
    }

    /**
     * @return 录入时间
     */
    public Date getPlanCreateTime() {
        return planCreateTime;
    }

    /**
     * @param planCreateTime 
	 *            录入时间
     */
    public void setPlanCreateTime(Date planCreateTime) {
        this.planCreateTime = planCreateTime;
    }
}