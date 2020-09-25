/*
 * 文件名：PayhsrcsecratingService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：changwenbin
 * 修改时间：2018年11月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.wind.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.service.interf.core.constant.InterfDictCode;
import com.joyintech.service.interf.wind.conf.InterfDataSourceType;
import com.joyintech.service.interf.wind.dao.BusinessMapper;
import com.joyintech.service.interf.wind.dao.PayhsrcsecratingMapper;
import com.joyintech.tams.common.base.dao.InterfPayhsrcsecratingMapper;
import com.joyintech.tams.common.base.dao.MstBondCreditRatingMapper;
import com.joyintech.tams.common.base.entity.InterfPayhsrcsecrating;
import com.joyintech.tams.common.base.entity.MstBondCreditRating;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 债券评级信息表接口详细
 * 
 * @author changwenbin
 * @version 1.0 2018年11月16日
 * @see PayhsrcsecratingService
 * @since 1.0
 */
@Service
public class PayhsrcsecratingService extends WindService<InterfPayhsrcsecrating> {

	/**
	 * Wind查询Mapper
	 */
	@Autowired
	private PayhsrcsecratingMapper selectMapper;

	/**
	 * 接口表登录Mapper
	 */
	@Autowired
	private InterfPayhsrcsecratingMapper insertMapper;

	/**
	 * 业务数据查询Mapper
	 */
	@Autowired
	private BusinessMapper busiMapper;

	/**
	 * 债券信用评级Mapper
	 */
	@Autowired
	private MstBondCreditRatingMapper creditRateMapper;

	/**
	 * {@inheritDoc}
	 */
	@DataSource(InterfDataSourceType.WIND)
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public List<InterfPayhsrcsecrating> getDataList(String sysDate, long startNum, long endNum) {
		return selectMapper.select(sysDate, startNum, endNum);
	}

	/**
	 * {@inheritDoc}
	 */
	@DataSource(DataSourceType.MASERT)
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public int insert(InterfPayhsrcsecrating entity) throws Exception {
		// 删除接口表重复数据
		// insertMapper.deleteByPrimaryKey(entity.getObjectId());
		// 登录接口表
		insertMapper.insertSelective(entity);
		// 业务表登录
		return saveBusinessData(entity);
	}

	/**
	 * 登录业务表
	 * 
	 * @param entity
	 *            债券评级信息
	 * @return 保存行数
	 * @see
	 * @since 1.0
	 */
	private int saveBusinessData(InterfPayhsrcsecrating entity) {
		MstBondCreditRating infoKey = new MstBondCreditRating();
		// 债券代码
		// infoKey.setSecId(changeSecCode(entity.getSeccode()));
		// 机构代码
		String compId = busiMapper.selectComIdByName(entity.getRatingorg());
		infoKey.setGradeOrgId(compId);
		// 评级日期
		infoKey.setGradeDate(entity.getPubdate());
		// 评级类型
		infoKey.setoType(
				changeRateType(InterfDictCode.评级类型, SysDatadictListService.GRADE_TYPE, entity.getRatingtype()));
		// 长期短期
		infoKey.setShortLongTerm(
				changeRateType(InterfDictCode.长期短期, SysDatadictListService.LONG_SHORT_TERM, entity.getRatingsubtype()));
		// 评级结果
		infoKey.setGradeResult(entity.getRating());
		MstBondCreditRating info = creditRateMapper.selectByPrimaryKey(infoKey);
		// 更新人
		infoKey.setUpdateUser(WIND_USER);
		// 更新时间
		Date dateTime = DateUtil.parse(DateUtil.getCurrentDateTime());
		infoKey.setUpdateTime(dateTime);
		if (info == null) {
			// 创建人
			infoKey.setCreateUser(WIND_USER);
			// 创建时间
			infoKey.setCreateTime(dateTime);
			// 录入方式
			// 一体化对应etliu start
			// infoKey.setInputType(DictCodeListExt1.录入方式_外部接入.getValue());
			infoKey.setInputType(DictCodeListExt1.录入方式_接口导入.getValue());
			// 一体化对应etliu end
			return creditRateMapper.insertSelective(infoKey);
		} else {
			return creditRateMapper.updateByPrimaryKeySelective(infoKey);
		}
	}

}
