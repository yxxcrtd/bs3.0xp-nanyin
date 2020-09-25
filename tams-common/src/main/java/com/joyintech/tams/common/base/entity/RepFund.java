package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class RepFund extends BaseEntity {
    /**
     * 金融产品代码
     */
    private String repFinprodId;

    /**
     * 行业
     */
    private String repIndustry;

    /**
     * 行业二级分类
     */
    private String repIndustryLevel2;

    /**
     * 登记托管机构
     */
    private String repRegistOrg;

    /**
     * 是否属于政府投资基金
     */
    private String repIsGovInvest;

    /**
     * 政府投资基金投向
     */
    private String repGovInvestFor;

    /**
     * 基金公司名称
     */
    private String repFundCompName;

    /**
     * 基金管理机构名称
     */
    private String repMngPartyName;

    /**
     * 基金托管机构名称
     */
    private String repTrustPartyName;

    /**
     * 投资阶段
     */
    private String repInvestStage;

    /**
     * 企业类型（按规模划分）
     */
    private String repPartyType1;

    /**
     * 企业类型（按经济类型划分）
     */
    private String repPartyType2;

    /**
     * 企业类型（按技术领域划分）
     */
    private String repPartyType3;

    /**
     * 基金投资资产
     */
    private String repFundInvestAsset;

    /**
     * 备注
     */
    private String repRemark;
    
    /**
     * 是否由金融资产投资公司发行  中债新增字段 20200407
     */
    private String repFinancialComissue;

    /**
     * 取得 金融产品代码
     * @return 金融产品代码
     */
    public String getRepFinprodId() {
        return repFinprodId;
    }

    /**
     * 设置 金融产品代码
     * @param repFinprodId 金融产品代码
     */
    public void setRepFinprodId(String repFinprodId) {
        this.repFinprodId = repFinprodId == null ? null : repFinprodId.trim();
    }

    /**
     * 取得 行业
     * @return 行业
     */
    public String getRepIndustry() {
        return repIndustry;
    }

    /**
     * 设置 行业
     * @param repIndustry 行业
     */
    public void setRepIndustry(String repIndustry) {
        this.repIndustry = repIndustry == null ? null : repIndustry.trim();
    }

    /**
     * 取得 行业二级分类
     * @return 行业二级分类
     */
    public String getRepIndustryLevel2() {
        return repIndustryLevel2;
    }

    /**
     * 设置 行业二级分类
     * @param repIndustryLevel2 行业二级分类
     */
    public void setRepIndustryLevel2(String repIndustryLevel2) {
        this.repIndustryLevel2 = repIndustryLevel2 == null ? null : repIndustryLevel2.trim();
    }

    /**
     * 取得 登记托管机构
     * @return 登记托管机构
     */
    public String getRepRegistOrg() {
        return repRegistOrg;
    }

    /**
     * 设置 登记托管机构
     * @param repRegistOrg 登记托管机构
     */
    public void setRepRegistOrg(String repRegistOrg) {
        this.repRegistOrg = repRegistOrg == null ? null : repRegistOrg.trim();
    }

    /**
     * 取得 是否属于政府投资基金
     * @return 是否属于政府投资基金
     */
    public String getRepIsGovInvest() {
        return repIsGovInvest;
    }

    /**
     * 设置 是否属于政府投资基金
     * @param repIsGovInvest 是否属于政府投资基金
     */
    public void setRepIsGovInvest(String repIsGovInvest) {
        this.repIsGovInvest = repIsGovInvest == null ? null : repIsGovInvest.trim();
    }

    /**
     * 取得 政府投资基金投向
     * @return 政府投资基金投向
     */
    public String getRepGovInvestFor() {
        return repGovInvestFor;
    }

    /**
     * 设置 政府投资基金投向
     * @param repGovInvestFor 政府投资基金投向
     */
    public void setRepGovInvestFor(String repGovInvestFor) {
        this.repGovInvestFor = repGovInvestFor == null ? null : repGovInvestFor.trim();
    }

    /**
     * 取得 基金公司名称
     * @return 基金公司名称
     */
    public String getRepFundCompName() {
        return repFundCompName;
    }

    /**
     * 设置 基金公司名称
     * @param repFundCompName 基金公司名称
     */
    public void setRepFundCompName(String repFundCompName) {
        this.repFundCompName = repFundCompName == null ? null : repFundCompName.trim();
    }

    /**
     * 取得 基金管理机构名称
     * @return 基金管理机构名称
     */
    public String getRepMngPartyName() {
        return repMngPartyName;
    }

    /**
     * 设置 基金管理机构名称
     * @param repMngPartyName 基金管理机构名称
     */
    public void setRepMngPartyName(String repMngPartyName) {
        this.repMngPartyName = repMngPartyName == null ? null : repMngPartyName.trim();
    }

    /**
     * 取得 基金托管机构名称
     * @return 基金托管机构名称
     */
    public String getRepTrustPartyName() {
        return repTrustPartyName;
    }

    /**
     * 设置 基金托管机构名称
     * @param repTrustPartyName 基金托管机构名称
     */
    public void setRepTrustPartyName(String repTrustPartyName) {
        this.repTrustPartyName = repTrustPartyName == null ? null : repTrustPartyName.trim();
    }

    /**
     * 取得 投资阶段
     * @return 投资阶段
     */
    public String getRepInvestStage() {
        return repInvestStage;
    }

    /**
     * 设置 投资阶段
     * @param repInvestStage 投资阶段
     */
    public void setRepInvestStage(String repInvestStage) {
        this.repInvestStage = repInvestStage == null ? null : repInvestStage.trim();
    }

    /**
     * 取得 企业类型（按规模划分）
     * @return 企业类型（按规模划分）
     */
    public String getRepPartyType1() {
        return repPartyType1;
    }

    /**
     * 设置 企业类型（按规模划分）
     * @param repPartyType1 企业类型（按规模划分）
     */
    public void setRepPartyType1(String repPartyType1) {
        this.repPartyType1 = repPartyType1 == null ? null : repPartyType1.trim();
    }

    /**
     * 取得 企业类型（按经济类型划分）
     * @return 企业类型（按经济类型划分）
     */
    public String getRepPartyType2() {
        return repPartyType2;
    }

    /**
     * 设置 企业类型（按经济类型划分）
     * @param repPartyType2 企业类型（按经济类型划分）
     */
    public void setRepPartyType2(String repPartyType2) {
        this.repPartyType2 = repPartyType2 == null ? null : repPartyType2.trim();
    }

    /**
     * 取得 企业类型（按技术领域划分）
     * @return 企业类型（按技术领域划分）
     */
    public String getRepPartyType3() {
        return repPartyType3;
    }

    /**
     * 设置 企业类型（按技术领域划分）
     * @param repPartyType3 企业类型（按技术领域划分）
     */
    public void setRepPartyType3(String repPartyType3) {
        this.repPartyType3 = repPartyType3 == null ? null : repPartyType3.trim();
    }

    /**
     * 取得 基金投资资产
     * @return 基金投资资产
     */
    public String getRepFundInvestAsset() {
        return repFundInvestAsset;
    }

    /**
     * 设置 基金投资资产
     * @param repFundInvestAsset 基金投资资产
     */
    public void setRepFundInvestAsset(String repFundInvestAsset) {
        this.repFundInvestAsset = repFundInvestAsset == null ? null : repFundInvestAsset.trim();
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRepRemark() {
        return repRemark;
    }

    /**
     * 设置 备注
     * @param repRemark 备注
     */
    public void setRepRemark(String repRemark) {
        this.repRemark = repRemark == null ? null : repRemark.trim();
    }

	/**
	 * @return the repFinancialComissue
	 */
	public String getRepFinancialComissue() {
		return repFinancialComissue;
	}

	/**
	 * @param repFinancialComissue the repFinancialComissue to set
	 */
	public void setRepFinancialComissue(String repFinancialComissue) {
		this.repFinancialComissue = repFinancialComissue;
	}
}