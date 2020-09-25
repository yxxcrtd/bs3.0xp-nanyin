package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class FinCashResetPlanKey extends BaseEntity {
    /**
     * 现金流代码
     */
    private String cashId;

    /**
     * 调整后重置日
     */
    private Date resetDate;

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
     * 取得 调整后重置日
     * @return 调整后重置日
     */
    public Date getResetDate() {
        return resetDate;
    }

    /**
     * 设置 调整后重置日
     * @param resetDate 调整后重置日
     */
    public void setResetDate(Date resetDate) {
        this.resetDate = resetDate;
    }
}