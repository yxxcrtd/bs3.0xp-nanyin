package com.jixu.adapter.types;

import java.util.HashMap;

import com.jixu.common.pojo.InterfaceSource;
import com.orm.obj.InterfaceLog;

/**
 * 	自定义接口参数
 * @author 桃汰猪
 *
 */
public class CustomParams {
	
	/**
	 * 	适配器所有配置参数
	 */
	private InterfaceSource source;
	
	/**
	 * 	执行传入参数
	 */
	private HashMap<String, Object> paramsMap;
	
	/**
	 * 	执行结果日志，有成功数量，生成文件等信息
	 */
	private InterfaceLog ilog;

	/**
	 * 	适配器所有配置参数
	 * @return 适配器所有配置参数
	 */
	public InterfaceSource getSource() {
		return source;
	}

	/**
	 * 	适配器所有配置参数
	 * @param source 适配器所有配置参数
	 */
	public void setSource(InterfaceSource source) {
		this.source = source;
	}

	/**
	 *  执行传入参数
	 * @return 执行传入参数
	 */
	public HashMap<String, Object> getParamsMap() {
		return paramsMap;
	}

	/**
	 * 	执行传入参数
	 * @param paramsMap	执行传入参数
	 */
	public void setParamsMap(HashMap<String, Object> paramsMap) {
		this.paramsMap = paramsMap;
	}

	/**
	 * 	执行结果日志，有成功数量，生成文件等信息
	 * @return	执行结果日志，有成功数量，生成文件等信息
	 */
	public InterfaceLog getIlog() {
		return ilog;
	}

	/**
	 * 	执行结果日志，有成功数量，生成文件等信息
	 * @param ilog	执行结果日志，有成功数量，生成文件等信息
	 */
	public void setIlog(InterfaceLog ilog) {
		this.ilog = ilog;
	}
}
