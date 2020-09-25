package com.jixu.adapter.custom.service;

import com.jixu.adapter.types.CustomParams;
import com.jixu.common.type.ResultContainer;

public interface CustomExecuteService {
	
	/**
	 * 	适配器执行之后的自定义逻辑处理
	 * @param params
	 * @return
	 */
	public ResultContainer executeCustom(CustomParams params);
}
