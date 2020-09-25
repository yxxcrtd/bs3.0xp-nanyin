package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class ChbEquHoldImpTmp extends BaseEntity {
    /**
     * null
     */
    private String indentifyMark;

    /**
     * null
     */
    private String orgIndentifyMark;

    /**
     * null
     */
    private String registBankCode;

    /**
     * null
     */
    private String type;

    /**
     * 取得 null
     * @return null
     */
    public String getIndentifyMark() {
        return indentifyMark;
    }

    /**
     * 设置 null
     * @param indentifyMark null
     */
    public void setIndentifyMark(String indentifyMark) {
        this.indentifyMark = indentifyMark == null ? null : indentifyMark.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getOrgIndentifyMark() {
        return orgIndentifyMark;
    }

    /**
     * 设置 null
     * @param orgIndentifyMark null
     */
    public void setOrgIndentifyMark(String orgIndentifyMark) {
        this.orgIndentifyMark = orgIndentifyMark == null ? null : orgIndentifyMark.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getRegistBankCode() {
        return registBankCode;
    }

    /**
     * 设置 null
     * @param registBankCode null
     */
    public void setRegistBankCode(String registBankCode) {
        this.registBankCode = registBankCode == null ? null : registBankCode.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getType() {
        return type;
    }

    /**
     * 设置 null
     * @param type null
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
}