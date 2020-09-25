/*
 * 文件名：FlowRiskStressTest001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangkang
 * 修改时间：2019年8月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.risk.stresstestmanage.flowrisksstresstestview.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.risk.stresstestmanage.flowrisksstresstestview.service.FlowRiskStressTestView001Service;
import com.joyintech.tams.invest.risk.stresstestmanage.flowrisksstresstestview.dto.FlowRiskStressTestView001Dto;

/**
 * 流动性-压力测试概览
 * 〈功能详细描述〉
 * @author wangkang
 * @version 1.0 2019年8月13日
 * @see FlowRiskStressTest001Controller
 * @since 1.0
 */
@Controller
@PageId("FlowRiskStressTestView001")
@RequestMapping("/invest/risk/stresstestmanage/flowriskstresstestview/FlowRiskStressTestView001")
public class FlowRiskStressTestView001Controller extends BaseController{
	
	@Autowired(required = false)
    private FlowRiskStressTestView001Service service;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        // TODO Auto-generated method stub
    	this.setSelectOptionToModel(model, DictCode.开放模式); // 适用产品
    	this.setSelectOptionToModel(model, DictCode.压力测试_执行状态);
    	model.addAttribute("entity01", service.initPage(RequestUtil.getParameters(getRequest())));
        return super.init(model);
    }
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
            throws Exception {
        return service.export(this.getPageId(),
                RequestUtil.getParameters(getRequest()));

    }
    /*
     * 查询方法
     * */
    @ResponseBody
    @RequestMapping("findOnePage")
    public Page<FlowRiskStressTestView001Dto> selectForPage()
        throws Exception {
        Map<String, Object> map = RequestUtil.getParameters(getRequest());
        return service.selectAll(map);
    }
    
    /*
     * 查询压测下拉框
     * */
    @ResponseBody
    @RequestMapping("selectTestListMap")
    public List<SelectOptionDto> selectRiskFactorMap(@RequestParam Map<String, Object> param)
        throws Exception {

        return service.selectTestListMap(param);
    }
    /** 
     * 情景自动匹配完成-每次都是从oracle中查询
     */
    @ResponseBody
    @GetMapping("fuzzySelectScene")
    public List<SelectOptionDto> fuzzySelectScene() throws Exception {
        //Map<String, Object> map = RequestUtil.getParameters(getRequest());
        Map<String, Object> paramMap = new HashMap<>();
        Map<String, Object> pageMap = RequestUtil.getPageParam();
        Object search = pageMap.get("search");
        if (search != null) {
            paramMap.put("sceneStr", StringUtils.trim(search.toString()));
        }

        return service.fuzzySelectScene(paramMap);
    }
}
