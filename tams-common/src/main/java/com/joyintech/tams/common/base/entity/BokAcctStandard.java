package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class BokAcctStandard extends BaseEntity {
    /**
     * 会计准则代码
     */
    private String acctStandardId;

    /**
     * 会计准则名称
     */
    private String acctStandardName;

    /**
     * 取得 会计准则代码
     * @return 会计准则代码
     */
    public String getAcctStandardId() {
        return acctStandardId;
    }

    /**
     * 设置 会计准则代码
     * @param acctStandardId 会计准则代码
     */
    public void setAcctStandardId(String acctStandardId) {
        this.acctStandardId = acctStandardId == null ? null : acctStandardId.trim();
    }

    /**
     * 取得 会计准则名称
     * @return 会计准则名称
     */
    public String getAcctStandardName() {
        return acctStandardName;
    }

    /**
     * 设置 会计准则名称
     * @param acctStandardName 会计准则名称
     */
    public void setAcctStandardName(String acctStandardName) {
        this.acctStandardName = acctStandardName == null ? null : acctStandardName.trim();
    }
}