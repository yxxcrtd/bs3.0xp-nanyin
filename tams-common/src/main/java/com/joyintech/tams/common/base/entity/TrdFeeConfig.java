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
     * 交易市场
     */
    private String tradeMarketMode;

    /**
     * 计算过程中保留位数
     */
    private Long retentionBit;

    /**
     * 费用由券商承担费用
     */
    private String brokerBaerFee;

    /**
     * 佣金计算方式
     */
    private String commCalMethod;

    /**
     * 分档模式
     */
    private String gradeMode;

    /**
     * 券商代码
     */
    private String customerId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 资产分类一
     */
    private String assetClassify1;

    /**
     * 资产分类二
     */
    private String assetClassify2;

    /**
     * 交易平台，银行间、上交所固定收益平台、上交所大宗交易平台、深交所大宗平台、深交所综合协议交易平台、其他等
     */
    private String tradePlat;

    /**
     * 计算过程中其他费用保留位数
     */
    private String othrerRetentionBit;

    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 是否多券种
     */
    private String isMlti;

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

    /**
     * 取得 交易市场
     * @return 交易市场
     */
    public String getTradeMarketMode() {
        return tradeMarketMode;
    }

    /**
     * 设置 交易市场
     * @param tradeMarketMode 交易市场
     */
    public void setTradeMarketMode(String tradeMarketMode) {
        this.tradeMarketMode = tradeMarketMode == null ? null : tradeMarketMode.trim();
    }

    /**
     * 取得 计算过程中保留位数
     * @return 计算过程中保留位数
     */
    public Long getRetentionBit() {
        return retentionBit;
    }

    /**
     * 设置 计算过程中保留位数
     * @param retentionBit 计算过程中保留位数
     */
    public void setRetentionBit(Long retentionBit) {
        this.retentionBit = retentionBit;
    }

    /**
     * 取得 费用由券商承担费用
     * @return 费用由券商承担费用
     */
    public String getBrokerBaerFee() {
        return brokerBaerFee;
    }

    /**
     * 设置 费用由券商承担费用
     * @param brokerBaerFee 费用由券商承担费用
     */
    public void setBrokerBaerFee(String brokerBaerFee) {
        this.brokerBaerFee = brokerBaerFee == null ? null : brokerBaerFee.trim();
    }

    /**
     * 取得 佣金计算方式
     * @return 佣金计算方式
     */
    public String getCommCalMethod() {
        return commCalMethod;
    }

    /**
     * 设置 佣金计算方式
     * @param commCalMethod 佣金计算方式
     */
    public void setCommCalMethod(String commCalMethod) {
        this.commCalMethod = commCalMethod == null ? null : commCalMethod.trim();
    }

    /**
     * 取得 分档模式
     * @return 分档模式
     */
    public String getGradeMode() {
        return gradeMode;
    }

    /**
     * 设置 分档模式
     * @param gradeMode 分档模式
     */
    public void setGradeMode(String gradeMode) {
        this.gradeMode = gradeMode == null ? null : gradeMode.trim();
    }

    /**
     * 取得 券商代码
     * @return 券商代码
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * 设置 券商代码
     * @param customerId 券商代码
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 取得 资产分类一
     * @return 资产分类一
     */
    public String getAssetClassify1() {
        return assetClassify1;
    }

    /**
     * 设置 资产分类一
     * @param assetClassify1 资产分类一
     */
    public void setAssetClassify1(String assetClassify1) {
        this.assetClassify1 = assetClassify1 == null ? null : assetClassify1.trim();
    }

    /**
     * 取得 资产分类二
     * @return 资产分类二
     */
    public String getAssetClassify2() {
        return assetClassify2;
    }

    /**
     * 设置 资产分类二
     * @param assetClassify2 资产分类二
     */
    public void setAssetClassify2(String assetClassify2) {
        this.assetClassify2 = assetClassify2 == null ? null : assetClassify2.trim();
    }

    /**
     * 取得 交易平台，银行间、上交所固定收益平台、上交所大宗交易平台、深交所大宗平台、深交所综合协议交易平台、其他等
     * @return 交易平台，银行间、上交所固定收益平台、上交所大宗交易平台、深交所大宗平台、深交所综合协议交易平台、其他等
     */
    public String getTradePlat() {
        return tradePlat;
    }

    /**
     * 设置 交易平台，银行间、上交所固定收益平台、上交所大宗交易平台、深交所大宗平台、深交所综合协议交易平台、其他等
     * @param tradePlat 交易平台，银行间、上交所固定收益平台、上交所大宗交易平台、深交所大宗平台、深交所综合协议交易平台、其他等
     */
    public void setTradePlat(String tradePlat) {
        this.tradePlat = tradePlat == null ? null : tradePlat.trim();
    }

    /**
     * 取得 计算过程中其他费用保留位数
     * @return 计算过程中其他费用保留位数
     */
    public String getOthrerRetentionBit() {
        return othrerRetentionBit;
    }

    /**
     * 设置 计算过程中其他费用保留位数
     * @param othrerRetentionBit 计算过程中其他费用保留位数
     */
    public void setOthrerRetentionBit(String othrerRetentionBit) {
        this.othrerRetentionBit = othrerRetentionBit == null ? null : othrerRetentionBit.trim();
    }

    /**
     * 取得 组合代码
     * @return 组合代码
     */
    public String getPortfolioId() {
        return portfolioId;
    }

    /**
     * 设置 组合代码
     * @param portfolioId 组合代码
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId == null ? null : portfolioId.trim();
    }

    /**
     * 取得 是否多券种
     * @return 是否多券种
     */
    public String getIsMlti() {
        return isMlti;
    }

    /**
     * 设置 是否多券种
     * @param isMlti 是否多券种
     */
    public void setIsMlti(String isMlti) {
        this.isMlti = isMlti == null ? null : isMlti.trim();
    }
}