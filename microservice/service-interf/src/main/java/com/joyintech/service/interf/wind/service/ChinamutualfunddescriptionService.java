/*
 * 文件名：ChinamutualfunddescriptionService.java
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

import com.joyintech.tams.common.base.dao.InfChinamutualfunddescriptionMapper;
import com.joyintech.tams.common.base.entity.InfChinamutualfunddescriptionWithBLOBs;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.service.interf.wind.conf.InterfDataSourceType;
import com.joyintech.service.interf.wind.dao.ChinamutualfunddescriptionMapper;

/**
 * 中国共同基金基本资料处理详细
 * 
 * @author changwenbin
 * @version 1.0 2018年11月16日
 * @see ChinamutualfunddescriptionService
 * @since 1.0
 */
@Service
public class ChinamutualfunddescriptionService extends WindService<InfChinamutualfunddescriptionWithBLOBs> {

	/**
	 * 查询Wind数据Mapper
	 */
	@Autowired
	private ChinamutualfunddescriptionMapper selectMapper;

	/**
	 * 接口表Mapper
	 */
	@Autowired
	private InfChinamutualfunddescriptionMapper infMapper;

	/**
	 * {@inheritDoc}
	 */
	@DataSource(InterfDataSourceType.WIND)
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public List<InfChinamutualfunddescriptionWithBLOBs> getDataList(String sysDate, long startNum, long endNum) {
		return selectMapper.select(sysDate, startNum, endNum);
	}

	/**
	 * {@inheritDoc}
	 */
	@DataSource(DataSourceType.MASERT)
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public int insert(InfChinamutualfunddescriptionWithBLOBs entity) throws Exception {
		// 删除接口表重复数据
		infMapper.deleteByPrimaryKey(entity.getObjectId());
		// 数据登录接口表
		return infMapper.insert(entity);
	}
}
