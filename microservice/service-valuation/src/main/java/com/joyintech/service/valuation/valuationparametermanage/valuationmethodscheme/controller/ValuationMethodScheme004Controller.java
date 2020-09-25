/*
 * 文件名：ValuationMethodScheme004Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年8月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.valuationparametermanage.valuationmethodscheme.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.valuationparametermanage.valuationmethodscheme.dto.ValuationMethodScheme004Dto;
import com.joyintech.service.valuation.valuationparametermanage.valuationmethodscheme.service.ValuationMethodScheme004Service;
import com.joyintech.workflow.engine.constant.OperationType;

/**
 * 估值方案管理-左侧
 * 
 * @author zhangwei
 * @version 1.0 2018年8月28日
 * @see ValuationMethodScheme004Controller
 * @since 1.0
 */
@RestController("valuationMethodScheme004Ctl_dtx")
@RequestMapping("/ValuationMethodScheme004")
public class ValuationMethodScheme004Controller extends BaseController {

	@Autowired
	private ValuationMethodScheme004Service valuationMethodScheme004Service;

	/**
	 * 删除一般资产 并启动流程
	 * 
	 * @param dto
	 *            业务实体
	 * @return 画面地址
	 * @throws Exception
	 * @see
	 * @since 1.0
	 */
	@PostMapping("findBySchemeDetailId")
	public ValuationMethodScheme004Dto findBySchemeDetailId(@RequestBody String schemeDetailId) {
		return valuationMethodScheme004Service.findBySchemeDetailId(schemeDetailId);
	}

	/**
	 * 下拉框 ： 产品代码
	 * 
	 * @return
	 * @throws Exception
	 */
	@PostMapping("findFinprod")
	public List<SelectOptionDto> findFinprod(@RequestBody Map<String, Object> parameters) throws Exception {
		return valuationMethodScheme004Service.findFinprod(parameters);
	}

	/**
	 * 通过产品代码，获取产品信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@PostMapping("getFinprodInfo")
	public ValuationMethodScheme004Dto getFinprodInfo(@RequestBody Map<String, Object> pageParam) {
		return valuationMethodScheme004Service.getFinprodInfo(pageParam);
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
	public void insert(@RequestBody ValuationMethodScheme004Dto dto) throws Exception {
		valuationMethodScheme004Service.doComplete(dto, OperationType.NEW);
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
	public void update(@RequestBody ValuationMethodScheme004Dto dto) throws Exception {
		valuationMethodScheme004Service.doComplete(dto, OperationType.MODIFY);
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
	public void delete(@RequestBody ValuationMethodScheme004Dto dto) throws Exception {
		valuationMethodScheme004Service.doComplete(dto, OperationType.DELETE);
	}

}