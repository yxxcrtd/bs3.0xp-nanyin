package com.joyintech.tams.invest.common.risktrialrds.dto;

import java.util.Date;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.common.base.entity.RskIsupLog;
import com.joyintech.tams.invest.common.risktrialrds.dict.LogLvl;

public class IsupLogDto extends RskIsupLog {

	/**
	 * <意义，目的和功能，以及可能被用到的地方>
	 */
	private static final long serialVersionUID = 1L;
	private Date   valDate       ;
	private String indName;//监督点名称
	private String indType;//监督点类型
	private String portfolioMixName ;
	
	public Date getValDate() {
		return valDate;
	}
	public void setValDate(Date valDate) {
		this.valDate = valDate;
	}
	public String getIndName() {
		return indName;
	}
	public void setIndName(String indName) {
		this.indName = indName;
	}
	public String getIndType() {
		return indType;
	}
	public void setIndType(String indType) {
		this.indType = indType;
	}
	public String getPortfolioMixName() {
		return portfolioMixName;
	}
	public void setPortfolioMixName(String portfolioMixName) {
		this.portfolioMixName = portfolioMixName;
	}

}
