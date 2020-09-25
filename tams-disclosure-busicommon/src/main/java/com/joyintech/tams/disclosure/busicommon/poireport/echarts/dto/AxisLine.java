package com.joyintech.tams.disclosure.busicommon.poireport.echarts.dto;

/**
 * 坐标轴线
 */
public class AxisLine {

	public AxisLine(LineStyle lineStyle) {
		setLineStyle(lineStyle);
	}

	/**
	 * 线条样式
	 */
	private LineStyle lineStyle;

	public LineStyle getLineStyle() {
		return lineStyle;
	}

	public void setLineStyle(LineStyle lineStyle) {
		this.lineStyle = lineStyle;
	}

}
