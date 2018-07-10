package com.systemManage.pojo.base;

import java.io.Serializable;
import java.util.Date;

public class BiAbroad implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String abroadId;

    /**
     * 基本信息ID
     */
    private String baseInfoId;

    /**
     * 国家
     */
    private String abroadCountry;

    /**
     * 开始时间
     */
    private String abroadStartTime;

    /**
     * 结束时间
     */
    private String abroadEndTime;

    /**
     * 机构
     */
    private String abroadMechanism;

    /**
     * 目的(0.高访1.培训2.项目合作3.学习4.留学5.其他)
     */
    private String abroadObjective;

    /**
     * 录入时间
     */
    private Date abroadCreateTime;

    /**
     * @return ID
     */
    public String getAbroadId() {
        return abroadId;
    }

    /**
     * @param abroadId 
	 *            ID
     */
    public void setAbroadId(String abroadId) {
        this.abroadId = abroadId;
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
     * @return 国家
     */
    public String getAbroadCountry() {
        return abroadCountry;
    }

    /**
     * @param abroadCountry 
	 *            国家
     */
    public void setAbroadCountry(String abroadCountry) {
        this.abroadCountry = abroadCountry;
    }

    /**
     * @return 开始时间
     */
    public String getAbroadStartTime() {
        return abroadStartTime;
    }

    /**
     * @param abroadStartTime 
	 *            开始时间
     */
    public void setAbroadStartTime(String abroadStartTime) {
        this.abroadStartTime = abroadStartTime;
    }

    /**
     * @return 结束时间
     */
    public String getAbroadEndTime() {
        return abroadEndTime;
    }

    /**
     * @param abroadEndTime 
	 *            结束时间
     */
    public void setAbroadEndTime(String abroadEndTime) {
        this.abroadEndTime = abroadEndTime;
    }

    /**
     * @return 机构
     */
    public String getAbroadMechanism() {
        return abroadMechanism;
    }

    /**
     * @param abroadMechanism 
	 *            机构
     */
    public void setAbroadMechanism(String abroadMechanism) {
        this.abroadMechanism = abroadMechanism;
    }

    /**
     * @return 目的
     */
    public String getAbroadObjective() {
        return abroadObjective;
    }

    /**
     * @param abroadObjective 
	 *            目的
     */
    public void setAbroadObjective(String abroadObjective) {
        this.abroadObjective = abroadObjective;
    }

    /**
     * @return 录入时间
     */
    public Date getAbroadCreateTime() {
        return abroadCreateTime;
    }

    /**
     * @param abroadCreateTime 
	 *            录入时间
     */
    public void setAbroadCreateTime(Date abroadCreateTime) {
        this.abroadCreateTime = abroadCreateTime;
    }
}