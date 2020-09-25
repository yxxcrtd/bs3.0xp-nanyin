package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class TaxBokBalanceSum extends BaseEntity {
    /**
     * 流水号
     */
    private String seqNo;

    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 账套日期
     */
    private Date booksetDate;

    /**
     * 发生日期
     */
    private Date happenDate;

    /**
     * 入账日期
     */
    private Date bookDate;

    /**
     * 币种
     */
    private String oCcy;

    /**
     * 增值税类型
     */
    private String vatType;

    /**
     * 确认标识
     */
    private String taxPType;

    /**
     * 计税收入余额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal incomeAmtAll;

    /**
     * 当日发生额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal happenAmt;

    /**
     * 计税发生额汇总
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal taxAmtSum;

    /**
     * 增值税金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal taxAmt;

    /**
     * 处理序列
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bookOrder;

    /**
     * 取得 流水号
     * @return 流水号
     */
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * 设置 流水号
     * @param seqNo 流水号
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo == null ? null : seqNo.trim();
    }

    /**
     * 取得 账套代码
     * @return 账套代码
     */
    public String getBooksetId() {
        return booksetId;
    }

    /**
     * 设置 账套代码
     * @param booksetId 账套代码
     */
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId == null ? null : booksetId.trim();
    }

    /**
     * 取得 账套日期
     * @return 账套日期
     */
    public Date getBooksetDate() {
        return booksetDate;
    }

    /**
     * 设置 账套日期
     * @param booksetDate 账套日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setBooksetDate(Date booksetDate) {
        this.booksetDate = booksetDate;
    }

    /**
     * 取得 发生日期
     * @return 发生日期
     */
    public Date getHappenDate() {
        return happenDate;
    }

    /**
     * 设置 发生日期
     * @param happenDate 发生日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setHappenDate(Date happenDate) {
        this.happenDate = happenDate;
    }

    /**
     * 取得 入账日期
     * @return 入账日期
     */
    public Date getBookDate() {
        return bookDate;
    }

    /**
     * 设置 入账日期
     * @param bookDate 入账日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
    }

    /**
     * 取得 币种
     * @return 币种
     */
    public String getoCcy() {
        return oCcy;
    }

    /**
     * 设置 币种
     * @param oCcy 币种
     */
    public void setoCcy(String oCcy) {
        this.oCcy = oCcy == null ? null : oCcy.trim();
    }

    /**
     * 取得 增值税类型
     * @return 增值税类型
     */
    public String getVatType() {
        return vatType;
    }

    /**
     * 设置 增值税类型
     * @param vatType 增值税类型
     */
    public void setVatType(String vatType) {
        this.vatType = vatType == null ? null : vatType.trim();
    }

    /**
     * 取得 确认标识
     * @return 确认标识
     */
    public String getTaxPType() {
        return taxPType;
    }

    /**
     * 设置 确认标识
     * @param taxPType 确认标识
     */
    public void setTaxPType(String taxPType) {
        this.taxPType = taxPType == null ? null : taxPType.trim();
    }

    /**
     * 取得 计税收入余额
     * @return 计税收入余额
     */
    public BigDecimal getIncomeAmtAll() {
        return incomeAmtAll;
    }

    /**
     * 设置 计税收入余额
     * @param incomeAmtAll 计税收入余额
     */
    public void setIncomeAmtAll(BigDecimal incomeAmtAll) {
        this.incomeAmtAll = incomeAmtAll;
    }

    /**
     * 取得 当日发生额
     * @return 当日发生额
     */
    public BigDecimal getHappenAmt() {
        return happenAmt;
    }

    /**
     * 设置 当日发生额
     * @param happenAmt 当日发生额
     */
    public void setHappenAmt(BigDecimal happenAmt) {
        this.happenAmt = happenAmt;
    }

    /**
     * 取得 计税发生额汇总
     * @return 计税发生额汇总
     */
    public BigDecimal getTaxAmtSum() {
        return taxAmtSum;
    }

    /**
     * 设置 计税发生额汇总
     * @param taxAmtSum 计税发生额汇总
     */
    public void setTaxAmtSum(BigDecimal taxAmtSum) {
        this.taxAmtSum = taxAmtSum;
    }

    /**
     * 取得 增值税金额
     * @return 增值税金额
     */
    public BigDecimal getTaxAmt() {
        return taxAmt;
    }

    /**
     * 设置 增值税金额
     * @param taxAmt 增值税金额
     */
    public void setTaxAmt(BigDecimal taxAmt) {
        this.taxAmt = taxAmt;
    }

    /**
     * 取得 处理序列
     * @return 处理序列
     */
    public BigDecimal getBookOrder() {
        return bookOrder;
    }

    /**
     * 设置 处理序列
     * @param bookOrder 处理序列
     */
    public void setBookOrder(BigDecimal bookOrder) {
        this.bookOrder = bookOrder;
    }
}