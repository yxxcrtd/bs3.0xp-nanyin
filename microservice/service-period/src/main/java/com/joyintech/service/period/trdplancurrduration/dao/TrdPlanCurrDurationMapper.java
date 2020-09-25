/*
 * 文件名：TrdFundDurationMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2018年11月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.period.trdplancurrduration.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.service.period.trdplancurrduration.dto.TrdPlanCurrDurationDto;
import com.joyintech.tams.invest.common.dto.common.FinAssetIntInfo;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author Administrator
 * @version 1.0 2018年11月19日
 * @see TrdPlanCurrDurationMapper
 * @since 1.0
 */
@Repository
public interface TrdPlanCurrDurationMapper {
	/**
	 *
	 * 获取代办数据
	 * <功能详细描述>
	 * @param param 系统参数
	 * @return 返回结果
	 * @see
	 * @since 1.0
	 */
	TrdPlanCurrDurationDto findEffectiveData(Map<String, Object> param);
	/**
	 *
	 * 现金分红流程中
	 * <功能详细描述>
	 * @param param 系统参数
	 * @return 返回结果
	 * @see
	 * @since 1.0
	 */
	TrdPlanCurrDurationDto findCashDividend(Map<String, Object> param);
	/**
	 * 查询画面
	 * <功能详细描述>
	 * @param param
	 * @return
	 * @see
	 * @since 1.0
	 */
	List<TrdPlanCurrDurationDto> findPage(Map<String, Object> param);
    /**
     * 
     * 市场代码联动
     * <功能详细描述>
     * @param map 参数
     * @return 联动的详情数据
     * @see
     * @since 1.0
     */
	Map<String, Object> finprodMarketIdLink(Map<String, Object> map);
	/**
     * 
     * 组合代码联动
     * <功能详细描述>
     * @param map 参数
     * @return 联动的详情数据
     * @see
     * @since 1.0
     */
	Map<String, Object> portfolioIdLink(Map<String, Object> map);
	/**
	 *
	 * 计算登记份额
	 * <功能详细描述>
	 * @param map 参数
	 * @return 联动的详情数据
	 * @see
	 * @since 1.0
	 */
	Map<String, Object> calRegistShare(Map<String, Object> map);
	/**
	 * 统计是否存在后续交易
	 *
	 * @return 查询结果集
	 * @throws Exception 异常
	 */
	int countFollowDeal(Map<String, Object> map);
    /**
     * 查询利息明细信息
     * params 查询参数
     * @return 查询结果集
     * @throws Exception 异常
     */
	List<FinAssetIntInfo> findAssetIntInfo(Map<String, Object> params);
}
