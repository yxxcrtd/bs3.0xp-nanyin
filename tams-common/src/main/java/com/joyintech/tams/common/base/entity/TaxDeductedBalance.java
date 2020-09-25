package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class TaxDeductedBalance extends BaseEntity {
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
     * 账务明细流水
     */
    private String bokSeqno;

    /**
     * 账务明细代码
     */
    private String bokDetailId;

    /**
     * 发生日期
     */
    private Date happenDate;

    /**
     * 入账日期
     */
    private Date bookDate;

    /**
     * 凭证编号
     */
    private String bookId;

    /**
     * 处理序列
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bookOrder;

    /**
     * 币种
     */
    private String oCcy;

    /**
     * 待抵扣资产份额发生额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal cbAmt;

    /**
     * 待抵扣资产份额余额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal cbBalance;

    /**
     * 待抵扣增值额发生额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal taxAmt;

    /**
     * 待抵扣增值税余额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal balAmt;

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
     * 取得 账务明细流水
     * @return 账务明细流水
     */
    public String getBokSeqno() {
        return bokSeqno;
    }

    /**
     * 设置 账务明细流水
     * @param bokSeqno 账务明细流水
     */
    public void setBokSeqno(String bokSeqno) {
        this.bokSeqno = bokSeqno == null ? null : bokSeqno.trim();
    }

    /**
     * 取得 账务明细代码
     * @return 账务明细代码
     */
    public String getBokDetailId() {
        return bokDetailId;
    }

    /**
     * 设置 账务明细代码
     * @param bokDetailId 账务明细代码
     */
    public void setBokDetailId(String bokDetailId) {
        this.bokDetailId = bokDetailId == null ? null : bokDetailId.trim();
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
     * 取得 凭证编号
     * @return 凭证编号
     */
    public String getBookId() {
        return bookId;
    }

    /**
     * 设置 凭证编号
     * @param bookId 凭证编号
     */
    public void setBookId(String bookId) {
        this.bookId = bookId == null ? null : bookId.trim();
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
     * 取得 待抵扣资产份额发生额
     * @return 待抵扣资产份额发生额
     */
    public BigDecimal getCbAmt() {
        return cbAmt;
    }

    /**
     * 设置 待抵扣资产份额发生额
     * @param cbAmt 待抵扣资产份额发生额
     */
    public void setCbAmt(BigDecimal cbAmt) {
        this.cbAmt = cbAmt;
    }

    /**
     * 取得 待抵扣资产份额余额
     * @return 待抵扣资产份额余额
     */
    public BigDecimal getCbBalance() {
        return cbBalance;
    }

    /**
     * 设置 待抵扣资产份额余额
     * @param cbBalance 待抵扣资产份额余额
     */
    public void setCbBalance(BigDecimal cbBalance) {
        this.cbBalance = cbBalance;
    }

    /**
     * 取得 待抵扣增值额发生额
     * @return 待抵扣增值额发生额
     */
    public BigDecimal getTaxAmt() {
        return taxAmt;
    }

    /**
     * 设置 待抵扣增值额发生额
     * @param taxAmt 待抵扣增值额发生额
     */
    public void setTaxAmt(BigDecimal taxAmt) {
        this.taxAmt = taxAmt;
    }

    /**
     * 取得 待抵扣增值税余额
     * @return 待抵扣增值税余额
     */
    public BigDecimal getBalAmt() {
        return balAmt;
    }

    /**
     * 设置 待抵扣增值税余额
     * @param balAmt 待抵扣增值税余额
     */
    public void setBalAmt(BigDecimal balAmt) {
        this.balAmt = balAmt;
    }
}