/*
 * 文件名：PayhsrcinterestrateService.java
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

import com.joyintech.tams.common.base.dao.InterfPayhsrcinterestrateMapper;
import com.joyintech.tams.common.base.dao.MstIntRateInfoMapper;
import com.joyintech.tams.common.base.entity.InterfPayhsrcinterestrate;
import com.joyintech.tams.common.base.entity.MstIntRateInfo;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.service.interf.wind.conf.InterfDataSourceType;
import com.joyintech.service.interf.wind.dao.PayhsrcinterestrateMapper;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 基础利率重置表接口处理详细
 * 
 * @author changwenbin
 * @version 1.0 2018年11月16日
 * @see PayhsrcinterestrateService
 * @since 1.0
 */
@Service
public class PayhsrcinterestrateService extends WindService<InterfPayhsrcinterestrate> {

	/**
	 * Wind查询mapper
	 */
	@Autowired
	private PayhsrcinterestrateMapper selectMapper;

	/**
	 * 接口表登录mapper
	 */
	@Autowired
	private InterfPayhsrcinterestrateMapper insertMapper;

	/**
	 * 利率行情mapper
	 */
	@Autowired
	private MstIntRateInfoMapper intRateInfoMapper;

	/**
	 * {@inheritDoc}
	 */
	@DataSource(InterfDataSourceType.WIND)
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
    public List<InterfPayhsrcinterestrate> getDataList(String sysDate, long startNum,
                                                       long endNum) {
		return selectMapper.select(sysDate, startNum, endNum);
	}

	/**
	 * {@inheritDoc}
	 */
	@DataSource(DataSourceType.MASERT)
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
    public int insert(InterfPayhsrcinterestrate entity)
        throws Exception {
		// 删除接口表重复数据
		insertMapper.deleteByPrimaryKey(entity.getObjectId());
		// 接口表登录
		insertMapper.insertSelective(entity);
		// 业务表登录
		return saveBusinessData(entity);
	}

	/**
	 * 登录业务表
	 * 
     * @param entity 基础利率
	 * @return 保存行数
	 * @see
	 * @since 1.0
	 */
	private int saveBusinessData(InterfPayhsrcinterestrate entity) {
		MstIntRateInfo infoKey = new MstIntRateInfo();
		String intRateId = entity.getCurrencycode();
		// 行情代码
		infoKey.setIntRateId(intRateId);
		// 生效日
		infoKey.setEffDate(entity.getEffectivedate());
		// 发布日
		infoKey.setRelaeseDate(entity.getPublishdate());
		// 利率
		infoKey.setIntRate(entity.getInterestrate());
		// 币种
		infoKey.setCcy(entity.getCurrency());
        // 利率类型
        infoKey.setIntRateType(DictCodeListExt1.基准类型_2_利率.getValue());
		// 更新人
		infoKey.setUpdateUser(WIND_USER);
		// 更新时间
		Date dateTime = DateUtil.parse(DateUtil.getCurrentDateTime());
		infoKey.setUpdateTime(dateTime);
		if (intRateInfoMapper.selectByPrimaryKey(infoKey) == null) {
			// 创建人
			infoKey.setCreateUser(WIND_USER);
			// 创建时间
			infoKey.setCreateTime(dateTime);
			// 录入方式
			// 一体化对应etliu start
			// infoKey.setInputType(DictCodeListExt1.录入方式_外部接入.getValue());
			infoKey.setInputType(DictCodeListExt1.录入方式_接口导入.getValue());
			// 一体化对应etliu end
			return intRateInfoMapper.insertSelective(infoKey);
        }
        else {
			return intRateInfoMapper.updateByPrimaryKeySelective(infoKey);
		}
	}
}
