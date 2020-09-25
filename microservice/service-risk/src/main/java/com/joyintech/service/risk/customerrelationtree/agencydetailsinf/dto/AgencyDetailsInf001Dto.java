/*
 * 文件名：AgencyDetailsInf001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangkang
 * 修改时间：2019年12月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.customerrelationtree.agencydetailsinf.dto;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;

/**
 *  机构详细信息查询dto
 * 〈功能详细描述〉
 * @author wangkang
 * @version 1.0 2019年12月10日
 * @see AgencyDetailsInf001Dto
 * @since 1.0
 */
public class AgencyDetailsInf001Dto extends BaseEntity{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 画面Id - 工作流参数
     */
    private String pageId;
    
    /**
     * 画面处理模式
     */
    private String pageModel;

    /**
     * 操作类型 - 工作流参数
     */
    private String operateType;

    /**
     * 按钮ID - 工作流参数
     */
    private String elementId;
    
    /**
     *  机构名称
     */
    private String agencyName ;
    
    /**
     * 机构代码
     */
    private String agencyId ;
    
    /**
     * 序号
     */
    private String seqNo ;
    
    /**
     * 关联分类
     */
    private String corClass ;
    
    /**
     * 机构属性
     */
    private String agencyAttr ;
    
    /**
     * 组合代码
     */
    private String portfolioId ;
    
    /**
     *  组合名称
     */
    private String portfolioName ;
    
    /**
     * 资产一级分类
     */
    private String assetTypeOne ;
    
    /**
     * 资产二级分类
     */
    private String assetTypeTwo ;
    
    /**
     * 资产三级分类
     */
    private String assetTypeThree ;
    
    /**
     * 资产四级分类
     */
    private String assetTypeFour ;
    
    /**
     * 资产五级分类
     */
    private String assetTypeFive ;
    
    /**
     * 资产名称
     */
    private String assetName ;
    
    /**
     * 资产代码
     */
    private String assetId ;
    
    
    /**
     * 资产类型
     */
    private String assetType ;
    
    /**
     * 数量
     */
//    @JsonSerialize(using=StringSerializer.class)
    private String amount ;
    
    /**
     * 市值（元）
     */
//    @JsonSerialize(using=StringSerializer.class)
    private String marketValue ;
    
    /**
     * 成本（元）
     */
//    @JsonSerialize(using=StringSerializer.class)
    private String cost ;
    
    /**
     * 应收利息（元）
     */
//    @JsonSerialize(using=StringSerializer.class)
    private String interestReceivable ;
    
    /**
     * 直接投资
     */
    private String directInvestment ;
    
    /**
     *  成本（元）
     */
//    @JsonSerialize(using=StringSerializer.class)
    private String diCost ;
    
    /**
     *  数量
     */
//    @JsonSerialize(using=StringSerializer.class)
    private String diAmount ;
    
    /**
     *  市值（元）
     */
//    @JsonSerialize(using=StringSerializer.class)
    private String diMarketValue ;
    
    /**
     *  应收利息（元）
     */
//    @JsonSerialize(using=StringSerializer.class)
    private String diInterestReceivable ;
    
    /**
     *  间接投资
     */
    private String indirectInvestment ;
    
    /**
     *  通道
     */
    private String iiPass ;
    
    /**
     *  成本（元）
     */
//    @JsonSerialize(using=StringSerializer.class)
    private String iiCost ;
    
    /**
     *  数量
     */
    private String iiAmount ;
    
    /**
     * 市值（元）
     */
//    @JsonSerialize(using=StringSerializer.class)
    private String iiMarketValue ;
    
    /**
     * 发行人
     */
    private String issuer;
    
    /**
     * 融资人
     */
    private String financier;
    
    /**
     * 管理人
     */
    private String manager;
    
    /**
     * 担保人
     */
    private String sponsor;
    
    /**
     * 投资顾问
     */
    private String investAdviser;
    
    /**
     * 关联机构类型
     */
    private String corOrgtype;
    
    /**
     * 父id
     */
    private String pId;
    
    /**
     * 父name
     */
    private String pName;
    
    
    /**
     * id
     */
    private String id;
    
    /**
     * 机构名称
     */
    private String name;
    
    /**
     * 持仓汇总
     */
    private String positionAll;
    /**
     *  应收利息（元）
     */
//    @JsonSerialize(using=StringSerializer.class)
    private String iiInterestReceivable ;

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getPageModel() {
        return pageModel;
    }

    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    public String getAgencyAttr() {
        return agencyAttr;
    }

    public void setAgencyAttr(String agencyAttr) {
        this.agencyAttr = agencyAttr;
    }

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    public String getPortfolioName() {
        return portfolioName;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    public String getAssetTypeOne() {
        return assetTypeOne;
    }

    public void setAssetTypeOne(String assetTypeOne) {
        this.assetTypeOne = assetTypeOne;
    }

    public String getAssetTypeTwo() {
        return assetTypeTwo;
    }

    public void setAssetTypeTwo(String assetTypeTwo) {
        this.assetTypeTwo = assetTypeTwo;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(String marketValue) {
        this.marketValue = marketValue;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getInterestReceivable() {
        return interestReceivable;
    }

    public void setInterestReceivable(String interestReceivable) {
        this.interestReceivable = interestReceivable;
    }

    public String getDirectInvestment() {
        return directInvestment;
    }

    public void setDirectInvestment(String directInvestment) {
        this.directInvestment = directInvestment;
    }

    public String getDiCost() {
        return diCost;
    }

    public void setDiCost(String diCost) {
        this.diCost = diCost;
    }

    public String getDiAmount() {
        return diAmount;
    }

    public void setDiAmount(String diAmount) {
        this.diAmount = diAmount;
    }

    public String getDiMarketValue() {
        return diMarketValue;
    }

    public void setDiMarketValue(String diMarketValue) {
        this.diMarketValue = diMarketValue;
    }

    public String getDiInterestReceivable() {
        return diInterestReceivable;
    }

    public void setDiInterestReceivable(String diInterestReceivable) {
        this.diInterestReceivable = diInterestReceivable;
    }

    public String getIndirectInvestment() {
        return indirectInvestment;
    }

    public void setIndirectInvestment(String indirectInvestment) {
        this.indirectInvestment = indirectInvestment;
    }

    public String getIiPass() {
//      return SysDataUtil.getNameFromDataDict(DictCode.回购拆借方向, super.getTradeDirection());
        return iiPass;
    }

    public void setIiPass(String iiPass) {
        this.iiPass = iiPass;
    }

    public String getIiCost() {
        return iiCost;
    }

    public void setIiCost(String iiCost) {
        this.iiCost = iiCost;
    }

    public String getIiAmount() {
        return iiAmount;
    }

    public void setIiAmount(String iiAmount) {
        this.iiAmount = iiAmount;
    }

    public String getIiMarketValue() {
        return iiMarketValue;
    }

    public void setIiMarketValue(String iiMarketValue) {
        this.iiMarketValue = iiMarketValue;
    }
    
    public String getIiInterestReceivable() {
        return iiInterestReceivable;
    }

    public void setIiInterestReceivable(String iiInterestReceivable) {
        this.iiInterestReceivable = iiInterestReceivable;
    }

    
    public String getIssuer() {
        return issuer;
    }

    
    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    
    public String getFinancier() {
        return financier;
    }

    
    public void setFinancier(String financier) {
        this.financier = financier;
    }

    
    public String getManager() {
        return manager;
    }

    
    public void setManager(String manager) {
        this.manager = manager;
    }

    
    public String getSponsor() {
        return sponsor;
    }

    
    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    
    public String getInvestAdviser() {
        return investAdviser;
    }

    
    public void setInvestAdviser(String investAdviser) {
        this.investAdviser = investAdviser;
    }

    
    public String getCorOrgtype() {
        return corOrgtype;
    }

    
    public void setCorOrgtype(String corOrgtype) {
        this.corOrgtype = corOrgtype;
    }

    
    public String getpId() {
        return pId;
    }

    
    public void setpId(String pId) {
        this.pId = pId;
    }

    
    public String getId() {
        return id;
    }

    
    public void setId(String id) {
        this.id = id;
    }

    
    public String getName() {
        return name;
    }

    
    public void setName(String name) {
        this.name = name;
    }

    
    public String getpName() {
        return pName;
    }

    
    public void setpName(String pName) {
        this.pName = pName;
    }

	public String getAssetTypeThree() {
		return assetTypeThree;
	}

	public void setAssetTypeThree(String assetTypeThree) {
		this.assetTypeThree = assetTypeThree;
	}

	public String getAssetTypeFour() {
		return assetTypeFour;
	}

	public void setAssetTypeFour(String assetTypeFour) {
		this.assetTypeFour = assetTypeFour;
	}

	public String getAssetTypeFive() {
		return assetTypeFive;
	}

	public void setAssetTypeFive(String assetTypeFive) {
		this.assetTypeFive = assetTypeFive;
	}

	public String getCorClass() {
		return corClass;
	}

	public void setCorClass(String corClass) {
		this.corClass = corClass;
	}

	public String getPositionAll() {
		return positionAll;
	}

	public void setPositionAll(String positionAll) {
		this.positionAll = positionAll;
	}
    
    
}
