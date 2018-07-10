package com.systemManage.pojo.base;

import java.io.Serializable;
import java.util.Date;

public class BiJob implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String jobId;

    /**
     * 基本信息ID
     */
    private String baseInfoId;

    /**
     * 兼职类别(0.政府兼职1.企业兼职2.学术兼职)
     */
    private String jobType;

    /**
     * 机构类别（0.国内兼职1.国外兼职）
     */
    private String jobMechanismType;

    /**
     * 兼职开始时间
     */
    private String jobStartTime;

    /**
     * 兼职结束时间
     */
    private String jobEndTime;

    /**
     * 兼职机构
     */
    private String jobMechanism;

    /**
     * 职务名称
     */
    private String jobPost;

    /**
     * 录入时间
     */
    private Date jobCreateTime;

    /**
     * @return ID
     */
    public String getJobId() {
        return jobId;
    }

    /**
     * @param jobId 
	 *            ID
     */
    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    /**
     * @return 基本信息ID
     */
    public String getbaseInfoId() {
        return baseInfoId;
    }

    /**
     * @param baseInfoId 
	 *            基本信息ID
     */
    public void setbaseInfoId(String baseInfoId) {
        this.baseInfoId = baseInfoId;
    }

    /**
     * @return 兼职类别(0.政府兼职1.企业兼职2.学术兼职)
     */
    public String getJobType() {
        return jobType;
    }

    /**
     * @param jobType 
	 *            兼职类别(0.政府兼职1.企业兼职2.学术兼职)
     */
    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    /**
     * @return 机构类别（0.国内兼职1.国外兼职）
     */
    public String getJobMechanismType() {
        return jobMechanismType;
    }

    /**
     * @param jobMechanismType 
	 *            机构类别（0.国内兼职1.国外兼职）
     */
    public void setJobMechanismType(String jobMechanismType) {
        this.jobMechanismType = jobMechanismType;
    }

    /**
     * @return 兼职开始时间
     */
    public String getJobStartTime() {
        return jobStartTime;
    }

    /**
     * @param jobStartTime 
	 *            兼职开始时间
     */
    public void setJobStartTime(String jobStartTime) {
        this.jobStartTime = jobStartTime;
    }

    /**
     * @return 兼职结束时间
     */
    public String getJobEndTime() {
        return jobEndTime;
    }

    /**
     * @param jobEndTime 
	 *            兼职结束时间
     */
    public void setJobEndTime(String jobEndTime) {
        this.jobEndTime = jobEndTime;
    }

    /**
     * @return 兼职机构
     */
    public String getJobMechanism() {
        return jobMechanism;
    }

    /**
     * @param jobMechanism 
	 *            兼职机构
     */
    public void setJobMechanism(String jobMechanism) {
        this.jobMechanism = jobMechanism;
    }

    /**
     * @return 职务名称
     */
    public String getJobPost() {
        return jobPost;
    }

    /**
     * @param jobPost 
	 *            职务名称
     */
    public void setJobPost(String jobPost) {
        this.jobPost = jobPost;
    }

    /**
     * @return 录入时间
     */
    public Date getJobCreateTime() {
        return jobCreateTime;
    }

    /**
     * @param jobCreateTime 
	 *            录入时间
     */
    public void setJobCreateTime(Date jobCreateTime) {
        this.jobCreateTime = jobCreateTime;
    }
}