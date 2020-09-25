package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;
import java.util.Date;

public class ChbAstBtmInfoSub extends ChbAstBtmInfoSubKey {
    /**
     * 行内资产/负债编码
     */
    private String assetCode;

    /**
     * 发行机构代码
     */
    private String issuePartyId;

    /**
     * 二级资产类别 参照数据字典
     */
    private String assetType2;

    /**
     * 币种 参照数据字典
     */
    private String ccy;

    /**
     * 交易流通场所 参照数据字典
     */
    private String tradeMarket;

    /**
     * 资管计划名称
     */
    private String planName;

    /**
     * 发起人机构编码
     */
    private String partyCode;

    /**
     * 登记编码
     */
    private String checkCode;

    /**
     * 管理人
     */
    private String managerId;

    /**
     * 托管人
     */
    private String trusteeId;

    /**
     * 金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal amt;

    /**
     * 资金实际投向 参照数据字典
     */
    private String truActType;

    /**
     * 资金运用方式
     */
    private String investType;

    /**
     * 资金运用行业 参照数据字典
     */
    private String capUseIndu;

    /**
     * 资金运用行业二级分类 参照数据字典
     */
    private String capUseInduLevel2;

    /**
     * 成立日
     */
    private Date setDate;

    /**
     * 终止日
     */
    private Date endDate;

    /**
     * 资管计划属性 参照数据字典
     */
    private String proProperty;

    /**
     * 是否有预期收益率 参照数据字典
     */
    private String rateFlag;

    /**
     * 最高预期收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal maxRate;

    /**
     * 最低预期收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal minRate;

    /**
     * 购买结构 参照数据字典
     */
    private String buyStruct;

    /**
     * 管理方式 参照数据字典
     */
    private String manageType;

    /**
     * 是否掌控计划底层资产详情 参照数据字典
     */
    private String isCtrlBottom;

    /**
     * 管理费率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal mgrFeeRate;

    /**
     * 托管费率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal truFeeRate;

    /**
     * 交易相关合计费率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tradeFeeRate;

    /**
     * 中介服务机构合计费率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal agencyFeeRate;

    /**
     * 其他合计费率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal otherFeeRate;

    /**
     * 委托方
     */
    private String entruParty;

    /**
     * 是否为委托投资
     */
    private String isEntruInv;

    /**
     * 备注
     */
    private String remark;

    /**
     * 工作流代码
     */
    private String workflowId;

    /**
     * 报送状态
     */
    private String sendStatus;

    /**
     * 数据日期
     */
    private Date statusDate;

    /**
     * 取得 行内资产/负债编码
     * @return 行内资产/负债编码
     */
    public String getAssetCode() {
        return assetCode;
    }

    /**
     * 设置 行内资产/负债编码
     * @param assetCode 行内资产/负债编码
     */
    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode == null ? null : assetCode.trim();
    }

    /**
     * 取得 发行机构代码
     * @return 发行机构代码
     */
    public String getIssuePartyId() {
        return issuePartyId;
    }

    /**
     * 设置 发行机构代码
     * @param issuePartyId 发行机构代码
     */
    public void setIssuePartyId(String issuePartyId) {
        this.issuePartyId = issuePartyId == null ? null : issuePartyId.trim();
    }

    /**
     * 取得 二级资产类别 参照数据字典
     * @return 二级资产类别 参照数据字典
     */
    public String getAssetType2() {
        return assetType2;
    }

    /**
     * 设置 二级资产类别 参照数据字典
     * @param assetType2 二级资产类别 参照数据字典
     */
    public void setAssetType2(String assetType2) {
        this.assetType2 = assetType2 == null ? null : assetType2.trim();
    }

    /**
     * 取得 币种 参照数据字典
     * @return 币种 参照数据字典
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 币种 参照数据字典
     * @param ccy 币种 参照数据字典
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }

    /**
     * 取得 交易流通场所 参照数据字典
     * @return 交易流通场所 参照数据字典
     */
    public String getTradeMarket() {
        return tradeMarket;
    }

    /**
     * 设置 交易流通场所 参照数据字典
     * @param tradeMarket 交易流通场所 参照数据字典
     */
    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket == null ? null : tradeMarket.trim();
    }

    /**
     * 取得 资管计划名称
     * @return 资管计划名称
     */
    public String getPlanName() {
        return planName;
    }

    /**
     * 设置 资管计划名称
     * @param planName 资管计划名称
     */
    public void setPlanName(String planName) {
        this.planName = planName == null ? null : planName.trim();
    }

    /**
     * 取得 发起人机构编码
     * @return 发起人机构编码
     */
    public String getPartyCode() {
        return partyCode;
    }

    /**
     * 设置 发起人机构编码
     * @param partyCode 发起人机构编码
     */
    public void setPartyCode(String partyCode) {
        this.partyCode = partyCode == null ? null : partyCode.trim();
    }

    /**
     * 取得 登记编码
     * @return 登记编码
     */
    public String getCheckCode() {
        return checkCode;
    }

    /**
     * 设置 登记编码
     * @param checkCode 登记编码
     */
    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode == null ? null : checkCode.trim();
    }

    /**
     * 取得 管理人
     * @return 管理人
     */
    public String getManagerId() {
        return managerId;
    }

    /**
     * 设置 管理人
     * @param managerId 管理人
     */
    public void setManagerId(String managerId) {
        this.managerId = managerId == null ? null : managerId.trim();
    }

    /**
     * 取得 托管人
     * @return 托管人
     */
    public String getTrusteeId() {
        return trusteeId;
    }

    /**
     * 设置 托管人
     * @param trusteeId 托管人
     */
    public void setTrusteeId(String trusteeId) {
        this.trusteeId = trusteeId == null ? null : trusteeId.trim();
    }

    /**
     * 取得 金额
     * @return 金额
     */
    public BigDecimal getAmt() {
        return amt;
    }

    /**
     * 设置 金额
     * @param amt 金额
     */
    public void setAmt(BigDecimal amt) {
        this.amt = amt;
    }

    /**
     * 取得 资金实际投向 参照数据字典
     * @return 资金实际投向 参照数据字典
     */
    public String getTruActType() {
        return truActType;
    }

    /**
     * 设置 资金实际投向 参照数据字典
     * @param truActType 资金实际投向 参照数据字典
     */
    public void setTruActType(String truActType) {
        this.truActType = truActType == null ? null : truActType.trim();
    }

    /**
     * 取得 资金运用方式
     * @return 资金运用方式
     */
    public String getInvestType() {
        return investType;
    }

    /**
     * 设置 资金运用方式
     * @param investType 资金运用方式
     */
    public void setInvestType(String investType) {
        this.investType = investType == null ? null : investType.trim();
    }

    /**
     * 取得 资金运用行业 参照数据字典
     * @return 资金运用行业 参照数据字典
     */
    public String getCapUseIndu() {
        return capUseIndu;
    }

    /**
     * 设置 资金运用行业 参照数据字典
     * @param capUseIndu 资金运用行业 参照数据字典
     */
    public void setCapUseIndu(String capUseIndu) {
        this.capUseIndu = capUseIndu == null ? null : capUseIndu.trim();
    }

    /**
     * 取得 资金运用行业二级分类 参照数据字典
     * @return 资金运用行业二级分类 参照数据字典
     */
    public String getCapUseInduLevel2() {
        return capUseInduLevel2;
    }

    /**
     * 设置 资金运用行业二级分类 参照数据字典
     * @param capUseInduLevel2 资金运用行业二级分类 参照数据字典
     */
    public void setCapUseInduLevel2(String capUseInduLevel2) {
        this.capUseInduLevel2 = capUseInduLevel2 == null ? null : capUseInduLevel2.trim();
    }

    /**
     * 取得 成立日
     * @return 成立日
     */
    public Date getSetDate() {
        return setDate;
    }

    /**
     * 设置 成立日
     * @param setDate 成立日
     */
    public void setSetDate(Date setDate) {
        this.setDate = setDate;
    }

    /**
     * 取得 终止日
     * @return 终止日
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 设置 终止日
     * @param endDate 终止日
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * 取得 资管计划属性 参照数据字典
     * @return 资管计划属性 参照数据字典
     */
    public String getProProperty() {
        return proProperty;
    }

    /**
     * 设置 资管计划属性 参照数据字典
     * @param proProperty 资管计划属性 参照数据字典
     */
    public void setProProperty(String proProperty) {
        this.proProperty = proProperty == null ? null : proProperty.trim();
    }

    /**
     * 取得 是否有预期收益率 参照数据字典
     * @return 是否有预期收益率 参照数据字典
     */
    public String getRateFlag() {
        return rateFlag;
    }

    /**
     * 设置 是否有预期收益率 参照数据字典
     * @param rateFlag 是否有预期收益率 参照数据字典
     */
    public void setRateFlag(String rateFlag) {
        this.rateFlag = rateFlag == null ? null : rateFlag.trim();
    }

    /**
     * 取得 最高预期收益率
     * @return 最高预期收益率
     */
    public BigDecimal getMaxRate() {
        return maxRate;
    }

    /**
     * 设置 最高预期收益率
     * @param maxRate 最高预期收益率
     */
    public void setMaxRate(BigDecimal maxRate) {
        this.maxRate = maxRate;
    }

    /**
     * 取得 最低预期收益率
     * @return 最低预期收益率
     */
    public BigDecimal getMinRate() {
        return minRate;
    }

    /**
     * 设置 最低预期收益率
     * @param minRate 最低预期收益率
     */
    public void setMinRate(BigDecimal minRate) {
        this.minRate = minRate;
    }

    /**
     * 取得 购买结构 参照数据字典
     * @return 购买结构 参照数据字典
     */
    public String getBuyStruct() {
        return buyStruct;
    }

    /**
     * 设置 购买结构 参照数据字典
     * @param buyStruct 购买结构 参照数据字典
     */
    public void setBuyStruct(String buyStruct) {
        this.buyStruct = buyStruct == null ? null : buyStruct.trim();
    }

    /**
     * 取得 管理方式 参照数据字典
     * @return 管理方式 参照数据字典
     */
    public String getManageType() {
        return manageType;
    }

    /**
     * 设置 管理方式 参照数据字典
     * @param manageType 管理方式 参照数据字典
     */
    public void setManageType(String manageType) {
        this.manageType = manageType == null ? null : manageType.trim();
    }

    /**
     * 取得 是否掌控计划底层资产详情 参照数据字典
     * @return 是否掌控计划底层资产详情 参照数据字典
     */
    public String getIsCtrlBottom() {
        return isCtrlBottom;
    }

    /**
     * 设置 是否掌控计划底层资产详情 参照数据字典
     * @param isCtrlBottom 是否掌控计划底层资产详情 参照数据字典
     */
    public void setIsCtrlBottom(String isCtrlBottom) {
        this.isCtrlBottom = isCtrlBottom == null ? null : isCtrlBottom.trim();
    }

    /**
     * 取得 管理费率
     * @return 管理费率
     */
    public BigDecimal getMgrFeeRate() {
        return mgrFeeRate;
    }

    /**
     * 设置 管理费率
     * @param mgrFeeRate 管理费率
     */
    public void setMgrFeeRate(BigDecimal mgrFeeRate) {
        this.mgrFeeRate = mgrFeeRate;
    }

    /**
     * 取得 托管费率
     * @return 托管费率
     */
    public BigDecimal getTruFeeRate() {
        return truFeeRate;
    }

    /**
     * 设置 托管费率
     * @param truFeeRate 托管费率
     */
    public void setTruFeeRate(BigDecimal truFeeRate) {
        this.truFeeRate = truFeeRate;
    }

    /**
     * 取得 交易相关合计费率
     * @return 交易相关合计费率
     */
    public BigDecimal getTradeFeeRate() {
        return tradeFeeRate;
    }

    /**
     * 设置 交易相关合计费率
     * @param tradeFeeRate 交易相关合计费率
     */
    public void setTradeFeeRate(BigDecimal tradeFeeRate) {
        this.tradeFeeRate = tradeFeeRate;
    }

    /**
     * 取得 中介服务机构合计费率
     * @return 中介服务机构合计费率
     */
    public BigDecimal getAgencyFeeRate() {
        return agencyFeeRate;
    }

    /**
     * 设置 中介服务机构合计费率
     * @param agencyFeeRate 中介服务机构合计费率
     */
    public void setAgencyFeeRate(BigDecimal agencyFeeRate) {
        this.agencyFeeRate = agencyFeeRate;
    }

    /**
     * 取得 其他合计费率
     * @return 其他合计费率
     */
    public BigDecimal getOtherFeeRate() {
        return otherFeeRate;
    }

    /**
     * 设置 其他合计费率
     * @param otherFeeRate 其他合计费率
     */
    public void setOtherFeeRate(BigDecimal otherFeeRate) {
        this.otherFeeRate = otherFeeRate;
    }

    /**
     * 取得 委托方
     * @return 委托方
     */
    public String getEntruParty() {
        return entruParty;
    }

    /**
     * 设置 委托方
     * @param entruParty 委托方
     */
    public void setEntruParty(String entruParty) {
        this.entruParty = entruParty == null ? null : entruParty.trim();
    }

    /**
     * 取得 是否为委托投资
     * @return 是否为委托投资
     */
    public String getIsEntruInv() {
        return isEntruInv;
    }

    /**
     * 设置 是否为委托投资
     * @param isEntruInv 是否为委托投资
     */
    public void setIsEntruInv(String isEntruInv) {
        this.isEntruInv = isEntruInv == null ? null : isEntruInv.trim();
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
     * 取得 工作流代码
     * @return 工作流代码
     */
    public String getWorkflowId() {
        return workflowId;
    }

    /**
     * 设置 工作流代码
     * @param workflowId 工作流代码
     */
    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId == null ? null : workflowId.trim();
    }

    /**
     * 取得 报送状态
     * @return 报送状态
     */
    public String getSendStatus() {
        return sendStatus;
    }

    /**
     * 设置 报送状态
     * @param sendStatus 报送状态
     */
    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus == null ? null : sendStatus.trim();
    }

    /**
     * 取得 数据日期
     * @return 数据日期
     */
    public Date getStatusDate() {
        return statusDate;
    }

    /**
     * 设置 数据日期
     * @param statusDate 数据日期
     */
    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }
}