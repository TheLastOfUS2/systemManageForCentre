package com.systemManage.pojo.base;

import java.io.Serializable;
import java.util.Date;

public class MwSummary implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String summaryId;

    /**
     * 总结标题
     */
    private String summaryTitle;

    /**
     * 撰写时间
     */
    private String summaryTime;

    /**
     * 总结周期（0.年度总结1.十二五总结2.十一五总结3.十五总结）
     */
    private String summaryCycle;

    /**
     * 总结内容
     */
    private String summaryContent;

    /**
     * 备注
     */
    private String summaryRemarks;

    /**
     * 是否删除（0.未删除1.删除--回收站）
     */
    private String summaryDel;

    /**
     * 录入时间
     */
    private Date summaryCreateTime;

    /**
     * @return ID
     */
    public String getSummaryId() {
        return summaryId;
    }

    /**
     * @param summaryId 
	 *            ID
     */
    public void setSummaryId(String summaryId) {
        this.summaryId = summaryId;
    }

    /**
     * @return 总结标题
     */
    public String getSummaryTitle() {
        return summaryTitle;
    }

    /**
     * @param summaryTitle 
	 *            总结标题
     */
    public void setSummaryTitle(String summaryTitle) {
        this.summaryTitle = summaryTitle;
    }

    /**
     * @return 撰写时间
     */
    public String getSummaryTime() {
        return summaryTime;
    }

    /**
     * @param summaryTime 
	 *            撰写时间
     */
    public void setSummaryTime(String summaryTime) {
        this.summaryTime = summaryTime;
    }

    /**
     * @return 总结周期（0.十三五1.年度总结...）
     */
    public String getSummaryCycle() {
        return summaryCycle;
    }

    /**
     * @param summaryCycle 
	 *            总结周期（0.十三五1.年度总结...）
     */
    public void setSummaryCycle(String summaryCycle) {
        this.summaryCycle = summaryCycle;
    }

    /**
     * @return 总结内容
     */
    public String getSummaryContent() {
        return summaryContent;
    }

    /**
     * @param summaryContent 
	 *            总结内容
     */
    public void setSummaryContent(String summaryContent) {
        this.summaryContent = summaryContent;
    }

    /**
     * @return 备注
     */
    public String getSummaryRemarks() {
        return summaryRemarks;
    }

    /**
     * @param summaryRemarks 
	 *            备注
     */
    public void setSummaryRemarks(String summaryRemarks) {
        this.summaryRemarks = summaryRemarks;
    }

    /**
     * @return 是否删除（0.未删除1.删除--回收站）
     */
    public String getSummaryDel() {
        return summaryDel;
    }

    /**
     * @param summaryDel 
	 *            是否删除（0.未删除1.删除--回收站）
     */
    public void setSummaryDel(String summaryDel) {
        this.summaryDel = summaryDel;
    }

    /**
     * @return 录入时间
     */
    public Date getSummaryCreateTime() {
        return summaryCreateTime;
    }

    /**
     * @param summaryCreateTime 
	 *            录入时间
     */
    public void setSummaryCreateTime(Date summaryCreateTime) {
        this.summaryCreateTime = summaryCreateTime;
    }
}