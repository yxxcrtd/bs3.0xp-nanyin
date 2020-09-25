/*
 * 文件名：TrdIRSTradeMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：admin
 * 修改时间：2019年7月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.trade.irs.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.base.entity.FinCashInfo;
import com.joyintech.tams.common.base.entity.FinCashPlanEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.service.trade.irs.dto.TrdIRSTradeDto;

/**
 *  利率互换交易
 * 〈功能详细描述〉
 * @author qiaominghui
 * @version 1.0 2019年7月16日
 * @see TrdIRSTradeMapper
 * @since 1.0
 */
@Repository
public interface TrdIRSTradeMapper {

	/**
	 * 查询
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param pageParam
	 * @return
	 * @see
	 * @since 1.0
	 */
	public List<TrdIRSTradeDto> selectDeal(Map<String, Object> pageParam);

	/**
	 * 
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param params
	 * @return
	 * @see
	 * @since 1.0
	 */
	public TrdIRSTradeDto getTrdIRSTradeDto(Map<String, Object> params);

	 /**
     * 委托代码带出交易信息
     * 
     * @param map 委托代码
     * @return 交易信息
     * @see
     * @since 1.0
     */
	public TrdIRSTradeDto getDealInfoByEntrustId(@Param("entrustId") String entrustId);

	/**
	 * 根据交易编号获取相应的现金流计划明细
	 *
	 * @param tradeId 交易编号
	 * @return 现金流计划明细list
	 * @throws Exception 异常
	 * @see
	 * @since 1.0
	 */
	public List<FinCashPlanEntity> getCashPlanList(@Param("tradeId") String tradeId);

	public List<FinCashInfo> getFinCashInfoList(@Param("tradeId") String tradeId);
}
