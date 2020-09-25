package com.joyintech.tams.disclosure.busicommon.poireport.echarts.dto;

/**
 * 图例
 */
public class Legend {

	public Legend(String left, String top, String bottom, Object[] data) {
		setLeft(left);
		setTop(top);
		setBottom(bottom);
		setData(data);
	}

	/**
	 * 离容器左侧的距离（auto、left、right、center、20、20%）
	 */
	private String left;

	/**
	 * 离容器上侧的距离（auto、top、middle、bottom、20、20%）
	 */
	private String top;

	/**
	 * 离容器下侧的距离（auto、20、20%）
	 */
	private String bottom;

	/**
	 * 图例文本数组
	 */
	private Object[] data;

	public String getLeft() {
		return left;
	}

	public void setLeft(String left) {
		this.left = left;
	}

	public String getTop() {
		return top;
	}

	public void setTop(String top) {
		this.top = top;
	}

	public String getBottom() {
		return bottom;
	}

	public void setBottom(String bottom) {
		this.bottom = bottom;
	}

	public Object[] getData() {
		return data;
	}

	public void setData(Object[] data) {
		this.data = data;
	}

}
