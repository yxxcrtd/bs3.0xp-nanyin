/*
 * 文件名：CbondconversionratioService.java
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

import com.joyintech.tams.common.base.dao.InterfCbondconversionratioMapper;
import com.joyintech.tams.common.base.entity.InterfCbondconversionratio;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.service.interf.wind.conf.InterfDataSourceType;
import com.joyintech.service.interf.wind.dao.CbondconversionratioMapper;

/**
 * 中国债券回购标准券折算率接口处理详细
 * 
 * @author changwenbin
 * @version 1.0 2018年11月16日
 * @see CbondconversionratioService
 * @since 1.0
 */
@Service
public class CbondconversionratioService extends WindService<InterfCbondconversionratio> {

	/**
	 * Wind查询Mapper
	 */
	@Autowired
	private CbondconversionratioMapper selectMapper;

	/**
	 * 接口表登录Mapper
	 */
	@Autowired
	private InterfCbondconversionratioMapper insertMapper;

	/**
	 * {@inheritDoc}
	 */
	@DataSource(InterfDataSourceType.WIND)
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public List<InterfCbondconversionratio> getDataList(String sysDate, long startNum, long endNum) {
		return selectMapper.select(sysDate, startNum, endNum);
	}

	/**
	 * {@inheritDoc}
	 */
	@DataSource(DataSourceType.MASERT)
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public int insert(InterfCbondconversionratio entity) throws Exception {
		// 删除重复数据
		insertMapper.deleteByPrimaryKey(entity.getObjectId());
		// 登陆接口表
		return insertMapper.insertSelective(entity);
	}

}
