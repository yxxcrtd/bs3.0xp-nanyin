package com.joyintech.interf.bankname.sale.dto;

/**
 * 导出模块调用下游，传给下游的数据
 * @author 桃汰猪
 *
 */
public class CallClassParamsDto {
	
	/**
	 * 适配器编号
	 */
	private int adapterID;
	
	/**
	 * 适配器名称
	 */
	private String adapterName;

	/**
	 * @return	适配器编号
	 */
	public int getAdapterID() {
		return adapterID;
	}

	public void setAdapterID(int adapterID) {
		this.adapterID = adapterID;
	}

	/**
	 * 适配器名称
	 * @return
	 */
	public String getAdapterName() {
		return adapterName;
	}

	public void setAdapterName(String adapterName) {
		this.adapterName = adapterName;
	}
}
