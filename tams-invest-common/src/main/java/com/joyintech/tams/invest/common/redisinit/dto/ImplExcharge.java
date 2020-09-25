package com.joyintech.tams.invest.common.redisinit.dto;

import java.math.BigDecimal;

import com.joyintech.tams.invest.common.riskinfrds.info.entity.IExcharge;

/**
 * 汇率行情存储结构
 * 
 * @author zy_zhangrui
 *
 */
public class ImplExcharge implements IExcharge {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -6585056597245945940L;

	/**
	 * 基础币种
	 */
	private String basisCcy;

	/**
	 * 设置 基础币种
	 * 
	 * @param basisCcy 基础币种
	 */
	public void setBasisCcy(String basisCcy){
		this.basisCcy = basisCcy;
	}

	@Override
	public String getBasisCcy() {
		return basisCcy;
	}

	/**
	 * 报价币种
	 */
	private String quotCcy;

	/**
	 * 设置 报价币种
	 * 
	 * @param quotCcy 报价币种
	 */
	public void setQuotCcy(String quotCcy){
		this.quotCcy = quotCcy;
	}

	@Override
	public String getQuotCcy() {
		return quotCcy;
	}

	/**
	 * 汇率值
	 */
	private BigDecimal exchargeVal;
	
	/**
	 * 设置 汇率值
	 * 
	 * @param exchangeVal 汇率值
	 */
	public void setExchargeVal(BigDecimal exchangeVal){
		this.exchargeVal = exchangeVal;
	}

	@Override
	public BigDecimal getExchargeVal() {
		return exchargeVal;
	}

}
