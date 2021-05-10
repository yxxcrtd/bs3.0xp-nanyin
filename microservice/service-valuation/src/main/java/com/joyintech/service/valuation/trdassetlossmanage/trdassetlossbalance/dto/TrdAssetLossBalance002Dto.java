package com.joyintech.service.valuation.trdassetlossmanage.trdassetlossbalance.dto;

import com.joyintech.framework.base.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 统计分析明细表（资产减值）BOK_STAT_LOSS_POSIITON
 */
public class TrdAssetLossBalance002Dto extends BaseEntity {
    private String seqNo;   //流水
    private String bokSeqno;   //账务明细流水
    private String booksetId;   //账套代码
    private Date happenDate;   //发生日期
    private Date bookDate;   //入账日期
    private Date booksetDate;   //账套日期
    private String bokDetailId;   //账务明细代码
    private String busiId;   //业务明细代码
    private String schemeDetailId;   //方案明细代码
    private String tradeId;   //交易编号
    private String lossExeType;   //减值处理方式：减值处理方式：减值，减值使用、清理核销
    private String ccy;   //原币
    private String bCcy;   //本币
    private BigDecimal tdyCostAmt;   //当日账面价值
    private BigDecimal tdyIntAmt;   //
    private BigDecimal tdyLossamtAdd;   //当日减值金额
    private BigDecimal tdyLossamt;   //当日累计减值金额
    private BigDecimal tdyLossamtAddB;   //当日减值金额_本位币
    private BigDecimal tdyLossamtB;   //当日累计减值金额_本位币

    private String finprodAbbr;   // 资产名称
    private String booksetName;     // 账套名称

    public String getSeqNo() {
        return seqNo;
    }

    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }

    public String getBooksetName() {
        return booksetName;
    }

    public void setBooksetName(String booksetName) {
        this.booksetName = booksetName;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    public String getBokSeqno() {
        return bokSeqno;
    }

    public void setBokSeqno(String bokSeqno) {
        this.bokSeqno = bokSeqno;
    }

    public String getBooksetId() {
        return booksetId;
    }

    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }

    public Date getHappenDate() {
        return happenDate;
    }

    public void setHappenDate(Date happenDate) {
        this.happenDate = happenDate;
    }

    public Date getBookDate() {
        return bookDate;
    }

    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
    }

    public Date getBooksetDate() {
        return booksetDate;
    }

    public void setBooksetDate(Date booksetDate) {
        this.booksetDate = booksetDate;
    }

    public String getBokDetailId() {
        return bokDetailId;
    }

    public void setBokDetailId(String bokDetailId) {
        this.bokDetailId = bokDetailId;
    }

    public String getBusiId() {
        return busiId;
    }

    public void setBusiId(String busiId) {
        this.busiId = busiId;
    }

    public String getSchemeDetailId() {
        return schemeDetailId;
    }

    public void setSchemeDetailId(String schemeDetailId) {
        this.schemeDetailId = schemeDetailId;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getLossExeType() {
        return lossExeType;
    }

    public void setLossExeType(String lossExeType) {
        this.lossExeType = lossExeType;
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public String getbCcy() {
        return bCcy;
    }

    public void setbCcy(String bCcy) {
        this.bCcy = bCcy;
    }

    public BigDecimal getTdyCostAmt() {
        return tdyCostAmt;
    }

    public void setTdyCostAmt(BigDecimal tdyCostAmt) {
        this.tdyCostAmt = tdyCostAmt;
    }

    public BigDecimal getTdyIntAmt() {
        return tdyIntAmt;
    }

    public void setTdyIntAmt(BigDecimal tdyIntAmt) {
        this.tdyIntAmt = tdyIntAmt;
    }

    public BigDecimal getTdyLossamtAdd() {
        return tdyLossamtAdd;
    }

    public void setTdyLossamtAdd(BigDecimal tdyLossamtAdd) {
        this.tdyLossamtAdd = tdyLossamtAdd;
    }

    public BigDecimal getTdyLossamt() {
        return tdyLossamt;
    }

    public void setTdyLossamt(BigDecimal tdyLossamt) {
        this.tdyLossamt = tdyLossamt;
    }

    public BigDecimal getTdyLossamtAddB() {
        return tdyLossamtAddB;
    }

    public void setTdyLossamtAddB(BigDecimal tdyLossamtAddB) {
        this.tdyLossamtAddB = tdyLossamtAddB;
    }

    public BigDecimal getTdyLossamtB() {
        return tdyLossamtB;
    }

    public void setTdyLossamtB(BigDecimal tdyLossamtB) {
        this.tdyLossamtB = tdyLossamtB;
    }
}
