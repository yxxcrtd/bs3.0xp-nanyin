package com.joyintech.tams.invest.batch.autogeneration.proledger.dto;

import com.joyintech.tams.common.base.entity.FinBaseRuleInfo;

public class FinBaseRuleInfoDto extends FinBaseRuleInfo{

	private static final long serialVersionUID = 1L;
	
	 /**
     * 产品款型
     */
    private String prodStyle;

    /**
     * 期限上限
     */
    private String termMax;

	public String getProdStyle() {
		return prodStyle;
	}

	public void setProdStyle(String prodStyle) {
		this.prodStyle = prodStyle;
	}

	public String getTermMax() {
		return termMax;
	}

	public void setTermMax(String termMax) {
		this.termMax = termMax;
	}

}
