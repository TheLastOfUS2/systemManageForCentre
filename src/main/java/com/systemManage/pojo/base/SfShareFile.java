package com.systemManage.pojo.base;

import java.io.Serializable;
import java.util.Date;

public class SfShareFile implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 共享文件id
     */
    private String shareFileId;

    /**
     * 共享名称
     */
    private String shareFileName;
    
    /**
     * 共享文件类型（0.工作论文1.研讨会讲稿2.参考文献3.会议通讯录4.其他）
     */
    private String shareFileType;

    /**
     * 共享文件提交人
     */
    private String shareFileSubmitter;

    /**
     * 备注
     */
    private String shareFileRemarks;

    /**
     * 是否删除（0.未删除1.已删除）
     */
    private String shareFileDel;
    
    /**
     * 上传时间
     */
    private Date shareFileCreateTime;
    
    public String getShareFileId() {
		return shareFileId;
	}

	public void setShareFileId(String shareFileId) {
		this.shareFileId = shareFileId;
	}

	public String getShareFileName() {
		return shareFileName;
	}

	public void setShareFileName(String shareFileName) {
		this.shareFileName = shareFileName;
	}

	public String getShareFileSubmitter() {
		return shareFileSubmitter;
	}

	public String getShareFileType() {
		return shareFileType;
	}

	public void setShareFileType(String shareFileType) {
		this.shareFileType = shareFileType;
	}

	public void setShareFileSubmitter(String shareFileSubmitter) {
		this.shareFileSubmitter = shareFileSubmitter;
	}

	public String getShareFileRemarks() {
		return shareFileRemarks;
	}

	public void setShareFileRemarks(String shareFileRemarks) {
		this.shareFileRemarks = shareFileRemarks;
	}

	public String getShareFileDel() {
		return shareFileDel;
	}

	public void setShareFileDel(String shareFileDel) {
		this.shareFileDel = shareFileDel;
	}

	public Date getShareFileCreateTime() {
		return shareFileCreateTime;
	}

	public void setShareFileCreateTime(Date shareFileCreateTime) {
		this.shareFileCreateTime = shareFileCreateTime;
	}
}