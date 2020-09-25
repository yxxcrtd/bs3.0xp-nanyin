package com.joyintech.tams.disclosure.information.config.thresholdCheck.dto;

import java.util.Date;

import com.joyintech.framework.base.BaseEntity;

public class ThresholdCheck001Dto  extends BaseEntity{

	
	/**
	 * 报表名称
	 */
	private  String reportName;
	
	/**
	 * 报表标识
	 */
	private  String  flag;
	
	/**
	 * 数量
	 */
	private  Integer  count;
	
	/**
	 * 序号
	 */
    private  Integer  rId;
    
    /**
     * 报表日期
     */
	private  String  reportDate;
	
	 /**
     * 阈值指标名称
     */
	private  String indexId;
	
	/**
	 * 阈值
	 */
    private  String  indexValue;

	/**
	 * 产品代码
	 */
    private  String  prodId;
    
    /**
	 * 产品名称
	 */
    private  String  prodName;
    
    /**
	 * 产品指标值
	 */
    private   String  prodValue;
    
    
    /**
     * 异常结果说明
     */
    private  String  errormsg; 
    
    /**
     * 产品组合
     * @return
     */
	private  String prodArray;
	
	/**
	 * 页面开始日期
	 */
	private  String  startDate;
	
	/**
	 * 页面结束日期
	 */
	private   String  endDate;
	
	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getrId() {
		return rId;
	}

	public void setrId(Integer rId) {
		this.rId = rId;
	}

	public String getReportDate() {
		return reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	public String getIndexId() {
		return indexId;
	}

	public void setIndexId(String indexId) {
		this.indexId = indexId;
	}

	public String getIndexValue() {
		return indexValue;
	}

	public void setIndexValue(String indexValue) {
		this.indexValue = indexValue;
	}

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getProdValue() {
		return prodValue;
	}

	public void setProdValue(String prodValue) {
		this.prodValue = prodValue;
	}

	public String getErrormsg() {
		return errormsg;
	}

	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getProdArray() {
		return prodArray;
	}

	public void setProdArray(String prodArray) {
		this.prodArray = prodArray;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	
}
