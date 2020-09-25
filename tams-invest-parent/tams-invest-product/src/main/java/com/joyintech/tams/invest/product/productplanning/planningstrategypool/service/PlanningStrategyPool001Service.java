/*
 * 文件名：PlanningStrategyPool001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：niuqianyu
 * 修改时间：2019年12月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.productplanning.planningstrategypool.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.productplanning.planningstrategypool.dto.PlanningStrategyPool001Dto;

/**
 * 规划策略池维护
 * 
 * @author niuqianyu
 * @version 1.0 2019年12月13日
 * @see PlanningStrategyPool001Service
 * @since 1.0
 */
@Service
public class PlanningStrategyPool001Service extends CommonExportService<PlanningStrategyPool001Dto> {

	/**
	 * 策略代码下拉框
	 * 
	 * @param pageParam
	 * @return
	 */
	public Page<SelectOptionDto> getStrategyId(Map<String, Object> param) {
		return restTemplate.postForPage(ServiceApplication.PRODUCT_SERVICE_NAME + "/PlanningStrategyPool/getStrategyId",
				param, SelectOptionDto.class);
	}

	/**
	 * 分页查询
	 * 
	 * @param parameters
	 * @return
	 */
	public Page<PlanningStrategyPool001Dto> selectForPage(Map<String, Object> pageParam) {
		Page<PlanningStrategyPool001Dto> page = restTemplate.postForPage(
				ServiceApplication.PRODUCT_SERVICE_NAME + "/PlanningStrategyPool/selectForPage", pageParam,
				PlanningStrategyPool001Dto.class);
		return page;
	}

	/**
	 * 导出
	 * 
	 * @param paraMap
	 * @return
	 * @see
	 * @since 1.0
	 */
	public List<PlanningStrategyPool001Dto> selectForExport(Map<String, Object> paraMap) {
		return restTemplate.postForList(
				ServiceApplication.PRODUCT_SERVICE_NAME + "/PlanningStrategyPool/selectForExport", paraMap,
				PlanningStrategyPool001Dto.class);
	}

}
