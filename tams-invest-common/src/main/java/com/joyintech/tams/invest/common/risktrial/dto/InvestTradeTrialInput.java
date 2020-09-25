package com.joyintech.tams.invest.common.risktrial.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.tams.common.base.entity.TrdProductDeal;

/**
 * 监督指标输入条件DTO
 * @author 裴家俊
 * @version 1.0 2018年11月28日
 * @see InvestTradeTrialInput
 * @since 1.0
 */
public class InvestTradeTrialInput {
	/**
     * 日期/必填
     */
	private String valDate; 
	/**
     * 组合代码/必填
     */
	private String portfolioCode;
	/**
     * 资产代码/非必填
     */
	private String assetCode;
	 /**
     * 交易金额/交易份额/此金额实时影响指标表达式的计算/必填
     */
	@JsonSerialize(using=BigDecimalSerializer.class)
	private BigDecimal tradeAmount;
	/**
     * 监控时点/必填
     */
	private String timePoint;
	/**
     * 资产类型/必填
     */
	private String assetType;
	/**
     * 资产二级类型/非必填
     */
	private String assetSecType;
	/**
     * 资产三级类型/非必填
     */
	private String assetThrType;
	/**
     * 投资性质/非必填
     */
	private String investProporty;
	/**
     * 交易方向
     */
	private String tradeDirection;
	/**
	 * 金额类型{"0":交易金额,"1":交易份额}
	 */
	private String amtType;
	/**
     * 是否启用，默认启用，输入参数可以不用传
     */
	private String isEnabled;
	
	private String tradeCode;
	
	/**
     * 
     * <质押式回购多组合计数>
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    private int mun;
    
    /**
     * 质押式回购多组合风险试算
     */
    private String index;
    
    /**
     * 获取 mun
     * @return mun.
     */
    public int getMun() {
        return mun;
    }
    
    /**
     * 设置 mun
     * @param mun mun
     */
    public void setMun(int mun) {
        this.mun = mun;
    }
    
    /**
     * 获取 index
     * @return index.
     */
    public String getIndex() {
        return index;
    }
    
    /**
     * 设置 index
     * @param index index
     */
    public void setIndex(String index) {
        this.index = index;
    }

    /**
	 * etliu添加，是否需要按照assetThrType进行条件过滤
	 */
	private boolean needAssetThrType;

	/**
	 * 交易实体dto
	 * @return
	 */
	private TrdProductDeal trdProductDeal;
	
    public String getValDate() {
		return valDate;
	}
	public void setValDate(String valDate) {
		this.valDate = valDate;
	}
	public String getPortfolioCode() {
		return portfolioCode;
	}
	public void setPortfolioCode(String portfolioCode) {
		this.portfolioCode = portfolioCode;
	}
	public String getAssetCode() {
		return assetCode;
	}
	public void setAssetCode(String assetCode) {
		this.assetCode = assetCode;
	}
	public BigDecimal getTradeAmount() {
		return tradeAmount;
	}
	public void setTradeAmount(BigDecimal tradeAmount) {
		this.tradeAmount = tradeAmount;
	}
	public String getTimePoint() {
		return timePoint;
	}
	public void setTimePoint(String timePoint) {
		this.timePoint = timePoint;
	}
	public String getAssetType() {
		return assetType;
	}
	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}
	public String getAssetSecType() {
		return assetSecType;
	}
	public void setAssetSecType(String assetSecType) {
		this.assetSecType = assetSecType;
	}
	public String getTradeDirection() {
		return tradeDirection;
	}
	public void setTradeDirection(String tradeDirection) {
		this.tradeDirection = tradeDirection;
	}
	public String getIsEnabled() {
		return isEnabled;
	}
	public void setIsEnabled(String isEnabled) {
		this.isEnabled = isEnabled;
	}
	public String getTradeCode() {
		return tradeCode;
	}
	public void setTradeCode(String tradeCode) {
		this.tradeCode = tradeCode;
	}
    /**
     * 获取 amtType
     * @return amtType.
     */
    public String getAmtType() {
        return amtType;
    }
    
    /**
     * 设置 amtType
     * @param amtType amtType
     */
    public void setAmtType(String amtType) {
        this.amtType = amtType;
    }
    
    public String getAssetThrType() {
		return assetThrType;
	}
	public void setAssetThrType(String assetThrType) {
		this.assetThrType = assetThrType;
	}
	public String getInvestProporty() {
		return investProporty;
	}
	public void setInvestProporty(String investProporty) {
		this.investProporty = investProporty;
	}
	
    /**
     * 获取 needAssetThrType
     * @return needAssetThrType.
     */
    public boolean isNeedAssetThrType() {
        return needAssetThrType;
    }
    
    /**
     * 设置 needAssetThrType
     * @param needAssetThrType needAssetThrType
     */
    public void setNeedAssetThrType(boolean needAssetThrType) {
        this.needAssetThrType = needAssetThrType;
    }

	public TrdProductDeal getTrdProductDeal() {
		return trdProductDeal;
	}

	public void setTrdProductDeal(TrdProductDeal trdProductDeal) {
		this.trdProductDeal = trdProductDeal;
	}

	public String replaceForString(String sql) {
		return sql.replaceAll("\\$\\{valDate\\}", valDate)
			.replaceAll("\\$\\{portfolioCode\\}", portfolioCode)
			.replaceAll("\\$\\{assetCode\\}", assetCode);
	}
}
