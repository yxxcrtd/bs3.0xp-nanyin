package com.joyintech.tams.model.impairment.batch.eadcalculation.dto;

import java.util.Date;
import java.util.List;

/**
 * 股票行情查询参数对象DTO
 * @author zhanfgei
 *
 */
public class ModStockMarketParamsDto {
	//股票代码 集合
	private List<String> stockIdList;
	
	//查询开始日期
	private Date startDate;
	
	//查询日期/查询结束日期
	private Date searchDate;
	
	public List<String> getStockIdList() {
		return stockIdList;
	}
	public Date getSearchDate() {
		return searchDate;
	}
	public void setStockIdList(List<String> stockIdList) {
		this.stockIdList = stockIdList;
	}
	public void setSearchDate(Date searchDate) {
		this.searchDate = searchDate;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	
	
	
	
}
