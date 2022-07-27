package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

public class RepFloatFee extends BaseEntity {

    /**
     * 子产品代码：内部销售代码
     */
    private String prodId;
    /**
     * 产品主代码
     */
    private String pProdId;
    /**
     * 确认日
     */
    private Date affirmDate;
    /**
     * 业务代码：只需要传业务代码为“03赎回”的数据
     */
    private String businessCode;
    /**
     * 确认份额：保留2位小数
     */
    private BigDecimal affirmNum;
    /**
     * 确认金额：保留2位小数
     */
    private BigDecimal affirmAmt;
    /**
     * 超额报酬：保留2位小数
     */
    private BigDecimal aboveAmt;
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

    public String getpProdId() {
        return pProdId;
    }

    public void setpProdId(String pProdId) {
        this.pProdId = pProdId;
    }

    public Date getAffirmDate() {
        return affirmDate;
    }

    public void setAffirmDate(Date affirmDate) {
        this.affirmDate = affirmDate;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public BigDecimal getAffirmNum() {
        return affirmNum;
    }

    public void setAffirmNum(BigDecimal affirmNum) {
        this.affirmNum = affirmNum;
    }

    public BigDecimal getAffirmAmt() {
        return affirmAmt;
    }

    public void setAffirmAmt(BigDecimal affirmAmt) {
        this.affirmAmt = affirmAmt;
    }

    public BigDecimal getAboveAmt() {
        return aboveAmt;
    }

    public void setAboveAmt(BigDecimal aboveAmt) {
        this.aboveAmt = aboveAmt;
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
