package com.joyintech.tams.invest.common.redisinit.dto;

import java.math.BigDecimal;

import com.joyintech.tams.invest.common.riskinfrds.info.entity.IFundValuation;

/**
 * 基金估值信息存储结构
 * 
 * @author zy_zhangrui
 *
 */
public class ImplFundValuation implements IFundValuation {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -4278181285269029045L;

	/**
	 * 资产代码
	 */
	private String assetCode;
	
	/**
	 * 设置 资产代码
	 * 
	 * @param assetCode 资产代码
	 */
	public void setAssetCode(String assetCode){
		this.assetCode = assetCode;
	}
	
	@Override
	public String getAssetCode() {
		return assetCode;
	}

	/**
	 * 估值日期
	 */
	private String valDate;

	/**
	 * 设置 估值日期
	 * 
	 * @param valDate 估值日期
	 */
	public void setValDate(String valDate){
		this.valDate = valDate;
	}
	
	@Override
	public String getVaDate() {
		return valDate;
	}

	/**
	 * 估值
	 */
	private BigDecimal valuation;

	/**
	 * 设置 估值
	 * 
	 * @param valuation 估值
	 */
	public void setValuation(BigDecimal valuation){
		this.valuation = valuation;
	}

	@Override
	public BigDecimal getValuation() {
		return valuation;
	}

}
