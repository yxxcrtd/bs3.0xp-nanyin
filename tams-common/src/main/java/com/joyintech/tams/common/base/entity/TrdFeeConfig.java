package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class TrdFeeConfig extends BaseEntity {
    /**
     * 流水号
     */
    private String seqNo;

    /**
     * 交易场所
     */
    private String tradeMarket;

    /**
     * 费用类型
     */
    private String feeType;

    /**
     * 登记托管机构
     */
    private String registOrg;

    /**
     * 交易品种
     */
    private String tradeType;

    /**
     * 交易方向
     */
    private String ps;

    /**
     * 期限品种
     */
    private String termType;

    /**
     * 证券账户代码
     */
    private String secAcctId;

    /**
     * 计费类型
     */
    private String chargeType;

    /**
     * 计费基数
     */
    private String chargeBasis;

    /**
     * 是否分档
     */
    private String isGrade;

    /**
     * 生效日
     */
    private Date effDate;

    /**
     * 批次号
     */
    private String batchSeq;

    /**
     * 是否随交易支付
     */
    private String isPayWithTrade;

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
     * 取得 交易场所
     * @return 交易场所
     */
    public String getTradeMarket() {
        return tradeMarket;
    }

    /**
     * 设置 交易场所
     * @param tradeMarket 交易场所
     */
    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket == null ? null : tradeMarket.trim();
    }

    /**
     * 取得 费用类型
     * @return 费用类型
     */
    public String getFeeType() {
        return feeType;
    }

    /**
     * 设置 费用类型
     * @param feeType 费用类型
     */
    public void setFeeType(String feeType) {
        this.feeType = feeType == null ? null : feeType.trim();
    }

    /**
     * 取得 登记托管机构
     * @return 登记托管机构
     */
    public String getRegistOrg() {
        return registOrg;
    }

    /**
     * 设置 登记托管机构
     * @param registOrg 登记托管机构
     */
    public void setRegistOrg(String registOrg) {
        this.registOrg = registOrg == null ? null : registOrg.trim();
    }

    /**
     * 取得 交易品种
     * @return 交易品种
     */
    public String getTradeType() {
        return tradeType;
    }

    /**
     * 设置 交易品种
     * @param tradeType 交易品种
     */
    public void setTradeType(String tradeType) {
        this.tradeType = tradeType == null ? null : tradeType.trim();
    }

    /**
     * 取得 交易方向
     * @return 交易方向
     */
    public String getPs() {
        return ps;
    }

    /**
     * 设置 交易方向
     * @param ps 交易方向
     */
    public void setPs(String ps) {
        this.ps = ps == null ? null : ps.trim();
    }

    /**
     * 取得 期限品种
     * @return 期限品种
     */
    public String getTermType() {
        return termType;
    }

    /**
     * 设置 期限品种
     * @param termType 期限品种
     */
    public void setTermType(String termType) {
        this.termType = termType == null ? null : termType.trim();
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
     * 取得 计费类型
     * @return 计费类型
     */
    public String getChargeType() {
        return chargeType;
    }

    /**
     * 设置 计费类型
     * @param chargeType 计费类型
     */
    public void setChargeType(String chargeType) {
        this.chargeType = chargeType == null ? null : chargeType.trim();
    }

    /**
     * 取得 计费基数
     * @return 计费基数
     */
    public String getChargeBasis() {
        return chargeBasis;
    }

    /**
     * 设置 计费基数
     * @param chargeBasis 计费基数
     */
    public void setChargeBasis(String chargeBasis) {
        this.chargeBasis = chargeBasis == null ? null : chargeBasis.trim();
    }

    /**
     * 取得 是否分档
     * @return 是否分档
     */
    public String getIsGrade() {
        return isGrade;
    }

    /**
     * 设置 是否分档
     * @param isGrade 是否分档
     */
    public void setIsGrade(String isGrade) {
        this.isGrade = isGrade == null ? null : isGrade.trim();
    }

    /**
     * 取得 生效日
     * @return 生效日
     */
    public Date getEffDate() {
        return effDate;
    }

    /**
     * 设置 生效日
     * @param effDate 生效日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setEffDate(Date effDate) {
        this.effDate = effDate;
    }

    /**
     * 取得 批次号
     * @return 批次号
     */
    public String getBatchSeq() {
        return batchSeq;
    }

    /**
     * 设置 批次号
     * @param batchSeq 批次号
     */
    public void setBatchSeq(String batchSeq) {
        this.batchSeq = batchSeq == null ? null : batchSeq.trim();
    }

    /**
     * 取得 是否随交易支付
     * @return 是否随交易支付
     */
    public String getIsPayWithTrade() {
        return isPayWithTrade;
    }

    /**
     * 设置 是否随交易支付
     * @param isPayWithTrade 是否随交易支付
     */
    public void setIsPayWithTrade(String isPayWithTrade) {
        this.isPayWithTrade = isPayWithTrade == null ? null : isPayWithTrade.trim();
    }
}