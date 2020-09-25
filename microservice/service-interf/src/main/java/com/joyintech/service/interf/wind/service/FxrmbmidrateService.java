/*
 * 文件名：FxrmbmidrateService.java
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

import com.joyintech.tams.common.base.dao.InterfFxrmbmidrateMapper;
import com.joyintech.tams.common.base.dao.MstExchargeQuoteMapper;
import com.joyintech.tams.common.base.entity.InterfFxrmbmidrate;
import com.joyintech.tams.common.base.entity.MstExchargeQuote;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.interf.wind.conf.InterfDataSourceType;
import com.joyintech.service.interf.wind.dao.FxrmbmidrateMapper;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 中国外汇市场汇率接口处理
 * 
 * @author changwenbin
 * @version 1.0 2018年11月16日
 * @see FxrmbmidrateService
 * @since 1.0
 */
@Service
public class FxrmbmidrateService extends WindService<InterfFxrmbmidrate> {

	/**
	 * Wind查询Mapper
	 */
	@Autowired
	private FxrmbmidrateMapper selectMapper;

	/**
	 * 接口表登录Mapper
	 */
	@Autowired
	private InterfFxrmbmidrateMapper insertMapper;

	/**
	 * 汇率行情Mapper
	 */
	@Autowired
	private MstExchargeQuoteMapper exchargeQuoteMapper;

	/**
	 * {@inheritDoc}
	 */
	@DataSource(InterfDataSourceType.WIND)
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public List<InterfFxrmbmidrate> getDataList(String sysDate, long startNum, long endNum) {
		return selectMapper.select(sysDate, startNum, endNum);
	}

	/**
	 * {@inheritDoc}
	 */
	@DataSource(DataSourceType.MASERT)
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public int insert(InterfFxrmbmidrate entity) throws Exception {
		// 删除接口表重复数据
		insertMapper.deleteByPrimaryKey(entity.getObjectId());
		// 登录接口表
		insertMapper.insertSelective(entity);
		// 业务表登录
		return saveBusinessData(entity);
	}

	/**
	 * 登录业务表
	 * 
	 * @param entity
	 *            中国外汇市场汇率
	 * @return 保存行数
	 * @see
	 * @since 1.0
	 */
	private int saveBusinessData(InterfFxrmbmidrate entity) {
		MstExchargeQuote info = new MstExchargeQuote();
		// 行情代码
		info.setExchargeCode(entity.getCrncyCode());
		// 交易日
		info.setTradeDate(DateUtil.parse(entity.getTradeDt()));
		// 开盘价
		info.setOpenPrice(entity.getCrncyMidrate());
		// 最高价
		info.setHighPrice(entity.getCrncyMidrate());
		// 最低价
		info.setLowPrice(entity.getCrncyMidrate());
		// 中间价
		info.setMidPrice(entity.getCrncyMidrate());

		String ccy = entity.getCrncyCode();
		int index = StringUtil.indexOf(ccy, "/");
		// 基础币种
		info.setBasisCyy(StringUtil.substring(ccy, 0, index));
		// 报价币种
		info.setQuotCyy(StringUtil.substring(ccy, index + 1));
		// 报价单位
		info.setQuotUnit(1L);
		// 更新人
		info.setUpdateUser(WIND_USER);
		// 更新时间
		Date dateTime = DateUtil.parse(DateUtil.getCurrentDateTime());
		info.setUpdateTime(dateTime);
		if (exchargeQuoteMapper.selectByPrimaryKey(info) == null) {
			// 创建人
			info.setCreateUser(WIND_USER);
			// 创建时间
			info.setCreateTime(dateTime);
			// 录入方式
			// 一体化对应etliu start
			// info.setInputType(DictCodeListExt1.录入方式_外部接入.getValue());
			info.setInputType(DictCodeListExt1.录入方式_接口导入.getValue());
			// 一体化对应etliu end
			return exchargeQuoteMapper.insertSelective(info);
		} else {
			return exchargeQuoteMapper.updateByPrimaryKeySelective(info);
		}
	}
}
