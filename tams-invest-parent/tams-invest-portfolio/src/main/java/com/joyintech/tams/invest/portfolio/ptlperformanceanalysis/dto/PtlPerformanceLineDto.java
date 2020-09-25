package com.joyintech.tams.invest.portfolio.ptlperformanceanalysis.dto;

import java.util.List;

public class PtlPerformanceLineDto {
	
	private String portfolioId;
	
	private String searchDateStart;
	
	private String searchDateEnd;
	
	//横坐标x的指标
	private List<String>  xaxisdata;
	
	//纵坐标y的指标
	private List<String> yrightaxisdata;
	
	//纵坐标y的指标
	private List<String> yleftaxisdata;
	
	
	private List<String> yrightdata;
	
	
	private List<String> yleftdata;
	
	

	public String getPortfolioId() {
		return portfolioId;
	}


	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId;
	}


	public String getSearchDateStart() {
		return searchDateStart;
	}


	public void setSearchDateStart(String searchDateStart) {
		this.searchDateStart = searchDateStart;
	}


	public String getSearchDateEnd() {
		return searchDateEnd;
	}


	public void setSearchDateEnd(String searchDateEnd) {
		this.searchDateEnd = searchDateEnd;
	}


	public List<String> getXaxisdata() {
		return xaxisdata;
	}


	public void setXaxisdata(List<String> xaxisdata) {
		this.xaxisdata = xaxisdata;
	}


	public List<String> getYrightaxisdata() {
		return yrightaxisdata;
	}


	public void setYrightaxisdata(List<String> yrightaxisdata) {
		this.yrightaxisdata = yrightaxisdata;
	}


	public List<String> getYleftaxisdata() {
		return yleftaxisdata;
	}


	public void setYleftaxisdata(List<String> yleftaxisdata) {
		this.yleftaxisdata = yleftaxisdata;
	}


	public List<String> getYrightdata() {
		return yrightdata;
	}


	public void setYrightdata(List<String> yrightdata) {
		this.yrightdata = yrightdata;
	}


	public List<String> getYleftdata() {
		return yleftdata;
	}


	public void setYleftdata(List<String> yleftdata) {
		this.yleftdata = yleftdata;
	}

}
