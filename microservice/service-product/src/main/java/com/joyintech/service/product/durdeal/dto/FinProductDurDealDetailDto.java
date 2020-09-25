/*
 * 文件名：FinProductDurDealDetailDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.product.durdeal.dto;

import java.math.BigDecimal;

import com.joyintech.framework.base.BaseEntity;

/**
 * 产品存续期交易查询明细数据dto
 *
 * @author etliu
 * @version 1.0 2018年12月25日
 * @see FinProductDurDealDetailDto
 * @since 1.0
 */
public class FinProductDurDealDetailDto extends BaseEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -3767321887084849280L;

    /**
     * 子产品号
     */
    private String sonProdId;

    /**
     * 客户等级
     */
    private String customLevel;

    /**
     * 金额上限
     */
    private BigDecimal amtMax;

    /**
     * 金额下限
     */
    private BigDecimal amtMin;

    /**
     * 期限上限
     */
    private String termMax;

    /**
     * 期限下限
     */
    private String termMin;

    /**
     * 当日申购金额
     */
    private BigDecimal tdyBuyMoney;

    /**
     * 当日申购份额
     */
    private BigDecimal tdyBuyQty;

    /**
     * 当日赎回金额
     */
    private BigDecimal tdyRedMoney;

    /**
     * 当日赎回份额
     */
    private BigDecimal tdyRedQty;

    /**
     * 当日已结转收益
     */
    private BigDecimal tdyCarriedInterest;

    /**
     * 当日红利再投
     */
    private BigDecimal tdyCarriedMoney;

    /**
     * 当日强增金额
     */
    private BigDecimal tdyForceIncreaseMoney;

    /**
     * 当日强增份额
     */
    private BigDecimal tdyForceIncreaseQty;

    /**
     * 当日强减金额
     */
    private BigDecimal tdyForceReduceMoney;

    /**
     * 当日强减份额
     */
    private BigDecimal tdyForceReduceQty;

    /**
     * 收益率/单位净值/万份收益
     */
    private String yield;

    /**
     * 有效状态
     */
    private String effectStatus;
    
    public String getEffectStatus() {
		return effectStatus;
	}

	public void setEffectStatus(String effectStatus) {
		this.effectStatus = effectStatus;
	}
    /**
     * 获取 sonProdId
     *
     * @return sonProdId.
     */
    public String getSonProdId() {
        return sonProdId;
    }

    /**
     * 设置 sonProdId
     *
     * @param sonProdId sonProdId
     */
    public void setSonProdId(String sonProdId) {
        this.sonProdId = sonProdId;
    }

    /**
     * 获取 customLevel
     *
     * @return customLevel.
     */
    public String getCustomLevel() {
        return customLevel;
    }

    /**
     * 设置 customLevel
     *
     * @param customLevel customLevel
     */
    public void setCustomLevel(String customLevel) {
        this.customLevel = customLevel;
    }

    /**
     * 获取 amtMax
     *
     * @return amtMax.
     */
    public BigDecimal getAmtMax() {
        return amtMax;
    }

    /**
     * 设置 amtMax
     *
     * @param amtMax amtMax
     */
    public void setAmtMax(BigDecimal amtMax) {
        this.amtMax = amtMax;
    }

    /**
     * 获取 amtMin
     *
     * @return amtMin.
     */
    public BigDecimal getAmtMin() {
        return amtMin;
    }

    /**
     * 设置 amtMin
     *
     * @param amtMin amtMin
     */
    public void setAmtMin(BigDecimal amtMin) {
        this.amtMin = amtMin;
    }

    /**
     * 获取 termMax
     *
     * @return termMax.
     */
    public String getTermMax() {
        return termMax;
    }

    /**
     * 设置 termMax
     *
     * @param termMax termMax
     */
    public void setTermMax(String termMax) {
        this.termMax = termMax;
    }

    /**
     * 获取 termMin
     *
     * @return termMin.
     */
    public String getTermMin() {
        return termMin;
    }

    /**
     * 设置 termMin
     *
     * @param termMin termMin
     */
    public void setTermMin(String termMin) {
        this.termMin = termMin;
    }

    /**
     * 获取 tdyBuyMoney
     *
     * @return tdyBuyMoney.
     */
    public BigDecimal getTdyBuyMoney() {
        return tdyBuyMoney;
    }

    /**
     * 设置 tdyBuyMoney
     *
     * @param tdyBuyMoney tdyBuyMoney
     */
    public void setTdyBuyMoney(BigDecimal tdyBuyMoney) {
        this.tdyBuyMoney = tdyBuyMoney;
    }

    /**
     * 获取 tdyBuyQty
     *
     * @return tdyBuyQty.
     */
    public BigDecimal getTdyBuyQty() {
        return tdyBuyQty;
    }

    /**
     * 设置 tdyBuyQty
     *
     * @param tdyBuyQty tdyBuyQty
     */
    public void setTdyBuyQty(BigDecimal tdyBuyQty) {
        this.tdyBuyQty = tdyBuyQty;
    }

    /**
     * 获取 tdyRedMoney
     *
     * @return tdyRedMoney.
     */
    public BigDecimal getTdyRedMoney() {
        return tdyRedMoney;
    }

    /**
     * 设置 tdyRedMoney
     *
     * @param tdyRedMoney tdyRedMoney
     */
    public void setTdyRedMoney(BigDecimal tdyRedMoney) {
        this.tdyRedMoney = tdyRedMoney;
    }

    /**
     * 获取 tdyRedQty
     *
     * @return tdyRedQty.
     */
    public BigDecimal getTdyRedQty() {
        return tdyRedQty;
    }

    /**
     * 设置 tdyRedQty
     *
     * @param tdyRedQty tdyRedQty
     */
    public void setTdyRedQty(BigDecimal tdyRedQty) {
        this.tdyRedQty = tdyRedQty;
    }

    /**
     * 获取 tdyCarriedInterest
     *
     * @return tdyCarriedInterest.
     */
    public BigDecimal getTdyCarriedInterest() {
        return tdyCarriedInterest;
    }

    /**
     * 设置 tdyCarriedInterest
     *
     * @param tdyCarriedInterest tdyCarriedInterest
     */
    public void setTdyCarriedInterest(BigDecimal tdyCarriedInterest) {
        this.tdyCarriedInterest = tdyCarriedInterest;
    }

    /**
     * 获取 tdyCarriedMoney
     *
     * @return tdyCarriedMoney.
     */
    public BigDecimal getTdyCarriedMoney() {
        return tdyCarriedMoney;
    }

    /**
     * 设置 tdyCarriedMoney
     *
     * @param tdyCarriedMoney tdyCarriedMoney
     */
    public void setTdyCarriedMoney(BigDecimal tdyCarriedMoney) {
        this.tdyCarriedMoney = tdyCarriedMoney;
    }

    /**
     * 获取 tdyForceIncreaseMoney
     *
     * @return tdyForceIncreaseMoney.
     */
    public BigDecimal getTdyForceIncreaseMoney() {
        return tdyForceIncreaseMoney;
    }

    /**
     * 设置 tdyForceIncreaseMoney
     *
     * @param tdyForceIncreaseMoney tdyForceIncreaseMoney
     */
    public void setTdyForceIncreaseMoney(BigDecimal tdyForceIncreaseMoney) {
        this.tdyForceIncreaseMoney = tdyForceIncreaseMoney;
    }

    /**
     * 获取 tdyForceIncreaseQty
     *
     * @return tdyForceIncreaseQty.
     */
    public BigDecimal getTdyForceIncreaseQty() {
        return tdyForceIncreaseQty;
    }

    /**
     * 设置 tdyForceIncreaseQty
     *
     * @param tdyForceIncreaseQty tdyForceIncreaseQty
     */
    public void setTdyForceIncreaseQty(BigDecimal tdyForceIncreaseQty) {
        this.tdyForceIncreaseQty = tdyForceIncreaseQty;
    }

    /**
     * 获取 tdyForceReduceMoney
     *
     * @return tdyForceReduceMoney.
     */
    public BigDecimal getTdyForceReduceMoney() {
        return tdyForceReduceMoney;
    }

    /**
     * 设置 tdyForceReduceMoney
     *
     * @param tdyForceReduceMoney tdyForceReduceMoney
     */
    public void setTdyForceReduceMoney(BigDecimal tdyForceReduceMoney) {
        this.tdyForceReduceMoney = tdyForceReduceMoney;
    }

    /**
     * 获取 tdyForceReduceQty
     *
     * @return tdyForceReduceQty.
     */
    public BigDecimal getTdyForceReduceQty() {
        return tdyForceReduceQty;
    }

    /**
     * 设置 tdyForceReduceQty
     *
     * @param tdyForceReduceQty tdyForceReduceQty
     */
    public void setTdyForceReduceQty(BigDecimal tdyForceReduceQty) {
        this.tdyForceReduceQty = tdyForceReduceQty;
    }

    /**
     * 获取 yield
     *
     * @return yield.
     */
    public String getYield() {
        return yield;
    }

    /**
     * 设置 yield
     *
     * @param yield yield
     */
    public void setYield(String yield) {
        this.yield = yield;
    }

}
