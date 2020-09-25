package com.joyintech.tams.disclosure.busicommon.poireport.echarts.dto;

/**
 * y坐标轴
 */
public class YAxis{

	public YAxis(String name,String type,String position,boolean scale,AxisLine axisLine) {
		setName(name);
		setType(type);
		setPosition(position);
		setScale(scale);
		setAxisLine(axisLine);
	}
	/**
	 * 类型 
	 * 
	 * 'value'：数值轴，适用于连续数据。 
	 * 'category'：类目轴，适用于离散的类目数据，为该类型时必须通过 data设置类目数据。 
	 * 'time'：时间轴，适用于连续的时序数据，与数值轴相比时间轴带有时间的格式化，在刻度计算上也有所不同，例如会根据跨度的范围来决定使用月，星期，日还是小时范围的刻度。
	 * 'log'：对数轴。适用于对数数据。
	 */
	private String type;
	
	/**
	 * 位置
	 * 
	 * left、right
	 */
	private String position;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 是否是脱离 0 值比例
	 * 
	 * true 坐标刻度不会强制包含零刻度
	 */
	private boolean scale;
	
	/**
	 * 轴线
	 */
	private AxisLine axisLine;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isScale() {
		return scale;
	}

	public void setScale(boolean scale) {
		this.scale = scale;
	}

	public AxisLine getAxisLine() {
		return axisLine;
	}

	public void setAxisLine(AxisLine axisLine) {
		this.axisLine = axisLine;
	}
	
	
}
