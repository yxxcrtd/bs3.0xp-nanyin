/*
 * 文件名：ValuationMethodScheme003Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年8月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.valuation.valuationparametermanage.valuationmethodscheme.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.service.valuation.valuationparametermanage.valuationmethodscheme.dto.SelectPageOptionDto;
import com.joyintech.service.valuation.valuationparametermanage.valuationmethodscheme.dto.ValuationMethodScheme003Dto;
import com.joyintech.service.valuation.valuationparametermanage.valuationmethodscheme.service.ValuationMethodScheme00301Service;
import com.joyintech.service.valuation.valuationparametermanage.valuationmethodscheme.service.ValuationMethodScheme003Service;
import com.joyintech.workflow.engine.constant.OperationType;

/**
 * 估值方案管理-左侧
 * 
 * @author zhangwei
 * @version 1.0 2018年8月28日
 * @see ValuationMethodScheme003Controller
 * @since 1.0
 */
@RestController("valuationMethodScheme003Ctl_dtx")
@RequestMapping("/ValuationMethodScheme003")
public class ValuationMethodScheme003Controller extends BaseController {

	@Autowired
	private ValuationMethodScheme003Service valuationMethodScheme003Service;

	/** modify by dongjun on 20181218 start **/
	@Autowired
	private ValuationMethodScheme00301Service valuationMethodScheme00301Service;

	/** modify by dongjun on 20181218 end **/

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
	public ValuationMethodScheme003Dto findBySchemeDetailId(@RequestBody String schemeDetailId) {
		return valuationMethodScheme003Service.findBySchemeDetailId(schemeDetailId);
	}

	/**
	 * 联动：交易市场 - 证券明细分类 获得证券明细分类
	 * 
	 * @return
	 * @throws Exception
	 */
	@PostMapping("getLinkSelectOption/{page}/{length}")
	public Page<SelectPageOptionDto> getLinkSelectOption(@RequestBody Map<String, Object> param,
			@PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
		return valuationMethodScheme00301Service.getLinkSelectOption(page, length, param);
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
	public void insert(@RequestBody ValuationMethodScheme003Dto dto) throws Exception {
		valuationMethodScheme003Service.doComplete(dto, OperationType.NEW);
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
	public void update(@RequestBody ValuationMethodScheme003Dto dto) throws Exception {
		valuationMethodScheme003Service.doComplete(dto, OperationType.MODIFY);
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
	public void delete(@RequestBody ValuationMethodScheme003Dto dto) throws Exception {
		valuationMethodScheme003Service.doComplete(dto, OperationType.DELETE);
	}
	
//	/**
//	 * 复制
//	 * 
//	 * @param dto
//	 * @throws Exception
//	 */
//	@Transactional
//    @DTXTransaction
//	@PostMapping("copy")
//	public void copy(@RequestBody ValuationMethodScheme003Dto dto) throws Exception {
//		valuationMethodScheme003Service.copy(dto);
//	}

}
