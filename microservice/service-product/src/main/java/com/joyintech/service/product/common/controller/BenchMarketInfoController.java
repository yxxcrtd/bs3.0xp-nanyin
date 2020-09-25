/*
 * 文件名：BenchMarketInfoController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：陈卓
 * 修改时间：2018年9月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.product.common.controller;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.annotation.DTXTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.tams.common.base.entity.FinBaseRuleInfoKey;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.common.dto.BussBenchMarkInfoDto;
import com.joyintech.service.product.common.dto.BussBenchMarkLayeredInfoDto;
import com.joyintech.service.product.common.service.BenchMarketInfoService;

/**
 * 业绩基准业务组件
 * 
 * @author chenzhuo
 * @version 1.0 2018年9月29日
 * @see BenchMarketInfoController
 * @since 1.0
 */
@RestController("backBenchMarketInfoController_dtx")
@RequestMapping("/BenchMarketInfo")
public class BenchMarketInfoController {
	/**
	 * {@linkplain}
	 */
	@Autowired(required=false)
	private BenchMarketInfoService benchMarketInfoService;

	/**
	 * 基准信息取得
	 * @return 基准信息
	 */
	@GetMapping("/getFinBaseRuleInfo")
	public List<SelectOptionDto> getFinBaseRuleInfo() {
		return benchMarketInfoService.getFinBaseRuleInfo();
	}
	/**
     * 页面参数设置
     * @return 基准信息
     */
    @PostMapping("/getByFinprodIdAndEffDate")
    public BussBenchMarkInfoDto getByFinprodIdAndEffDate(@RequestBody Map<String,Object> param) {
        return benchMarketInfoService.getByFinprodIdAndEffDate(param);
    }
	/**
     * 页面参数设置
     * @return 基准信息
     */
    @PostMapping("/getBenchMarkLayeredInfoList")
    public List<BussBenchMarkLayeredInfoDto> getBenchMarkLayeredInfoList(@RequestBody List<BussBenchMarkLayeredInfoDto> list) {
        return benchMarketInfoService.getBenchMarkLayeredInfoList(list);
    }
    /**
     * 页面参数设置
     * @return 基准信息
     */
    @PostMapping("/getByFinprodIdAndEffDateList")
    public List<BussBenchMarkInfoDto> getByFinprodIdAndEffDateList(@RequestBody Map<String,Object> param) {
        return benchMarketInfoService.getByFinprodIdAndEffDateList(param);
    }
    /**
     * 根据主键删除
     * @return 基准信息
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/deleteByPrimaryKey")
    public int deleteByPrimaryKey(@RequestBody FinBaseRuleInfoKey key) {
        return benchMarketInfoService.deleteByPrimaryKey(key);
    }
    /**
     * 根据基准代码删除
     * @return 基准信息
     */
    @Transactional
    @DTXTransaction
    @PostMapping("/delete")
    public int delete(String baseRuleId) {
        return benchMarketInfoService.delete(baseRuleId);
    }
}
