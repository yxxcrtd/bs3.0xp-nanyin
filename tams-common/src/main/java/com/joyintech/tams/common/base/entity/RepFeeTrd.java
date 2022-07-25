package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


public class RepFeeTrd extends BaseEntity {
    /**
     * 产品主代码
     */
    private String prodId;
    /**
     * 支付日
     */
    private Date payDate;
    /**
     * 费用类型：01 托管费/02 管理费（固定）/03 管理费（超额）/04 销售费/05 申购费/06 赎回费/07 客户收益
     */
    private String feeType;
    /**
     * 费用金额
     */
    private BigDecimal paidFee;
    /**
     * 自成立以来发生额（贷方）
     */
    private BigDecimal accrual;

    /**
     * 数据状态
     */
    private String status;
    /**
     * 错误信息
     */
    private String errorMsg;

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public BigDecimal getPaidFee() {
        return paidFee;
    }

    public void setPaidFee(BigDecimal paidFee) {
        this.paidFee = paidFee;
    }

    public BigDecimal getAccrual() {
        return accrual;
    }

    public void setAccrual(BigDecimal accrual) {
        this.accrual = accrual;
    }


    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}

