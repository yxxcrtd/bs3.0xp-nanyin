package com.joyintech.service.asset.bond.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;
import java.util.Objects;

public final class FinCashPlanCheck {

    /**
     * 调整后的计算结束日
     */
    private Date mdate;

    /**
     * 调整后的计划支付日
     */
    private Date payDate;

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

    /**
     * 取得 调整后的计划支付日
     * @return 调整后的计划支付日
     */
    public Date getPayDate() {
        return payDate;
    }

    /**
     * 设置 调整后的计划支付日
     * @param payDate 调整后的计划支付日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FinCashPlanCheck that = (FinCashPlanCheck) o;
        return Objects.equals(mdate,that.mdate) && Objects.equals(payDate,that.payDate);

    }


    @Override
    public int hashCode(){
        return Objects.hash(mdate,payDate);
    }

}