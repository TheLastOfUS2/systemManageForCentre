package com.systemManage.pojo.base;

import java.io.Serializable;
import java.util.Date;

public class BiBaseInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String baseInfoId;

    /**
     * 账号ID
     */
    private String accountId;

    /**
     * 学生类型(0.科研人员1.行政人员2.博士后3.博士4.硕士5.其他人员)
     */
    private String baseInfoType;

    /**
     * 姓名
     */
    private String baseInfoName;

    /**
     * 性别(0.女1.男)
     */
    private String baseInfoSex;

    private String baseInfoBirthDate;

    /**
     * 身份证号/护照号
     */
    private String baseInfoIdNumber;

    /**
     * 民族
     */
    private String baseInfoNation;

    /**
     * 籍贯
     */
    private String baseInfoNativePlace;

    /**
     * 政治面貌
     */
    private String baseInfoPoliticalOutlook;

    /**
     * 入党时间
     */
    private String baseInfoMemberTime;

    /**
     * 学历(0.博士1.硕士2.学士)
     */
    private String baseInfoEducation;

    /**
     * 学位（0.博士1.硕士2.学士）
     */
    private String baseInfoDegree;

    /**
     * 专业
     */
    private String baseInfoSpecialty;

    /**
     * 职称（0.研究员1.副研究员2.助理研究员3.教授4.副教授5.讲师6.助教）
     */
    private String baseInfoPositionalTitles;

    /**
     * 级别（0.高级1.副高2.中级3.初级）
     */
    private String baseInfoLevel;

    /**
     * 职务
     */
    private String baseInfoPost;

    /**
     * 是否博导（0.否1.是）
     */
    private String baseInfoIfDoctorTutor;

    /**
     * 是否硕导（0.否1.是）
     */
    private String baseInfoIfMasterTutor;

    private String baseInfoStartWorkTime;

    /**
     * 研究方向
     */
    private String baseInfoResearchDirection;

    /**
     * 研究专长
     */
    private String baseInfoResearchExpertise;

    /**
     * 人才计划
     */
    private String baseInfoTalentPlan;

    /**
     * 荣誉称号
     */
    private String baseInfoHonoraryTitle;

    /**
     * 工行卡号（校园卡）
     */
    private String baseInfoCampusCard;

    /**
     * 大连银行卡号（工资卡）
     */
    private String baseInfoPayrollCard;

    /**
     * 合作导师/导师姓名
     */
    private String baseInfoMentor;

    private String baseInfoStartTime;

    private String baseInfoEndTime;

    /**
     * 出站报告
     */
    private String baseInfoDepartureReport;

    /**
     * 就业单位
     */
    private String baseInfoEmploymentCompany;

    /**
     * 年级
     */
    private String baseInfoGrade;

    /**
     * 学位论文题目
     */
    private String baseInfoContactInformation;

    /**
     * 在读期间获得奖项或荣誉
     */
    private String baseInfoHonor;

    /**
     * 署名中心成果
     */
    private String baseInfoAchievement;

    /**
     * 国籍
     */
    private String baseInfoNationality;

    /**
     * 原工作单位
     */
    private String baseInfoPrimaryWorkUnit;

    /**
     * 外聘称谓
     */
    private String baseInfoExternalAppellation;

    private String baseInfoEngageStartTime;

    private String baseInfoEngageEndTime;

    /**
     * 个人简介
     */
    private String baseInfoProfile;

    /**
     * 备注
     */
    private String baseInfoRemarks;

    /**
     * 是否删除（0.未删除1.删除--回收站）
     */
    private String baseInfoDel;

    private Date baseCreateTime;

    /**
     * @return ID
     */
    public String getBaseInfoId() {
        return baseInfoId;
    }

    /**
     * @param baseInfoId 
	 *            ID
     */
    public void setBaseInfoId(String baseInfoId) {
        this.baseInfoId = baseInfoId;
    }

    /**
     * @return 账号ID
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * @param accountId 
	 *            账号ID
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    /**
     * @return 学生类型(0.科研人员1.行政人员2.博士后3.博士4.硕士5.其他人员)
     */
    public String getBaseInfoType() {
        return baseInfoType;
    }

    /**
     * @param baseInfoType 
	 *            学生类型(0.科研人员1.行政人员2.博士后3.博士4.硕士5.其他人员)
     */
    public void setBaseInfoType(String baseInfoType) {
        this.baseInfoType = baseInfoType;
    }

    /**
     * @return 姓名
     */
    public String getBaseInfoName() {
        return baseInfoName;
    }

    /**
     * @param baseInfoName 
	 *            姓名
     */
    public void setBaseInfoName(String baseInfoName) {
        this.baseInfoName = baseInfoName;
    }

    /**
     * @return 性别(0.女1.男)
     */
    public String getBaseInfoSex() {
        return baseInfoSex;
    }

    /**
     * @param baseInfoSex 
	 *            性别(0.女1.男)
     */
    public void setBaseInfoSex(String baseInfoSex) {
        this.baseInfoSex = baseInfoSex;
    }

    public String getBaseInfoBirthDate() {
        return baseInfoBirthDate;
    }

    public void setBaseInfoBirthDate(String baseInfoBirthDate) {
        this.baseInfoBirthDate = baseInfoBirthDate;
    }

    /**
     * @return 身份证号/护照号
     */
    public String getBaseInfoIdNumber() {
        return baseInfoIdNumber;
    }

    /**
     * @param baseInfoIdNumber 
	 *            身份证号/护照号
     */
    public void setBaseInfoIdNumber(String baseInfoIdNumber) {
        this.baseInfoIdNumber = baseInfoIdNumber;
    }

    /**
     * @return 民族
     */
    public String getBaseInfoNation() {
        return baseInfoNation;
    }

    /**
     * @param baseInfoNation 
	 *            民族
     */
    public void setBaseInfoNation(String baseInfoNation) {
        this.baseInfoNation = baseInfoNation;
    }

    /**
     * @return 籍贯
     */
    public String getBaseInfoNativePlace() {
        return baseInfoNativePlace;
    }

    /**
     * @param baseInfoNativePlace 
	 *            籍贯
     */
    public void setBaseInfoNativePlace(String baseInfoNativePlace) {
        this.baseInfoNativePlace = baseInfoNativePlace;
    }

    /**
     * @return 政治面貌
     */
    public String getBaseInfoPoliticalOutlook() {
        return baseInfoPoliticalOutlook;
    }

    /**
     * @param baseInfoPoliticalOutlook 
	 *            政治面貌
     */
    public void setBaseInfoPoliticalOutlook(String baseInfoPoliticalOutlook) {
        this.baseInfoPoliticalOutlook = baseInfoPoliticalOutlook;
    }

    /**
     * @return 入党时间
     */
    public String getBaseInfoMemberTime() {
        return baseInfoMemberTime;
    }

    /**
     * @param baseInfoMemberTime 
	 *            入党时间
     */
    public void setBaseInfoMemberTime(String baseInfoMemberTime) {
        this.baseInfoMemberTime = baseInfoMemberTime;
    }

    /**
     * @return 学历(0.博士1.硕士2.学士)
     */
    public String getBaseInfoEducation() {
        return baseInfoEducation;
    }

    /**
     * @param baseInfoEducation 
	 *            学历(0.博士1.硕士2.学士)
     */
    public void setBaseInfoEducation(String baseInfoEducation) {
        this.baseInfoEducation = baseInfoEducation;
    }

    /**
     * @return 学位（0.博士1.硕士2.学士）
     */
    public String getBaseInfoDegree() {
        return baseInfoDegree;
    }

    /**
     * @param baseInfoDegree 
	 *            学位（0.博士1.硕士2.学士）
     */
    public void setBaseInfoDegree(String baseInfoDegree) {
        this.baseInfoDegree = baseInfoDegree;
    }

    /**
     * @return 专业
     */
    public String getBaseInfoSpecialty() {
        return baseInfoSpecialty;
    }

    /**
     * @param baseInfoSpecialty 
	 *            专业
     */
    public void setBaseInfoSpecialty(String baseInfoSpecialty) {
        this.baseInfoSpecialty = baseInfoSpecialty;
    }

    /**
     * @return 职称（0.研究员1.副研究员2.助理研究员3.教授4.副教授5.讲师6.助教）
     */
    public String getBaseInfoPositionalTitles() {
        return baseInfoPositionalTitles;
    }

    /**
     * @param baseInfoPositionalTitles 
	 *            职称（0.研究员1.副研究员2.助理研究员3.教授4.副教授5.讲师6.助教）
     */
    public void setBaseInfoPositionalTitles(String baseInfoPositionalTitles) {
        this.baseInfoPositionalTitles = baseInfoPositionalTitles;
    }

    /**
     * @return 级别（0.高级1.副高2.中级3.初级）
     */
    public String getBaseInfoLevel() {
        return baseInfoLevel;
    }

    /**
     * @param baseInfoLevel 
	 *            级别（0.高级1.副高2.中级3.初级）
     */
    public void setBaseInfoLevel(String baseInfoLevel) {
        this.baseInfoLevel = baseInfoLevel;
    }

    /**
     * @return 职务
     */
    public String getBaseInfoPost() {
        return baseInfoPost;
    }

    /**
     * @param baseInfoPost 
	 *            职务
     */
    public void setBaseInfoPost(String baseInfoPost) {
        this.baseInfoPost = baseInfoPost;
    }

    /**
     * @return 是否博导（0.否1.是）
     */
    public String getBaseInfoIfDoctorTutor() {
        return baseInfoIfDoctorTutor;
    }

    /**
     * @param baseInfoIfDoctorTutor 
	 *            是否博导（0.否1.是）
     */
    public void setBaseInfoIfDoctorTutor(String baseInfoIfDoctorTutor) {
        this.baseInfoIfDoctorTutor = baseInfoIfDoctorTutor;
    }

    /**
     * @return 是否硕导（0.否1.是）
     */
    public String getBaseInfoIfMasterTutor() {
        return baseInfoIfMasterTutor;
    }

    /**
     * @param baseInfoIfMasterTutor 
	 *            是否硕导（0.否1.是）
     */
    public void setBaseInfoIfMasterTutor(String baseInfoIfMasterTutor) {
        this.baseInfoIfMasterTutor = baseInfoIfMasterTutor;
    }

    public String getBaseInfoStartWorkTime() {
        return baseInfoStartWorkTime;
    }

    public void setBaseInfoStartWorkTime(String baseInfoStartWorkTime) {
        this.baseInfoStartWorkTime = baseInfoStartWorkTime;
    }

    /**
     * @return 研究方向
     */
    public String getBaseInfoResearchDirection() {
        return baseInfoResearchDirection;
    }

    /**
     * @param baseInfoResearchDirection 
	 *            研究方向
     */
    public void setBaseInfoResearchDirection(String baseInfoResearchDirection) {
        this.baseInfoResearchDirection = baseInfoResearchDirection;
    }

    /**
     * @return 研究专长
     */
    public String getBaseInfoResearchExpertise() {
        return baseInfoResearchExpertise;
    }

    /**
     * @param baseInfoResearchExpertise 
	 *            研究专长
     */
    public void setBaseInfoResearchExpertise(String baseInfoResearchExpertise) {
        this.baseInfoResearchExpertise = baseInfoResearchExpertise;
    }

    /**
     * @return 人才计划
     */
    public String getBaseInfoTalentPlan() {
        return baseInfoTalentPlan;
    }

    /**
     * @param baseInfoTalentPlan 
	 *            人才计划
     */
    public void setBaseInfoTalentPlan(String baseInfoTalentPlan) {
        this.baseInfoTalentPlan = baseInfoTalentPlan;
    }

    /**
     * @return 荣誉称号
     */
    public String getBaseInfoHonoraryTitle() {
        return baseInfoHonoraryTitle;
    }

    /**
     * @param baseInfoHonoraryTitle 
	 *            荣誉称号
     */
    public void setBaseInfoHonoraryTitle(String baseInfoHonoraryTitle) {
        this.baseInfoHonoraryTitle = baseInfoHonoraryTitle;
    }

    /**
     * @return 工行卡号（校园卡）
     */
    public String getBaseInfoCampusCard() {
        return baseInfoCampusCard;
    }

    /**
     * @param baseInfoCampusCard 
	 *            工行卡号（校园卡）
     */
    public void setBaseInfoCampusCard(String baseInfoCampusCard) {
        this.baseInfoCampusCard = baseInfoCampusCard;
    }

    /**
     * @return 大连银行卡号（工资卡）
     */
    public String getBaseInfoPayrollCard() {
        return baseInfoPayrollCard;
    }

    /**
     * @param baseInfoPayrollCard 
	 *            大连银行卡号（工资卡）
     */
    public void setBaseInfoPayrollCard(String baseInfoPayrollCard) {
        this.baseInfoPayrollCard = baseInfoPayrollCard;
    }

    /**
     * @return 合作导师/导师姓名
     */
    public String getBaseInfoMentor() {
        return baseInfoMentor;
    }

    /**
     * @param baseInfoMentor 
	 *            合作导师/导师姓名
     */
    public void setBaseInfoMentor(String baseInfoMentor) {
        this.baseInfoMentor = baseInfoMentor;
    }

    public String getBaseInfoStartTime() {
        return baseInfoStartTime;
    }

    public void setBaseInfoStartTime(String baseInfoStartTime) {
        this.baseInfoStartTime = baseInfoStartTime;
    }

    public String getBaseInfoEndTime() {
        return baseInfoEndTime;
    }

    public void setBaseInfoEndTime(String baseInfoEndTime) {
        this.baseInfoEndTime = baseInfoEndTime;
    }

    /**
     * @return 出站报告
     */
    public String getBaseInfoDepartureReport() {
        return baseInfoDepartureReport;
    }

    /**
     * @param baseInfoDepartureReport 
	 *            出站报告
     */
    public void setBaseInfoDepartureReport(String baseInfoDepartureReport) {
        this.baseInfoDepartureReport = baseInfoDepartureReport;
    }

    /**
     * @return 就业单位
     */
    public String getBaseInfoEmploymentCompany() {
        return baseInfoEmploymentCompany;
    }

    /**
     * @param baseInfoEmploymentCompany 
	 *            就业单位
     */
    public void setBaseInfoEmploymentCompany(String baseInfoEmploymentCompany) {
        this.baseInfoEmploymentCompany = baseInfoEmploymentCompany;
    }

    /**
     * @return 年级
     */
    public String getBaseInfoGrade() {
        return baseInfoGrade;
    }

    /**
     * @param baseInfoGrade 
	 *            年级
     */
    public void setBaseInfoGrade(String baseInfoGrade) {
        this.baseInfoGrade = baseInfoGrade;
    }

    /**
     * @return 学位论文题目
     */
    public String getBaseInfoContactInformation() {
        return baseInfoContactInformation;
    }

    /**
     * @param baseInfoContactInformation 
	 *            学位论文题目
     */
    public void setBaseInfoContactInformation(String baseInfoContactInformation) {
        this.baseInfoContactInformation = baseInfoContactInformation;
    }

    /**
     * @return 在读期间获得奖项或荣誉
     */
    public String getBaseInfoHonor() {
        return baseInfoHonor;
    }

    /**
     * @param baseInfoHonor 
	 *            在读期间获得奖项或荣誉
     */
    public void setBaseInfoHonor(String baseInfoHonor) {
        this.baseInfoHonor = baseInfoHonor;
    }

    /**
     * @return 署名中心成果
     */
    public String getBaseInfoAchievement() {
        return baseInfoAchievement;
    }

    /**
     * @param baseInfoAchievement 
	 *            署名中心成果
     */
    public void setBaseInfoAchievement(String baseInfoAchievement) {
        this.baseInfoAchievement = baseInfoAchievement;
    }

    /**
     * @return 国籍
     */
    public String getBaseInfoNationality() {
        return baseInfoNationality;
    }

    /**
     * @param baseInfoNationality 
	 *            国籍
     */
    public void setBaseInfoNationality(String baseInfoNationality) {
        this.baseInfoNationality = baseInfoNationality;
    }

    /**
     * @return 原工作单位
     */
    public String getBaseInfoPrimaryWorkUnit() {
        return baseInfoPrimaryWorkUnit;
    }

    /**
     * @param baseInfoPrimaryWorkUnit 
	 *            原工作单位
     */
    public void setBaseInfoPrimaryWorkUnit(String baseInfoPrimaryWorkUnit) {
        this.baseInfoPrimaryWorkUnit = baseInfoPrimaryWorkUnit;
    }

    /**
     * @return 外聘称谓
     */
    public String getBaseInfoExternalAppellation() {
        return baseInfoExternalAppellation;
    }

    /**
     * @param baseInfoExternalAppellation 
	 *            外聘称谓
     */
    public void setBaseInfoExternalAppellation(String baseInfoExternalAppellation) {
        this.baseInfoExternalAppellation = baseInfoExternalAppellation;
    }

    public String getBaseInfoEngageStartTime() {
        return baseInfoEngageStartTime;
    }

    public void setBaseInfoEngageStartTime(String baseInfoEngageStartTime) {
        this.baseInfoEngageStartTime = baseInfoEngageStartTime;
    }

    public String getBaseInfoEngageEndTime() {
        return baseInfoEngageEndTime;
    }

    public void setBaseInfoEngageEndTime(String baseInfoEngageEndTime) {
        this.baseInfoEngageEndTime = baseInfoEngageEndTime;
    }

    /**
     * @return 个人简介
     */
    public String getBaseInfoProfile() {
        return baseInfoProfile;
    }

    /**
     * @param baseInfoProfile 
	 *            个人简介
     */
    public void setBaseInfoProfile(String baseInfoProfile) {
        this.baseInfoProfile = baseInfoProfile;
    }

    /**
     * @return 备注
     */
    public String getBaseInfoRemarks() {
        return baseInfoRemarks;
    }

    /**
     * @param baseInfoRemarks 
	 *            备注
     */
    public void setBaseInfoRemarks(String baseInfoRemarks) {
        this.baseInfoRemarks = baseInfoRemarks;
    }

    /**
     * @return 是否删除（0.未删除1.删除--回收站）
     */
    public String getBaseInfoDel() {
        return baseInfoDel;
    }

    /**
     * @param baseInfoDel 
	 *            是否删除（0.未删除1.删除--回收站）
     */
    public void setBaseInfoDel(String baseInfoDel) {
        this.baseInfoDel = baseInfoDel;
    }

    public Date getBaseCreateTime() {
        return baseCreateTime;
    }

    public void setBaseCreateTime(Date baseCreateTime) {
        this.baseCreateTime = baseCreateTime;
    }
}