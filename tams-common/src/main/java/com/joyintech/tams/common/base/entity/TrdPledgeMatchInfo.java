package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class TrdPledgeMatchInfo extends BaseEntity {
    /**
     * 流水号
     */
    private String seqno;

    /**
     * 下行交易编号
     */
    private String cstpTradeId;

    /**
     * 预录入交易编号
     */
    private String preTradeId;

    /**
     * 委托编号
     */
    private String entrustId;

    /**
     * 证券账户代码
     */
    private String secAcctId;

    /**
     * 质押金融产品代码
     */
    private String pledgeFinprodId;

    /**
     * 匹配额度
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal matchNum;

    /**
     * 匹配方式
     */
    private String matchType;

    /**
     * 交易日期
     */
    private Date tradeDate;

    /**
     * 上游系统
     */
    private String fSysId;

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
     * 取得 下行交易编号
     * @return 下行交易编号
     */
    public String getCstpTradeId() {
        return cstpTradeId;
    }

    /**
     * 设置 下行交易编号
     * @param cstpTradeId 下行交易编号
     */
    public void setCstpTradeId(String cstpTradeId) {
        this.cstpTradeId = cstpTradeId == null ? null : cstpTradeId.trim();
    }

    /**
     * 取得 预录入交易编号
     * @return 预录入交易编号
     */
    public String getPreTradeId() {
        return preTradeId;
    }

    /**
     * 设置 预录入交易编号
     * @param preTradeId 预录入交易编号
     */
    public void setPreTradeId(String preTradeId) {
        this.preTradeId = preTradeId == null ? null : preTradeId.trim();
    }

    /**
     * 取得 委托编号
     * @return 委托编号
     */
    public String getEntrustId() {
        return entrustId;
    }

    /**
     * 设置 委托编号
     * @param entrustId 委托编号
     */
    public void setEntrustId(String entrustId) {
        this.entrustId = entrustId == null ? null : entrustId.trim();
    }

    /**
     * 取得 证券账户代码
     * @return 证券账户代码
     */
    public String getSecAcctId() {
        return secAcctId;
    }

    /**
     * 设置 证券账户代码
     * @param secAcctId 证券账户代码
     */
    public void setSecAcctId(String secAcctId) {
        this.secAcctId = secAcctId == null ? null : secAcctId.trim();
    }

    /**
     * 取得 质押金融产品代码
     * @return 质押金融产品代码
     */
    public String getPledgeFinprodId() {
        return pledgeFinprodId;
    }

    /**
     * 设置 质押金融产品代码
     * @param pledgeFinprodId 质押金融产品代码
     */
    public void setPledgeFinprodId(String pledgeFinprodId) {
        this.pledgeFinprodId = pledgeFinprodId == null ? null : pledgeFinprodId.trim();
    }

    /**
     * 取得 匹配额度
     * @return 匹配额度
     */
    public BigDecimal getMatchNum() {
        return matchNum;
    }

    /**
     * 设置 匹配额度
     * @param matchNum 匹配额度
     */
    public void setMatchNum(BigDecimal matchNum) {
        this.matchNum = matchNum;
    }

    /**
     * 取得 匹配方式
     * @return 匹配方式
     */
    public String getMatchType() {
        return matchType;
    }

    /**
     * 设置 匹配方式
     * @param matchType 匹配方式
     */
    public void setMatchType(String matchType) {
        this.matchType = matchType == null ? null : matchType.trim();
    }

    /**
     * 取得 交易日期
     * @return 交易日期
     */
    public Date getTradeDate() {
        return tradeDate;
    }

    /**
     * 设置 交易日期
     * @param tradeDate 交易日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    /**
     * 取得 上游系统
     * @return 上游系统
     */
    public String getfSysId() {
        return fSysId;
    }

    /**
     * 设置 上游系统
     * @param fSysId 上游系统
     */
    public void setfSysId(String fSysId) {
        this.fSysId = fSysId == null ? null : fSysId.trim();
    }
}