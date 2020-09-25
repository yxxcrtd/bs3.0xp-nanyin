package com.joyintech.service.riskrds.limitmanagement.rsksupervisionindicatorparam.dto;

import java.util.List;

public class ParamSqlDto {
	/**
	 * 查询结果
	 */
	private String select;
	/**
	 * 查询数据源
	 */
	private String from;
	/**
	 * 条件子句
	 */
	private List<ParamConditionDto> where;
	public String getSelect() {
		return select;
	}
	public void setSelect(String select) {
		this.select = select;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public List<ParamConditionDto> getWhere() {
		return where;
	}
	public void setWhere(List<ParamConditionDto> where) {
		this.where = where;
	}
	
	

}
