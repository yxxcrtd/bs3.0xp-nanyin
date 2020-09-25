package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class SttStressTipsKey extends BaseEntity {
    /**
     * 模板代码
     */
    private String tmplId;

    /**
     * 参数代码
     */
    private String tipsCode;

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
     * 取得 参数代码
     * @return 参数代码
     */
    public String getTipsCode() {
        return tipsCode;
    }

    /**
     * 设置 参数代码
     * @param tipsCode 参数代码
     */
    public void setTipsCode(String tipsCode) {
        this.tipsCode = tipsCode == null ? null : tipsCode.trim();
    }
}