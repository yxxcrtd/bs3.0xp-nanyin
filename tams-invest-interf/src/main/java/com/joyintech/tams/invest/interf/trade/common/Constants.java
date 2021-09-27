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
	 * 信评系统
	 */
	public final static String INTERF_SYSTEM_XP = "xingping";
	/**
	 * 恒生资管系统
	 */
	public final static String INTERF_SYSTEM_ZG = "hszg";
	/**
	 *状态1新增
	 */
	public final static Integer INTERF_STATUS_NEW = 1;
    /**
	 *状态2处理中
	 */
	public final static Integer INTERF_STATUS_PROCESS = 2;
	/**
	 *线程数
	 */
	public final static Integer INTERF_THREAD_NUMBER = 10;
	/**
	 *接口配置状态1有效
	 */
	public final static Integer INTERF_CONFIG_STATUS_ENABLE = 1;
	/**
	 *接口配置状态1无效
	 */
	public final static Integer INTERF_CONFIG_STATUS_DISABLE = 0;
	/**
	 *接口默认执行方法
	 */
	public final static String INTERF_DEFAULT_METHOD = "executeInterf";

}
