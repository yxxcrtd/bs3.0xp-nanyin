/*
 * 文件名：TrdCommandDispatcher001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangkang
 * 修改时间：2018年9月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.period.trdqueryduration.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.period.trdqueryduration.dto.TrdQueryDurationDto;
import org.apache.ibatis.annotations.Param;

import com.joyintech.framework.select.SuggestDto;


public interface TrdQueryDuration001Mapper {
	/**
	 * 流程中的搜索
	 * @param map
	 * @return
	 */
	List<TrdQueryDurationDto> selectForPage(Map<String, Object> map);
	/**
	 * 有效时的搜索
	 * @param map
	 * @return
	 */
	List <TrdQueryDurationDto> validSelectForPage(Map<String, Object> map);
	/**
	 * 撤销时的搜索
	 * @param map
	 * @return
	 */
	List <TrdQueryDurationDto> cancelSelectForPage(Map<String, Object> map);
	/**
	 * 有效状态为空的查询
	 * @param map
	 * @return
	 */
	List <TrdQueryDurationDto>  allSelectForPage(Map<String, Object> map);

	/**
	 * 证券账户/合作资产联想框
	 * @param
	 * @return
	 */
	List<SelectOptionDto> getSecAcctIdAndChlIds(Map<String,Object> map);

	/**
	 * 资产代码联想框
	 * @param finprodMarketId
	 * @return
	 */
	List<SuggestDto> getFinprodMarketIds(@Param("finprodMarketId")String finprodMarketId);


	/**
	 * 组合代码联想框
	 * @param
	 * @return
	 */
	List<SelectOptionDto> getPortfolioIds(Map<String,Object> map);
}
