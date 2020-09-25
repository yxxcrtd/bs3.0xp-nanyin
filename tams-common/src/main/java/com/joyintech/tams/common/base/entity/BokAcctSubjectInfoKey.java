package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class BokAcctSubjectInfoKey extends BaseEntity {
    /**
     * 核算主体代码
     */
    private String acctSubjectId;

    /**
     * 核算主体类别，理财产品，投资组合、部门、银行等
     */
    private String acctSubjectType;

    /**
     * 取得 核算主体代码
     * @return 核算主体代码
     */
    public String getAcctSubjectId() {
        return acctSubjectId;
    }

    /**
     * 设置 核算主体代码
     * @param acctSubjectId 核算主体代码
     */
    public void setAcctSubjectId(String acctSubjectId) {
        this.acctSubjectId = acctSubjectId == null ? null : acctSubjectId.trim();
    }

    /**
     * 取得 核算主体类别，理财产品，投资组合、部门、银行等
     * @return 核算主体类别，理财产品，投资组合、部门、银行等
     */
    public String getAcctSubjectType() {
        return acctSubjectType;
    }

    /**
     * 设置 核算主体类别，理财产品，投资组合、部门、银行等
     * @param acctSubjectType 核算主体类别，理财产品，投资组合、部门、银行等
     */
    public void setAcctSubjectType(String acctSubjectType) {
        this.acctSubjectType = acctSubjectType == null ? null : acctSubjectType.trim();
    }
}