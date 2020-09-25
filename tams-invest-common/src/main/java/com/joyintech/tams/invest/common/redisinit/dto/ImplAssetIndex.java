package com.joyintech.tams.invest.common.redisinit.dto;

import com.joyintech.tams.invest.common.riskinfrds.info.entity.IAssetIndex;

public class ImplAssetIndex implements IAssetIndex {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -3655958320169110774L;

	/**
	 * 市场代码
	 */
	private String marketCode;

	/**
	 * 设置 市场代码
	 * 
	 * @param marketCode 市场代码
	 */
	public void setMarketCode(String marketCode){
		this.marketCode = marketCode;
	}

	@Override
	public String getMarketCode() {
		return marketCode;
	}

	/**
	 * 交易场所
	 */
	private String tradePalce;

	/**
	 * 设置 交易场所
	 * 
	 * @param marketCode 交易场所
	 */
	public void setTradePalce(String tradePalce){
		this.tradePalce = tradePalce;
	}

	@Override
	public String getTradePalce() {
		return tradePalce;
	}

	/**
	 * 资产代码
	 */
	private String assetCode;

	/**
	 * 设置 资产代码
	 * 
	 * @param marketCode 资产代码
	 */
	public void setAssetCode(String assetCode){
		this.assetCode = assetCode;
	}

	@Override
	public String getAssetCode() {
		return assetCode;
	}

}
