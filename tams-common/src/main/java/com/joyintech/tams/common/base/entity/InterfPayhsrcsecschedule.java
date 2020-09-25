package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class InterfPayhsrcsecschedule extends InterfPayhsrcsecscheduleKey {
    /**
     * 理论付息起始日
     */
    private Date totalintereststartday;

    /**
     * 理论付息结束日
     */
    private Date totalinterestendday;

    /**
     * 理论支付日
     */
    private Date interestpayday;

    /**
     * 当期利率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal couponrate;

    /**
     * 应计利息和
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal accrueintamt;

    /**
     * 日应计利息
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal dailyaccrueint;

    /**
     * 支付本金
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal prinamtpay;

    /**
     * 支付现金流，即支付的本金加利息
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal cashpay;

    /**
     * 计息本金
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal prinamt;

    /**
     * 取得 理论付息起始日
     * @return 理论付息起始日
     */
    public Date getTotalintereststartday() {
        return totalintereststartday;
    }

    /**
     * 设置 理论付息起始日
     * @param totalintereststartday 理论付息起始日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setTotalintereststartday(Date totalintereststartday) {
        this.totalintereststartday = totalintereststartday;
    }

    /**
     * 取得 理论付息结束日
     * @return 理论付息结束日
     */
    public Date getTotalinterestendday() {
        return totalinterestendday;
    }

    /**
     * 设置 理论付息结束日
     * @param totalinterestendday 理论付息结束日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setTotalinterestendday(Date totalinterestendday) {
        this.totalinterestendday = totalinterestendday;
    }

    /**
     * 取得 理论支付日
     * @return 理论支付日
     */
    public Date getInterestpayday() {
        return interestpayday;
    }

    /**
     * 设置 理论支付日
     * @param interestpayday 理论支付日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setInterestpayday(Date interestpayday) {
        this.interestpayday = interestpayday;
    }

    /**
     * 取得 当期利率
     * @return 当期利率
     */
    public BigDecimal getCouponrate() {
        return couponrate;
    }

    /**
     * 设置 当期利率
     * @param couponrate 当期利率
     */
    public void setCouponrate(BigDecimal couponrate) {
        this.couponrate = couponrate;
    }

    /**
     * 取得 应计利息和
     * @return 应计利息和
     */
    public BigDecimal getAccrueintamt() {
        return accrueintamt;
    }

    /**
     * 设置 应计利息和
     * @param accrueintamt 应计利息和
     */
    public void setAccrueintamt(BigDecimal accrueintamt) {
        this.accrueintamt = accrueintamt;
    }

    /**
     * 取得 日应计利息
     * @return 日应计利息
     */
    public BigDecimal getDailyaccrueint() {
        return dailyaccrueint;
    }

    /**
     * 设置 日应计利息
     * @param dailyaccrueint 日应计利息
     */
    public void setDailyaccrueint(BigDecimal dailyaccrueint) {
        this.dailyaccrueint = dailyaccrueint;
    }

    /**
     * 取得 支付本金
     * @return 支付本金
     */
    public BigDecimal getPrinamtpay() {
        return prinamtpay;
    }

    /**
     * 设置 支付本金
     * @param prinamtpay 支付本金
     */
    public void setPrinamtpay(BigDecimal prinamtpay) {
        this.prinamtpay = prinamtpay;
    }

    /**
     * 取得 支付现金流，即支付的本金加利息
     * @return 支付现金流，即支付的本金加利息
     */
    public BigDecimal getCashpay() {
        return cashpay;
    }

    /**
     * 设置 支付现金流，即支付的本金加利息
     * @param cashpay 支付现金流，即支付的本金加利息
     */
    public void setCashpay(BigDecimal cashpay) {
        this.cashpay = cashpay;
    }

    /**
     * 取得 计息本金
     * @return 计息本金
     */
    public BigDecimal getPrinamt() {
        return prinamt;
    }

    /**
     * 设置 计息本金
     * @param prinamt 计息本金
     */
    public void setPrinamt(BigDecimal prinamt) {
        this.prinamt = prinamt;
    }
}