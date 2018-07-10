package com.systemManage.pojo.base;

import java.io.Serializable;
import java.util.Date;

public class MwMemorabilia implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String memorabiliaId;

    /**
     * 主题
     */
    private String memorabiliaTheme;

    /**
     * 时间
     */
    private String memorabiliaTime;

    /**
     * 简要内容
     */
    private String memorabiliaContent;

    /**
     * 备注
     */
    private String memorabiliaRemarks;

    /**
     * 是否删除（0.未删除1.删除--回收站）
     */
    private String memorabiliaDel;

    /**
     * 录入时间
     */
    private Date memorabiliaCreateTime;

    /**
     * @return ID
     */
    public String getMemorabiliaId() {
        return memorabiliaId;
    }

    /**
     * @param memorabiliaId 
	 *            ID
     */
    public void setMemorabiliaId(String memorabiliaId) {
        this.memorabiliaId = memorabiliaId;
    }

    /**
     * @return 主题
     */
    public String getMemorabiliaTheme() {
        return memorabiliaTheme;
    }

    /**
     * @param memorabiliaTheme 
	 *            主题
     */
    public void setMemorabiliaTheme(String memorabiliaTheme) {
        this.memorabiliaTheme = memorabiliaTheme;
    }

    /**
     * @return 时间
     */
    public String getMemorabiliaTime() {
        return memorabiliaTime;
    }

    /**
     * @param memorabiliaTime 
	 *            时间
     */
    public void setMemorabiliaTime(String memorabiliaTime) {
        this.memorabiliaTime = memorabiliaTime;
    }

    /**
     * @return 简要内容
     */
    public String getMemorabiliaContent() {
        return memorabiliaContent;
    }

    /**
     * @param memorabiliaContent 
	 *            简要内容
     */
    public void setMemorabiliaContent(String memorabiliaContent) {
        this.memorabiliaContent = memorabiliaContent;
    }

    /**
     * @return 备注
     */
    public String getMemorabiliaRemarks() {
        return memorabiliaRemarks;
    }

    /**
     * @param memorabiliaRemarks 
	 *            备注
     */
    public void setMemorabiliaRemarks(String memorabiliaRemarks) {
        this.memorabiliaRemarks = memorabiliaRemarks;
    }

    /**
     * @return 是否删除（0.未删除1.删除--回收站）
     */
    public String getMemorabiliaDel() {
        return memorabiliaDel;
    }

    /**
     * @param memorabiliaDel 
	 *            是否删除（0.未删除1.删除--回收站）
     */
    public void setMemorabiliaDel(String memorabiliaDel) {
        this.memorabiliaDel = memorabiliaDel;
    }

    /**
     * @return 录入时间
     */
    public Date getMemorabiliaCreateTime() {
        return memorabiliaCreateTime;
    }

    /**
     * @param memorabiliaCreateTime 
	 *            录入时间
     */
    public void setMemorabiliaCreateTime(Date memorabiliaCreateTime) {
        this.memorabiliaCreateTime = memorabiliaCreateTime;
    }
}