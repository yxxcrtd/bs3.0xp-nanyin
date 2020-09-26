package com.joyintech.tams.svreport.interf.dis.repfeetrdinterf.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class DisRepFeeTrd extends BaseEntity {
    
    /**
     * 现金流代码
     */
    private String cashId;

    /**
     * 交易编号
     */
    private String busiId;

    /**
     * 费用代码
     */
    private String feeId;

    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 实付费用
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal paidFee;

    /**
     * 支付日
     */
    private Date payDate;

    /**
     * 导入方法
     */
    private String inputType;

    /**
     * 导入用户
     */
    private String inputUser;

    /**
     * 适配器ID

     */
    private String adapterId;

    /**
     * 费用二级类型
     */
    private String cashType2;

    /**
     * 取得 交易编号
     * @return 交易编号
     */
    public String getBusiId() {
        return busiId;
    }

    /**
     * 设置 交易编号
     * @param busiId 交易编号
     */
    public void setBusiId(String busiId) {
        this.busiId = busiId == null ? null : busiId.trim();
    }

    /**
     * 取得 费用代码
     * @return 费用代码
     */
    public String getFeeId() {
        return feeId;
    }

    /**
     * 设置 费用代码
     * @param feeId 费用代码
     */
    public void setFeeId(String feeId) {
        this.feeId = feeId == null ? null : feeId.trim();
    }

    /**
     * 取得 产品代码
     * @return 产品代码
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 产品代码
     * @param prodId 产品代码
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * 取得 实付费用
     * @return 实付费用
     */
    public BigDecimal getPaidFee() {
        return paidFee;
    }

    /**
     * 设置 实付费用
     * @param paidFee 实付费用
     */
    public void setPaidFee(BigDecimal paidFee) {
        this.paidFee = paidFee;
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
     * 取得 导入方法
     * @return 导入方法
     */
    public String getInputType() {
        return inputType;
    }

    /**
     * 设置 导入方法
     * @param inputType 导入方法
     */
    public void setInputType(String inputType) {
        this.inputType = inputType == null ? null : inputType.trim();
    }

    /**
     * 取得 导入用户
     * @return 导入用户
     */
    public String getInputUser() {
        return inputUser;
    }

    /**
     * 设置 导入用户
     * @param inputUser 导入用户
     */
    public void setInputUser(String inputUser) {
        this.inputUser = inputUser == null ? null : inputUser.trim();
    }

    /**
     * 取得 适配器ID

     * @return 适配器ID

     */
    public String getAdapterId() {
        return adapterId;
    }

    /**
     * 设置 适配器ID

     * @param adapterId 适配器ID

     */
    public void setAdapterId(String adapterId) {
        this.adapterId = adapterId == null ? null : adapterId.trim();
    }

    /**
     * 取得 费用二级类型
     * @return 费用二级类型
     */
    public String getCashType2() {
        return cashType2;
    }

    /**
     * 设置 费用二级类型
     * @param cashType2 费用二级类型
     */
    public void setCashType2(String cashType2) {
        this.cashType2 = cashType2 == null ? null : cashType2.trim();
    }

    
    /**
     * 获取 cashId
     * @return cashId.
     */
    public String getCashId() {
        return cashId;
    }

    
    /**
     * 设置 cashId
     * @param cashId cashId
     */
    public void setCashId(String cashId) {
        this.cashId = cashId;
    }
    
    
}