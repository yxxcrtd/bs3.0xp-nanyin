package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class FinCashAccrscheKey extends BaseEntity {
    /**
     * 金融产品代码
     */
    private String cashId;

    /**
     * 计提日
     */
    private Date accrDate;

    /**
     * 取得 金融产品代码
     * @return 金融产品代码
     */
    public String getCashId() {
        return cashId;
    }

    /**
     * 设置 金融产品代码
     * @param cashId 金融产品代码
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