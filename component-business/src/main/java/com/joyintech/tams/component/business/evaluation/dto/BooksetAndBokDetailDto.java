package com.joyintech.tams.component.business.evaluation.dto;

/**
 * 查询两个参数特用
 * 2019年10月10日
 * yangxiaofeng
 */
public class BooksetAndBokDetailDto {

	private String booksetId; // 套账代码
	private String bokDetailId; // 账务明细代码
	
	public String getBooksetId() {
		return booksetId;
	}
	public void setBooksetId(String booksetId) {
		this.booksetId = booksetId;
	}
	public String getBokDetailId() {
		return bokDetailId;
	}
	public void setBokDetailId(String bokDetailId) {
		this.bokDetailId = bokDetailId;
	}
}
