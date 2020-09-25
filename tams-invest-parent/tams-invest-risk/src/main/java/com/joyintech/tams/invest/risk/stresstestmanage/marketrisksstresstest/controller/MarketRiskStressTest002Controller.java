/*
 * 文件名：FlowRiskStressTest001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangkang
 * 修改时间：2019年8月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.stresstestmanage.marketrisksstresstest.controller;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.ImmutableMap;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.invest.risk.stresstestmanage.marketrisksstresstest.dto.MarketRiskStressTest001Dto;
import com.joyintech.tams.invest.risk.stresstestmanage.marketrisksstresstest.dto.MarketRiskStressTest002Dto;
import com.joyintech.tams.invest.risk.stresstestmanage.marketrisksstresstest.service.MarketRiskStressTest002Service;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangkang
 * @version 1.0 2019年8月13日
 * @see MarketRiskStressTest002Controller
 * @since 1.0
 */
@Controller
@PageId("MarketRiskStressTest002")
@RequestMapping("/invest/risk/stresstestmanage/marketrisksstresstest/MarketRiskStressTest002")
public class MarketRiskStressTest002Controller extends BaseController{
    /**
     * 因子状态查看service
     */
	@Autowired(required = false)
    private MarketRiskStressTest002Service marketRiskStressTest002Service;
    

    @Override
    public String init(Model model) {
    	//产品代码
    	model.addAttribute("prodIds",getParam("portfolioSingleName"));
    	model.addAttribute("portfolioSingleName",getParam("portfolioSingleName"));
    	//情景代码
    	model.addAttribute("sceneId",getParam("sceneId"));
    	//基准日期
    	model.addAttribute("baseDate",getParam("analysisDate"));
    	model.addAttribute("analysisDate",getParam("analysisDate"));
    	//结束日期
    	model.addAttribute("analysisMdate",getParam("analysisMdate"));
    	//测试名称
    	model.addAttribute("testName",getParam("testName"));
    	//备注
    	model.addAttribute("remark",getParam("remark"));
    	//风险因子的异常处理
    	model.addAttribute("riskFacHandling",getParam("riskFacHandling"));
    	
        return super.init(model);
    }
    
    
    /** 产品持仓分页查询 */
    @ResponseBody
    @RequestMapping("/selectProdForPage")
    public Page<MarketRiskStressTest002Dto> selectProdForPage() throws Exception {
        Map<String, Object> map = RequestUtil.getParameters(getRequest());
        String prodIds = getParam("prodIds");
        return marketRiskStressTest002Service.selectProdForPage(ImmutableMap.of("prodIds",map.get("prodIds").toString(),"sceneId",map.get("sceneId").toString(),"baseDate",map.get("baseDate").toString()));
    } 
    
    /** 判断有无未准备因子 */
    @ResponseBody
    @RequestMapping("/judgeFacStatus")
    public Page<MarketRiskStressTest002Dto> judgeFacStatus(@RequestBody MarketRiskStressTest002Dto dto) throws Exception {
        return marketRiskStressTest002Service.selectFacForPage(ImmutableMap.of("prodIds",dto.getProdIds(),"sceneId",dto.getSceneId(),"baseDate",dto.getBaseDate(),"inIsOneRow",dto.getFtrValueStruct()));
    } 
    
    /** 因为未准备分页查询 */
    @ResponseBody
    @RequestMapping("/selectFacForPage")
    public Page<MarketRiskStressTest002Dto> selectFacForPage() throws Exception {
        Map<String, Object> map = RequestUtil.getParameters(getRequest());
        String prodIds = getParam("object");
        return marketRiskStressTest002Service.selectFacForPage(ImmutableMap.of("prodIds",map.get("prodIds").toString(),"sceneId",map.get("sceneId").toString(),"baseDate",map.get("baseDate").toString()));
    } 
    
    /**
     * 日期字符串转为日期格式
     * @param dateStr
     * @return
     */
    public Date StringToDate(String dateStr){
    	Date date = null;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
			date = format.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return date;
    }
    
    
    
}
