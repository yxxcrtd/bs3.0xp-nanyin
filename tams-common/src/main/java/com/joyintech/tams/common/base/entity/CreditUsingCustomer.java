package com.joyintech.tams.common.base.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;

public class CreditUsingCustomer extends BaseEntity {
    /**
     * 流水号
     */
    private String seqno;

    /**
     * 客户代码
     */
    private String customerId;

    /**
     * 占用日期
     */
    private Date cdate;

    /**
     * 占用前剩余额度
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal lastAmount;

    /**
     * 本次占用额度
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal amount;

    /**
     * 占用后剩余额度
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal newAmount;

    /**
     * 交易编号
     */
    private String tradeId;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 分支序号
     */
    private Long branch;

    /**
     * 是否穿透
     */
    private String isBottom;

    /**
     * 授信额度代码
     */
    private String creditLinesId;

    /**
     * 占用类型
     */
    private String creditOccureType;

    /**
     * 委托代码
     */
    private String entrustId;

    /**
     * 原交易编号
     */
    private String oriTradeId;

    /**
     * 本次占用额度叠加后
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal amountAdd;

    /**
     * 取得 流水号
     * @return 流水号
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 设置 流水号
     * @param seqno 流水号
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    /**
     * 取得 客户代码
     * @return 客户代码
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * 设置 客户代码
     * @param customerId 客户代码
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    /**
     * 取得 占用日期
     * @return 占用日期
     */
    public Date getCdate() {
        return cdate;
    }

    /**
     * 设置 占用日期
     * @param cdate 占用日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }

    /**
     * 取得 占用前剩余额度
     * @return 占用前剩余额度
     */
    public BigDecimal getLastAmount() {
        return lastAmount;
    }

    /**
     * 设置 占用前剩余额度
     * @param lastAmount 占用前剩余额度
     */
    public void setLastAmount(BigDecimal lastAmount) {
        this.lastAmount = lastAmount;
    }

    /**
     * 取得 本次占用额度
     * @return 本次占用额度
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 设置 本次占用额度
     * @param amount 本次占用额度
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 取得 占用后剩余额度
     * @return 占用后剩余额度
     */
    public BigDecimal getNewAmount() {
        return newAmount;
    }

    /**
     * 设置 占用后剩余额度
     * @param newAmount 占用后剩余额度
     */
    public void setNewAmount(BigDecimal newAmount) {
        this.newAmount = newAmount;
    }

    /**
     * 取得 交易编号
     * @return 交易编号
     */
    public String getTradeId() {
        return tradeId;
    }

    /**
     * 设置 交易编号
     * @param tradeId 交易编号
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId == null ? null : tradeId.trim();
    }

    /**
     * 取得 金融产品代码
     * @return 金融产品代码
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 金融产品代码
     * @param finprodId 金融产品代码
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId == null ? null : finprodId.trim();
    }

    /**
     * 取得 分支序号
     * @return 分支序号
     */
    public Long getBranch() {
        return branch;
    }

    /**
     * 设置 分支序号
     * @param branch 分支序号
     */
    public void setBranch(Long branch) {
        this.branch = branch;
    }

    /**
     * 取得 是否穿透
     * @return 是否穿透
     */
    public String getIsBottom() {
        return isBottom;
    }

    /**
     * 设置 是否穿透
     * @param isBottom 是否穿透
     */
    public void setIsBottom(String isBottom) {
        this.isBottom = isBottom == null ? null : isBottom.trim();
    }

    /**
     * 取得 授信额度代码
     * @return 授信额度代码
     */
    public String getCreditLinesId() {
        return creditLinesId;
    }

    /**
     * 设置 授信额度代码
     * @param creditLinesId 授信额度代码
     */
    public void setCreditLinesId(String creditLinesId) {
        this.creditLinesId = creditLinesId == null ? null : creditLinesId.trim();
    }

    /**
     * 取得 占用类型
     * @return 占用类型
     */
    public String getCreditOccureType() {
        return creditOccureType;
    }

    /**
     * 设置 占用类型
     * @param creditOccureType 占用类型
     */
    public void setCreditOccureType(String creditOccureType) {
        this.creditOccureType = creditOccureType == null ? null : creditOccureType.trim();
    }

    /**
     * 取得 委托代码
     * @return 委托代码
     */
    public String getEntrustId() {
        return entrustId;
    }

    /**
     * 设置 委托代码
     * @param entrustId 委托代码
     */
    public void setEntrustId(String entrustId) {
        this.entrustId = entrustId == null ? null : entrustId.trim();
    }

    /**
     * 取得 原交易编号
     * @return 原交易编号
     */
    public String getOriTradeId() {
        return oriTradeId;
    }

    /**
     * 设置 原交易编号
     * @param oriTradeId 原交易编号
     */
    public void setOriTradeId(String oriTradeId) {
        this.oriTradeId = oriTradeId == null ? null : oriTradeId.trim();
    }

    /**
     * 取得 本次占用额度叠加后
     * @return 本次占用额度叠加后
     */
    public BigDecimal getAmountAdd() {
        return amountAdd;
    }

    /**
     * 设置 本次占用额度叠加后
     * @param amountAdd 本次占用额度叠加后
     */
    public void setAmountAdd(BigDecimal amountAdd) {
        this.amountAdd = amountAdd;
    }

    /**
     * 顶层金融产品代码
     */
    private String pFinprodId;

    public String getpFinprodId() {
        return pFinprodId;
    }

    public void setpFinprodId(String pFinprodId) {
        this.pFinprodId = pFinprodId;
    }
}