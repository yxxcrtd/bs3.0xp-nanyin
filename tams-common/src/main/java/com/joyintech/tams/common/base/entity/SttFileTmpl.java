package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class SttFileTmpl extends BaseEntity {
    /**
     * 模板代码
     */
    private String tmplId;

    /**
     * 文件ID，文件上传表的主键
     */
    private String fileId;

    /**
     * 取得 模板代码
     * @return 模板代码
     */
    public String getTmplId() {
        return tmplId;
    }

    /**
     * 设置 模板代码
     * @param tmplId 模板代码
     */
    public void setTmplId(String tmplId) {
        this.tmplId = tmplId == null ? null : tmplId.trim();
    }

    /**
     * 取得 文件ID，文件上传表的主键
     * @return 文件ID，文件上传表的主键
     */
    public String getFileId() {
        return fileId;
    }

    /**
     * 设置 文件ID，文件上传表的主键
     * @param fileId 文件ID，文件上传表的主键
     */
    public void setFileId(String fileId) {
        this.fileId = fileId == null ? null : fileId.trim();
    }
}