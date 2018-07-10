package com.systemManage.pojo.base;

import java.io.Serializable;
import java.util.Date;

public class PcTeaching implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String teachingId;

    /**
     * 授课人
     */
    private String teachingName;
    
    /**
     * 授课人ID
     */
    private String teachingNameId;
    
    /**
     * 指导学生姓名
     */
    private String teachingStudentName;
    
    /**
     * 指导学生年级
     */
    private String teachingStudentGrade;

    /**
     * 年度
     */
    private String teachingYear;

    /**
     * 学期(0.第一学期1.第二学期)
     */
    private String teachingTerm;

    /**
     * 授课类型（0.博士1.硕士）
     */
    private String teachingType;

    /**
     * 课程名称
     */
    private String teachingTitle;

    /**
     * 授课课时
     */
    private String teachingHour;

    /**
     * 备注
     */
    private String teachingRemarks;

    /**
     * 是否删除（0.未删除1.删除--回收站）
     */
    private String teachingDel;

    /**
     * 录入时间
     */
    private Date teachingCreateTime;
    
    
    public String getTeachingNameId() {
		return teachingNameId;
	}

	public void setTeachingNameId(String teachingNameId) {
		this.teachingNameId = teachingNameId;
	}

	public String getTeachingStudentName() {
		return teachingStudentName;
	}

	public void setTeachingStudentName(String teachingStudentName) {
		this.teachingStudentName = teachingStudentName;
	}

	public String getTeachingStudentGrade() {
		return teachingStudentGrade;
	}

	public void setTeachingStudentGrade(String teachingStudentGrade) {
		this.teachingStudentGrade = teachingStudentGrade;
	}

    /**
     * @return ID
     */
    public String getTeachingId() {
        return teachingId;
    }

    /**
     * @param teachingId 
	 *            ID
     */
    public void setTeachingId(String teachingId) {
        this.teachingId = teachingId;
    }

    /**
     * @return 姓名
     */
    public String getTeachingName() {
        return teachingName;
    }

    /**
     * @param teachingName 
	 *            姓名
     */
    public void setTeachingName(String teachingName) {
        this.teachingName = teachingName;
    }

    /**
     * @return 年度
     */
    public String getTeachingYear() {
        return teachingYear;
    }

    /**
     * @param teachingYear 
	 *            年度
     */
    public void setTeachingYear(String teachingYear) {
        this.teachingYear = teachingYear;
    }

    /**
     * @return 学期
     */
    public String getTeachingTerm() {
        return teachingTerm;
    }

    /**
     * @param teachingTerm 
	 *            学期
     */
    public void setTeachingTerm(String teachingTerm) {
        this.teachingTerm = teachingTerm;
    }

    /**
     * @return 授课类型（0.博士1.硕士）
     */
    public String getTeachingType() {
        return teachingType;
    }

    /**
     * @param teachingType 
	 *            授课类型（0.博士1.硕士）
     */
    public void setTeachingType(String teachingType) {
        this.teachingType = teachingType;
    }

    /**
     * @return 课程名称
     */
    public String getTeachingTitle() {
        return teachingTitle;
    }

    /**
     * @param teachingTitle 
	 *            课程名称
     */
    public void setTeachingTitle(String teachingTitle) {
        this.teachingTitle = teachingTitle;
    }

    /**
     * @return 授课课时
     */
    public String getTeachingHour() {
        return teachingHour;
    }

    /**
     * @param teachingHour 
	 *            授课课时
     */
    public void setTeachingHour(String teachingHour) {
        this.teachingHour = teachingHour;
    }

    /**
     * @return 备注
     */
    public String getTeachingRemarks() {
        return teachingRemarks;
    }

    /**
     * @param teachingRemarks 
	 *            备注
     */
    public void setTeachingRemarks(String teachingRemarks) {
        this.teachingRemarks = teachingRemarks;
    }

    /**
     * @return 是否删除（0.未删除1.删除--回收站）
     */
    public String getTeachingDel() {
        return teachingDel;
    }

    /**
     * @param teachingDel 
	 *            是否删除（0.未删除1.删除--回收站）
     */
    public void setTeachingDel(String teachingDel) {
        this.teachingDel = teachingDel;
    }

    /**
     * @return 录入时间
     */
    public Date getTeachingCreateTime() {
        return teachingCreateTime;
    }

    /**
     * @param teachingCreateTime 
	 *            录入时间
     */
    public void setTeachingCreateTime(Date teachingCreateTime) {
        this.teachingCreateTime = teachingCreateTime;
    }
}