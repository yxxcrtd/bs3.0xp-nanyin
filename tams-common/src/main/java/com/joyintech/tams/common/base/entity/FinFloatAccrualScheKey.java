package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class FinFloatAccrualScheKey extends BaseEntity {
    /**
     * 现金流代码
     */
    private String cashId;

    /**
     * 计提日
     */
    private Date accrDate;

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
     * 取得 计提日
     * @return 计提日
     */
    public Date getAccrDate() {
        return accrDate;
    }

    /**
     * 设置 计提日
     * @param accrDate 计提日
     */
    public void setAccrDate(Date accrDate) {
        this.accrDate = accrDate;
    }
}