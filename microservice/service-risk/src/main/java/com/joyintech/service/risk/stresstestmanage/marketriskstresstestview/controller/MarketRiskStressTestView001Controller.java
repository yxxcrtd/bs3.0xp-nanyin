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
import com.joyintech.service.risk.stresstestmanage.marketriskstresstestview.dto.MarketRiskStressTestView001Dto;
import com.joyintech.service.risk.stresstestmanage.marketriskstresstestview.service.MarketRiskStressTestView001Service;

/**
 * 市场-压力测试概览
 * 〈功能详细描述〉
 * @author lifangchen
 * @version 1.0 2019年8月13日
 * @see FlowRiskStressTest001Controller
 * @since 1.0
 */
@RestController("backMarketRiskStressTestView001Controller")
@RequestMapping("/MarketRiskStressTestView001")
public class MarketRiskStressTestView001Controller{
	
	@Autowired
    private MarketRiskStressTestView001Service marketRiskStressTestView001Service;
	
	 @PostMapping("findPage/{page}/{length}")
	    public Page<MarketRiskStressTestView001Dto> selectAll(@RequestBody Map<String, Object> param,
	            @PathVariable("page") int page, @PathVariable("length") int length) throws Exception {
			 Map<String, Object> param1=param;
			 Object st=param1.get("testId");
			 if(st!=""&&st!=null){
				 String [] str=((String) st).split(",");
				 List<String> list=Arrays.asList(str);
				 param1.replace("testId", list);
		     }else{
			     param1.remove("testId"); 
		     }
			 Object sc=param1.get("sceneIds");
			 if(sc!=""&&sc!=null){
				 String [] str=((String) sc).split(",");
				 List<String> list=Arrays.asList(str);
				 param1.replace("sceneIds", list);
			 }else{
			     param1.remove("sceneIds"); 
		     }
			 return marketRiskStressTestView001Service.selectForPage(page, length, param1);
	    }
	 @PostMapping("selectTestListMap")
	    public List<SelectOptionDto> selectTestListMap(@RequestBody Map<String, Object> param) throws Exception {
	       
	        return marketRiskStressTestView001Service.selectTestListMap(param);
	    }
	 @PostMapping("initPage")
	    public List<MarketRiskStressTestView001Dto> initPage(@RequestBody Map<String, Object> param) throws Exception {
	       
	        return marketRiskStressTestView001Service.initPage(param);
	    }
}

