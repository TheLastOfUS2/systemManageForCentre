package com.systemManage.pojo.base;

import java.io.Serializable;
import java.util.Date;

public class BiMailList implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String mailListId;

    /**
     * 基本信息ID
     */
    private String baseInfoId;

    /**
     * 手机
     */
    private String mailListPhone;

    /**
     * 小号
     */
    private String mailListPhone1;

    /**
     * 办公电话
     */
    private String mailListOph;

    /**
     * 工作室号
     */
    private String mailListStudio;

    /**
     * 电子邮箱
     */
    private String mailListEmail;

    /**
     * 通讯地址
     */
    private String mailListAddress;

    /**
     * 单位
     */
    private String mailListCompany;

    /**
     * 备注
     */
    private String mailListRemarks;

    /**
     * 是否删除（0.未删除1.删除--回收站）
     */
    private String mailListDel;
    
    /**
     * 生成时间
     */
    private Date mailListCreateTime;

    /**
     * @return ID
     */
    public String getMailListId() {
        return mailListId;
    }

    /**
     * @param mailListId 
	 *            ID
     */
    public void setMailListId(String mailListId) {
        this.mailListId = mailListId;
    }

    /**
     * @return 基本信息ID
     */
    public String getBaseInfoId() {
        return baseInfoId;
    }

    /**
     * @param baseInfoId 
	 *            基本信息ID
     */
    public void setBaseInfoId(String baseInfoId) {
        this.baseInfoId = baseInfoId;
    }

    /**
     * @return 手机
     */
    public String getMailListPhone() {
        return mailListPhone;
    }

    /**
     * @param mailListPhone 
	 *            手机
     */
    public void setMailListPhone(String mailListPhone) {
        this.mailListPhone = mailListPhone;
    }

    /**
     * @return 小号
     */
    public String getMailListPhone1() {
        return mailListPhone1;
    }

    /**
     * @param mailListPhone1 
	 *            小号
     */
    public void setMailListPhone1(String mailListPhone1) {
        this.mailListPhone1 = mailListPhone1;
    }

    /**
     * @return 办公电话
     */
    public String getMailListOph() {
        return mailListOph;
    }

    /**
     * @param mailListOph 
	 *            办公电话
     */
    public void setMailListOph(String mailListOph) {
        this.mailListOph = mailListOph;
    }

    /**
     * @return 工作室号
     */
    public String getMailListStudio() {
        return mailListStudio;
    }

    /**
     * @param mailListStudio 
	 *            工作室号
     */
    public void setMailListStudio(String mailListStudio) {
        this.mailListStudio = mailListStudio;
    }

    /**
     * @return 电子邮箱
     */
    public String getMailListEmail() {
        return mailListEmail;
    }

    /**
     * @param mailListEmail 
	 *            电子邮箱
     */
    public void setMailListEmail(String mailListEmail) {
        this.mailListEmail = mailListEmail;
    }

    /**
     * @return 通讯地址
     */
    public String getMailListAddress() {
        return mailListAddress;
    }

    /**
     * @param mailListAddress 
	 *            通讯地址
     */
    public void setMailListAddress(String mailListAddress) {
        this.mailListAddress = mailListAddress;
    }

    /**
     * @return 单位
     */
    public String getMailListCompany() {
        return mailListCompany;
    }

    /**
     * @param mailListCompany 
	 *            单位
     */
    public void setMailListCompany(String mailListCompany) {
        this.mailListCompany = mailListCompany;
    }

    /**
     * @return 备注
     */
    public String getMailListRemarks() {
        return mailListRemarks;
    }

    /**
     * @param mailListRemarks 
	 *            备注
     */
    public void setMailListRemarks(String mailListRemarks) {
        this.mailListRemarks = mailListRemarks;
    }

    /**
     * @return 是否删除（0.未删除1.删除--回收站）
     */
    public String getMailListDel() {
        return mailListDel;
    }

    /**
     * @param mailListDel 
	 *            是否删除（0.未删除1.删除--回收站）
     */
    public void setMailListDel(String mailListDel) {
        this.mailListDel = mailListDel;
    }

    public Date getMailListCreateTime() {
        return mailListCreateTime;
    }

    public void setMailListCreateTime(Date mailListCreateTime) {
        this.mailListCreateTime = mailListCreateTime;
    }
}