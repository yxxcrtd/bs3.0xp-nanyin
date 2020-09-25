package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class FinCashPlanKey extends BaseEntity {
    /**
     * 现金流代码
     */
    private String cashId;

    /**
     * 调整后的计算结束日
     */
    private Date mdate;

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
     * 取得 调整后的计算结束日
     * @return 调整后的计算结束日
     */
    public Date getMdate() {
        return mdate;
    }

    /**
     * 设置 调整后的计算结束日
     * @param mdate 调整后的计算结束日
     */
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }
}