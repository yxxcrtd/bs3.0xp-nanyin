package com.joyintech.tams.common.base.entity;

import java.util.Date;

public class ChbAstQdiiShareSub extends ChbAstQdiiShareSubKey {
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
     * 股票/基金代码
     */
    private String shrsFundId;

    /**
     * 股票/基金名称
     */
    private String shrsFundName;

    /**
     * 发行机构
     */
    private String issueParty;

    /**
     * 行业 参照数据字典
     */
    private String industry;

    /**
     * 行业二级分类 参照数据字典
     */
    private String industryLevel2;

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
     * 取得 股票/基金代码
     * @return 股票/基金代码
     */
    public String getShrsFundId() {
        return shrsFundId;
    }

    /**
     * 设置 股票/基金代码
     * @param shrsFundId 股票/基金代码
     */
    public void setShrsFundId(String shrsFundId) {
        this.shrsFundId = shrsFundId == null ? null : shrsFundId.trim();
    }

    /**
     * 取得 股票/基金名称
     * @return 股票/基金名称
     */
    public String getShrsFundName() {
        return shrsFundName;
    }

    /**
     * 设置 股票/基金名称
     * @param shrsFundName 股票/基金名称
     */
    public void setShrsFundName(String shrsFundName) {
        this.shrsFundName = shrsFundName == null ? null : shrsFundName.trim();
    }

    /**
     * 取得 发行机构
     * @return 发行机构
     */
    public String getIssueParty() {
        return issueParty;
    }

    /**
     * 设置 发行机构
     * @param issueParty 发行机构
     */
    public void setIssueParty(String issueParty) {
        this.issueParty = issueParty == null ? null : issueParty.trim();
    }

    /**
     * 取得 行业 参照数据字典
     * @return 行业 参照数据字典
     */
    public String getIndustry() {
        return industry;
    }

    /**
     * 设置 行业 参照数据字典
     * @param industry 行业 参照数据字典
     */
    public void setIndustry(String industry) {
        this.industry = industry == null ? null : industry.trim();
    }

    /**
     * 取得 行业二级分类 参照数据字典
     * @return 行业二级分类 参照数据字典
     */
    public String getIndustryLevel2() {
        return industryLevel2;
    }

    /**
     * 设置 行业二级分类 参照数据字典
     * @param industryLevel2 行业二级分类 参照数据字典
     */
    public void setIndustryLevel2(String industryLevel2) {
        this.industryLevel2 = industryLevel2 == null ? null : industryLevel2.trim();
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