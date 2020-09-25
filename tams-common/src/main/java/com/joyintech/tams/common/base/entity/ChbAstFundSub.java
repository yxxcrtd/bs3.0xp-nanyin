package com.joyintech.tams.common.base.entity;

import java.util.Date;

public class ChbAstFundSub extends ChbAstFundSubKey {
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
     * 基金代码
     */
    private String code;

    /**
     * 基金名称
     */
    private String name;

    /**
     * 发行机构所属行业 参照数据字典
     */
    private String industry;

    /**
     * 行业二级分类 参照数据字典
     */
    private String industryLevel2;

    /**
     * 登记备案机构 参照数据字典
     */
    private String registOrg;

    /**
     * 产业投资基金公司名称
     */
    private String fundCompName;

    /**
     * 基金管理机构名称
     */
    private String mngOrgName;

    /**
     * 基金托管机构名称
     */
    private String truOrgName;

    /**
     * 投资阶段 参照数据字典
     */
    private String investStage;

    /**
     * 发行机构类型（按规模划分） 参照数据字典
     */
    private String partyType1;

    /**
     * 发行机构类型（按经济类型划分） 参照数据字典
     */
    private String partyType2;

    /**
     * 发行机构类型（按技术领域划分） 参照数据字典
     */
    private String partyType3;

    /**
     * 基金投资资产
     */
    private String fundInvestAsset;

    /**
     * 是否政府投资基金
     */
    private String isGovInvFund;

    /**
     * 具体投向
     */
    private String speGoal;

    /**
     * FOF投向
     */
    private String fofGoal;

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
     * 取得 基金代码
     * @return 基金代码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置 基金代码
     * @param code 基金代码
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 取得 基金名称
     * @return 基金名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置 基金名称
     * @param name 基金名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 取得 发行机构所属行业 参照数据字典
     * @return 发行机构所属行业 参照数据字典
     */
    public String getIndustry() {
        return industry;
    }

    /**
     * 设置 发行机构所属行业 参照数据字典
     * @param industry 发行机构所属行业 参照数据字典
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
     * 取得 登记备案机构 参照数据字典
     * @return 登记备案机构 参照数据字典
     */
    public String getRegistOrg() {
        return registOrg;
    }

    /**
     * 设置 登记备案机构 参照数据字典
     * @param registOrg 登记备案机构 参照数据字典
     */
    public void setRegistOrg(String registOrg) {
        this.registOrg = registOrg == null ? null : registOrg.trim();
    }

    /**
     * 取得 产业投资基金公司名称
     * @return 产业投资基金公司名称
     */
    public String getFundCompName() {
        return fundCompName;
    }

    /**
     * 设置 产业投资基金公司名称
     * @param fundCompName 产业投资基金公司名称
     */
    public void setFundCompName(String fundCompName) {
        this.fundCompName = fundCompName == null ? null : fundCompName.trim();
    }

    /**
     * 取得 基金管理机构名称
     * @return 基金管理机构名称
     */
    public String getMngOrgName() {
        return mngOrgName;
    }

    /**
     * 设置 基金管理机构名称
     * @param mngOrgName 基金管理机构名称
     */
    public void setMngOrgName(String mngOrgName) {
        this.mngOrgName = mngOrgName == null ? null : mngOrgName.trim();
    }

    /**
     * 取得 基金托管机构名称
     * @return 基金托管机构名称
     */
    public String getTruOrgName() {
        return truOrgName;
    }

    /**
     * 设置 基金托管机构名称
     * @param truOrgName 基金托管机构名称
     */
    public void setTruOrgName(String truOrgName) {
        this.truOrgName = truOrgName == null ? null : truOrgName.trim();
    }

    /**
     * 取得 投资阶段 参照数据字典
     * @return 投资阶段 参照数据字典
     */
    public String getInvestStage() {
        return investStage;
    }

    /**
     * 设置 投资阶段 参照数据字典
     * @param investStage 投资阶段 参照数据字典
     */
    public void setInvestStage(String investStage) {
        this.investStage = investStage == null ? null : investStage.trim();
    }

    /**
     * 取得 发行机构类型（按规模划分） 参照数据字典
     * @return 发行机构类型（按规模划分） 参照数据字典
     */
    public String getPartyType1() {
        return partyType1;
    }

    /**
     * 设置 发行机构类型（按规模划分） 参照数据字典
     * @param partyType1 发行机构类型（按规模划分） 参照数据字典
     */
    public void setPartyType1(String partyType1) {
        this.partyType1 = partyType1 == null ? null : partyType1.trim();
    }

    /**
     * 取得 发行机构类型（按经济类型划分） 参照数据字典
     * @return 发行机构类型（按经济类型划分） 参照数据字典
     */
    public String getPartyType2() {
        return partyType2;
    }

    /**
     * 设置 发行机构类型（按经济类型划分） 参照数据字典
     * @param partyType2 发行机构类型（按经济类型划分） 参照数据字典
     */
    public void setPartyType2(String partyType2) {
        this.partyType2 = partyType2 == null ? null : partyType2.trim();
    }

    /**
     * 取得 发行机构类型（按技术领域划分） 参照数据字典
     * @return 发行机构类型（按技术领域划分） 参照数据字典
     */
    public String getPartyType3() {
        return partyType3;
    }

    /**
     * 设置 发行机构类型（按技术领域划分） 参照数据字典
     * @param partyType3 发行机构类型（按技术领域划分） 参照数据字典
     */
    public void setPartyType3(String partyType3) {
        this.partyType3 = partyType3 == null ? null : partyType3.trim();
    }

    /**
     * 取得 基金投资资产
     * @return 基金投资资产
     */
    public String getFundInvestAsset() {
        return fundInvestAsset;
    }

    /**
     * 设置 基金投资资产
     * @param fundInvestAsset 基金投资资产
     */
    public void setFundInvestAsset(String fundInvestAsset) {
        this.fundInvestAsset = fundInvestAsset == null ? null : fundInvestAsset.trim();
    }

    /**
     * 取得 是否政府投资基金
     * @return 是否政府投资基金
     */
    public String getIsGovInvFund() {
        return isGovInvFund;
    }

    /**
     * 设置 是否政府投资基金
     * @param isGovInvFund 是否政府投资基金
     */
    public void setIsGovInvFund(String isGovInvFund) {
        this.isGovInvFund = isGovInvFund == null ? null : isGovInvFund.trim();
    }

    /**
     * 取得 具体投向
     * @return 具体投向
     */
    public String getSpeGoal() {
        return speGoal;
    }

    /**
     * 设置 具体投向
     * @param speGoal 具体投向
     */
    public void setSpeGoal(String speGoal) {
        this.speGoal = speGoal == null ? null : speGoal.trim();
    }

    /**
     * 取得 FOF投向
     * @return FOF投向
     */
    public String getFofGoal() {
        return fofGoal;
    }

    /**
     * 设置 FOF投向
     * @param fofGoal FOF投向
     */
    public void setFofGoal(String fofGoal) {
        this.fofGoal = fofGoal == null ? null : fofGoal.trim();
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