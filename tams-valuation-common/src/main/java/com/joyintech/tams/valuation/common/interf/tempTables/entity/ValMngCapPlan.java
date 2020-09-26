package com.joyintech.tams.valuation.common.interf.tempTables.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class ValMngCapPlan extends BaseEntity {
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
     * 还本日
     */
    private Date repayDate;

    /**
     * 支付日
     */
    private Date payDate;

    /**
     * 还本金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal repayAmt;

    /**
     * 利息金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal intAmt;

    /**
     * 是否还本带息
     */
    private String isInt;

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
     * 取得 还本日
     * @return 还本日
     */
    public Date getRepayDate() {
        return repayDate;
    }

    /**
     * 设置 还本日
     * @param repayDate 还本日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRepayDate(Date repayDate) {
        this.repayDate = repayDate;
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
     * 取得 还本金额
     * @return 还本金额
     */
    public BigDecimal getRepayAmt() {
        return repayAmt;
    }

    /**
     * 设置 还本金额
     * @param repayAmt 还本金额
     */
    public void setRepayAmt(BigDecimal repayAmt) {
        this.repayAmt = repayAmt;
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

    /**
     * 取得 是否还本带息
     * @return 是否还本带息
     */
    public String getIsInt() {
        return isInt;
    }

    /**
     * 设置 是否还本带息
     * @param isInt 是否还本带息
     */
    public void setIsInt(String isInt) {
        this.isInt = isInt == null ? null : isInt.trim();
    }
}