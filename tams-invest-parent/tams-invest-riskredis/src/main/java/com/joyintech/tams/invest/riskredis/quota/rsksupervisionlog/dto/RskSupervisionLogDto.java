package com.joyintech.tams.invest.riskredis.quota.rsksupervisionlog.dto;


import java.util.Date;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeRds;


/**
 * @description: RskSupervisionLogDto
 * @author: 裴家俊
 * @create: 2018-11-01 14:30
 **/
public class RskSupervisionLogDto extends BaseEntity {
	/**
	 * <意义，目的和功能，以及可能被用到的地方>
	 */
	private static final long serialVersionUID = 1L;
	private String functionId ;
	private Date   valDate       ;
	private String portfolioId ;
	private String indType       ;
	private String indId;//监督点ID
	private String indName;//监督点名称
	private String suptype;//监督点类型
	private String logLvl        ;
	private String message       ;
	private Date   logTime       ;
	private String portfolioName ;
	
	public Date getValDate() {
		return valDate;
	}
	public void setValDate(Date valDate) {
		this.valDate = valDate;
	}
	public String getPortfolioId() {
		return portfolioId;
	}
	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId;
	}
	
	public String getIndType() {
		return indType;
	}
	public void setIndType(String indType) {
		this.indType = indType;
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
	public String getSuptype() {
		return suptype;
	}
	public void setSuptype(String suptype) {
		this.suptype = suptype;
	}
	public String getLogLvl() {
		return logLvl;
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
	public String getPortfolioName() {
		return portfolioName;
	}
	public void setPortfolioName(String portfolioName) {
		this.portfolioName = portfolioName;
	}

	public String getFunctionId() {
		return functionId;
	}

	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}
	public String getFunctionIdStr() {
		return SysDataUtil.getNameFromDataDict(BussinessDictCodeRds.批机能类型, this.getFunctionId());
	}
	public String getIndTypeStr() {
		return SysDataUtil.getNameFromDataDict(BussinessDictCodeRds.指标类型, this.getIndType());
	}
	public String getLogLvlStr() {
		return SysDataUtil.getNameFromDataDict(BussinessDictCodeRds.日志级别, this.getLogLvl());
	}
}
