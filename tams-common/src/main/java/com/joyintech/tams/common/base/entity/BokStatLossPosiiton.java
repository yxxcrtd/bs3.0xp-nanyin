package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class BokStatLossPosiiton extends BaseEntity {
    /**
     * 流水
     */
    private String seqno;

    /**
     * 账务明细流水
     */
    private String bokSeqno;

    /**
     * 账套代码
     */
    private String booksetId;

    /**
     * 发生日期
     */
    private Date happenDate;

    /**
     * 入账日期
     */
    private Date bookDate;

    /**
     * 账套日期
     */
    private Date booksetDate;

    /**
     * 账务明细代码
     */
    private String bokDetailId;

    /**
     * 业务明细代码
     */
    private String busiId;

    /**
     * 方案明细代码
     */
    private String schemeDetailId;

    /**
     * 交易编号
     */
    private String tradeId;

    /**
     * 减值处理方式：减值处理方式：减值，减值使用、清理核销
     */
    private String lossExeType;

    /**
     * 原币
     */
    private String ccy;

    /**
     * 本币
     */
    private String bCcy;

    /**
     * 当日账面本金价值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyCostAmt;

    /**
     * 当日账面应收利息
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyIntAmt;

    /**
     * 当日减值金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyLossamtAdd;

    /**
     * 当日累计减值金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyLossamt;

    /**
     * 当日减值金额_本位币
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyLossamtAddB;

    /**
     * 当日累计减值金额当日累计减值金额_本位币
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tdyLossamtB;

    /**
     * 减值生成方式
     */
    private String lossGenType;

    /**
     * 取得 流水
     * @return 流水
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 设置 流水
     * @param seqno 流水
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
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
     * 取得 业务明细代码
     * @return 业务明细代码
     */
    public String getBusiId() {
        return busiId;
    }

    /**
     * 设置 业务明细代码
     * @param busiId 业务明细代码
     */
    public void setBusiId(String busiId) {
        this.busiId = busiId == null ? null : busiId.trim();
    }

    /**
     * 取得 方案明细代码
     * @return 方案明细代码
     */
    public String getSchemeDetailId() {
        return schemeDetailId;
    }

    /**
     * 设置 方案明细代码
     * @param schemeDetailId 方案明细代码
     */
    public void setSchemeDetailId(String schemeDetailId) {
        this.schemeDetailId = schemeDetailId == null ? null : schemeDetailId.trim();
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
     * 取得 减值处理方式：减值处理方式：减值，减值使用、清理核销
     * @return 减值处理方式：减值处理方式：减值，减值使用、清理核销
     */
    public String getLossExeType() {
        return lossExeType;
    }

    /**
     * 设置 减值处理方式：减值处理方式：减值，减值使用、清理核销
     * @param lossExeType 减值处理方式：减值处理方式：减值，减值使用、清理核销
     */
    public void setLossExeType(String lossExeType) {
        this.lossExeType = lossExeType == null ? null : lossExeType.trim();
    }

    /**
     * 取得 原币
     * @return 原币
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 原币
     * @param ccy 原币
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }

    /**
     * 取得 本币
     * @return 本币
     */
    public String getbCcy() {
        return bCcy;
    }

    /**
     * 设置 本币
     * @param bCcy 本币
     */
    public void setbCcy(String bCcy) {
        this.bCcy = bCcy == null ? null : bCcy.trim();
    }

    /**
     * 取得 当日账面本金价值
     * @return 当日账面本金价值
     */
    public BigDecimal getTdyCostAmt() {
        return tdyCostAmt;
    }

    /**
     * 设置 当日账面本金价值
     * @param tdyCostAmt 当日账面本金价值
     */
    public void setTdyCostAmt(BigDecimal tdyCostAmt) {
        this.tdyCostAmt = tdyCostAmt;
    }

    /**
     * 取得 当日账面应收利息
     * @return 当日账面应收利息
     */
    public BigDecimal getTdyIntAmt() {
        return tdyIntAmt;
    }

    /**
     * 设置 当日账面应收利息
     * @param tdyIntAmt 当日账面应收利息
     */
    public void setTdyIntAmt(BigDecimal tdyIntAmt) {
        this.tdyIntAmt = tdyIntAmt;
    }

    /**
     * 取得 当日减值金额
     * @return 当日减值金额
     */
    public BigDecimal getTdyLossamtAdd() {
        return tdyLossamtAdd;
    }

    /**
     * 设置 当日减值金额
     * @param tdyLossamtAdd 当日减值金额
     */
    public void setTdyLossamtAdd(BigDecimal tdyLossamtAdd) {
        this.tdyLossamtAdd = tdyLossamtAdd;
    }

    /**
     * 取得 当日累计减值金额
     * @return 当日累计减值金额
     */
    public BigDecimal getTdyLossamt() {
        return tdyLossamt;
    }

    /**
     * 设置 当日累计减值金额
     * @param tdyLossamt 当日累计减值金额
     */
    public void setTdyLossamt(BigDecimal tdyLossamt) {
        this.tdyLossamt = tdyLossamt;
    }

    /**
     * 取得 当日减值金额_本位币
     * @return 当日减值金额_本位币
     */
    public BigDecimal getTdyLossamtAddB() {
        return tdyLossamtAddB;
    }

    /**
     * 设置 当日减值金额_本位币
     * @param tdyLossamtAddB 当日减值金额_本位币
     */
    public void setTdyLossamtAddB(BigDecimal tdyLossamtAddB) {
        this.tdyLossamtAddB = tdyLossamtAddB;
    }

    /**
     * 取得 当日累计减值金额当日累计减值金额_本位币
     * @return 当日累计减值金额当日累计减值金额_本位币
     */
    public BigDecimal getTdyLossamtB() {
        return tdyLossamtB;
    }

    /**
     * 设置 当日累计减值金额当日累计减值金额_本位币
     * @param tdyLossamtB 当日累计减值金额当日累计减值金额_本位币
     */
    public void setTdyLossamtB(BigDecimal tdyLossamtB) {
        this.tdyLossamtB = tdyLossamtB;
    }

    /**
     * 取得 减值生成方式
     * @return 减值生成方式
     */
    public String getLossGenType() {
        return lossGenType;
    }

    /**
     * 设置 减值生成方式
     * @param lossGenType 减值生成方式
     */
    public void setLossGenType(String lossGenType) {
        this.lossGenType = lossGenType == null ? null : lossGenType.trim();
    }
}