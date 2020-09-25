package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class FinRateInfoStructureKey extends BaseEntity {
    /**
     * 现金流代码
     */
    private String cashId;

    /**
     * 生效日
     */
    private Date effDate;

    /**
     * 取得 现金流代码
     * @return 现金流代码
     */
    public String getCashId() {
        return cashId;
    }

    /**
     * 设置 现金流代码
     * @param cashId 现金流代码
     */
    public void setCashId(String cashId) {
        this.cashId = cashId == null ? null : cashId.trim();
    }

    /**
     * 取得 生效日
     * @return 生效日
     */
    public Date getEffDate() {
        return effDate;
    }

    /**
     * 设置 生效日
     * @param effDate 生效日
     */
    public void setEffDate(Date effDate) {
        this.effDate = effDate;
    }
}