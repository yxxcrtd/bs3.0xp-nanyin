package com.joyintech.tams.disclosure.busicommon.poireport.echarts.dto;

import java.io.Serializable;
import java.util.List;

/**
 * echarts数据对象
 */
public class Option implements Serializable {

	private static final long serialVersionUID = -6350451414906691183L;

	/**
	 * 标题
	 */
	private Title title;

	/**
	 * 图例
	 */
	private Legend legend;

	/**
	 * x坐标
	 */
	private XAxis xAxis;

	/**
	 * y坐标集合
	 */
	private List<YAxis> yAxis;

	/**
	 * 数据集合
	 */
	private List<Series> Series;

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}

	public Legend getLegend() {
		return legend;
	}

	public void setLegend(Legend legend) {
		this.legend = legend;
	}

	public XAxis getxAxis() {
		return xAxis;
	}

	public void setxAxis(XAxis xAxis) {
		this.xAxis = xAxis;
	}

	public List<YAxis> getyAxis() {
		return yAxis;
	}

	public void setyAxis(List<YAxis> yAxis) {
		this.yAxis = yAxis;
	}

	public List<Series> getSeries() {
		return Series;
	}

	public void setSeries(List<Series> series) {
		Series = series;
	}
}
