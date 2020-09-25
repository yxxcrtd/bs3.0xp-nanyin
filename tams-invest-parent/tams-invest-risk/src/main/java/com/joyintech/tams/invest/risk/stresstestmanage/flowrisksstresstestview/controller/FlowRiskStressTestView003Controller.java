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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.invest.risk.stresstestmanage.flowrisksstresstestview.dto.FlowRiskStressTestView003Dto;
import com.joyintech.tams.invest.risk.stresstestmanage.flowrisksstresstestview.service.FlowRiskStressTestView003Service;

/**
 * 〈现金流详细〉
 * 〈功能详细描述〉
 * @author wangkang
 * @version 1.0 2019年8月13日
 * @see FlowRiskStressTest001Controller
 * @since 1.0
 */
@Controller
@PageId("FlowRiskStressTestView003")
@RequestMapping("/invest/risk/stresstestmanage/flowriskstresstestview/FlowRiskStressTestView003")
public class FlowRiskStressTestView003Controller extends BaseController{
    
    @Autowired(required = false)
    private FlowRiskStressTestView003Service service;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        // TODO Auto-generated method stub
        model.addAttribute("cashFlowDetail", service.getCashFlowDetail(RequestUtil.getPageParam()));
        model.addAttribute("entity1", RequestUtil.getPageParam());
        model.addAttribute("finprodId",(RequestUtil.getPageParam()).get("finprodId"));//产品编号
        model.addAttribute("flowTestId",(RequestUtil.getPageParam()).get("flowTestId"));//测试代码
        model.addAttribute("testName",(RequestUtil.getPageParam()).get("testName"));//测试名称
        Object finprodName=(RequestUtil.getPageParam()).get("finprodName");
        if(finprodName==null || "undefined".equals((finprodName.toString()))){
        }else{
        	model.addAttribute("finprodName",(RequestUtil.getPageParam()).get("finprodName"));//产品名称
        }
        model.addAttribute("baseDate",(RequestUtil.getPageParam()).get("baseDate"));//基准时间
        model.addAttribute("startTime",(RequestUtil.getPageParam()).get("startTime"));//压测执行时间
        model.addAttribute("testStartDate",(RequestUtil.getPageParam()).get("testStartDate"));//分析结束日
        model.addAttribute("testEndDate",(RequestUtil.getPageParam()).get("testEndDate"));//分析结束日
        return super.init(model);
    }
    
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
            throws Exception {
        return service.export(this.getPageId(),
                RequestUtil.getParameters(getRequest()));

    }

}
