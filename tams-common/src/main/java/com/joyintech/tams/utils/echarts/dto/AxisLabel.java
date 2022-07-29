package com.joyintech.tams.utils.echarts.dto;

/**
 * 坐标轴刻度标签的相关设置
 */
public class AxisLabel {

	public AxisLabel(){}

	public AxisLabel(String rotate,int interval){
		setRotate(rotate);
		setInterval(interval);
	}
	/**
	 * 刻度标签旋转的角度 [-90,90 度]
	 */
	private String rotate;

	/**
	 * 设置X轴的刻度间隔
	 */
	private int interval;

	private String formatter;

	public String getFormatter() {
		return formatter;
	}

	public void setFormatter(String formatter) {
		this.formatter = formatter;
	}

	public String getRotate() {
		return rotate;
	}

	public void setRotate(String rotate) {
		this.rotate = rotate;
	}

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}
}
