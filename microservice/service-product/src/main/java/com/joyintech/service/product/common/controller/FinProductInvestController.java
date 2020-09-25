/*
 * 文件名：FinProductGoalInfoController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年10月8日
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

import com.joyintech.service.product.common.dto.BussFinProductInvestInfoDto;
import com.joyintech.service.product.common.service.FinProductInvestService;

/**
 * 产品投向业务组件
 * 
 * @author yanya
 * @version 1.0 2019年8月23日
 * @see FinProductInvestController
 * @since 1.0
 */
@RestController("backBussFinProductInvestCtl_dtx")
@RequestMapping("/BussFinProductInvest")
public class FinProductInvestController {
	
	/**
	 * {@linkplain}
	 */
	@Autowired
	private FinProductInvestService finProductInvestService;
	

	/**
	 * 根据金融产品代码查询产品投向信息
	 * @param finprodId 金融产品代码
	 * @return DB变更行数
	 */
	@GetMapping("select")
	public BussFinProductInvestInfoDto select (String finprodId) {
		return finProductInvestService.select(finprodId);
	}
	
	/**
	 * 根据金融产品代码删除产品投向信息
	 * @param finprodId 金融产品代码
	 * @return DB变更行数
	 */
	@Transactional
	@DTXTransaction
	@GetMapping("delete")
	public Integer delete (String finprodId) {
		return finProductInvestService.delete(finprodId);
	}
	
	/**
	 * 更新产品估值信息
	 * @param prdValParam 产品投向信息
	 * @return DB变更行数
	 */
	@Transactional
	@DTXTransaction
	@PostMapping("update")
	public Integer update (BussFinProductInvestInfoDto bussFinProductInvestInfoDto){
		return finProductInvestService.update(bussFinProductInvestInfoDto);
	}
	
	/**
	 * 新增产品投向信息
	 * @param prdValParam 产品投向信息
	 * @return DB变更行数
	 */
	@Transactional
	@DTXTransaction
	@PostMapping("insert")
	public Integer insert (BussFinProductInvestInfoDto bussFinProductInvestInfoDto){
		return finProductInvestService.insert(bussFinProductInvestInfoDto);
	}
}
