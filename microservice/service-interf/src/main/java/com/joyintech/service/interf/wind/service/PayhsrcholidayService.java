/*
 * 文件名：PayhsrcholidayService.java
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

import com.joyintech.tams.common.base.dao.InterfPayhsrcholidayMapper;
import com.joyintech.tams.common.base.dao.MstHolidayMapper;
import com.joyintech.tams.common.base.entity.InterfPayhsrcholiday;
import com.joyintech.tams.common.base.entity.MstHoliday;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.interf.core.constant.InterfDictCodeList;
import com.joyintech.service.interf.wind.conf.InterfDataSourceType;
import com.joyintech.service.interf.wind.dao.PayhsrcholidayMapper;

/**
 * 节假日信息数据接口处理详细
 * 
 * @author changwenbin
 * @version 1.0 2018年11月16日
 * @see PayhsrcholidayService
 * @since 1.0
 */
@Service
public class PayhsrcholidayService extends WindService<InterfPayhsrcholiday> {

	/**
	 * 用户
	 */
	private static final String USER = "system";

	/**
	 * Wind查询Mapper
	 */
	@Autowired
	private PayhsrcholidayMapper selectMapper;

	/**
	 * 接口表登录Mapper
	 */
	@Autowired
	private InterfPayhsrcholidayMapper insertMapper;

	/**
	 * 业务表Mapper
	 */
	@Autowired
	private MstHolidayMapper mstHolidayMapper;

	/**
	 * {@inheritDoc}
	 */
	@DataSource(InterfDataSourceType.WIND)
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public List<InterfPayhsrcholiday> getDataList(String sysDate, long startNum, long endNum) {
		return selectMapper.select(sysDate, startNum, endNum);
	}

	/**
	 * {@inheritDoc}
	 */
	@DataSource(DataSourceType.MASERT)
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public int insert(InterfPayhsrcholiday entity) throws Exception {
		// 删除重复数据
		insertMapper.deleteByPrimaryKey(entity.getObjectId());
		// 登陆接口表
		insertMapper.insertSelective(entity);
		// 登录业务表
		return saveBussinessData(entity);
	}

	/**
	 * 业务表登录
	 * 
	 * @param entity
	 *            登录数据
	 * @return 登录件数
	 * @see
	 * @since 1.0
	 */
	private int saveBussinessData(InterfPayhsrcholiday entity) {
		// 是否工作日 的数据字典转换
		long effectflag = 0;
		if (StringUtil.equals(entity.getEffectflag(), InterfDictCodeList.WIND节假日标志_节假日.getValue())) {
			effectflag = 1;
		}
		// 设置查询entity
		MstHoliday mstkey = new MstHoliday();
		mstkey.setCalendarId("01");
		mstkey.setIsHoliday(effectflag);
		mstkey.setCdate(DateUtil.parse(entity.getHolidate()));
		mstkey.setUpdateUser(USER);
		// 查询数据
		MstHoliday mst = mstHolidayMapper.selectByPrimaryKey(mstkey);
		if (mst == null) {
			mstkey.setCreateUser(USER);
			// 登录业务表
			return mstHolidayMapper.insertSelective(mstkey);
		}
		// 如果是否节假日不一样，更新业务表
		return effectflag != mst.getIsHoliday() ? mstHolidayMapper.updateByPrimaryKeySelective(mstkey) : 0;
	}
}
