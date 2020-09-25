/*
 * 文件名：FlowRiskFactroRManageDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2019年8月21日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.stresstestmanage.marketriskstresstestview.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.grid.GridRowDto;
import com.joyintech.framework.util.StringUtil;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author lifangchen
 * @version 1.0 2019年8月21日
 * @see MarketRiskStressTestView002Dto
 * @since 1.0
 */
public class MarketRiskStressTestView002Dto extends BaseEntity implements ITableRow, GridRowDto{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -253867465191421606L;
	/**
	 * 
	 */
	
	private String testId;//压力测试编号
	private String testName;//压力测试名称
	private String portfolioName;//组合名称
	private String portfolioId;//组合名称
	private String fPortfolioId;//父组合名称
	private String finprodId;//产品编号
    private String finprodName;//产品名称
    private String assetId;//资产代码
    private String assetName;//资产名称
    private String idTree;//树id
    private String type8;//一级
    private String type9;//二级
    private String type10;//三级
    private String type11;//四级
    private String type12;//五级
    private String assetType2;//资产二级类型
    
    private String sceneIdAndsceneName;//压力测试编号和名称
    
    //产品指标
    private String openAmount;//持仓数量
    private String currentPrice;//当前价格
    private String marketCap;//持仓市值
    private String allAssetsRate;//占组合总资产比(%)产品指标
    private String netAssetsRate;//占组合净资产比(%)产品指标
    
    //承压指标
    private String scenePrice;//情景价格
    private String priceChange;//价格变动
    private String sceneMarVal;//情景市值
    private String allAssetsRate2;//占组合总资产比(%)承压指标
    private String netAssetsRate2;//占组合净资产比(%)承压指标
    private String proAndLosRat;//损益比例
    private String amountOfDam;//损失金额
    
    private String baseDate;//压测基准日
    private String startTime;//压测执行时间
    private String testStartDate;//压测开始日
    private String testEndDate;//压测结束日
    
    private String indId;//指标代码
    private String indName;//指标名称
    private String remark;//备注

    
    
    /**
	 * @return sceneIdAndsceneName
	 */
	public String getSceneIdAndsceneName() {
		return sceneIdAndsceneName;
	}


	/**
	 * @param sceneIdAndsceneName 要设置的 sceneIdAndsceneName
	 */
	public void setSceneIdAndsceneName(String sceneIdAndsceneName) {
		this.sceneIdAndsceneName = sceneIdAndsceneName;
	}


	/**
     * 行ID
     */
    private String rowId;
    /**
     * 是否有子节点
     */
    private String hasChildren;
    /**
     * 名称
     */
    private String name;
	
    
    public String getParentId() {
        return this.getfPortfolioId();
    }


    public void setParentId(String parentId) {
        setfPortfolioId(parentId);
    }
    /**
	 * @return startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime 要设置的 startTime
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return portfolioId
	 */
	public String getPortfolioId() {
		return portfolioId;
	}

	/**
	 * @param portfolioId 要设置的 portfolioId
	 */
	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId;
	}

	/**
	 * @return baseDate
	 */
	public String getBaseDate() {
		return baseDate;
	}

	/**
	 * @param baseDate 要设置的 baseDate
	 */
	public void setBaseDate(String baseDate) {
		this.baseDate = baseDate;
	}

	/**
	 * @return testStartDate
	 */
	public String getTestStartDate() {
		return testStartDate;
	}

	/**
	 * @param testStartDate 要设置的 testStartDate
	 */
	public void setTestStartDate(String testStartDate) {
		this.testStartDate = testStartDate;
	}

	/**
	 * @return testEndDate
	 */
	public String getTestEndDate() {
		return testEndDate;
	}

	/**
	 * @param testEndDate 要设置的 testEndDate
	 */
	public void setTestEndDate(String testEndDate) {
		this.testEndDate = testEndDate;
	}

	/**
     * 获取 portfolioName
     * @return portfolioName.
     */
    public String getPortfolioName() {
        return portfolioName;
    }
    
    /**
     * 设置 portfolioName
     * @param portfolioName portfolioName
     */
    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }
    
    /**
     * 获取 finprodId
     * @return finprodId.
     */
    public String getFinprodId() {
        return finprodId;
    }
    
    /**
     * 设置 finprodId
     * @param finprodId finprodId
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }
    
    /**
     * 获取 finprodName
     * @return finprodName.
     */
    public String getFinprodName() {
        return finprodName;
    }
    
    /**
     * 设置 finprodName
     * @param finprodName finprodName
     */
    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }
    
    /**
     * 得到indId
     * @return
     */
	public String getIndId() {
		return indId;
	}
	/**
	 * 设置 indId
	 * @param indId
	 */
	public void setIndId(String indId) {
		this.indId = indId;
	}
	/**
	 * 得到 remark
	 * @return
	 */
	public String getIndName() {
		return indName;
	}
	/**
	 * 设置 indName
	 * @param indName
	 */
	public void setIndName(String indName) {
		this.indName = indName;
	}
	/**
	 * 	得到 remark
	 * @return
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 设置 reamrk
	 * @param remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getTestId() {
		return testId;
	}

	public void setTestId(String testId) {
		this.testId = testId;
	}

	public String getTestName() {
		return testName;
	}


	public void setTestName(String testName) {
		this.testName = testName;
	}


	public String getOpenAmount() {
		return openAmount;
	}

	public void setOpenAmount(String openAmount) {
		this.openAmount = openAmount;
	}

	public String getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(String currentPrice) {
		this.currentPrice = currentPrice;
	}

	public String getMarketCap() {
		return marketCap;
	}

	public void setMarketCap(String marketCap) {
		this.marketCap = marketCap;
	}

	public String getAllAssetsRate() {
		return allAssetsRate;
	}

	public void setAllAssetsRate(String allAssetsRate) {
		if(allAssetsRate !="" && allAssetsRate!=null){
			allAssetsRate=(Double.valueOf(allAssetsRate)).toString();
    	}
		this.allAssetsRate = allAssetsRate;
	}

	public String getNetAssetsRate() {
		return netAssetsRate;
	}

	public void setNetAssetsRate(String netAssetsRate) {
		this.netAssetsRate = netAssetsRate;
	}

	public String getScenePrice() {
		return scenePrice;
	}

	public void setScenePrice(String scenePrice) {
		this.scenePrice = scenePrice;
	}

	public String getPriceChange() {
		return priceChange;
	}

	public void setPriceChange(String priceChange) {
		if(priceChange !="" && priceChange!=null){
			priceChange=(Double.valueOf(priceChange)).toString();
    	}
		this.priceChange = priceChange;
	}

	public String getSceneMarVal() {
		return sceneMarVal;
	}

	public void setSceneMarVal(String sceneMarVal) {
		this.sceneMarVal = sceneMarVal;
	}

	public String getAllAssetsRate2() {
		return allAssetsRate2;
	}

	public void setAllAssetsRate2(String allAssetsRate2) {
		if(allAssetsRate2 !="" && allAssetsRate2!=null){
			allAssetsRate2=(Double.valueOf(allAssetsRate2)).toString();
    	}
		this.allAssetsRate2 = allAssetsRate2;
	}

	public String getNetAssetsRate2() {
		return netAssetsRate2;
	}

	public void setNetAssetsRate2(String netAssetsRate2) {
		this.netAssetsRate2 = netAssetsRate2;
	}

	public String getProAndLosRat() {
		return proAndLosRat;
	}

	public void setProAndLosRat(String proAndLosRat) {
		if(proAndLosRat !="" && proAndLosRat!=null){
			proAndLosRat=(Double.valueOf(proAndLosRat)).toString();
    	}
		this.proAndLosRat = proAndLosRat;
	}

	public String getAmountOfDam() {
		return amountOfDam;
	}

	public void setAmountOfDam(String amountOfDam) {
		this.amountOfDam = amountOfDam;
	}

	public String getAssetId() {
		return assetId;
	}

	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}

	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}


	public String getfPortfolioId() {
		return fPortfolioId;
	}


	public void setfPortfolioId(String fPortfolioId) {
		this.fPortfolioId = fPortfolioId;
	}


	public String getHasChildren() {
		return hasChildren;
	}


	public void setHasChildren(String hasChildren) {
		this.hasChildren = hasChildren;
	}


	@Override
	public String getRowId() {
		return this.rowId;
	}


	@Override
	public void setRowId(String rowId) {
		this.rowId = rowId;
	}


	@Override
	public int getOrder() {
		return 0;
	}


	@Override
	public void setOrder(int order) {
		
	}


	@Override
	public String getId() {
//		return this.getAssetId();
		return this.getIdTree();
	}


	@Override
	public void setId(String id) {
//		setAssetId(id);
		setIdTree(id);
	}


	@Override
	public String getName() {
		return name;
	}


	@Override
	public void setName(String name) {
		this.name = name;
	}


	@Override
	public boolean isLeaf() {
		return StringUtil.equals(com.joyintech.framework.constant.DictCodeList.是否_是.getValue(), hasChildren);
	}


	@Override
	public void setLeaf(boolean leaf) {
		
	}


	public String getIdTree() {
		return idTree;
	}


	public void setIdTree(String idTree) {
		this.idTree = idTree;
	}


	public String getType8() {
		return type8;
	}


	public void setType8(String type8) {
		this.type8 = type8;
	}


	public String getType9() {
		return type9;
	}


	public void setType9(String type9) {
		this.type9 = type9;
	}


	public String getType10() {
		return type10;
	}


	public void setType10(String type10) {
		this.type10 = type10;
	}


	public String getType11() {
		return type11;
	}


	public void setType11(String type11) {
		this.type11 = type11;
	}


	public String getType12() {
		return type12;
	}


	public void setType12(String type12) {
		this.type12 = type12;
	}


	public String getAssetType2() {
		return assetType2;
	}


	public void setAssetType2(String assetType2) {
		this.assetType2 = assetType2;
	}
    
	
    
}
