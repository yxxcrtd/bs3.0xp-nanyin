package com.jixu.adapter.control.service;

import java.util.HashMap;

import com.jixu.common.type.ResultContainer;

public interface ExecuteJixuAdapter {
	
	/**
	 * 执行适配器
	 * @param adapterID	适配器编号
	 * @param outParams	传入参数
	 * @return
	 */
	public ResultContainer executeAdapter(int adapterID, HashMap<String, Object> outParams);
	
	/**
	 * 执行适配器
	 * @param adapterID	适配器编号
	 * @return
	 */
	public ResultContainer executeAdapter(int adapterID);
}
