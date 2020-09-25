package com.joyintech.tams.invest.common.risktrial.dto;

import java.util.Date;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.invest.common.risktrial.dict.LogLvl;

public class IsupLogDto extends BaseEntity{

	/**
	 * <意义，目的和功能，以及可能被用到的地方>
	 */
	private static final long serialVersionUID = 1L;
	private String logId;
	private Date   valDate       ;
	private String portfolioId ;
	private String indId;//监督点ID
	private String indName;//监督点名称
	private String indType;//监督点类型
	private String logLvl        ;
	private String message       ;
	private Date   logTime       ;
	private String portfolioMixName ;
	
	
	public String getLogId() {
		return logId;
	}
	public void setLogId(String logId) {
		this.logId = logId;
	}
	public Date getValDate() {
		return valDate;
	}
	public void setValDate(Date valDate) {
		this.valDate = valDate;
	}
	public String getLogLvl() {
		return logLvl;
	}
	public void setLogLvl(LogLvl logLvl) {
		this.logLvl = logLvl.toString();
	}
	public void setLogLvl(String logLvl) {
		this.logLvl = logLvl;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getLogTime() {
		return logTime;
	}
	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}
	public String getPortfolioId() {
		return portfolioId;
	}
	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId;
	}
	public String getIndId() {
		return indId;
	}
	public void setIndId(String indId) {
		this.indId = indId;
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
