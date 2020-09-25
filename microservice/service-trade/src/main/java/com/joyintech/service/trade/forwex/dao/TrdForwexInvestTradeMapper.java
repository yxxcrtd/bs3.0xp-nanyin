/*
 * 文件名：TrdForwexInvestTradeDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiaominghui
 * 修改时间：2019年7月4日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.trade.forwex.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.service.trade.forwex.dto.TrdForwexInvestTradeDto;

/**
 * 外汇远期投资交易mapper
 * 〈功能详细描述〉
 * @author qiaominghui
 * @version 1.0 2019年7月4日
 * @see TrdForwexInvestTradeMapper
 * @since 1.0
 */
@Repository
public interface TrdForwexInvestTradeMapper{

	/**
	 * 查询
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param pageParam
	 * @return
	 * @see
	 * @since 1.0
	 */
	public List<TrdForwexInvestTradeDto> selectDeal(Map<String, Object> pageParam);

	/**
	 * 
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param params
	 * @return
	 * @see
	 * @since 1.0
	 */
	public TrdForwexInvestTradeDto getTrdForwexInvestTradeDto(Map<String, Object> params);

	 /**
     * 委托代码带出交易信息
     * 
     * @param map 委托代码
     * @return 交易信息
     * @see
     * @since 1.0
     */
	public TrdForwexInvestTradeDto getDealInfoByEntrustId(@Param("entrustId") String entrustId);
    
    
}
