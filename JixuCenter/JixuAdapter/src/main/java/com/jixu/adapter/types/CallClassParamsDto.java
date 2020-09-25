package com.jixu.adapter.types;

import java.util.Map;

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
	 *  个性化字段封装
	 */
	private Map<String,Object> params;

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

	public Map<String,Object> getParams() {
		return params;
	}

	public void setParams(Map<String,Object> params) {
		this.params = params;
	}
}
