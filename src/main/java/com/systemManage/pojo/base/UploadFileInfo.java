package com.systemManage.pojo.base;

import java.io.Serializable;
import java.util.Date;

public class UploadFileInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String fileId;

    /**
     * 关联ID
     */
    private String relevanceId;

    /**
     * 关联表名
     */
    private String tableName;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件存储路径
     */
    private String filePath;

    /**
     * 文件类型-和每个前台页面进行匹配
     */
    private String fileType;

    /**
     * 文件描述
     */
    private String fileContent;

    /**
     * 上传时间
     */
    private Date fileCreateTime;

    /**
     * @return 主键ID
     */
    public String getFileId() {
        return fileId;
    }

    /**
     * @param fileId 
     *            主键ID
     */
    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    /**
     * @return 关联ID
     */
    public String getRelevanceId() {
        return relevanceId;
    }

    /**
     * @param relevanceId 
     *            关联ID
     */
    public void setRelevanceId(String relevanceId) {
        this.relevanceId = relevanceId;
    }

    /**
     * @return 关联表名
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * @param tableName 
     *            关联表名
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * @return 文件名称
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName 
     *            文件名称
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return 文件存储路径
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * @param filePath 
     *            文件存储路径
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * @return 文件类型-和每个前台页面进行匹配
     */
    public String getFileType() {
        return fileType;
    }

    /**
     * @param fileType 
     *            文件类型-和每个前台页面进行匹配
     */
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    /**
     * @return 文件描述
     */
    public String getFileContent() {
        return fileContent;
    }

    /**
     * @param fileContent 
     *            文件描述
     */
    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }

    /**
     * @return 上传时间
     */
    public Date getFileCreateTime() {
        return fileCreateTime;
    }

    /**
     * @param fileCreateTime 
     *            上传时间
     */
    public void setFileCreateTime(Date fileCreateTime) {
        this.fileCreateTime = fileCreateTime;
    }
}