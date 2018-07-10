package com.systemManage.pojo.base;

import java.io.Serializable;
import java.util.Date;

public class AmPaper implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 论文ID
     */
    private String paperId;

    /**
     * 第一作者
     */
    private String baseInfoId;

    /**
     * 论文类型（0.期刊论文1.会议论文2.报纸文章）
     */
    private String paperType;

    /**
     * 题目/成果名称
     */
    private String paperTitle;

    /**
     * 会议名称/报纸名称
     */
    private String paperName;

    /**
     * 发表期刊
     */
    private String paperPublishJournal;

    /**
     * 期刊类型（0.中文1.外文）
     */
    private String paperPeriodicalType;

    /**
     * 卷期号
     */
    private String paperVolumeNumber;

    /**
     * 发表时间
     */
    private String paperPublishTime;

    /**
     * 页码开始范围（第几页 到第一页）
     */
    private String paperPageStartRange;

    /**
     * 页码结束范围
     */
    private String paperPageEndRange;

    /**
     * 支持课题
     */
    private String paperSupportTopic;

    /**
     * 转载情况
     */
    private String paperReprintCount;

    /**
     * 检索类型(CSSCI来源集刊、CSSCI来源期刊、CSSCI来源期刊扩展、CSSCI、SCI、EI、ISTP、SSCI、AHCI、ISSHP、ISSPT、其他、无)
     */
    private String paperRetrievalType;

    /**
     * 内容摘要
     */
    private String paperContent;

    /**
     * 地点
     */
    private String paperAddress;

    /**
     * 板块
     */
    private String paperPlate;

    /**
     * 是否理论版(0.否1.是)
     */
    private String paperIfTheory;

    /**
     * 备注
     */
    private String paperRemarks;

    /**
     * 是否删除（0.未删除1.删除--回收站）
     */
    private String paperDel;
    
    /**
     * 创建时间
     */
    private Date paperCreateTime;

    /**
     * @return 论文ID
     */
    public String getPaperId() {
        return paperId;
    }

    /**
     * @param paperId 
	 *            论文ID
     */
    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    /**
     * @return 第一作者
     */
    public String getBaseInfoId() {
        return baseInfoId;
    }

    /**
     * @param baseInfoId 
	 *            第一作者
     */
    public void setBaseInfoId(String baseInfoId) {
        this.baseInfoId = baseInfoId;
    }

    /**
     * @return 论文类型（0.期刊论文1.会议论文2.报纸文章）
     */
    public String getPaperType() {
        return paperType;
    }

    /**
     * @param paperType 
	 *            论文类型（0.期刊论文1.会议论文2.报纸文章）
     */
    public void setPaperType(String paperType) {
        this.paperType = paperType;
    }

    /**
     * @return 题目/成果名称
     */
    public String getPaperTitle() {
        return paperTitle;
    }

    /**
     * @param paperTitle 
	 *            题目/成果名称
     */
    public void setPaperTitle(String paperTitle) {
        this.paperTitle = paperTitle;
    }

    /**
     * @return 会议名称/报纸名称
     */
    public String getPaperName() {
        return paperName;
    }

    /**
     * @param paperName 
	 *            会议名称/报纸名称
     */
    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    /**
     * @return 发表期刊
     */
    public String getPaperPublishJournal() {
        return paperPublishJournal;
    }

    /**
     * @param paperPublishJournal 
	 *            发表期刊
     */
    public void setPaperPublishJournal(String paperPublishJournal) {
        this.paperPublishJournal = paperPublishJournal;
    }

    /**
     * @return 期刊类型（0.中文1.外文）
     */
    public String getPaperPeriodicalType() {
        return paperPeriodicalType;
    }

    /**
     * @param paperPeriodicalType 
	 *            期刊类型（0.中文1.外文）
     */
    public void setPaperPeriodicalType(String paperPeriodicalType) {
        this.paperPeriodicalType = paperPeriodicalType;
    }

    /**
     * @return 卷期号
     */
    public String getPaperVolumeNumber() {
        return paperVolumeNumber;
    }

    /**
     * @param paperVolumeNumber 
	 *            卷期号
     */
    public void setPaperVolumeNumber(String paperVolumeNumber) {
        this.paperVolumeNumber = paperVolumeNumber;
    }

    /**
     * @return 发表时间
     */
    public String getPaperPublishTime() {
        return paperPublishTime;
    }

    /**
     * @param paperPublishTime 
	 *            发表时间
     */
    public void setPaperPublishTime(String paperPublishTime) {
        this.paperPublishTime = paperPublishTime;
    }

    /**
     * @return 页码开始范围（第几页 到第一页）
     */
    public String getPaperPageStartRange() {
        return paperPageStartRange;
    }

    /**
     * @param paperPageStartRange 
	 *            页码开始范围（第几页 到第一页）
     */
    public void setPaperPageStartRange(String paperPageStartRange) {
        this.paperPageStartRange = paperPageStartRange;
    }

    /**
     * @return 页码结束范围
     */
    public String getPaperPageEndRange() {
        return paperPageEndRange;
    }

    /**
     * @param paperPageEndRange 
	 *            页码结束范围
     */
    public void setPaperPageEndRange(String paperPageEndRange) {
        this.paperPageEndRange = paperPageEndRange;
    }

    /**
     * @return 支持课题
     */
    public String getPaperSupportTopic() {
        return paperSupportTopic;
    }

    /**
     * @param paperSupportTopic 
	 *            支持课题
     */
    public void setPaperSupportTopic(String paperSupportTopic) {
        this.paperSupportTopic = paperSupportTopic;
    }

    /**
     * @return 转载情况
     */
    public String getPaperReprintCount() {
        return paperReprintCount;
    }

    /**
     * @param paperReprintCount 
	 *            转载情况
     */
    public void setPaperReprintCount(String paperReprintCount) {
        this.paperReprintCount = paperReprintCount;
    }

    /**
     * @return 检索类型(0.CSSCI…….)
     */
    public String getPaperRetrievalType() {
        return paperRetrievalType;
    }

    /**
     * @param paperRetrievalType 
	 *            检索类型(0.CSSCI…….)
     */
    public void setPaperRetrievalType(String paperRetrievalType) {
        this.paperRetrievalType = paperRetrievalType;
    }

    /**
     * @return 检索类型(0.CSSCI…….)
     */
    public String getPaperContent() {
        return paperContent;
    }

    /**
     * @param paperContent 
	 *            检索类型(0.CSSCI…….)
     */
    public void setPaperContent(String paperContent) {
        this.paperContent = paperContent;
    }

    /**
     * @return 地点
     */
    public String getPaperAddress() {
        return paperAddress;
    }

    /**
     * @param paperAddress 
	 *            地点
     */
    public void setPaperAddress(String paperAddress) {
        this.paperAddress = paperAddress;
    }

    /**
     * @return 板块
     */
    public String getPaperPlate() {
        return paperPlate;
    }

    /**
     * @param paperPlate 
	 *            板块
     */
    public void setPaperPlate(String paperPlate) {
        this.paperPlate = paperPlate;
    }

    /**
     * @return 是否理论版(0.否1.是)
     */
    public String getPaperIfTheory() {
        return paperIfTheory;
    }

    /**
     * @param paperIfTheory 
	 *            是否理论版(0.否1.是)
     */
    public void setPaperIfTheory(String paperIfTheory) {
        this.paperIfTheory = paperIfTheory;
    }

    /**
     * @return 备注
     */
    public String getPaperRemarks() {
        return paperRemarks;
    }

    /**
     * @param paperRemarks 
	 *            备注
     */
    public void setPaperRemarks(String paperRemarks) {
        this.paperRemarks = paperRemarks;
    }

    /**
     * @return 是否删除（0.未删除1.删除--回收站）
     */
    public String getPaperDel() {
        return paperDel;
    }

    /**
     * @param paperDel 
	 *            是否删除（0.未删除1.删除--回收站）
     */
    public void setPaperDel(String paperDel) {
        this.paperDel = paperDel;
    }

    public Date getPaperCreateTime() {
        return paperCreateTime;
    }

    public void setPaperCreateTime(Date paperCreateTime) {
        this.paperCreateTime = paperCreateTime;
    }
}