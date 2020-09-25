package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class TaxTurnedBalance extends BaseEntity {
    /**
     * 流水号
     */
    private String seqNo;

    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 账务明细代码
     */
    private String bokDetailId;

    /**
     * 币种
     */
    private String oCcy;

    /**
     * 待抵扣资产份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal cbAmt;

    /**
     * 待抵扣增值额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal incomeAmt;

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
     * 取得 待抵扣资产份额
     * @return 待抵扣资产份额
     */
    public BigDecimal getCbAmt() {
        return cbAmt;
    }

    /**
     * 设置 待抵扣资产份额
     * @param cbAmt 待抵扣资产份额
     */
    public void setCbAmt(BigDecimal cbAmt) {
        this.cbAmt = cbAmt;
    }

    /**
     * 取得 待抵扣增值额
     * @return 待抵扣增值额
     */
    public BigDecimal getIncomeAmt() {
        return incomeAmt;
    }

    /**
     * 设置 待抵扣增值额
     * @param incomeAmt 待抵扣增值额
     */
    public void setIncomeAmt(BigDecimal incomeAmt) {
        this.incomeAmt = incomeAmt;
    }
}