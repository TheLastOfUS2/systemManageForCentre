package com.systemManage.pojo.base;

import java.io.Serializable;
import java.util.Date;

public class BiEducational implements Serializable {
    private static final long serialVersionUID = 1L;

    private String educationalId;

    private String baseInfoId;

    private String educationalCountry;

    private String educationalSchool;

    private String educationalStartTime;

    private String educationalEndTime;

    private String educationalCollege;

    private String educationalMajor;

    private String educationalEducation;

    private String educationalDegree;

    private Date educationalCreateTime;

    public String getEducationalId() {
        return educationalId;
    }

    public void setEducationalId(String educationalId) {
        this.educationalId = educationalId;
    }

    public String getBaseInfoId() {
        return baseInfoId;
    }

    public void setBaseInfoId(String baseInfoId) {
        this.baseInfoId = baseInfoId;
    }

    public String getEducationalCountry() {
        return educationalCountry;
    }

    public void setEducationalCountry(String educationalCountry) {
        this.educationalCountry = educationalCountry;
    }

    public String getEducationalSchool() {
        return educationalSchool;
    }

    public void setEducationalSchool(String educationalSchool) {
        this.educationalSchool = educationalSchool;
    }

    public String getEducationalStartTime() {
        return educationalStartTime;
    }

    public void setEducationalStartTime(String educationalStartTime) {
        this.educationalStartTime = educationalStartTime;
    }

    public String getEducationalEndTime() {
        return educationalEndTime;
    }

    public void setEducationalEndTime(String educationalEndTime) {
        this.educationalEndTime = educationalEndTime;
    }

    public String getEducationalCollege() {
        return educationalCollege;
    }

    public void setEducationalCollege(String educationalCollege) {
        this.educationalCollege = educationalCollege;
    }

    public String getEducationalMajor() {
        return educationalMajor;
    }

    public void setEducationalMajor(String educationalMajor) {
        this.educationalMajor = educationalMajor;
    }

    public String getEducationalEducation() {
        return educationalEducation;
    }

    public void setEducationalEducation(String educationalEducation) {
        this.educationalEducation = educationalEducation;
    }

    public String getEducationalDegree() {
        return educationalDegree;
    }

    public void setEducationalDegree(String educationalDegree) {
        this.educationalDegree = educationalDegree;
    }

    public Date getEducationalCreateTime() {
        return educationalCreateTime;
    }

    public void setEducationalCreateTime(Date educationalCreateTime) {
        this.educationalCreateTime = educationalCreateTime;
    }
}