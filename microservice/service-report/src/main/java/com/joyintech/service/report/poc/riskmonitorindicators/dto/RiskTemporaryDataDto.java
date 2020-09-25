/*
 * 文件名：ProductLedger001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2019年9月24日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.report.poc.riskmonitorindicators.dto;

import java.math.BigDecimal;
 
/**
 * 风险监测指标 (临时数据)
 * @author wangyang1
 * @version 1.0 2019年11月20日
 * @see RiskTemporaryDataDto
 * @since 1.0
 */
public class RiskTemporaryDataDto {
    
     //组合名称  
    private String prodId;
    
    //产品经理
    private String prodManager;
    
    //资产名称
    private String assetCode;
    
    //净资产
    private BigDecimal netAsset;
    
    //总资产
    private BigDecimal totalAsset;
    
    //净资产
    private BigDecimal netQtyAsset;
    
    //总资产
    private BigDecimal totalQtyAsset;
    
    
    //穿透后净资产
    private BigDecimal netAssetPierce;
    
    //穿透后总资产
    private BigDecimal totalAssetPierce;
    
    //开放式高流动性资产
    private BigDecimal openHighMobilityAsset;
    
    //穿透后 开放式高流动性资产
    private BigDecimal openHighMobilityAssetPierce;
    
    //现金
    private BigDecimal cash;
    
    //穿透后 现金
    private BigDecimal cashPierce;
    
    //高流动性资产
    private BigDecimal highMobilityAsset;
    
    //穿透后 高流动性资产
    private BigDecimal highMobilityAssetPierce;
    
    // 单一最大认购客户集中度
    private BigDecimal SingleCustomerConcentration;
    
    //穿透后 单一最大认购客户集中度
    private BigDecimal SingleCustomerConcentrationPierce;
    
    //单一最大资产集中度
    private BigDecimal maxAssetsCD;
    
    //穿透后 单一最大资产集中度
    private BigDecimal maxAssetsCDPierce;
    
    //单一最大融资人集中度
    private BigDecimal largestFinancierCD;
    
    //穿透后 单一最大融资人集中度
    private BigDecimal largestFinancierCDPierce;
    
    //非公开债券集中度
    private BigDecimal nonPublicBondCD;
    
    //穿透后  非公开债券集中度
    private BigDecimal nonPublicBondCDPierce;
    
    //单一机构非标占资本净额比例
    private BigDecimal singleNonStandardrRatio;
    
    //非标资产占产品净资产比例  
    private BigDecimal netAassetsRatio;
    
    //非标资产占全行总资产比例 
    private BigDecimal totalAassetsRatio;
    
    //穿透后 非标资产占产品净资产比例  
    private BigDecimal singleNonStandardrRatioPierce;
    
    //穿透后 非标资产占产品净资产比例  
    private BigDecimal netAassetsRatioPierce;
    
    //穿透后 非标资产占全行总资产比例 
    private BigDecimal totalAassetsRatioPierce;
    
    //浮动盈亏金额
    private BigDecimal tdyFloatIngpl;
    
    //穿透后 浮动盈亏金额
    private BigDecimal tdyFloatIngplPierce;
   
    //债券久期
    private BigDecimal secDuration;
     
    private BigDecimal secDurationMol;
    
    private BigDecimal secDurationDel;
    //产品久期
    private BigDecimal productDuration;
    
    //资产久期
    private BigDecimal assetDuration;
    
    //是否是天天开放产品
    private String isDuration;
   
    //余额
    private BigDecimal totalMatchamt;
    
    //巨额赎回比例
    private BigDecimal hugeRedRatio;
    
    //募集下限
    private BigDecimal saleMin;
    
    private BigDecimal matchamt;
    
    private BigDecimal ssDuration;
    
    private BigDecimal assetDurationMol;
    
    private BigDecimal assetDurationDen;
    
    private BigDecimal openHighMobilityMol;
    
    private BigDecimal openHighMobilityDen;
    
    private BigDecimal highMobilityMol;
    
    private BigDecimal highMobilityDen;
    
    private BigDecimal tdyFloatIngplMol;
    
    private BigDecimal tdyFloatIngplDen;
    
    private BigDecimal durationmismatch;//久期错配
    private BigDecimal termmismatch;//期限错配
    private BigDecimal leverageratio;//杠杆率% 
 
    private BigDecimal mobilityAssetSummary;
    
    private BigDecimal singlejoinratio;//单一关联方单笔交易占资本净额比例
     
    public BigDecimal getSinglejoinratio() {
		return singlejoinratio;
	}

	public void setSinglejoinratio(BigDecimal singlejoinratio) {
		this.singlejoinratio = singlejoinratio;
	}


	/**
     * 获取 mobilityAssetSummary
     * @return mobilityAssetSummary.
     */
    public BigDecimal getMobilityAssetSummary() {
        return mobilityAssetSummary;
    }





    
    /**
     * 设置 mobilityAssetSummary
     * @param mobilityAssetSummary mobilityAssetSummary
     */
    public void setMobilityAssetSummary(BigDecimal mobilityAssetSummary) {
        this.mobilityAssetSummary = mobilityAssetSummary;
    }





    /**
     * 获取 durationmismatch
     * @return durationmismatch.
     */
    public BigDecimal getDurationmismatch() {
        return durationmismatch;
    }




    
    /**
     * 设置 durationmismatch
     * @param durationmismatch durationmismatch
     */
    public void setDurationmismatch(BigDecimal durationmismatch) {
        this.durationmismatch = durationmismatch;
    }
  
    /**
     * 获取 termmismatch
     * @return termmismatch.
     */
    public BigDecimal getTermmismatch() {
        return termmismatch;
    }
 
    /**
     * 设置 termmismatch
     * @param termmismatch termmismatch
     */
    public void setTermmismatch(BigDecimal termmismatch) {
        this.termmismatch = termmismatch;
    }
 
    /**
     * 获取 leverageratio
     * @return leverageratio.
     */
    public BigDecimal getLeverageratio() {
        return leverageratio;
    }
 
    
    /**
     * 设置 leverageratio
     * @param leverageratio leverageratio
     */
    public void setLeverageratio(BigDecimal leverageratio) {
        this.leverageratio = leverageratio;
    }
 
    /**
     * 获取 tdyFloatIngplMol
     * @return tdyFloatIngplMol.
     */
    public BigDecimal getTdyFloatIngplMol() {
        return tdyFloatIngplMol;
    }





    
    /**
     * 设置 tdyFloatIngplMol
     * @param tdyFloatIngplMol tdyFloatIngplMol
     */
    public void setTdyFloatIngplMol(BigDecimal tdyFloatIngplMol) {
        this.tdyFloatIngplMol = tdyFloatIngplMol;
    }





    
    /**
     * 获取 tdyFloatIngplDen
     * @return tdyFloatIngplDen.
     */
    public BigDecimal getTdyFloatIngplDen() {
        return tdyFloatIngplDen;
    }





    
    /**
     * 设置 tdyFloatIngplDen
     * @param tdyFloatIngplDen tdyFloatIngplDen
     */
    public void setTdyFloatIngplDen(BigDecimal tdyFloatIngplDen) {
        this.tdyFloatIngplDen = tdyFloatIngplDen;
    }





    /**
     * 获取 singleNonStandardrRatio
     * @return singleNonStandardrRatio.
     */
    public BigDecimal getSingleNonStandardrRatio() {
        return singleNonStandardrRatio;
    }
 
    /**
     * 设置 singleNonStandardrRatio
     * @param singleNonStandardrRatio singleNonStandardrRatio
     */
    public void setSingleNonStandardrRatio(BigDecimal singleNonStandardrRatio) {
        this.singleNonStandardrRatio = singleNonStandardrRatio;
    }
 
    /**
     * 获取 singleNonStandardrRatioPierce
     * @return singleNonStandardrRatioPierce.
     */
    public BigDecimal getSingleNonStandardrRatioPierce() {
        return singleNonStandardrRatioPierce;
    }

 
    /**
     * 设置 singleNonStandardrRatioPierce
     * @param singleNonStandardrRatioPierce singleNonStandardrRatioPierce
     */
    public void setSingleNonStandardrRatioPierce(BigDecimal singleNonStandardrRatioPierce) {
        this.singleNonStandardrRatioPierce = singleNonStandardrRatioPierce;
    }





    /**
     * 获取 matchamt
     * @return matchamt.
     */
    public BigDecimal getMatchamt() {
        return matchamt;
    }



    
    /**
     * 设置 matchamt
     * @param matchamt matchamt
     */
    public void setMatchamt(BigDecimal matchamt) {
        this.matchamt = matchamt;
    }



    /**
     * 获取 isDuration
     * @return isDuration.
     */
    public String getIsDuration() {
        return isDuration;
    }


    
    /**
     * 设置 isDuration
     * @param isDuration isDuration
     */
    public void setIsDuration(String isDuration) {
        this.isDuration = isDuration;
    }


    
    /**
     * 获取 totalMatchamt
     * @return totalMatchamt.
     */
    public BigDecimal getTotalMatchamt() {
        return totalMatchamt;
    }


    
    /**
     * 设置 totalMatchamt
     * @param totalMatchamt totalMatchamt
     */
    public void setTotalMatchamt(BigDecimal totalMatchamt) {
        this.totalMatchamt = totalMatchamt;
    }


    
    /**
     * 获取 hugeRedRatio
     * @return hugeRedRatio.
     */
    public BigDecimal getHugeRedRatio() {
        return hugeRedRatio;
    }


    
    /**
     * 设置 hugeRedRatio
     * @param hugeRedRatio hugeRedRatio
     */
    public void setHugeRedRatio(BigDecimal hugeRedRatio) {
        this.hugeRedRatio = hugeRedRatio;
    }


    
    /**
     * 获取 saleMin
     * @return saleMin.
     */
    public BigDecimal getSaleMin() {
        return saleMin;
    }


    
    /**
     * 设置 saleMin
     * @param saleMin saleMin
     */
    public void setSaleMin(BigDecimal saleMin) {
        this.saleMin = saleMin;
    }


    /**
     * 获取 prodId
     * @return prodId.
     */
    public String getProdId() {
        return prodId;
    }

    
    /**
     * 设置 prodId
     * @param prodId prodId
     */
    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    
    /**
     * 获取 prodManager
     * @return prodManager.
     */
    public String getProdManager() {
        return prodManager;
    }


    
    /**
     * 设置 prodManager
     * @param prodManager prodManager
     */
    public void setProdManager(String prodManager) {
        this.prodManager = prodManager;
    }


    /**
     * 获取 netAsset
     * @return netAsset.
     */
    public BigDecimal getNetAsset() {
        return netAsset;
    }
    
    /**
     * 设置 netAsset
     * @param netAsset netAsset
     */
    public void setNetAsset(BigDecimal netAsset) {
        this.netAsset = netAsset;
    }
    
    /**
     * 获取 totalAsset
     * @return totalAsset.
     */
    public BigDecimal getTotalAsset() {
        return totalAsset;
    }
    
    /**
     * 设置 totalAsset
     * @param totalAsset totalAsset
     */
    public void setTotalAsset(BigDecimal totalAsset) {
        this.totalAsset = totalAsset;
    }
    
    /**
     * 获取 openHighMobilityAsset
     * @return openHighMobilityAsset.
     */
    public BigDecimal getOpenHighMobilityAsset() {
        return openHighMobilityAsset;
    }
    
    /**
     * 设置 openHighMobilityAsset
     * @param openHighMobilityAsset openHighMobilityAsset
     */
    public void setOpenHighMobilityAsset(BigDecimal openHighMobilityAsset) {
        this.openHighMobilityAsset = openHighMobilityAsset;
    }
    
    /**
     * 获取 openHighMobilityAssetPierce
     * @return openHighMobilityAssetPierce.
     */
    public BigDecimal getOpenHighMobilityAssetPierce() {
        return openHighMobilityAssetPierce;
    }
    
    /**
     * 设置 openHighMobilityAssetPierce
     * @param openHighMobilityAssetPierce openHighMobilityAssetPierce
     */
    public void setOpenHighMobilityAssetPierce(BigDecimal openHighMobilityAssetPierce) {
        this.openHighMobilityAssetPierce = openHighMobilityAssetPierce;
    }
    
    /**
     * 获取 cash
     * @return cash.
     */
    public BigDecimal getCash() {
        return cash;
    }
    
    /**
     * 设置 cash
     * @param cash cash
     */
    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }
    
    /**
     * 获取 cashPierce
     * @return cashPierce.
     */
    public BigDecimal getCashPierce() {
        return cashPierce;
    }
    
    /**
     * 设置 cashPierce
     * @param cashPierce cashPierce
     */
    public void setCashPierce(BigDecimal cashPierce) {
        this.cashPierce = cashPierce;
    }
    
    /**
     * 获取 highMobilityAsset
     * @return highMobilityAsset.
     */
    public BigDecimal getHighMobilityAsset() {
        return highMobilityAsset;
    }
    
    /**
     * 设置 highMobilityAsset
     * @param highMobilityAsset highMobilityAsset
     */
    public void setHighMobilityAsset(BigDecimal highMobilityAsset) {
        this.highMobilityAsset = highMobilityAsset;
    }
    
    /**
     * 获取 highMobilityAssetPierce
     * @return highMobilityAssetPierce.
     */
    public BigDecimal getHighMobilityAssetPierce() {
        return highMobilityAssetPierce;
    }
    
    /**
     * 设置 highMobilityAssetPierce
     * @param highMobilityAssetPierce highMobilityAssetPierce
     */
    public void setHighMobilityAssetPierce(BigDecimal highMobilityAssetPierce) {
        this.highMobilityAssetPierce = highMobilityAssetPierce;
    }
    
    /**
     * 获取 maxAssetsCD
     * @return maxAssetsCD.
     */
    public BigDecimal getMaxAssetsCD() {
        return maxAssetsCD;
    }
    
    /**
     * 设置 maxAssetsCD
     * @param maxAssetsCD maxAssetsCD
     */
    public void setMaxAssetsCD(BigDecimal maxAssetsCD) {
        this.maxAssetsCD = maxAssetsCD;
    }
    
    /**
     * 获取 largestFinancierCD
     * @return largestFinancierCD.
     */
    public BigDecimal getLargestFinancierCD() {
        return largestFinancierCD;
    }
    
    /**
     * 设置 largestFinancierCD
     * @param largestFinancierCD largestFinancierCD
     */
    public void setLargestFinancierCD(BigDecimal largestFinancierCD) {
        this.largestFinancierCD = largestFinancierCD;
    }
    
    /**
     * 获取 nonPublicBondCD
     * @return nonPublicBondCD.
     */
    public BigDecimal getNonPublicBondCD() {
        return nonPublicBondCD;
    }
    
    /**
     * 设置 nonPublicBondCD
     * @param nonPublicBondCD nonPublicBondCD
     */
    public void setNonPublicBondCD(BigDecimal nonPublicBondCD) {
        this.nonPublicBondCD = nonPublicBondCD;
    }
    
    /**
     * 获取 netAassetsRatio
     * @return netAassetsRatio.
     */
    public BigDecimal getNetAassetsRatio() {
        return netAassetsRatio;
    }
    
    /**
     * 设置 netAassetsRatio
     * @param netAassetsRatio netAassetsRatio
     */
    public void setNetAassetsRatio(BigDecimal netAassetsRatio) {
        this.netAassetsRatio = netAassetsRatio;
    }
    
    /**
     * 获取 totalAassetsRatio
     * @return totalAassetsRatio.
     */
    public BigDecimal getTotalAassetsRatio() {
        return totalAassetsRatio;
    }
    
    /**
     * 设置 totalAassetsRatio
     * @param totalAassetsRatio totalAassetsRatio
     */
    public void setTotalAassetsRatio(BigDecimal totalAassetsRatio) {
        this.totalAassetsRatio = totalAassetsRatio;
    }
    
    /**
     * 获取 tdyFloatIngpl
     * @return tdyFloatIngpl.
     */
    public BigDecimal getTdyFloatIngpl() {
        return tdyFloatIngpl;
    }
    
    /**
     * 设置 tdyFloatIngpl
     * @param tdyFloatIngpl tdyFloatIngpl
     */
    public void setTdyFloatIngpl(BigDecimal tdyFloatIngpl) {
        this.tdyFloatIngpl = tdyFloatIngpl;
    }


    
    /**
     * 获取 netAssetPierce
     * @return netAssetPierce.
     */
    public BigDecimal getNetAssetPierce() {
        return netAssetPierce;
    }


    
    /**
     * 设置 netAssetPierce
     * @param netAssetPierce netAssetPierce
     */
    public void setNetAssetPierce(BigDecimal netAssetPierce) {
        this.netAssetPierce = netAssetPierce;
    }


    
    /**
     * 获取 totalAssetPierce
     * @return totalAssetPierce.
     */
    public BigDecimal getTotalAssetPierce() {
        return totalAssetPierce;
    }


    
    /**
     * 设置 totalAssetPierce
     * @param totalAssetPierce totalAssetPierce
     */
    public void setTotalAssetPierce(BigDecimal totalAssetPierce) {
        this.totalAssetPierce = totalAssetPierce;
    }


    
    /**
     * 获取 maxAssetsCDPierce
     * @return maxAssetsCDPierce.
     */
    public BigDecimal getMaxAssetsCDPierce() {
        return maxAssetsCDPierce;
    }


    
    /**
     * 设置 maxAssetsCDPierce
     * @param maxAssetsCDPierce maxAssetsCDPierce
     */
    public void setMaxAssetsCDPierce(BigDecimal maxAssetsCDPierce) {
        this.maxAssetsCDPierce = maxAssetsCDPierce;
    }


    
    /**
     * 获取 largestFinancierCDPierce
     * @return largestFinancierCDPierce.
     */
    public BigDecimal getLargestFinancierCDPierce() {
        return largestFinancierCDPierce;
    }


    
    /**
     * 设置 largestFinancierCDPierce
     * @param largestFinancierCDPierce largestFinancierCDPierce
     */
    public void setLargestFinancierCDPierce(BigDecimal largestFinancierCDPierce) {
        this.largestFinancierCDPierce = largestFinancierCDPierce;
    }


    
    /**
     * 获取 nonPublicBondCDPierce
     * @return nonPublicBondCDPierce.
     */
    public BigDecimal getNonPublicBondCDPierce() {
        return nonPublicBondCDPierce;
    }


    
    /**
     * 设置 nonPublicBondCDPierce
     * @param nonPublicBondCDPierce nonPublicBondCDPierce
     */
    public void setNonPublicBondCDPierce(BigDecimal nonPublicBondCDPierce) {
        this.nonPublicBondCDPierce = nonPublicBondCDPierce;
    }


    
    /**
     * 获取 netAassetsRatioPierce
     * @return netAassetsRatioPierce.
     */
    public BigDecimal getNetAassetsRatioPierce() {
        return netAassetsRatioPierce;
    }


    
    /**
     * 设置 netAassetsRatioPierce
     * @param netAassetsRatioPierce netAassetsRatioPierce
     */
    public void setNetAassetsRatioPierce(BigDecimal netAassetsRatioPierce) {
        this.netAassetsRatioPierce = netAassetsRatioPierce;
    }


    
    /**
     * 获取 totalAassetsRatioPierce
     * @return totalAassetsRatioPierce.
     */
    public BigDecimal getTotalAassetsRatioPierce() {
        return totalAassetsRatioPierce;
    }
 
    /**
     * 设置 totalAassetsRatioPierce
     * @param totalAassetsRatioPierce totalAassetsRatioPierce
     */
    public void setTotalAassetsRatioPierce(BigDecimal totalAassetsRatioPierce) {
        this.totalAassetsRatioPierce = totalAassetsRatioPierce;
    }
 
    /**
     * 获取 tdyFloatIngplPierce
     * @return tdyFloatIngplPierce.
     */
    public BigDecimal getTdyFloatIngplPierce() {
        return tdyFloatIngplPierce;
    }
 
    /**
     * 设置 tdyFloatIngplPierce
     * @param tdyFloatIngplPierce tdyFloatIngplPierce
     */
    public void setTdyFloatIngplPierce(BigDecimal tdyFloatIngplPierce) {
        this.tdyFloatIngplPierce = tdyFloatIngplPierce;
    }
 
    /**
     * 获取 secDuration
     * @return secDuration.
     */
    public BigDecimal getSecDuration() {
        return secDuration;
    }
 
    /**
     * 设置 secDuration
     * @param secDuration secDuration
     */
    public void setSecDuration(BigDecimal secDuration) {
        this.secDuration = secDuration;
    }
 
    /**
     * 获取 productDuration
     * @return productDuration.
     */
    public BigDecimal getProductDuration() {
        return productDuration;
    }
 
    /**
     * 设置 productDuration
     * @param productDuration productDuration
     */
    public void setProductDuration(BigDecimal productDuration) {
        this.productDuration = productDuration;
    }
 
    /**
     * 获取 assetDuration
     * @return assetDuration.
     */
    public BigDecimal getAssetDuration() {
        return assetDuration;
    }
 
    /**
     * 设置 assetDuration
     * @param assetDuration assetDuration
     */
    public void setAssetDuration(BigDecimal assetDuration) {
        this.assetDuration = assetDuration;
    }

 
    /**
     * 获取 ssDuration
     * @return ssDuration.
     */
    public BigDecimal getSsDuration() {
        return ssDuration;
    }
 
    /**
     * 设置 ssDuration
     * @param ssDuration ssDuration
     */
    public void setSsDuration(BigDecimal ssDuration) {
        this.ssDuration = ssDuration;
    }





    
    /**
     * 获取 assetDurationMol
     * @return assetDurationMol.
     */
    public BigDecimal getAssetDurationMol() {
        return assetDurationMol;
    }





    
    /**
     * 设置 assetDurationMol
     * @param assetDurationMol assetDurationMol
     */
    public void setAssetDurationMol(BigDecimal assetDurationMol) {
        this.assetDurationMol = assetDurationMol;
    }





    
    /**
     * 获取 assetDurationDen
     * @return assetDurationDen.
     */
    public BigDecimal getAssetDurationDen() {
        return assetDurationDen;
    }





    
    /**
     * 设置 assetDurationDen
     * @param assetDurationDen assetDurationDen
     */
    public void setAssetDurationDen(BigDecimal assetDurationDen) {
        this.assetDurationDen = assetDurationDen;
    }
 
    /**
     * 获取 openHighMobilityMol
     * @return openHighMobilityMol.
     */
    public BigDecimal getOpenHighMobilityMol() {
        return openHighMobilityMol;
    }
  
    /**
     * 设置 openHighMobilityMol
     * @param openHighMobilityMol openHighMobilityMol
     */
    public void setOpenHighMobilityMol(BigDecimal openHighMobilityMol) {
        this.openHighMobilityMol = openHighMobilityMol;
    }
 
    /**
     * 获取 openHighMobilityDen
     * @return openHighMobilityDen.
     */
    public BigDecimal getOpenHighMobilityDen() {
        return openHighMobilityDen;
    }
  
    /**
     * 设置 openHighMobilityDen
     * @param openHighMobilityDen openHighMobilityDen
     */
    public void setOpenHighMobilityDen(BigDecimal openHighMobilityDen) {
        this.openHighMobilityDen = openHighMobilityDen;
    }

 
    /**
     * 获取 highMobilityMol
     * @return highMobilityMol.
     */
    public BigDecimal getHighMobilityMol() {
        return highMobilityMol;
    }

 
    /**
     * 设置 highMobilityMol
     * @param highMobilityMol highMobilityMol
     */
    public void setHighMobilityMol(BigDecimal highMobilityMol) {
        this.highMobilityMol = highMobilityMol;
    }
 
    
    /**
     * 获取 highMobilityDen
     * @return highMobilityDen.
     */
    public BigDecimal getHighMobilityDen() {
        return highMobilityDen;
    }

 
    /**
     * 设置 highMobilityDen
     * @param highMobilityDen highMobilityDen
     */
    public void setHighMobilityDen(BigDecimal highMobilityDen) {
        this.highMobilityDen = highMobilityDen;
    }
 
    
    /**
     * 获取 singleCustomerConcentration
     * @return singleCustomerConcentration.
     */
    public BigDecimal getSingleCustomerConcentration() {
        return SingleCustomerConcentration;
    }
 
    
    /**
     * 设置 singleCustomerConcentration
     * @param singleCustomerConcentration singleCustomerConcentration
     */
    public void setSingleCustomerConcentration(BigDecimal singleCustomerConcentration) {
        SingleCustomerConcentration = singleCustomerConcentration;
    }
 
    
    /**
     * 获取 singleCustomerConcentrationPierce
     * @return singleCustomerConcentrationPierce.
     */
    public BigDecimal getSingleCustomerConcentrationPierce() {
        return SingleCustomerConcentrationPierce;
    }

 
    
    /**
     * 设置 singleCustomerConcentrationPierce
     * @param singleCustomerConcentrationPierce singleCustomerConcentrationPierce
     */
    public void setSingleCustomerConcentrationPierce(BigDecimal singleCustomerConcentrationPierce) {
        SingleCustomerConcentrationPierce = singleCustomerConcentrationPierce;
    }






    
    /**
     * 获取 assetCode
     * @return assetCode.
     */
    public String getAssetCode() {
        return assetCode;
    }






    
    /**
     * 设置 assetCode
     * @param assetCode assetCode
     */
    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }






    
    /**
     * 获取 netQtyAsset
     * @return netQtyAsset.
     */
    public BigDecimal getNetQtyAsset() {
        return netQtyAsset;
    }






    
    /**
     * 设置 netQtyAsset
     * @param netQtyAsset netQtyAsset
     */
    public void setNetQtyAsset(BigDecimal netQtyAsset) {
        this.netQtyAsset = netQtyAsset;
    }






    
    /**
     * 获取 totalQtyAsset
     * @return totalQtyAsset.
     */
    public BigDecimal getTotalQtyAsset() {
        return totalQtyAsset;
    }






    
    
    /**
     * 获取 secDurationMol
     * @return secDurationMol.
     */
    public BigDecimal getSecDurationMol() {
        return secDurationMol;
    }






    
    /**
     * 设置 secDurationMol
     * @param secDurationMol secDurationMol
     */
    public void setSecDurationMol(BigDecimal secDurationMol) {
        this.secDurationMol = secDurationMol;
    }






    
    /**
     * 获取 secDurationDel
     * @return secDurationDel.
     */
    public BigDecimal getSecDurationDel() {
        return secDurationDel;
    }






    
    /**
     * 设置 secDurationDel
     * @param secDurationDel secDurationDel
     */
    public void setSecDurationDel(BigDecimal secDurationDel) {
        this.secDurationDel = secDurationDel;
    }






    /**
     * 设置 totalQtyAsset
     * @param totalQtyAsset totalQtyAsset
     */
    public void setTotalQtyAsset(BigDecimal totalQtyAsset) {
        this.totalQtyAsset = totalQtyAsset;
    }
 
     
}
