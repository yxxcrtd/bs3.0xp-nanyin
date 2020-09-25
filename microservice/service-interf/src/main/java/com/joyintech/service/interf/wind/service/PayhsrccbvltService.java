/*
 * 文件名：PayhsrccbvltService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：changwenbin
 * 修改时间：2018年11月19日
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

import com.joyintech.tams.common.base.dao.InterfPayhsrccbvltMapper;
import com.joyintech.tams.common.base.dao.MstBondValuationInfoMapper;
import com.joyintech.tams.common.base.entity.InterfPayhsrccbvlt;
import com.joyintech.tams.common.base.entity.MstBondValuationInfo;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.service.interf.wind.conf.InterfDataSourceType;
import com.joyintech.service.interf.wind.dao.PayhsrccbvltMapper;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 债券市价估值表接口处理详细
 * 
 * @author changwenbin
 * @version 1.0 2018年11月19日
 * @see PayhsrccbvltService
 * @since 1.0
 */
@Service
public class PayhsrccbvltService extends WindService<InterfPayhsrccbvlt> {

	/**
	 * Wind查询Mapper
	 */
	@Autowired
	private PayhsrccbvltMapper selectMapper;

	/**
	 * 接口表登录Mapper
	 */
	@Autowired
	private InterfPayhsrccbvltMapper insertMapper;

	/**
	 * 债券估值行情Mapper
	 */
	@Autowired
	private MstBondValuationInfoMapper bondValuationMapper;

	/**
	 * {@inheritDoc}
	 */
	@DataSource(InterfDataSourceType.WIND)
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public List<InterfPayhsrccbvlt> getDataList(String sysDate, long startNum, long endNum) {
		return selectMapper.select(sysDate, startNum, endNum);
	}

	/**
	 * {@inheritDoc}
	 */
	@DataSource(DataSourceType.MASERT)
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public int insert(InterfPayhsrccbvlt entity) throws Exception {
		// 删除重复数据
		//insertMapper.deleteByPrimaryKey(entity.getObjectId());
		// 登陆接口表
		insertMapper.insertSelective(entity);
		// 业务表登录
		return saveBusinessData(entity);
	}

	/**
	 * 登录业务表
	 * 
	 * @param entity
	 *            债券市价估值
	 * @return 保存行数
	 * @see
	 * @since 1.0
	 */
	private int saveBusinessData(InterfPayhsrccbvlt entity) {
		MstBondValuationInfo info = new MstBondValuationInfo();
		// 债券代码
		info.setSecId(changeSecCode(entity.getSecid()));
		// 估值日
		info.setValueDate(DateUtil.parse(entity.getCdate()));
		// 估值来源
		info.setValueSource(DictCodeList.估值来源_中债登_CBC.getValue());
		// 剩余期限
		info.setLastPeriod(entity.getEncashYears());
		// 市价全价
		info.setPrice(entity.getMdprice());
		// 市价净价
		info.setNetprice(entity.getMcprice());
		// 市价久期
		info.setmDuration(entity.getmMduration());
		// 市价凸性
		info.setmConvexity(entity.getmCnvxty());
		// 基点价值
		info.setBpvalue(entity.getMbasisvalue());
		// 利差久期
		info.setSduration(entity.getMsprdD());
		// 利差凸性
		info.setScnvxty(entity.getMsprdCnvxty());
		// 利率久期
		info.setInterestDuration(entity.getMyieldD());
		// 利率凸性
		info.setInterestCnvxty(entity.getMyieldCnvxty());
		// 市价收益率
		info.setBpyield(entity.getMyield());
		// 更新人
		info.setUpdateUser(WIND_USER);
		// 更新时间
		Date dateTime = DateUtil.parse(DateUtil.getCurrentDateTime());
		info.setUpdateTime(dateTime);
		if (bondValuationMapper.selectByPrimaryKey(info) == null) {
			// 创建人
			info.setCreateUser(WIND_USER);
			// 创建时间
			info.setCreateTime(dateTime);
			// 录入方式
			// 一体化对应etliu start
			// info.setInputType(DictCodeListExt1.录入方式_外部接入.getValue());
			info.setInputType(DictCodeListExt1.录入方式_接口导入.getValue());
			// 一体化对应etliu end
			return bondValuationMapper.insertSelective(info);
		} else {
			return bondValuationMapper.updateByPrimaryKeySelective(info);
		}
	}
}
