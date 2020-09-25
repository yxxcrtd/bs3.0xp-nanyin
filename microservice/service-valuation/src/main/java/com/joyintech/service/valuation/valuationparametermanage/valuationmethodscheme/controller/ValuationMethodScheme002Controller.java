/*
 * 文件名：ValuationMethodScheme002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年8月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.valuationparametermanage.valuationmethodscheme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.base.BaseController;
import com.joyintech.service.valuation.valuationparametermanage.valuationmethodscheme.dto.ValuationMethodScheme002Dto;
import com.joyintech.service.valuation.valuationparametermanage.valuationmethodscheme.service.ValuationMethodScheme002Service;
import com.joyintech.workflow.engine.constant.OperationType;

/**
 * 估值方案管理-左侧
 * 
 * @author zhangwei
 * @version 1.0 2018年8月28日
 * @see ValuationMethodScheme002Controller
 * @since 1.0
 */
@RestController("valuationMethodScheme002Ctl_dtx")
@RequestMapping("/ValuationMethodScheme002")
public class ValuationMethodScheme002Controller extends BaseController {

	@Autowired
	private ValuationMethodScheme002Service valuationMethodScheme002Service;

	@GetMapping("/countBokBookSetPlan")
	public int countBokBookSetPlan(String schId) {
		return valuationMethodScheme002Service.countBokBookSetPlan(schId);
	}

	/**
	 * 删除方案 并启动流程
	 * 
	 * @param dto
	 *            业务实体
	 * @return 画面地址
	 * @throws Exception
	 * @see
	 * @since 1.0
	 */
	@PostMapping("findBySchemeId")
	public ValuationMethodScheme002Dto findBySchemeId(@RequestBody String schemeId) {
		return valuationMethodScheme002Service.findBySchemeId(schemeId);
	}

	/**
	 * 新建
	 * 
	 * @param dto
	 * @throws Exception
	 */
	@Transactional
    @DTXTransaction
	@PostMapping("insert")
	public void insert(@RequestBody ValuationMethodScheme002Dto dto) throws Exception {
		valuationMethodScheme002Service.doComplete(dto, OperationType.NEW);
	}

	/**
	 * 修改
	 * 
	 * @param dto
	 * @throws Exception
	 * @see
	 * @since 1.0
	 */
	@Transactional
    @DTXTransaction
	@PostMapping("update")
	public void update(@RequestBody ValuationMethodScheme002Dto dto) throws Exception {
		valuationMethodScheme002Service.doComplete(dto, OperationType.MODIFY);
	}

	/**
	 * 删除
	 * 
	 * @param dto
	 * @throws Exception
	 * @see
	 * @since 1.0
	 */
	@Transactional
    @DTXTransaction
	@PostMapping("delete")
	public void delete(@RequestBody ValuationMethodScheme002Dto dto) throws Exception {
		valuationMethodScheme002Service.doComplete(dto, OperationType.DELETE);
	}

}
