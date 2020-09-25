/*
 * 文件名：FinProductValuationController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年10月08日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.product.common.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.tams.common.base.entity.PrdValParam;
import com.joyintech.service.product.common.service.FinProductValuationService;

/**
 * 产品估值业务组件
 * 
 * @author zhangliang
 * @version 1.0 2018年10月08日
 * @see FinProductValuationController
 * @since 1.0
 */
@RestController("backFinProductValuationController_dtx")
@RequestMapping("/FinProductValuation")
public class FinProductValuationController {
	
	/**
	 * {@linkplain}
	 */
	@Autowired
	private FinProductValuationService finProductValuationService;

	/**
	 * 根据金融产品代码查询产品估值信息
	 * @param finprodId 金融产品代码
	 * @return DB变更行数
	 */
	@GetMapping("select")
	public PrdValParam select (String finprodId) {
		return finProductValuationService.select(finprodId);
	}
	
	/**
	 * 根据金融产品代码删除产品估值信息
	 * @param finprodId 金融产品代码
	 * @return DB变更行数
	 */
	@Transactional
	@DTXTransaction
	@GetMapping("delete")
	public Integer delete (String finprodId) {
		return finProductValuationService.delete(finprodId);
	}
	
	/**
	 * 更新产品估值信息
	 * @param prdValParam 产品估值信息
	 * @return DB变更行数
	 */
	@Transactional
	@DTXTransaction
	@PostMapping("update")
	public Integer update (PrdValParam prdValParam){
		return finProductValuationService.update(prdValParam);
	}
	
	/**
	 * 新增产品估值信息
	 * @param prdValParam 产品估值信息
	 * @return DB变更行数
	 */
	@Transactional
	@DTXTransaction
	@PostMapping("insert")
	public Integer insert (PrdValParam prdValParam){
		return finProductValuationService.insert(prdValParam);
	}
	
}
