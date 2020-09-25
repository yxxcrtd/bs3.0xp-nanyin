package com.joyintech.tams.disclosure.busicommon.poireport.echarts.dto;

/**
 * 线条样式
 */
public class LineStyle {

	public LineStyle(String color, String type) {
		setColor(color);
		setType(type);
	}

	/**
	 * 颜色
	 */
	private String color;

	/**
	 * 刻度线类型
	 * 
	 * solid 实线 dashed 虚线 dotted 散步
	 */
	private String type;

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
