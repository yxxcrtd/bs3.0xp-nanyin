/*
 * 文件名：CmoneymarketfscarryovermService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：changwenbin
 * 修改时间：2019年1月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.wind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.tams.common.base.dao.InfCmoneymarketfscarryovermMapper;
import com.joyintech.tams.common.base.entity.InfCmoneymarketfscarryoverm;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.service.interf.wind.conf.InterfDataSourceType;
import com.joyintech.service.interf.wind.dao.CmoneymarketfscarryovermMapper;

/**
 * 中国货币市场基金份额结转方式处理详细
 * 
 * @author zhangrui1
 * @version 1.0 2019年1月7日
 * @see CmoneymarketfscarryovermService
 * @since 1.0
 */
@Service
public class CmoneymarketfscarryovermService extends WindService<InfCmoneymarketfscarryoverm> {

	/**
	 * Wind查询Mapper
	 */
	@Autowired
	private CmoneymarketfscarryovermMapper selectMapper;

	/**
	 * 接口表登录Mapper
	 */
	@Autowired
	private InfCmoneymarketfscarryovermMapper insertMapper;

	/**
	 * {@inheritDoc}
	 */
	@DataSource(InterfDataSourceType.WIND)
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public List<InfCmoneymarketfscarryoverm> getDataList(String sysDate, long startNum, long endNum) {
		return selectMapper.select(sysDate, startNum, endNum);
	}

	/**
	 * {@inheritDoc}
	 */
	@DataSource(DataSourceType.MASERT)
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public int insert(InfCmoneymarketfscarryoverm entity) throws Exception {
		// 删除重复数据
		insertMapper.deleteByPrimaryKey(entity.getObjectId());
		// 登陆接口表
		return insertMapper.insertSelective(entity);
	}

}
