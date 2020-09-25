/*
 * 文件名：InvestInterfMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管系统V2.0
 * 修改人：王波
 * 修改时间：2020年1月20日
 * 修改内容：新建
 * 系统名称：理财资管系统V2.0
 */
 
package com.joyintech.invest.interf.common.core.dao;

import com.joyintech.framework.grid.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.invest.interf.common.core.entity.InterfStatus;

import java.util.List;
import java.util.Map;

/**
 * 
 * 〈投管接口状态mapper〉
 * 〈更新接口状态表相关方法〉
 * @author 王波
 * @version 1.0 2020年1月20日
 * @see InvestInterfStsMapper
 * @since 1.0
 */
@Repository
public interface InvestInterfStsMapper {
	/**
	 * <插入一条发送消息>
	 * 
	 * @return 记录数
	 * @see
	 * @since 1.0
	 */
	public int insertSendMsg(InterfStatus interfStatus);
	
	/**
	 * 
	 * <根据接口流水号增加发送次数>
	 * <功能详细描述>
	 * @param interfTermtrc
	 * @see
	 * @since 1.0
	 */
	public void incrSendCount(@Param("interfTermtrc") String interfTermtrc);

	/**
	 *
	 * <根据接口流水号获取当前流水的发送状态>
	 * <功能详细描述>
	 * @param interfTermtrc
	 * @see
	 * @since 1.0
	 */
	public String getSendStatus(@Param("interfTermtrc") String interfTermtrc);
	/**
	 * 
	 * <根据接口流水号更新发送状态>
	 * <功能详细描述>
	 * @param interfTermtrc
	 * @param status
	 * @see
	 * @since 1.0
	 */
	public void updateInterfStatus(@Param("interfTermtrc") String interfTermtrc, @Param("status")String status);

	/**
	 * @ 获取日志列表
	 * @param dataMap
	 * @return
	 * @see
	 * @since 1.0
	 */
	List<InterfStatus> getLogList(Map<String, Object> dataMap);


	/**
	 * @ 根据请求日期参数获取日志列表
	 * @param dataMap
	 * @return
	 * @see
	 * @since 1.0
	 */
	List<InterfStatus> getLogListByDate(Map<String, Object> dataMap);


}
