package com.joyintech.tams.invest.common.risktrial.dto;

import com.joyintech.tams.common.base.entity.RskIsupIndicatorParaDtl;

/**
 * 监督指标参数管理
 * @author 裴家俊
 * @date 2017年9月21日
 */
public class SupervisionParameterDto extends RskIsupIndicatorParaDtl{
    
	private static final long serialVersionUID = 6365500022221119309L;
	
	private String isMath;

	public String getIsMath() {
		return isMath;
	}

	public void setIsMath(String isMath) {
		this.isMath = isMath;
	}

	/**
	 * 参数资产类型
	 */
	private String paraAssetType;

	/**
	 * 参数资产二级类型
	 */
	private String paraAssetSecType;

	/**
	 * 资产三级类型
	 */
	private String paraAssetThrType;

	/**
	 * 投资性质
	 */
	private String investProporty;

	public String getParaAssetType() {
		return paraAssetType;
	}

	public void setParaAssetType(String paraAssetType) {
		this.paraAssetType = paraAssetType;
	}

	public String getParaAssetSecType() {
		return paraAssetSecType;
	}

	public void setParaAssetSecType(String paraAssetSecType) {
		this.paraAssetSecType = paraAssetSecType;
	}

	public String getParaAssetThrType() {
		return paraAssetThrType;
	}

	public void setParaAssetThrType(String paraAssetThrType) {
		this.paraAssetThrType = paraAssetThrType;
	}

	public String getInvestProporty() {
		return investProporty;
	}

	public void setInvestProporty(String investProporty) {
		this.investProporty = investProporty;
	}
}
