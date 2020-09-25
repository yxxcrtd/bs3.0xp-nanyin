/*
 * 文件名：PtlInvPortfolioStrategyMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：niuqianyu
 * 修改时间：2019年7月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.portfolio.ptlinveststrategypool.dao;



import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.portfolio.ptlinveststrategypool.dto.PtlInvStrategyDetailDto;
import com.joyintech.service.portfolio.ptlinveststrategypool.dto.PtlInvestStrategyPoolDto;



@Repository
public interface PtlInvestStrategyPoolMapper {
	/**
	 * 画面查询方法
	 * 
	 * @param page
	 * @param length
	 * @param pageParam 
	 * @return
	 */
	List<PtlInvestStrategyPoolDto> selectForPage(Map<String, Object> pageParam);
	/**
	 * 画面查询方法
	 * 
	 * @param page
	 * @param length
	 * @param pageParam
	 * @return
	 */
	Integer selectTreeChildCount(Map<String, Object> pageParam);
	/**
	 * 查询组合策略中是否有数据
	 * @param pageParam
	 * @return
	 */
	Integer selectIsPtlPortfolioCount(Map<String, Object> pageParam);
	/**
	 * 通过策略代码删除策略详情表信息
	 * @param stargegyId 投资策略代码
	 * @return
	 */
	Integer deleteStrategyDetailByStrategyId(@Param("strategyId") String strategyId);
	/**
	 * 通过策略代码删除策略详情表信息
	 * @param stargegyId 投资策略代码
	 * @return
	 */
	Integer deleteStrategyEveIndexByStrategyId(@Param("strategyId") String strategyId);
	/**
	 * 查询策略详情信息
	 * @param strategyId 策略代码
	 * @return
	 */
	List<PtlInvestStrategyPoolDto> getPtlInvestStrategyPool(@Param("strategyId") String strategyId);
	/**
	 * 查询策略详情信息列表
	 * @param strategyId 策略代码
	 * @return
	 */
	List<PtlInvStrategyDetailDto> getPtlInvestStrategyPoolDetail(@Param("strategyId") String strategyId);
	/**
	 * 导出Excel方法
	 * 
	 * @param page
	 * @param length
	 * @param pageParam
	 * @return
	 */
	List<PtlInvestStrategyPoolDto> selectForExport(Map<String, Object> pageParam);
	/**
	 * 更新策略指标表
	 * @param pageParam 条件
	 * @return
	 */
	Integer updateStrategyEveIndexByStrategyId(Map<String, Object> pageParam);
	/**
     * 获取自增序列值
     * @param sequenceName 序列名
     * @return
     */
    String getNextId(@Param("sequenceName")String sequenceName);
    /**
     * 根据batchID删除
     * @param batchID 
     * @return
     */
    Integer delsteStrategyDetailClassByBatchId(@Param("batchId")String batchId);
}
