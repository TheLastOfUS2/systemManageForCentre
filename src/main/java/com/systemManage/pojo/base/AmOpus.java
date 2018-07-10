package com.systemManage.pojo.base;

import java.io.Serializable;
import java.util.Date;

public class AmOpus implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 著作ID
     */
    private String opusId;

    /**
     * 第一作者
     */
    private String baseInfoId;

    /**
     * 著作名称
     */
    private String opusName;

    /**
     * 著作类型（0.专著1.编著2.译著3.教材4.工具书5.普及行出版物）
     */
    private String opusType;

    /**
     * 出版时间
     */
    private String opusPublishTime;

    /**
     * 语种（0.中文1.外文）
     */
    private String opusLanguages;

    /**
     * 出版单位
     */
    private String opusPublishCompany;

    /**
     * 支持课题
     */
    private String opusSupportTopic;

    /**
     * 内容摘要
     */
    private String opusContent;

    /**
     * 备注
     */
    private String opusRemarks;

    /**
     * 是否删除（0.未删除1.删除--回收站）
     */
    private String opusDel;

    /**
     * 录入时间
     */
    private Date opusCreateTime;

    /**
     * @return 著作ID
     */
    public String getOpusId() {
        return opusId;
    }

    /**
     * @param opusId 
	 *            著作ID
     */
    public void setOpusId(String opusId) {
        this.opusId = opusId;
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
     * @return 著作名称
     */
    public String getOpusName() {
        return opusName;
    }

    /**
     * @param opusName 
	 *            著作名称
     */
    public void setOpusName(String opusName) {
        this.opusName = opusName;
    }

    /**
     * @return 著作类型（0.专著1.编著2.译著3.教材4.工具书5.普及行出版物）
     */
    public String getOpusType() {
        return opusType;
    }

    /**
     * @param opusType 
	 *            著作类型（0.专著1.编著2.译著3.教材4.工具书5.普及行出版物）
     */
    public void setOpusType(String opusType) {
        this.opusType = opusType;
    }

    /**
     * @return 出版时间
     */
    public String getOpusPublishTime() {
        return opusPublishTime;
    }

    /**
     * @param opusPublishTime 
	 *            出版时间
     */
    public void setOpusPublishTime(String opusPublishTime) {
        this.opusPublishTime = opusPublishTime;
    }

    /**
     * @return 出版时间
     */
    public String getOpusLanguages() {
        return opusLanguages;
    }

    /**
     * @param opusLanguages 
	 *            出版时间
     */
    public void setOpusLanguages(String opusLanguages) {
        this.opusLanguages = opusLanguages;
    }

    /**
     * @return 出版单位
     */
    public String getOpusPublishCompany() {
        return opusPublishCompany;
    }

    /**
     * @param opusPublishCompany 
	 *            出版单位
     */
    public void setOpusPublishCompany(String opusPublishCompany) {
        this.opusPublishCompany = opusPublishCompany;
    }

    /**
     * @return 支持课题
     */
    public String getOpusSupportTopic() {
        return opusSupportTopic;
    }

    /**
     * @param opusSupportTopic 
	 *            支持课题
     */
    public void setOpusSupportTopic(String opusSupportTopic) {
        this.opusSupportTopic = opusSupportTopic;
    }

    /**
     * @return 内容摘要
     */
    public String getOpusContent() {
        return opusContent;
    }

    /**
     * @param opusContent 
	 *            内容摘要
     */
    public void setOpusContent(String opusContent) {
        this.opusContent = opusContent;
    }

    /**
     * @return 备注
     */
    public String getOpusRemarks() {
        return opusRemarks;
    }

    /**
     * @param opusRemarks 
	 *            备注
     */
    public void setOpusRemarks(String opusRemarks) {
        this.opusRemarks = opusRemarks;
    }

    /**
     * @return 是否删除（0.未删除1.删除--回收站）
     */
    public String getOpusDel() {
        return opusDel;
    }

    /**
     * @param opusDel 
	 *            是否删除（0.未删除1.删除--回收站）
     */
    public void setOpusDel(String opusDel) {
        this.opusDel = opusDel;
    }

    /**
     * @return 录入时间
     */
    public Date getOpusCreateTime() {
        return opusCreateTime;
    }

    /**
     * @param opusCreateTime 
	 *            录入时间
     */
    public void setOpusCreateTime(Date opusCreateTime) {
        this.opusCreateTime = opusCreateTime;
    }
}