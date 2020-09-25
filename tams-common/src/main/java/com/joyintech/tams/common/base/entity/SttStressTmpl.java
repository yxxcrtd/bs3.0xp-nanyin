package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class SttStressTmpl extends BaseEntity {
    /**
     * 模板代码
     */
    private String tmplId;

    /**
     * 模板名称
     */
    private String tmplName;

    /**
     * 启用状态
     */
    private String effStatus;

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
     * 取得 模板名称
     * @return 模板名称
     */
    public String getTmplName() {
        return tmplName;
    }

    /**
     * 设置 模板名称
     * @param tmplName 模板名称
     */
    public void setTmplName(String tmplName) {
        this.tmplName = tmplName == null ? null : tmplName.trim();
    }

    /**
     * 取得 启用状态
     * @return 启用状态
     */
    public String getEffStatus() {
        return effStatus;
    }

    /**
     * 设置 启用状态
     * @param effStatus 启用状态
     */
    public void setEffStatus(String effStatus) {
        this.effStatus = effStatus == null ? null : effStatus.trim();
    }
}