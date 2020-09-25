package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class SysUploadFileInfo extends BaseEntity {
    /**
     * 流水号
     */
    private String seqNo;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 路径
     */
    private String path;

    /**
     * 业务代码
     */
    private String businessId;

    /**
     * 取得 流水号
     * @return 流水号
     */
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * 设置 流水号
     * @param seqNo 流水号
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo == null ? null : seqNo.trim();
    }

    /**
     * 取得 文件名
     * @return 文件名
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 设置 文件名
     * @param fileName 文件名
     */
    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    /**
     * 取得 路径
     * @return 路径
     */
    public String getPath() {
        return path;
    }

    /**
     * 设置 路径
     * @param path 路径
     */
    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    /**
     * 取得 业务代码
     * @return 业务代码
     */
    public String getBusinessId() {
        return businessId;
    }

    /**
     * 设置 业务代码
     * @param businessId 业务代码
     */
    public void setBusinessId(String businessId) {
        this.businessId = businessId == null ? null : businessId.trim();
    }
}