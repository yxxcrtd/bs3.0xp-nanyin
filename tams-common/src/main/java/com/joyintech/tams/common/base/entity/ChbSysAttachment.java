package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ChbSysAttachment extends BaseEntity {
    /**
     * l流水号
     */
    private String seqno;

    /**
     * 文件号
     */
    private String fileNo;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件路径
     */
    private String filePath;

    /**
     * 工作流代码
     */
    private String workflowId;

    /**
     * 部门代码
     */
    private String deptCode;

    /**
     * 取得 l流水号
     * @return l流水号
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 设置 l流水号
     * @param seqno l流水号
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    /**
     * 取得 文件号
     * @return 文件号
     */
    public String getFileNo() {
        return fileNo;
    }

    /**
     * 设置 文件号
     * @param fileNo 文件号
     */
    public void setFileNo(String fileNo) {
        this.fileNo = fileNo == null ? null : fileNo.trim();
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

    /**
     * 取得 工作流代码
     * @return 工作流代码
     */
    public String getWorkflowId() {
        return workflowId;
    }

    /**
     * 设置 工作流代码
     * @param workflowId 工作流代码
     */
    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId == null ? null : workflowId.trim();
    }

    /**
     * 取得 部门代码
     * @return 部门代码
     */
    public String getDeptCode() {
        return deptCode;
    }

    /**
     * 设置 部门代码
     * @param deptCode 部门代码
     */
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode == null ? null : deptCode.trim();
    }
}