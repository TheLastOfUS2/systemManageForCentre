package com.systemManage.pojo.base;

import java.io.Serializable;
import java.util.Date;

public class AmPrize implements Serializable {
    private static final long serialVersionUID = 1L;

    private String prizeId;

    /**
     * 作者
     */
    private String baseInfoId;

    /**
     * 成果名称
     */
    private String prizeTitle;

    /**
     * 成果形式（0.著作1.论文2.咨询报告3.研究报告4.音像软件5.工具书或参考书6.其他）
     */
    private String prizeWay;

    /**
     * 奖励名称
     */
    private String prizeAchievement;

    /**
     * 获奖等级（0.一1.二2.三3.优秀4.其他）
     */
    private String prizeGrade;

    /**
     * 奖励级别（0.国家1.部级2.省级3.省级以下）
     */
    private String prizeLevel;

    /**
     * 颁奖单位
     */
    private String prizeAwardCompany;

    /**
     * 获奖时间
     */
    private String prizeWinningTime;

    /**
     * 备注
     */
    private String prizeRemarks;

    /**
     * 是否删除（0.未删除1.删除--回收站）
     */
    private String prizeDel;

    private Date prizeCreateTime;

    public String getPrizeId() {
        return prizeId;
    }

    public void setPrizeId(String prizeId) {
        this.prizeId = prizeId;
    }

    /**
     * @return 作者
     */
    public String getBaseInfoId() {
        return baseInfoId;
    }

    /**
     * @param baseInfoId 
	 *            作者
     */
    public void setBaseInfoId(String baseInfoId) {
        this.baseInfoId = baseInfoId;
    }

    /**
     * @return 成果名称
     */
    public String getPrizeTitle() {
        return prizeTitle;
    }

    /**
     * @param prizeTitle 
	 *            成果名称
     */
    public void setPrizeTitle(String prizeTitle) {
        this.prizeTitle = prizeTitle;
    }

    /**
     * @return 成果形式（0.著作1.论文2.咨询报告3.研究报告4.音像软件5.工具书或参考书6.其他）
     */
    public String getPrizeWay() {
        return prizeWay;
    }

    /**
     * @param prizeWay 
	 *            成果形式（0.著作1.论文2.咨询报告3.研究报告4.音像软件5.工具书或参考书6.其他）
     */
    public void setPrizeWay(String prizeWay) {
        this.prizeWay = prizeWay;
    }

    /**
     * @return 奖励名称
     */
    public String getPrizeAchievement() {
        return prizeAchievement;
    }

    /**
     * @param prizeAchievement 
	 *            奖励名称
     */
    public void setPrizeAchievement(String prizeAchievement) {
        this.prizeAchievement = prizeAchievement;
    }

    /**
     * @return 获奖等级（0.一1.二2.三3.优秀4.其他）
     */
    public String getPrizeGrade() {
        return prizeGrade;
    }

    /**
     * @param prizeGrade 
	 *            获奖等级（0.一1.二2.三3.优秀4.其他）
     */
    public void setPrizeGrade(String prizeGrade) {
        this.prizeGrade = prizeGrade;
    }

    /**
     * @return 奖励级别（0.国家1.部级2.省级3.省级以下）
     */
    public String getPrizeLevel() {
        return prizeLevel;
    }

    /**
     * @param prizeLevel 
	 *            奖励级别（0.国家1.部级2.省级3.省级以下）
     */
    public void setPrizeLevel(String prizeLevel) {
        this.prizeLevel = prizeLevel;
    }

    /**
     * @return 颁奖单位
     */
    public String getPrizeAwardCompany() {
        return prizeAwardCompany;
    }

    /**
     * @param prizeAwardCompany 
	 *            颁奖单位
     */
    public void setPrizeAwardCompany(String prizeAwardCompany) {
        this.prizeAwardCompany = prizeAwardCompany;
    }

    /**
     * @return 获奖时间
     */
    public String getPrizeWinningTime() {
        return prizeWinningTime;
    }

    /**
     * @param prizeWinningTime 
	 *            获奖时间
     */
    public void setPrizeWinningTime(String prizeWinningTime) {
        this.prizeWinningTime = prizeWinningTime;
    }

    /**
     * @return 备注
     */
    public String getPrizeRemarks() {
        return prizeRemarks;
    }

    /**
     * @param prizeRemarks 
	 *            备注
     */
    public void setPrizeRemarks(String prizeRemarks) {
        this.prizeRemarks = prizeRemarks;
    }

    /**
     * @return 是否删除（0.未删除1.删除--回收站）
     */
    public String getPrizeDel() {
        return prizeDel;
    }

    /**
     * @param prizeDel 
	 *            是否删除（0.未删除1.删除--回收站）
     */
    public void setPrizeDel(String prizeDel) {
        this.prizeDel = prizeDel;
    }

    public Date getPrizeCreateTime() {
        return prizeCreateTime;
    }

    public void setPrizeCreateTime(Date prizeCreateTime) {
        this.prizeCreateTime = prizeCreateTime;
    }
}