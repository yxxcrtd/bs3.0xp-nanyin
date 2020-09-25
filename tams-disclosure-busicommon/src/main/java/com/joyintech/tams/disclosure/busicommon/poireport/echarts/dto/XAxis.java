package com.joyintech.tams.disclosure.busicommon.poireport.echarts.dto;

/**
 * x坐标轴
 */
public class XAxis {

	public XAxis(String name, String type, Object boundaryGap, AxisLabel axisLabel, Object[] data) {
		setName(name);
		setType(type);
		setBoundaryGap(boundaryGap);
		setAxisLabel(axisLabel);
		setData(data);
	}
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 类型
	 * 
	 * 'value' 数值轴，适用于连续数据。 
	 * 'category' 类目轴，适用于离散的类目数据，为该类型时必须通过 data设置类目数据。 
	 * 'time' 时间轴，适用于连续的时序数据，与数值轴相比时间轴带有时间的格式化，在刻度计算上也有所不同，例如会根据跨度的范围来决定使用月，星期，日还是小时范围的刻度。
	 * 'log' 对数轴。适用于对数数据。
	 */
	private String type;
	
	/**
	 * 坐标轴两边留白策略
	 */
	private Object boundaryGap;

	/**
	 * 刻度设置
	 */
	private AxisLabel axisLabel;
	
	/**
	 * 坐标说明文本数组
	 */
	private Object[] data;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getBoundaryGap() {
		return boundaryGap;
	}

	public void setBoundaryGap(Object boundaryGap) {
		this.boundaryGap = boundaryGap;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public AxisLabel getAxisLabel() {
		return axisLabel;
	}

	public void setAxisLabel(AxisLabel axisLabel) {
		this.axisLabel = axisLabel;
	}

	public Object[] getData() {
		return data;
	}

	public void setData(Object[] data) {
		this.data = data;
	}
	
}
