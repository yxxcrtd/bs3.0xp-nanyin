/*
 * 文件名：PtlInvPortfolioStrategyMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：niuqianyu
 * 修改时间：2019年7月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.portfolio.ptlinvportfoliostrategy.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.portfolio.ptlinvportfoliostrategy.dto.PtlIncomeIndexDto;
import com.joyintech.service.portfolio.ptlinvportfoliostrategy.dto.PtlInvPortfolioStrategyDto;
import com.joyintech.tams.common.base.entity.PtlIncomeIndexFloat;
import com.joyintech.tams.common.base.entity.PtlInvStrategy;
import com.joyintech.tams.common.base.entity.PtlPortfolio;
import com.joyintech.tams.common.base.entity.PtlPortfolioStgy;

@Repository
public interface PtlInvPortfolioStrategyMapper {

	/**
	 * 组合代码下拉列表
	 * 
	 * @param pageParam
	 * @return
	 */
	List<SelectOptionDto> getPortfolioId(Map<String, Object> pageParam);

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
	List<PtlInvPortfolioStrategyDto> selectForPage(Map<String, Object> pageParam);

	/**
	 * 通过组合代码，获取组合名称
	 * 
	 * @param pageParam
	 * @return
	 */
	PtlPortfolio getPortfolioName(Map<String, Object> pageParam);

	/**
	 * 新建-唯一性校验
	 * 
	 * @param param
	 * @return
	 */
	PtlPortfolioStgy newCheck(Map<String, Object> param);

	/**
	 * 双击查看
	 * 
	 * @param params
	 * @return
	 */
	List<PtlInvPortfolioStrategyDto> getPtlInvPortfolioStrategy(Map<String, Object> params);

	/**
	 * 删除浮动收益目标
	 * 
	 * @param params
	 * @return
	 */
	int deletePtlIncomeIndexFloat(Map<String, Object> params);

	/**
	 * 获取指标值
	 * 
	 * @param params
	 * @return
	 */
	BigDecimal getIntRate(Map<String, Object> params);

	/**
	 * 根据指标代码，获取浮动收益目标列表
	 * 
	 * @param params
	 * @return
	 */
	List<PtlIncomeIndexDto> getPtlIncomeIndexFloatList(Map<String, Object> params);

	/**
	 * 查询组合代码不是没有子组合的产品组合
	 */
	int getPtlPortfolio(Map<String, Object> params);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    PtlInvPortfolioStrategyDto getStrategy(Map<String, Object> pageParam);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param indexId
     * @return
     * @see
     * @since 1.0
     */
    List<PtlIncomeIndexFloat> selectFloatByIndexId(@Param("indexId")String indexId);

}
