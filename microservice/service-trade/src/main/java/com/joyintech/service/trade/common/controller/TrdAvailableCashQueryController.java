/*
 * 文件名：TrdAvailableCashQueryController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨智
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.trade.common.controller;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.service.trade.common.service.TrdAvailableCashQueryService;

@RestController
@RequestMapping("/TrdAvailableCashQuery")
public class TrdAvailableCashQueryController {
	/**
	 * trdAvailableCashQueryService
	 */
	@Autowired
	private TrdAvailableCashQueryService trdAvailableCashQueryService;

	@RequestMapping("getAvailableCash")
	public Map<String, BigDecimal> getAvailableCash(@RequestBody Map<String, Object> param) {
		return trdAvailableCashQueryService.getAvailableCash(param);
	}

	@RequestMapping("getSecManageAvailableCash")
	public BigDecimal getSecManageAvailableCash(@RequestBody Map<String, Object> param) {
		return trdAvailableCashQueryService.getSecManageAvailableCash(param);
	}

	@RequestMapping("getShowHandNum")
	public Map<String, Object> getShowHandNum(@RequestBody Map<String, Object> param) {
		return trdAvailableCashQueryService.getShowHandNum(param);
	}

	/**
	 * 获得资产维度的持仓
	 */
	@RequestMapping("/getFinAvaPosition")
	public Map<String, Object> getFinAvaPosition(@RequestBody Map<String, Object> map) {
		return trdAvailableCashQueryService.getFinAvaPosition(map);
	}

	@RequestMapping("/getSecPosition")
	public BigDecimal getSecPosition(@RequestBody Map<String, Object> param) {
		return trdAvailableCashQueryService.getSecPosition(param);
	}
}