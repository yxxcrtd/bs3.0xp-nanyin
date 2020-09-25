package com.joyintech.service.portfolio.ptlperformanceanalysis.dto;

import java.util.ArrayList;
import java.util.List;

public class PtlPerformanceLineDto {
	
	//横坐标x的指标
	private List<String>  xaxisdata = new ArrayList<String>();
	
	//纵坐标y的指标
	private List<String> yrightaxisdata = new ArrayList<String>();
	
	//纵坐标y的指标
	private List<String> yleftaxisdata = new ArrayList<String>();
	
	
	private List<String> yrightdata = new ArrayList<String>();
	
	
	private List<String> yleftdata = new ArrayList<String>();

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
