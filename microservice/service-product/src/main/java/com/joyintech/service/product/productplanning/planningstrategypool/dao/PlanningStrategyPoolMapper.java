/*
 * 文件名：PlanningStrategyPoolMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：niuqianyu
 * 修改时间：2019年12月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.product.productplanning.planningstrategypool.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.productplanning.planningstrategypool.dto.PlanningStrategyPoolDetailDto;
import com.joyintech.service.product.productplanning.planningstrategypool.dto.PlanningStrategyPoolDto;

/**
 * 规划策略池维护
 * 
 * @author niuqianyu
 * @version 1.0 2019年12月16日
 * @see PlanningStrategyPoolMapper
 * @since 1.0
 */
@Repository
public interface PlanningStrategyPoolMapper {

	/**
	 * 策略代码下拉框
	 * 
	 * @param pageParam
	 * @return
	 */
	List<SelectOptionDto> getStrategyId(Map<String, Object> pageParam);

	/**
	 * 画面查询方法
	 * 
	 * @param page
	 * @param length
	 * @param pageParam
	 * @return
	 */
	List<PlanningStrategyPoolDto> selectForPage(Map<String, Object> pageParam);

	/**
	 * 导出Excel方法
	 * 
	 * @param pageParam
	 * @return
	 * @see
	 * @since 1.0
	 */
	List<PlanningStrategyPoolDto> selectForExport(Map<String, Object> pageParam);

	/**
	 * 删除投资策略明细
	 * 
	 * @param strategyId
	 * @see
	 * @since 1.0
	 */
	int deletePtlInvStrategyDetail(String strategyId);

	/**
	 * 根据策略代码查询批次号
	 * 
	 * @param strategyId
	 * @see
	 * @since 1.0
	 */
	List<String> selectBatchIdList(@Param("strategyId") String strategyId);

	/**
	 * 删除投资策略明细分类
	 * 
	 * @param list
	 * @return
	 * @see
	 * @since 1.0
	 */
	int deletePtlInvStrategyDetailClass(List<String> list);

	/**
	 * 根据策略代码查询策略明细
	 * 
	 * @param strategyId
	 * @return
	 */
	List<PlanningStrategyPoolDetailDto> getPlanningStrategyPoolDetail(String strategyId);

}
