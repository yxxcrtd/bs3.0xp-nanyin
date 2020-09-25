/*
 * 文件名：InfoDiscAutoConfirmMapper.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：wangmin
 * 修改时间：2018年12月8日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */
 
package com.joyintech.tams.batch.disclosure.informationdisclourse.infodiscautoconfirm.dao;


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
public interface InfoDiscAutoConfirmMapper {
	/**
	 * 将自动确认的未确认数据披露数据更新为已确认
	 * @param isConfirm 修改的已确认状态
	 * @param unConfirm 传入的未确认状态
	 * @param isAutoConfirm 是否自动确认
	 * @return
	 */
	int updateAutoConfirm(@Param("isConfirm") String isConfirm, @Param("unConfirm") String unConfirm,
			@Param("isAutoConfirm") String isAutoConfirm);
}
