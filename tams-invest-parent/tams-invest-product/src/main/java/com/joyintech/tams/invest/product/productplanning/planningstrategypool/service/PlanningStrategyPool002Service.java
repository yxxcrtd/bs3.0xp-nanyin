/*
 * 文件名：PlanningStrategyPool002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：niuqianyu
 * 修改时间：2019年12月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.productplanning.planningstrategypool.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.productplanning.planningstrategypool.dto.PlanningStrategyPool001Dto;
import com.joyintech.tams.invest.product.productplanning.planningstrategypool.dto.PlanningStrategyPoolDetailDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 规划策略池维护新建
 * 
 * @author niuqianyu
 * @version 1.0 2019年12月17日
 * @see PlanningStrategyPool002Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PlanningStrategyPool002Service extends CommonExportService<PlanningStrategyPool001Dto>
		implements BaseProcessService<PlanningStrategyPool001Dto> {

	/**
	 * 编辑查看
	 * 
	 * @param strategyId
	 * @return
	 * @see
	 * @since 1.0
	 */
	public PlanningStrategyPool001Dto getPlanningStrategyPool(String strategyId) {
		return restTemplate
				.getForObject(
						ServiceApplication.PRODUCT_SERVICE_NAME
								+ "/PlanningStrategyPool/getPlanningStrategyPool?strategyId=" + strategyId,
						PlanningStrategyPool001Dto.class);
	}

	/**
	 * 根据策略代码查询策略明细
	 * 
	 * @param param
	 * @return
	 * @see
	 * @since 1.0
	 */
	public List<PlanningStrategyPoolDetailDto> getPlanningStrategyPoolDetail(String strategyId) {
		return restTemplate.getForList(
				ServiceApplication.PRODUCT_SERVICE_NAME
						+ "/PlanningStrategyPool/getPlanningStrategyPoolDetail?strategyId=" + strategyId,
				PlanningStrategyPoolDetailDto.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void doComplete(PlanningStrategyPool001Dto entity, OperationType type) {
		// 删除
		if (OperationType.DELETE.equals(type)) {
			restTemplate.postForObject(ServiceApplication.PRODUCT_SERVICE_NAME + "/PlanningStrategyPool/delete", entity,
					Integer.class);
		}
		// 新建
		if (OperationType.NEW.equals(type)) {
			restTemplate.postForObject(ServiceApplication.PRODUCT_SERVICE_NAME + "/PlanningStrategyPool/insert", entity,
					Integer.class);
		}
		// 修改
		if (OperationType.MODIFY.equals(type)) {
			restTemplate.postForObject(ServiceApplication.PRODUCT_SERVICE_NAME + "/PlanningStrategyPool/modify", entity,
					Integer.class);
		}

	}

}
