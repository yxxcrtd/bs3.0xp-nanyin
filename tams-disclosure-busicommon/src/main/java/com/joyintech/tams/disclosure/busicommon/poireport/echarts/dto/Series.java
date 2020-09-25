package com.joyintech.tams.disclosure.busicommon.poireport.echarts.dto;

/**
 * 图表数据
 */
public class Series {

	public Series() {
	}

	public Series(String name, String type, boolean smooth, String symbol, Object[] data) {
		setName(name);
		setType(type);
		setSmooth(smooth);
		setSymbol(symbol);
		setData(data);
	}

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 类型
	 * 
	 * line 折线/面积图、bar 柱状/条形图、pie 饼图
	 */
	private String type;

	/**
	 * 是否线条光滑
	 */
	private boolean smooth;

	/**
	 * 标记类型
	 * 
	 * circle、rect、roundRect、triangle、diamond、pin、arrow、none
	 */
	private String symbol;

	/**
	 * 数据
	 */
	private Object[] data;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isSmooth() {
		return smooth;
	}

	public void setSmooth(boolean smooth) {
		this.smooth = smooth;
	}

	public Object[] getData() {
		return data;
	}

	public void setData(Object[] data) {
		this.data = data;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

}
