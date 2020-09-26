package com.joyintech.tams.valuation.common.interf.tempTables.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class ValMngIntPlan extends BaseEntity {
    /**
     * 资产代码
     */
    private String assetId;

    /**
     * 现金流序号
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal orderNo;

    /**
     * 数据动作标志
     */
    private String dataActionSign;

    /**
     * 计息开始日
     */
    private Date vdate;

    /**
     * 计息结束日
     */
    private Date mdate;

    /**
     * 支付日
     */
    private Date payDate;

    /**
     * 是否支付利息
     */
    private String isPayInt;

    /**
     * 计息本金
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal intCap;

    /**
     * 利息金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal intAmt;

    /**
     * 取得 资产代码
     * @return 资产代码
     */
    public String getAssetId() {
        return assetId;
    }

    /**
     * 设置 资产代码
     * @param assetId 资产代码
     */
    public void setAssetId(String assetId) {
        this.assetId = assetId == null ? null : assetId.trim();
    }

    /**
     * 取得 现金流序号
     * @return 现金流序号
     */
    public BigDecimal getOrderNo() {
        return orderNo;
    }

    /**
     * 设置 现金流序号
     * @param orderNo 现金流序号
     */
    public void setOrderNo(BigDecimal orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * 取得 数据动作标志
     * @return 数据动作标志
     */
    public String getDataActionSign() {
        return dataActionSign;
    }

    /**
     * 设置 数据动作标志
     * @param dataActionSign 数据动作标志
     */
    public void setDataActionSign(String dataActionSign) {
        this.dataActionSign = dataActionSign == null ? null : dataActionSign.trim();
    }

    /**
     * 取得 计息开始日
     * @return 计息开始日
     */
    public Date getVdate() {
        return vdate;
    }

    /**
     * 设置 计息开始日
     * @param vdate 计息开始日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    /**
     * 取得 计息结束日
     * @return 计息结束日
     */
    public Date getMdate() {
        return mdate;
    }

    /**
     * 设置 计息结束日
     * @param mdate 计息结束日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * 取得 支付日
     * @return 支付日
     */
    public Date getPayDate() {
        return payDate;
    }

    /**
     * 设置 支付日
     * @param payDate 支付日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    /**
     * 取得 是否支付利息
     * @return 是否支付利息
     */
    public String getIsPayInt() {
        return isPayInt;
    }

    /**
     * 设置 是否支付利息
     * @param isPayInt 是否支付利息
     */
    public void setIsPayInt(String isPayInt) {
        this.isPayInt = isPayInt == null ? null : isPayInt.trim();
    }

    /**
     * 取得 计息本金
     * @return 计息本金
     */
    public BigDecimal getIntCap() {
        return intCap;
    }

    /**
     * 设置 计息本金
     * @param intCap 计息本金
     */
    public void setIntCap(BigDecimal intCap) {
        this.intCap = intCap;
    }

    /**
     * 取得 利息金额
     * @return 利息金额
     */
    public BigDecimal getIntAmt() {
        return intAmt;
    }

    /**
     * 设置 利息金额
     * @param intAmt 利息金额
     */
    public void setIntAmt(BigDecimal intAmt) {
        this.intAmt = intAmt;
    }
}