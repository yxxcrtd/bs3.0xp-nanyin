package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class ModCurveResultDetail extends BaseEntity {
    /**
     * 序号
     */
    private String seqno;

    /**
     * 结果表序号
     */
    private String resultSeqno;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 日期
     */
    private Date cdate;

    /**
     * 现金流日期
     */
    private Date cashDate;

    /**
     * 期限（年化）
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal period;

    /**
     * 现金流金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal cashAmt;

    /**
     * 折现利率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal disRate;

    /**
     * 折现因子
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal disFactor;

    /**
     * 折算后金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal disCashAmt;

    /**
     * 取得 序号
     * @return 序号
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 设置 序号
     * @param seqno 序号
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    /**
     * 取得 结果表序号
     * @return 结果表序号
     */
    public String getResultSeqno() {
        return resultSeqno;
    }

    /**
     * 设置 结果表序号
     * @param resultSeqno 结果表序号
     */
    public void setResultSeqno(String resultSeqno) {
        this.resultSeqno = resultSeqno == null ? null : resultSeqno.trim();
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
     * 取得 日期
     * @return 日期
     */
    public Date getCdate() {
        return cdate;
    }

    /**
     * 设置 日期
     * @param cdate 日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }

    /**
     * 取得 现金流日期
     * @return 现金流日期
     */
    public Date getCashDate() {
        return cashDate;
    }

    /**
     * 设置 现金流日期
     * @param cashDate 现金流日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setCashDate(Date cashDate) {
        this.cashDate = cashDate;
    }

    /**
     * 取得 期限（年化）
     * @return 期限（年化）
     */
    public BigDecimal getPeriod() {
        return period;
    }

    /**
     * 设置 期限（年化）
     * @param period 期限（年化）
     */
    public void setPeriod(BigDecimal period) {
        this.period = period;
    }

    /**
     * 取得 现金流金额
     * @return 现金流金额
     */
    public BigDecimal getCashAmt() {
        return cashAmt;
    }

    /**
     * 设置 现金流金额
     * @param cashAmt 现金流金额
     */
    public void setCashAmt(BigDecimal cashAmt) {
        this.cashAmt = cashAmt;
    }

    /**
     * 取得 折现利率
     * @return 折现利率
     */
    public BigDecimal getDisRate() {
        return disRate;
    }

    /**
     * 设置 折现利率
     * @param disRate 折现利率
     */
    public void setDisRate(BigDecimal disRate) {
        this.disRate = disRate;
    }

    /**
     * 取得 折现因子
     * @return 折现因子
     */
    public BigDecimal getDisFactor() {
        return disFactor;
    }

    /**
     * 设置 折现因子
     * @param disFactor 折现因子
     */
    public void setDisFactor(BigDecimal disFactor) {
        this.disFactor = disFactor;
    }

    /**
     * 取得 折算后金额
     * @return 折算后金额
     */
    public BigDecimal getDisCashAmt() {
        return disCashAmt;
    }

    /**
     * 设置 折算后金额
     * @param disCashAmt 折算后金额
     */
    public void setDisCashAmt(BigDecimal disCashAmt) {
        this.disCashAmt = disCashAmt;
    }
}