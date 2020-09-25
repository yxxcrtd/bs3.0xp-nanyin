package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;
import java.util.Date;

public class ChbAstOthdebtSub extends ChbAstOthdebtSubKey {
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
     * 所属国家或地区 参照数据字典
     */
    private String countryArea;

    /**
     * 名称
     */
    private String name;

    /**
     * 期限(天)
     */
    private Integer term;

    /**
     * 负债规模
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal debtScale;

    /**
     * 利率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal rate;

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
     * 取得 所属国家或地区 参照数据字典
     * @return 所属国家或地区 参照数据字典
     */
    public String getCountryArea() {
        return countryArea;
    }

    /**
     * 设置 所属国家或地区 参照数据字典
     * @param countryArea 所属国家或地区 参照数据字典
     */
    public void setCountryArea(String countryArea) {
        this.countryArea = countryArea == null ? null : countryArea.trim();
    }

    /**
     * 取得 名称
     * @return 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置 名称
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 取得 期限(天)
     * @return 期限(天)
     */
    public Integer getTerm() {
        return term;
    }

    /**
     * 设置 期限(天)
     * @param term 期限(天)
     */
    public void setTerm(Integer term) {
        this.term = term;
    }

    /**
     * 取得 负债规模
     * @return 负债规模
     */
    public BigDecimal getDebtScale() {
        return debtScale;
    }

    /**
     * 设置 负债规模
     * @param debtScale 负债规模
     */
    public void setDebtScale(BigDecimal debtScale) {
        this.debtScale = debtScale;
    }

    /**
     * 取得 利率
     * @return 利率
     */
    public BigDecimal getRate() {
        return rate;
    }

    /**
     * 设置 利率
     * @param rate 利率
     */
    public void setRate(BigDecimal rate) {
        this.rate = rate;
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