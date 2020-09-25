/*
 * 文件名：FinNetProductDurationDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zqz
 * 修改时间：2019年1月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.product.productduration.netproductduration.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.tams.common.base.entity.FinTradeProduct;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author zqz
 * @version 1.0 2019年1月15日
 * @see FinNetProductDurationLayerDto
 * @since 1.0
 */
@PageId("FinNetProductDurationLayerDto")
public class FinNetProductDurationLayerDto extends FinTradeProduct{
	/**
	 * 分层交易流水
	 */
	private String tradeId;
    /**
     * 产品款型
     */
	private String prodType;
	/*认购 start*/
    /**
     * 交易金额-认购分层
     */
    private BigDecimal subDealLayerAmt;
    /**
     * 认购费-认购分层
     */
    private BigDecimal subLayerFee;
    /*认购end*/
    /*申购start*/
    /**
     * 净值日
     */
    private Date purNetValueLayerDate;
    /**
     * 单位净值
     */
    private BigDecimal purUnitNetLayerValue;
    /**
     * 交易金额
     */
    private BigDecimal purDealLayerAmt;
    /**
     * 交易份额
     */
    private BigDecimal purDealLayerNum;
    /**
     * 申购费
     */ 		
    private BigDecimal purLayerFee;
    
    /*申购end*/
    /*赎回 包括产品赎回、产品提前赎回、产品违约赎回 start*/
    /**
     *净值日
     */
    private Date redNetValueLayerDate;
    /**
     *单位净值
     */
    private BigDecimal redUnitNetLayerValue;
    /**
     *交易份额
     */				
    private BigDecimal redDealLayerNum;
	/**
     *交易金额
     */
  private BigDecimal redDealLayerAmt;
	/**
     *赎回费(产品留存)
     */
    private BigDecimal redFeeLcLayer;
	/**
     *净金额
     */
    private BigDecimal redTotalNetLayerValue;
	/**
     *赎回费
     */
    private BigDecimal redLayerFee;
	/**
     *申购费(后端)
     */
    private BigDecimal redPurLayerFeeHd;
	/**
     *赎回收益
     */
    private BigDecimal redLayerProfit;
	/**
     *赎回成本
     */
    private BigDecimal redLayerCost;
    /*赎回 包括产品赎回、产品提前赎回、产品违约赎回 end*/
    /*到期要素 包括到期、提前终止 start*/
    /**
     *净值日
     */
    private Date expireNetValueLayerDate;
    /**
     *单位净值
     */
    private BigDecimal expireUnitNetLayerValue;
    /**
     *交易金额
     */
    private BigDecimal expireDealLayerAmt;
    /**
     *交易份额
     */
    private BigDecimal expireDealLayerNum;
    /**
     *到期收益
     */
    private BigDecimal expireLayerProfit;
    /**
     * 到期成本
     */
    private BigDecimal expireLayerCost;
    /*到期要素 包括到期、提前终止 end*/
    /*强增要素 包括强增、强减 start*/
    /**
     * 净值日
     */
    private Date addReduceNetValueLayerDate;
    /**
     * 单位净值
     */
    private BigDecimal addReduceUnitNetLayerValue;
    /**
     * 交易金额
     */
    private BigDecimal addReduceDealLayerAmt;
    /**
     * 交易份额
     */
    private BigDecimal addReduceDealLayerNum;
    
    /*强增要素 包括强增、强减 end*/
	public String getProdType() {
		return prodType;
	}
	public void setProdType(String prodType) {
		this.prodType = prodType;
	}
	public BigDecimal getSubDealLayerAmt() {
		return subDealLayerAmt;
	}
	public void setSubDealLayerAmt(BigDecimal subDealLayerAmt) {
		this.subDealLayerAmt = subDealLayerAmt;
	}
	public BigDecimal getSubLayerFee() {
		return subLayerFee;
	}
	public void setSubLayerFee(BigDecimal subLayerFee) {
		this.subLayerFee = subLayerFee;
	}
	public String getTradeId() {
		return tradeId;
	}
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}
	public Date getPurNetValueLayerDate() {
		return purNetValueLayerDate;
	}
	public void setPurNetValueLayerDate(Date purNetValueLayerDate) {
		this.purNetValueLayerDate = purNetValueLayerDate;
	}

	
	public BigDecimal getPurUnitNetLayerValue() {
		return purUnitNetLayerValue;
	}
	public void setPurUnitNetLayerValue(BigDecimal purUnitNetLayerValue) {
		this.purUnitNetLayerValue = purUnitNetLayerValue;
	}
	public BigDecimal getPurDealLayerAmt() {
		return purDealLayerAmt;
	}
	public void setPurDealLayerAmt(BigDecimal purDealLayerAmt) {
		this.purDealLayerAmt = purDealLayerAmt;
	}
	public BigDecimal getPurDealLayerNum() {
		return purDealLayerNum;
	}
	public void setPurDealLayerNum(BigDecimal purDealLayerNum) {
		this.purDealLayerNum = purDealLayerNum;
	}
	public BigDecimal getPurLayerFee() {
		return purLayerFee;
	}
	public void setPurLayerFee(BigDecimal purLayerFee) {
		this.purLayerFee = purLayerFee;
	}
	public Date getRedNetValueLayerDate() {
		return redNetValueLayerDate;
	}
	public void setRedNetValueLayerDate(Date redNetValueLayerDate) {
		this.redNetValueLayerDate = redNetValueLayerDate;
	}
	public BigDecimal getRedUnitNetLayerValue() {
		return redUnitNetLayerValue;
	}
	public void setRedUnitNetLayerValue(BigDecimal redUnitNetLayerValue) {
		this.redUnitNetLayerValue = redUnitNetLayerValue;
	}
	public BigDecimal getRedDealLayerNum() {
		return redDealLayerNum;
	}
	public void setRedDealLayerNum(BigDecimal redDealLayerNum) {
		this.redDealLayerNum = redDealLayerNum;
	}
	public BigDecimal getRedDealLayerAmt() {
		return redDealLayerAmt;
	}
	public void setRedDealLayerAmt(BigDecimal redDealLayerAmt) {
		this.redDealLayerAmt = redDealLayerAmt;
	}
	public BigDecimal getRedFeeLcLayer() {
		return redFeeLcLayer;
	}
	public void setRedFeeLcLayer(BigDecimal redFeeLcLayer) {
		this.redFeeLcLayer = redFeeLcLayer;
	}
	public BigDecimal getRedTotalNetLayerValue() {
		return redTotalNetLayerValue;
	}
	public void setRedTotalNetLayerValue(BigDecimal redTotalNetLayerValue) {
		this.redTotalNetLayerValue = redTotalNetLayerValue;
	}
	public BigDecimal getRedLayerFee() {
		return redLayerFee;
	}
	public void setRedLayerFee(BigDecimal redLayerFee) {
		this.redLayerFee = redLayerFee;
	}
	public BigDecimal getRedPurLayerFeeHd() {
		return redPurLayerFeeHd;
	}
	public void setRedPurLayerFeeHd(BigDecimal redPurLayerFeeHd) {
		this.redPurLayerFeeHd = redPurLayerFeeHd;
	}
	public BigDecimal getRedLayerProfit() {
		return redLayerProfit;
	}
	public void setRedLayerProfit(BigDecimal redLayerProfit) {
		this.redLayerProfit = redLayerProfit;
	}
	public BigDecimal getRedLayerCost() {
		return redLayerCost;
	}
	public void setRedLayerCost(BigDecimal redLayerCost) {
		this.redLayerCost = redLayerCost;
	}
	public Date getExpireNetValueLayerDate() {
		return expireNetValueLayerDate;
	}
	public void setExpireNetValueLayerDate(Date expireNetValueLayerDate) {
		this.expireNetValueLayerDate = expireNetValueLayerDate;
	}
	public BigDecimal getExpireUnitNetLayerValue() {
		return expireUnitNetLayerValue;
	}
	public void setExpireUnitNetLayerValue(BigDecimal expireUnitNetLayerValue) {
		this.expireUnitNetLayerValue = expireUnitNetLayerValue;
	}
	public BigDecimal getExpireDealLayerAmt() {
		return expireDealLayerAmt;
	}
	public void setExpireDealLayerAmt(BigDecimal expireDealLayerAmt) {
		this.expireDealLayerAmt = expireDealLayerAmt;
	}
	public BigDecimal getExpireDealLayerNum() {
		return expireDealLayerNum;
	}
	public void setExpireDealLayerNum(BigDecimal expireDealLayerNum) {
		this.expireDealLayerNum = expireDealLayerNum;
	}
	public BigDecimal getExpireLayerProfit() {
		return expireLayerProfit;
	}
	public void setExpireLayerProfit(BigDecimal expireLayerProfit) {
		this.expireLayerProfit = expireLayerProfit;
	}
	public BigDecimal getExpireLayerCost() {
		return expireLayerCost;
	}
	public void setExpireLayerCost(BigDecimal expireLayerCost) {
		this.expireLayerCost = expireLayerCost;
	}
	public Date getAddReduceNetValueLayerDate() {
		return addReduceNetValueLayerDate;
	}
	public void setAddReduceNetValueLayerDate(Date addReduceNetValueLayerDate) {
		this.addReduceNetValueLayerDate = addReduceNetValueLayerDate;
	}
	public BigDecimal getAddReduceUnitNetLayerValue() {
		return addReduceUnitNetLayerValue;
	}
	public void setAddReduceUnitNetLayerValue(BigDecimal addReduceUnitNetLayerValue) {
		this.addReduceUnitNetLayerValue = addReduceUnitNetLayerValue;
	}
	public BigDecimal getAddReduceDealLayerAmt() {
		return addReduceDealLayerAmt;
	}
	public void setAddReduceDealLayerAmt(BigDecimal addReduceDealLayerAmt) {
		this.addReduceDealLayerAmt = addReduceDealLayerAmt;
	}
	public BigDecimal getAddReduceDealLayerNum() {
		return addReduceDealLayerNum;
	}
	public void setAddReduceDealLayerNum(BigDecimal addReduceDealLayerNum) {
		this.addReduceDealLayerNum = addReduceDealLayerNum;
	}
    
	
}
