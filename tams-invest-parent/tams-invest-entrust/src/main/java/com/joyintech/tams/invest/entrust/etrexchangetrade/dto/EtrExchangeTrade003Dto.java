/*
 * 文件名：EtrExchangeTradeDto002.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：chendijun
 * 修改时间：2020年7月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.entrust.etrexchangetrade.dto;

import java.math.BigDecimal;
import java.util.List;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.workflow.engine.annotation.PrimaryKey;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author chendijun
 * @version 1.0 2020年7月2日
 * @see EtrExchangeTrade003Dto
 * @since 1.0
 */
@PageId("EtrExchangeTradeMain")
public class EtrExchangeTrade003Dto extends EtrEntrust{
    
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -3012626876825089762L;
    
    /**
     * 执行进度
     */
    private BigDecimal implementPercent;
    /**
     * 成交进度
     */
    private BigDecimal dealPercent;
    
    /**
     * 组合名称
     */
    private String portfolioName;

    private String finprodAbbr;

    private List<EtrExchangeTrade003Dto> selectedItem;

    private String paAssetType;

    /**
     * 市场代码
     */
    private String finprodMarketId;

    /**
     * 席位号
     */
    private String counterSeat;

    /**
     * 约定号
     */
    private String counterConNo;

    /**
     * 交易货币账户
     */
    private String dealCurAcct;

    /**
     * 目标类型
     */
    private String targetType;

    /**
     * 本方交易员
     */
    private String trader;

    /**
     * 对手方交易商
     */
    private String counterOrg;
    
    /**
     * 申报类型 [db字段：DEAL_MODE，交易方式]
     */
    private String declaType;
    
    /**
     * 
     */
    private String calMode;
    
    /**
     * 全价
     */
    private BigDecimal fprice;
    
    /**
     * 收益率
     */
    private BigDecimal  yield;
    
    
    /**
     * 资产类型
     */
    private String assetType;


    /**
     * 获取 implementPercent
     * @return implementPercent.
     */
    public BigDecimal getImplementPercent() {
        return implementPercent;
    }

    /**
     * 设置 implementPercent
     * @param implementPercent implementPercent
     */
    public void setImplementPercent(BigDecimal implementPercent) {
        this.implementPercent = implementPercent;
    }
    
    /**
     * 获取执行百分比
     * @return
     * @see
     * @since 1.0
     */
    public BigDecimal getImplementPercentAge(){
        if(StringUtil.isNotEmpty(dealPercent)){
            return implementPercent.multiply(new BigDecimal(100));
        }else{
            return new BigDecimal(0);
        }
    }

    /**
     * 获取 dealPercent
     * @return dealPercent.
     */
    public BigDecimal getDealPercent() {
        return dealPercent;
    }
    
    /**
     * 获取成交百分比
     * @return
     * @see
     * @since 1.0
     */
    public BigDecimal getDealPercentAge(){
        if(StringUtil.isNotEmpty(dealPercent)){
            return dealPercent.multiply(new BigDecimal(100));
        }else{
            return new BigDecimal(0);
        }
    }

    /**
     * 设置 dealPercent
     * @param dealPercent dealPercent
     */
    public void setDealPercent(BigDecimal dealPercent) {
        this.dealPercent = dealPercent;
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
     * 获取 getPsStr
     * @return getPsStr.
     */
    public String getPsStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.交易方向, super.getPs());
    }

    public  String getFinprodType2String(){
        return SysDataUtil.getNameFromDataDict(DictCode.金融产品类型_投管用, super.getFinprodType2());
    }
    
    public  String getAssetTypeString(){
        return SysDataUtil.getNameFromDataDict(DictCode.平安资产类型, this.assetType);
    }

    public String getEntrustStatusString(){
        return SysDataUtil.getNameFromDataDict(DictCode.委托状态, super.getEntrustStatus());
    }

    public String getTradeMarketString(){
        return  SysDataUtil.getNameFromDataDict(DictCode.交易场所, super.getTradeMarket());
    }

    public String getPriceModelString(){
        return  SysDataUtil.getNameFromDataDict(DictCode.价格模式, super.getPriceModel());
    }

    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }

    public List<EtrExchangeTrade003Dto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<EtrExchangeTrade003Dto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    @Override
    @PrimaryKey(PrimaryKey.Field.PK_1)
    public String getEntrustId(){
        return super.getEntrustId();
    }

    public String getPaAssetType() {
        return paAssetType;
    }

    public void setPaAssetType(String paAssetType) {
        this.paAssetType = paAssetType;
    }

    public String getTradePlatString(){
        return SysDataUtil.getNameFromDataDict(DictCode.交易平台, super.getTradePlat());
    }

    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    public String getCounterSeat() {
        return counterSeat;
    }

    public void setCounterSeat(String counterSeat) {
        this.counterSeat = counterSeat;
    }

    public String getCounterConNo() {
        return counterConNo;
    }

    public void setCounterConNo(String counterConNo) {
        this.counterConNo = counterConNo;
    }

    public String getDealCurAcct() {
        return dealCurAcct;
    }

    public void setDealCurAcct(String dealCurAcct) {
        this.dealCurAcct = dealCurAcct;
    }

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    /**
     * 获取 trader
     * 
     * @return trader
     */
    public String getTrader() {
        return trader;
    }

    /**
     * 设置 trader
     * 
     * @param trader String
     */
    public void setTrader(String trader) {
        this.trader = trader;
    }

    /**
     * 获取 counterOrg
     * 
     * @return counterOrg
     */
    public String getCounterOrg() {
        return counterOrg;
    }

    /**
     * 设置 counterOrg
     * 
     * @param counterOrg String
     */
    public void setCounterOrg(String counterOrg) {
        this.counterOrg = counterOrg;
    }

	public String getDeclaType() {
		return declaType;
	}

	public void setDeclaType(String declaType) {
		this.declaType = declaType;
	}

	public String getCalMode() {
		return calMode;
	}

	public void setCalMode(String calMode) {
		this.calMode = calMode;
	}

	public BigDecimal getFprice() {
		return fprice;
	}

	public void setFprice(BigDecimal fprice) {
		this.fprice = fprice;
	}

	public BigDecimal getYield() {
		return yield;
	}

	public void setYield(BigDecimal yield) {
		this.yield = yield;
	}

	public String getAssetType() {
		return assetType;
	}

	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}
}
