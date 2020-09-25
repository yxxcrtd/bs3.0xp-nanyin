package com.joyintech.tams.common.base.entity;

public class BokValbalTableFile extends BokValbalTableFileKey {
    /**
     * 文件类型
     */
    private String fileType;

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
}