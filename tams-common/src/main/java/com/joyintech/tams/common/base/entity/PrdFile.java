package com.joyintech.tams.common.base.entity;

public class PrdFile extends PrdFileKey {
    /**
     * 文件模板类型
     */
    private String fileType;

    /**
     * 备注
     */
    private String remark;

    /**
     * 取得 文件模板类型
     * @return 文件模板类型
     */
    public String getFileType() {
        return fileType;
    }

    /**
     * 设置 文件模板类型
     * @param fileType 文件模板类型
     */
    public void setFileType(String fileType) {
        this.fileType = fileType == null ? null : fileType.trim();
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}