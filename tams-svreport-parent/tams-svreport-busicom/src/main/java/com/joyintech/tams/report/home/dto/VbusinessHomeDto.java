package com.joyintech.tams.report.home.dto;

import java.util.List;

public class VbusinessHomeDto {

	private String reportCode;

	private List<HomeCellDto> cells;

	/**
	 * 产品登记编码
	 */
	private int prodRegistnum;

	/**
	 * 识别标识合并
	 */
	private int mergenum;

	/**
	 * 接口文件总数
	 */
	private int accessFiletotal;

	/**
	 * 数据检查总数
	 */
	private int checkDatatotal;

	/**
	 * 数据维护总数
	 */
	private int processedDatatotal;

	/**
	 * 排序no
	 * @return
	 */
	private  int  orderNo;





	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public int getProdRegistnum() {
		return prodRegistnum;
	}

	public void setProdRegistnum(int prodRegistnum) {
		this.prodRegistnum = prodRegistnum;
	}

	public int getMergenum() {
		return mergenum;
	}

	public void setMergenum(int mergenum) {
		this.mergenum = mergenum;
	}

	public int getAccessFiletotal() {
		return accessFiletotal;
	}

	public void setAccessFiletotal(int accessFiletotal) {
		this.accessFiletotal = accessFiletotal;
	}

	public int getCheckDatatotal() {
		return checkDatatotal;
	}

	public void setCheckDatatotal(int checkDatatotal) {
		this.checkDatatotal = checkDatatotal;
	}

	public int getProcessedDatatotal() {
		return processedDatatotal;
	}

	public void setProcessedDatatotal(int processedDatatotal) {
		this.processedDatatotal = processedDatatotal;
	}

	public String getReportCode() {
		return reportCode;
	}

	public void setReportCode(String reportCode) {
		this.reportCode = reportCode;
	}

	public List<HomeCellDto> getCells() {
		return cells;
	}

	public void setCells(List<HomeCellDto> cells) {
		this.cells = cells;
	}

}
