/*
 * 文件名：PayhsrcsecscheduleService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年11月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.wind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.tams.common.base.dao.InterfPayhsrcsecscheduleMapper;
import com.joyintech.tams.common.base.entity.InterfPayhsrcsecschedule;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.service.interf.wind.conf.InterfDataSourceType;
import com.joyintech.service.interf.wind.dao.PayhsrcsecscheduleMapper;

/**
 * 债券付息计划处理详细
 * 
 * @author zhangrui1
 * @version 1.0 2018年11月15日
 * @see PayhsrcsecscheduleService
 * @since 1.0
 */
@Service
public class PayhsrcsecscheduleService extends WindService<InterfPayhsrcsecschedule> {

	/**
	 * 数据查询
	 */
	@Autowired
	private PayhsrcsecscheduleMapper selectMapper;

	/**
	 * 接口表登录
	 */
	@Autowired
	private InterfPayhsrcsecscheduleMapper insertMapper;

	/**
	 * {@inheritDoc}
	 */
	@DataSource(InterfDataSourceType.WIND)
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public List<InterfPayhsrcsecschedule> getDataList(String sysDate, long startNum, long endNum) {
		return selectMapper.select(sysDate, startNum, endNum);
	}

	/**
	 * {@inheritDoc}
	 */
	@DataSource(DataSourceType.MASERT)
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public int insert(InterfPayhsrcsecschedule entity) throws Exception {
	//	insertMapper.deleteByPrimaryKey(entity.getObjectId());
		return insertMapper.insert(entity);
	}

}
