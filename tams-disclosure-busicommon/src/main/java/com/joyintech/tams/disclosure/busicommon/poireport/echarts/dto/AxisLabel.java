package com.joyintech.tams.disclosure.busicommon.poireport.echarts.dto;

/**
 * 坐标轴刻度标签的相关设置
 */
public class AxisLabel {
	
	public AxisLabel(String rotate){
		setRotate(rotate);
	}
	/**
	 * 刻度标签旋转的角度 [-90,90 度]
	 */
	private String rotate;

	public String getRotate() {
		return rotate;
	}

	public void setRotate(String rotate) {
		this.rotate = rotate;
	}

}
