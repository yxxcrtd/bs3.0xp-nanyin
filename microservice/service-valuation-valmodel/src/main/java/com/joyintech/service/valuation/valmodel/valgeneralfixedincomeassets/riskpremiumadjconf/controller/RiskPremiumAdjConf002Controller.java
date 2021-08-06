/*
 * 文件名：RiskPremiumAdjConf002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xutong
 * 修改时间：2021-06-28
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.valmodel.valgeneralfixedincomeassets.riskpremiumadjconf.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.service.valuation.valmodel.valgeneralfixedincomeassets.riskpremiumadjconf.dto.RiskPremiumAdjConf002Dto;
import com.joyintech.service.valuation.valmodel.valgeneralfixedincomeassets.riskpremiumadjconf.service.RiskPremiumAdjConf002Service;
import com.joyintech.workflow.engine.constant.OperationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * 风险溢价调整配置
 * @author xutong
 * @createTime 2021-06-28
 * @see RiskPremiumAdjConf002Controller
 * @since 1.0
 */
@RestController(value="serviceRiskPremiumAdjConf002Controller")
@RequestMapping("RiskPremiumAdjConf002")
public class RiskPremiumAdjConf002Controller {

	@Autowired
	private RiskPremiumAdjConf002Service riskPremiumAdjConf002Service;



	/**
	 * 流程完成后的操作
	 *
	 * @param dto 业务实体
	 * @return 画面地址
	 * @throws Exception
	 * @see
	 * @since 1.0
	 */
	@Transactional
	@DTXTransaction
	@PostMapping(value = "doComplete/{action}")
	public void doComplete(@RequestBody RiskPremiumAdjConf002Dto dto, @PathVariable("action") String action) {
		riskPremiumAdjConf002Service.doComplete(dto, OperationType.valueOf(action));
	}


}
