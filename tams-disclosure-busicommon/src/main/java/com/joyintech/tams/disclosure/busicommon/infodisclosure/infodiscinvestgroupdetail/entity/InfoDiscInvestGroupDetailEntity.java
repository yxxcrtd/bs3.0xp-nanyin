package com.joyintech.tams.disclosure.busicommon.infodisclosure.infodiscinvestgroupdetail.entity;

import com.joyintech.tams.common.base.entity.RepInvestdetail;

public class InfoDiscInvestGroupDetailEntity extends RepInvestdetail{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1386864662035127123L;
	
	/**
     * 资产代码
     */
    private String assetId;
    
    /**
     * 资产代码
     */
    private String scaleLimit;

    /**
     * 投资比例
     */
    private String rate;


	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	/**
     * 取得 资产代码
     * @return 资产代码
     */
	public String getAssetId() {
		return assetId;
	}

	/**
     * 设置 资产代码
     * @param assetId 资产代码
     */
	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}
	
	/**
     * 取得 超限比例
     * @return  超限比例
     */
	public String getScaleLimit() {
		return scaleLimit;
	}

	/**
     * 设置  超限比例
     * @param assetId  超限比例
     */
	public void setScaleLimit(String scaleLimit) {
		this.scaleLimit = scaleLimit;
	}
}
