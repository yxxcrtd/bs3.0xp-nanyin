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

import java.util.ArrayList;
import java.util.List;

import com.joyintech.framework.annotation.DTXTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.tams.common.base.entity.PtlGoal;
import com.joyintech.service.product.common.service.FinProductGoalInfoService;

/**
 * 产品投向业务组件
 * 
 * @author zhangliang
 * @version 1.0 2018年10月8日
 * @see FinProductGoalInfoController
 * @since 1.0
 */
@RestController("backFinProductGoalInfoController_dtx")
@RequestMapping("/FinProductGoalInfo")
public class FinProductGoalInfoController {
	
	/**
	 * {@linkplain}
	 */
	@Autowired(required=false)
	private FinProductGoalInfoService finProductGoalInfoService;
	
//	/**
//	 * 插入产品投向信息
//	 * @param finProductGoalInfoDto 产品投向信息
//	 */
//	@PostMapping("insertFinProdGoalInfo")
//	public void insertFinProdGoalInfo(@RequestBody FinProductGoalInfoDto finProductGoalInfoDto){
//		finProductGoalInfoService.insertFinProdGoalInfo(finProductGoalInfoDto);
//	}

	/**
	 * 根据金融产品代码删除投向信息
	 * @param finprodId 金融产品代码
	 */
	@Transactional
	@DTXTransaction
	@GetMapping("deleteFinProdGoalInfo")
	public void deleteFinProdGoalInfo(String finprodId){
		finProductGoalInfoService.deleteFinProdGoalInfo(finprodId);
	}
	
	/**
	 * 根据金融产品代码查询投向信息
	 * @param finprodId 金融产品代码
	 */
	@GetMapping("queryFinProdGoalInfo")
	public List<PtlGoal> queryFinProdGoalInfo(String finprodId){
		if (finProductGoalInfoService != null){
			return finProductGoalInfoService.queryFinProdGoalInfo(finprodId);
		}
		return new ArrayList<PtlGoal>();
	}
	
//	/**
//	 * 更新产品投向信息
//	 * @param finProductGoalInfoDto 产品投向信息
//	 */
//	@PostMapping("updateFinProdGoalInfo")
//	public void updateFinProdGoalInfo(@RequestBody FinProductGoalInfoDto finProductGoalInfoDto){
//		if (finProductGoalInfoService != null){
//			finProductGoalInfoService.updateFinProdGoalInfo(finProductGoalInfoDto);
//		}
//	}
}
