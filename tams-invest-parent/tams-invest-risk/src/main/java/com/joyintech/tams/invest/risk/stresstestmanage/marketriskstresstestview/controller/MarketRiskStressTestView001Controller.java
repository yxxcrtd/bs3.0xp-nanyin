/*
 * 文件名：FlowRiskStressTest001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangkang
 * 修改时间：2019年8月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.stresstestmanage.marketriskstresstestview.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.risk.stresstestmanage.marketriskstresstestview.dto.MarketRiskStressTestView001Dto;
import com.joyintech.tams.invest.risk.stresstestmanage.marketriskstresstestview.service.MarketRiskStressTestView001Service;

/**
 * 流动性-压力测试概览
 * 〈功能详细描述〉
 * @author lifangchen
 * @version 1.0 2019年8月13日
 * @see MarketRiskStressTest001Controller
 * @since 1.0
 */
@Controller
@PageId("MarketRiskStressTestView001")
@RequestMapping("/invest/risk/stresstestmanage/marketriskstresstestview/MarketRiskStressTestView001")
public class MarketRiskStressTestView001Controller extends BaseController{
	
	@Autowired(required = false)
    private MarketRiskStressTestView001Service marketRiskStressTestView001Service;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        // TODO Auto-generated method stub
    	this.setSelectOptionToModel(model, DictCode.开放模式); // 适用产品
    	this.setSelectOptionToModel(model, DictCode.压力测试_执行状态);
    	model.addAttribute("entity01", marketRiskStressTestView001Service.initPage(RequestUtil.getParameters(getRequest())));
        return super.init(model);
    }
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
            throws Exception {
        return marketRiskStressTestView001Service.export(this.getPageId(),
                RequestUtil.getParameters(getRequest()));

    }
    /*
     * 查询方法
     * */
    @ResponseBody
    @RequestMapping("findOnePage")
    public Page<MarketRiskStressTestView001Dto> selectForPage()
        throws Exception {
        Map<String, Object> map = RequestUtil.getParameters(getRequest());
        return marketRiskStressTestView001Service.selectAll(map);
    }
    
    /*
     * 查询压测下拉框
     * */
    @ResponseBody
    @RequestMapping("selectTestListMap")
    public List<SelectOptionDto> selectRiskFactorMap(@RequestParam Map<String, Object> param)
        throws Exception {

        return marketRiskStressTestView001Service.selectTestListMap(param);
    }
    
    @ResponseBody
    @RequestMapping("restart")
    public AjaxResult restart(@RequestBody Map<String,Object> map) {
    	String testId = (String)map.get("testId");
    	if(StringUtil.isNotEmpty(testId)) {
    		marketRiskStressTestView001Service.restart(testId);
    	}
    	return new AjaxResult();
    }
}
