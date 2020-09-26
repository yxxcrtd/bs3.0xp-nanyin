/*
 * 文件名：MoneyTaAutoCalReaderMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：May 25, 2019
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.batch.investratechange.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.joyintech.tams.valuation.batch.investratechange.dto.InvestRateChangeReaderDto;
import com.joyintech.tams.valuation.common.dto.DfMarketDataDto;

@Repository
public interface InvestRateChangeMapper {
	/**
	 * 查询需要利率交换的交易数据
	 * @param map
	 * @return
	 */
	List<InvestRateChangeReaderDto> selectNeedChangeTrade(Map<String, Object> map);
	/**
	 * 查询SHIBOR基准利率
	 */
	List<DfMarketDataDto> getShiborRateInfo(Map<String, Object> map);
	/**
	 * 	查询利率互换曲线信息
	 */
	List<DfMarketDataDto> getYieldCurveInfo(Map<String, Object> map);
	/**
	 * 查询SHIBOR-3m利率行情
	 */
	List<DfMarketDataDto> getShibor3mRateInfo(Map<String, Object> map);
	/**
	 * 查询节假日信息
	 */
	List<String> getHolidayList(Map<String, Object> map);
}
