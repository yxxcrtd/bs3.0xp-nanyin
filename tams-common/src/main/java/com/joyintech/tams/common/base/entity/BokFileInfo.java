package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class BokFileInfo extends BaseEntity {
    /**
     * 文件ID
     */
    private String fileId;

    /**
     * 文件类型
     */
    private String fileType;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件路径
     */
    private String filePath;

    /**
     * 取得 文件ID
     * @return 文件ID
     */
    public String getFileId() {
        return fileId;
    }

    /**
     * 设置 文件ID
     * @param fileId 文件ID
     */
    public void setFileId(String fileId) {
        this.fileId = fileId == null ? null : fileId.trim();
    }

    /**
     * 取得 文件类型
     * @return 文件类型
     */
    public String getFileType() {
        return fileType;
    }

    /**
     * 设置 文件类型
     * @param fileType 文件类型
     */
    public void setFileType(String fileType) {
        this.fileType = fileType == null ? null : fileType.trim();
    }

    /**
     * 取得 文件名称
     * @return 文件名称
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 设置 文件名称
     * @param fileName 文件名称
     */
    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    /**
     * 取得 文件路径
     * @return 文件路径
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * 设置 文件路径
     * @param filePath 文件路径
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }
}