/*
 * 文件名：InfoDiscAutoConfirmMapper.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：wangmin
 * 修改时间：2018年12月8日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */
 
package com.joyintech.tams.batch.disclosure.informationdisclourse.doReady.dao;


import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;



/**
 * 自动确认批处理mapper
 * @author wangmin
 * @version 1.0 2018年12月8日
 * @see InfoDiscAutoConfirmMapper
 * @since 1.0
 */
@Repository
public interface DisDoReadyMapper {


	/**
	 * 
	 * @param date
	 * @return
	 */
	int updateReadybyAdapter(@Param("date") String date);
	
	
	
	/**
	 * 
	 * @param date
	 * @return
	 */
	int insertReadyByInterf(@Param("date") String date);
	
	
	
	/**
	 * 
	 * @return
	 */
	String getValueId();
}
