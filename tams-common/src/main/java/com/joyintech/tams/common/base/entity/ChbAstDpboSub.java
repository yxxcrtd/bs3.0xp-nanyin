package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;
import java.util.Date;

public class ChbAstDpboSub extends ChbAstDpboSubKey {
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
     * 交易流通场所  参照数据字典
     */
    private String tradeMarket;

    /**
     * 资金存入银行
     */
    private String bankName;

    /**
     * 存款金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal prinAmt;

    /**
     * 起息日
     */
    private Date vdate;

    /**
     * 到期日
     */
    private Date mdate;

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
     * 是否为结构性存款 参照数据字典
     */
    private String isStruct;

    /**
     * 挂钩标的类别 参照数据字典
     */
    private String targetType;

    /**
     * 结构性存款挂钩标的
     */
    private String target;

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
     * 取得 交易流通场所  参照数据字典
     * @return 交易流通场所  参照数据字典
     */
    public String getTradeMarket() {
        return tradeMarket;
    }

    /**
     * 设置 交易流通场所  参照数据字典
     * @param tradeMarket 交易流通场所  参照数据字典
     */
    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket == null ? null : tradeMarket.trim();
    }

    /**
     * 取得 资金存入银行
     * @return 资金存入银行
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * 设置 资金存入银行
     * @param bankName 资金存入银行
     */
    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    /**
     * 取得 存款金额
     * @return 存款金额
     */
    public BigDecimal getPrinAmt() {
        return prinAmt;
    }

    /**
     * 设置 存款金额
     * @param prinAmt 存款金额
     */
    public void setPrinAmt(BigDecimal prinAmt) {
        this.prinAmt = prinAmt;
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
     * 取得 是否为结构性存款 参照数据字典
     * @return 是否为结构性存款 参照数据字典
     */
    public String getIsStruct() {
        return isStruct;
    }

    /**
     * 设置 是否为结构性存款 参照数据字典
     * @param isStruct 是否为结构性存款 参照数据字典
     */
    public void setIsStruct(String isStruct) {
        this.isStruct = isStruct == null ? null : isStruct.trim();
    }

    /**
     * 取得 挂钩标的类别 参照数据字典
     * @return 挂钩标的类别 参照数据字典
     */
    public String getTargetType() {
        return targetType;
    }

    /**
     * 设置 挂钩标的类别 参照数据字典
     * @param targetType 挂钩标的类别 参照数据字典
     */
    public void setTargetType(String targetType) {
        this.targetType = targetType == null ? null : targetType.trim();
    }

    /**
     * 取得 结构性存款挂钩标的
     * @return 结构性存款挂钩标的
     */
    public String getTarget() {
        return target;
    }

    /**
     * 设置 结构性存款挂钩标的
     * @param target 结构性存款挂钩标的
     */
    public void setTarget(String target) {
        this.target = target == null ? null : target.trim();
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