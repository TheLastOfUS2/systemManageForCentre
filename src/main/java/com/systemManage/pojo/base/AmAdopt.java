package com.systemManage.pojo.base;

import java.io.Serializable;
import java.util.Date;

public class AmAdopt implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 采纳批示ID
     */
    private String adoptId;

    /**
     * 作者
     */
    private String baseInfoId;

    /**
     * 报告名称
     */
    private String adoptName;

    /**
     * 成果形式（0.采纳1.批示2.刊发）
     */
    private String adoptWay;

    /**
     * 提交时间
     */
    private String adoptSubmitTime;

    /**
     * 采纳时间
     */
    private String adoptTime;

    /**
     * 采纳单位
     */
    private String adoptCompany;

    /**
     * 采纳刊物
     */
    private String adoptPublication;

    /**
     * 鉴定或评价
     */
    private String adoptEvaluate;

    /**
     * 支持课题
     */
    private String adoptSupportTopic;

    /**
     * 备注
     */
    private String adoptRemarks;

    /**
     * 是否删除（0.未删除1.删除--回收站）
     */
    private String adoptDel;

    private Date adoptCreateTime;

    /**
     * @return 采纳批示ID
     */
    public String getAdoptId() {
        return adoptId;
    }

    /**
     * @param adoptId 
     *            采纳批示ID
     */
    public void setAdoptId(String adoptId) {
        this.adoptId = adoptId;
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
     * @return 报告名称
     */
    public String getAdoptName() {
        return adoptName;
    }

    /**
     * @param adoptName 
     *            报告名称
     */
    public void setAdoptName(String adoptName) {
        this.adoptName = adoptName;
    }

    /**
     * @return 成果形式（0.采纳1.批示2.刊发）
     */
    public String getAdoptWay() {
        return adoptWay;
    }

    /**
     * @param adoptWay 
     *            成果形式（0.采纳1.批示2.刊发）
     */
    public void setAdoptWay(String adoptWay) {
        this.adoptWay = adoptWay;
    }

    /**
     * @return 提交时间
     */
    public String getAdoptSubmitTime() {
        return adoptSubmitTime;
    }

    /**
     * @param adoptSubmitTime 
     *            提交时间
     */
    public void setAdoptSubmitTime(String adoptSubmitTime) {
        this.adoptSubmitTime = adoptSubmitTime;
    }

    /**
     * @return 采纳时间
     */
    public String getAdoptTime() {
        return adoptTime;
    }

    /**
     * @param adoptTime 
     *            采纳时间
     */
    public void setAdoptTime(String adoptTime) {
        this.adoptTime = adoptTime;
    }

    /**
     * @return 采纳单位
     */
    public String getAdoptCompany() {
        return adoptCompany;
    }

    /**
     * @param adoptCompany 
     *            采纳单位
     */
    public void setAdoptCompany(String adoptCompany) {
        this.adoptCompany = adoptCompany;
    }

    /**
     * @return 采纳刊物
     */
    public String getAdoptPublication() {
        return adoptPublication;
    }

    /**
     * @param adoptPublication 
     *            采纳刊物
     */
    public void setAdoptPublication(String adoptPublication) {
        this.adoptPublication = adoptPublication;
    }

    /**
     * @return 鉴定或评价
     */
    public String getAdoptEvaluate() {
        return adoptEvaluate;
    }

    /**
     * @param adoptEvaluate 
     *            鉴定或评价
     */
    public void setAdoptEvaluate(String adoptEvaluate) {
        this.adoptEvaluate = adoptEvaluate;
    }

    /**
     * @return 支持课题
     */
    public String getAdoptSupportTopic() {
        return adoptSupportTopic;
    }

    /**
     * @param adoptSupportTopic 
     *            支持课题
     */
    public void setAdoptSupportTopic(String adoptSupportTopic) {
        this.adoptSupportTopic = adoptSupportTopic;
    }

    /**
     * @return 备注
     */
    public String getAdoptRemarks() {
        return adoptRemarks;
    }

    /**
     * @param adoptRemarks 
     *            备注
     */
    public void setAdoptRemarks(String adoptRemarks) {
        this.adoptRemarks = adoptRemarks;
    }

    /**
     * @return 是否删除（0.未删除1.删除--回收站）
     */
    public String getAdoptDel() {
        return adoptDel;
    }

    /**
     * @param adoptDel 
     *            是否删除（0.未删除1.删除--回收站）
     */
    public void setAdoptDel(String adoptDel) {
        this.adoptDel = adoptDel;
    }

    public Date getAdoptCreateTime() {
        return adoptCreateTime;
    }

    public void setAdoptCreateTime(Date adoptCreateTime) {
        this.adoptCreateTime = adoptCreateTime;
    }
}