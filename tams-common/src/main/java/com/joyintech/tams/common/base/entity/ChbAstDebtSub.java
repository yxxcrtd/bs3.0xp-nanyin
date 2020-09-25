package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;
import java.util.Date;

public class ChbAstDebtSub extends ChbAstDebtSubKey {
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
     * 起息日
     */
    private Date vdate;

    /**
     * 到期日
     */
    private Date mdate;

    /**
     * 对手方
     */
    private String rivalParty;

    /**
     * 年利率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal rateYear;

    /**
     * 计息基础 参照数据字典
     */
    private String basis;

    /**
     * 回购标的类别 参照数据字典
     */
    private String targetType;

    /**
     * 回购标的金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal targetAmt;

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
     * 取得 起息日
     * @return 起息日
     */
    public Date getVdate() {
        return vdate;
    }

    /**
     * 设置 起息日
     * @param vdate 起息日
     */
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    /**
     * 取得 到期日
     * @return 到期日
     */
    public Date getMdate() {
        return mdate;
    }

    /**
     * 设置 到期日
     * @param mdate 到期日
     */
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * 取得 对手方
     * @return 对手方
     */
    public String getRivalParty() {
        return rivalParty;
    }

    /**
     * 设置 对手方
     * @param rivalParty 对手方
     */
    public void setRivalParty(String rivalParty) {
        this.rivalParty = rivalParty == null ? null : rivalParty.trim();
    }

    /**
     * 取得 年利率
     * @return 年利率
     */
    public BigDecimal getRateYear() {
        return rateYear;
    }

    /**
     * 设置 年利率
     * @param rateYear 年利率
     */
    public void setRateYear(BigDecimal rateYear) {
        this.rateYear = rateYear;
    }

    /**
     * 取得 计息基础 参照数据字典
     * @return 计息基础 参照数据字典
     */
    public String getBasis() {
        return basis;
    }

    /**
     * 设置 计息基础 参照数据字典
     * @param basis 计息基础 参照数据字典
     */
    public void setBasis(String basis) {
        this.basis = basis == null ? null : basis.trim();
    }

    /**
     * 取得 回购标的类别 参照数据字典
     * @return 回购标的类别 参照数据字典
     */
    public String getTargetType() {
        return targetType;
    }

    /**
     * 设置 回购标的类别 参照数据字典
     * @param targetType 回购标的类别 参照数据字典
     */
    public void setTargetType(String targetType) {
        this.targetType = targetType == null ? null : targetType.trim();
    }

    /**
     * 取得 回购标的金额
     * @return 回购标的金额
     */
    public BigDecimal getTargetAmt() {
        return targetAmt;
    }

    /**
     * 设置 回购标的金额
     * @param targetAmt 回购标的金额
     */
    public void setTargetAmt(BigDecimal targetAmt) {
        this.targetAmt = targetAmt;
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