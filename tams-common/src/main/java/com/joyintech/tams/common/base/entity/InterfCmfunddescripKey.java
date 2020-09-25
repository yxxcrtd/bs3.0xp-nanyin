package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class InterfCmfunddescripKey extends BaseEntity {
    /**
     * 基金代码
     */
    private String fInfoCode;

    /**
     * 数据来源
     */
    private String source;

    /**
     * 取得 基金代码
     * @return 基金代码
     */
    public String getfInfoCode() {
        return fInfoCode;
    }

    /**
     * 设置 基金代码
     * @param fInfoCode 基金代码
     */
    public void setfInfoCode(String fInfoCode) {
        this.fInfoCode = fInfoCode == null ? null : fInfoCode.trim();
    }

    /**
     * 取得 数据来源
     * @return 数据来源
     */
    public String getSource() {
        return source;
    }

    /**
     * 设置 数据来源
     * @param source 数据来源
     */
    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }
}