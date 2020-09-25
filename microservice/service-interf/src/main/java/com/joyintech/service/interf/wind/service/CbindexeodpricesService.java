/*
 * 文件名：CbindexeodpricesServic.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：changwenbin
 * 修改时间：2018年11月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.wind.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.tams.common.base.dao.InterfCbindexeodpricesMapper;
import com.joyintech.tams.common.base.entity.InterfCbindexeodprices;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.service.interf.wind.conf.InterfDataSourceType;
import com.joyintech.service.interf.wind.dao.CbindexeodpricesMapper;

/**
 * 中国债券指数日行情处理详细
 * 
 * @author changwenbin
 * @version 1.0 2018年11月16日
 * @see CbindexeodpricesService
 * @since 1.0
 */
@Service
public class CbindexeodpricesService extends WindService<InterfCbindexeodprices> {

	/**
	 * Wind查询Mapper
	 */
	@Autowired
	private CbindexeodpricesMapper selectMapper;

	/**
	 * 接口表登录Mapper
	 */
	@Autowired
	private InterfCbindexeodpricesMapper insertMapper;

	/**
	 * {@inheritDoc}
	 */
	@DataSource(InterfDataSourceType.WIND)
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public List<InterfCbindexeodprices> getDataList(String sysDate, long startNum, long endNum) {
		return selectMapper.select(sysDate, startNum, endNum);
	}

	/**
	 * {@inheritDoc}
	 */
	@DataSource(DataSourceType.MASERT)
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public int insert(InterfCbindexeodprices entity) throws Exception {
		// 删除重复数据
		insertMapper.deleteByPrimaryKey(entity.getObjectId());
		// 登陆接口表
		return insertMapper.insertSelective(entity);
	}
}
