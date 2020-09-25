package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ChbBatchLogFileInfo extends BaseEntity {
    /**
     * 文件id
     */
    private String fileId;

    /**
     * 文件路径
     */
    private String filePath;

    /**
     * 取得 文件id
     * @return 文件id
     */
    public String getFileId() {
        return fileId;
    }

    /**
     * 设置 文件id
     * @param fileId 文件id
     */
    public void setFileId(String fileId) {
        this.fileId = fileId == null ? null : fileId.trim();
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