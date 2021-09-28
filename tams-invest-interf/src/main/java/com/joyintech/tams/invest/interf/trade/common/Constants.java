package com.joyintech.tams.invest.interf.trade.common;

/**
 * 接口Constants
 * 
 * @author yflv
 * @version 1.0 2021年09月24日
 * @see Constants
 * @since 1.0
 */
public class Constants {
	/**
	 * 接口类型-接收
	 */
	public final static String INTERF_TYPE_RECEIVE = "RECEIVE";
	/**
	 * 接口类型-发送
	 */
	public final static String INTERF_TYPE_SEND = "SEND";
	/**
	 * 接口系统-信评系统
	 */
	public final static String INTERF_SYSTEM_XP = "xingping";
	/**
	 * 接口系统-恒生资管系统
	 */
	public final static String INTERF_SYSTEM_HSZG = "hszg";
	/**
	 *接口状态-1新增
	 */
	public final static Integer INTERF_STATUS_NEW = 1;
    /**
	 *接口状态-2处理中
	 */
	public final static Integer INTERF_STATUS_PROCESS = 2;
	/**
	 *接口状态-3处理成功
	 */
	public final static Integer INTERF_STATUS_SUCCESS = 3;
	/**
	 *接口状态-4处理失败
	 */
	public final static Integer INTERF_STATUS_FAIL = 4;
	/**
	 *线程数
	 */
	public final static Integer INTERF_THREAD_NUMBER = 10;
	/**
	 *接口配置状态-1有效
	 */
	public final static Integer INTERF_CONFIG_STATUS_ENABLE = 1;
	/**
	 *接口配置状态-0无效
	 */
	public final static Integer INTERF_CONFIG_STATUS_DISABLE = 0;
	/**
	 *接口默认执行方法
	 */
	public final static String INTERF_DEFAULT_METHOD = "executeInterf";

}
