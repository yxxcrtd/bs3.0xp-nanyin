/*
 * 文件名：FlowRiskStressTest001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangkang
 * 修改时间：2019年8月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.stresstestmanage.marketriskstresstestview.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.service.risk.stresstestmanage.marketriskstresstestview.dto.MarketRiskStressTestView002Dto;
import com.joyintech.service.risk.stresstestmanage.marketriskstresstestview.service.MarketRiskStressTestView002Service;

/**
 * 市场-压力测试概览
 * 〈功能详细描述〉
 * @author lifangchen
 * @version 1.0 2019年8月13日
 * @see FlowRiskStressTest001Controller
 * @since 1.0
 */

@RestController
@RequestMapping("/MarketRiskStressTestView002")
public class MarketRiskStressTestView002Controller{

	@Autowired
    private MarketRiskStressTestView002Service marketRiskStressTestView002Service;
	
	/**
	 * 查询所有
	 * <功能详细描述>
	 * @param param
	 * @param page
	 * @param length
	 * @return
	 * @throws Exception
	 * @see
	 * @since 1.0
	 */
	@PostMapping("findPage/{page}/{length}")
    public Page<MarketRiskStressTestView002Dto> selectAll(@RequestBody Map<String, Object> param,
            @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {

	    return marketRiskStressTestView002Service.selectForPage(page, length, param);
    }
	
	/**
	 * 查询所有-导出
	 * <功能详细描述>
	 * @param param
	 * @param page
	 * @param length
	 * @return
	 * @throws Exception
	 * @see
	 * @since 1.0
	 */
	@PostMapping("selectForPageExport/{page}/{length}")
    public Page<MarketRiskStressTestView002Dto> selectForPageExport(@RequestBody Map<String, Object> param,
            @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
        return marketRiskStressTestView002Service.selectForPageExport(page, length, param);
    }
	

}

