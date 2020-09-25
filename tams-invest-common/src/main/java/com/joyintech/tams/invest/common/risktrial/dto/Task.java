package com.joyintech.tams.invest.common.risktrial.dto;

import java.util.Date;

public class Task {

	private String portfolioId;//组合与组合包代码
	private SupervisionTargetDto point;
	private Date date;
	
	public String getPortfolioId() {
		return portfolioId;
	}
	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId;
	}
	public SupervisionTargetDto getPoint() {
		return point;
	}
	public void setPoint(SupervisionTargetDto point) {
		this.point = point;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
