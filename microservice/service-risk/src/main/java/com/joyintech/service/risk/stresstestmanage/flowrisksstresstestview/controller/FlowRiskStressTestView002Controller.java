/*
 * 文件名：FlowRiskStressTest001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangkang
 * 修改时间：2019年8月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.stresstestmanage.flowrisksstresstestview.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.risk.stresstestmanage.flowrisksstresstestview.service.FlowRiskStressTestView002Service;
import com.joyintech.service.risk.stresstestmanage.flowrisksstresstestview.dto.FlowRiskStressTestView002Dto;

/**
 * 流动性-压力测试概览
 * 〈功能详细描述〉
 * @author wangkang
 * @version 1.0 2019年8月13日
 * @see FlowRiskStressTest001Controller
 * @since 1.0
 */
@RestController("backFlowRiskStressTestView002Controller")
@RequestMapping("/FlowRiskStressTestView002")
public class FlowRiskStressTestView002Controller{
	
	@Autowired
    private FlowRiskStressTestView002Service FlowRiskStressTestView002Service;
	
	 @PostMapping("findPage/{page}/{length}")
	    public Page<FlowRiskStressTestView002Dto> selectAll(@RequestBody Map<String, Object> param,
	            @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
	
	     return FlowRiskStressTestView002Service.selectForPage(page, length, param);
	     
	    }
	 
	 /**
	  * 根据测试代码得到测试明细信息
	  * @param param
	  * @return
	  * @throws Exception
	  */
	 @PostMapping("queryDetByTestId")
	 public FlowRiskStressTestView002Dto selectAll(@RequestBody Map<String, Object> param) throws Exception {

		 return FlowRiskStressTestView002Service.queryDetByTestId(param);
     
	 }

}

