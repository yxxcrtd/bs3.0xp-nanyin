/*
 * 文件名：FlowRiskStressTestView003Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：汪海
 * 修改时间：2019年8月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.stresstestmanage.flowrisksstresstestview.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.service.risk.stresstestmanage.flowriskscenemanage.dto.FlowRiskFactroRManageDto;
import com.joyintech.service.risk.stresstestmanage.flowrisksstresstestview.dto.FlowRiskStressTestView003Dto;
import com.joyintech.service.risk.stresstestmanage.flowrisksstresstestview.service.FlowRiskStressTestView003Service;

/**
 * 〈现金流详细〉
 * 〈功能详细描述〉
 * @author 汪海
 * @version 1.0 2019年8月30日
 * @see FlowRiskStressTestView003Controller
 * @since 1.0
 */
@RestController("backFlowRiskStressTestView003Controller")
@RequestMapping("/FlowRiskStressTestView003")
public class FlowRiskStressTestView003Controller {
    
    /**
     * FlowRiskSceneManage001Service
     */
    @Autowired
    private FlowRiskStressTestView003Service flowRiskStressTestView003Service;
    
    @PostMapping("getCashFlowDetail")
    public List<FlowRiskStressTestView003Dto> getCashFlowDetail(@RequestBody Map<String, Object> param) throws Exception {
        return flowRiskStressTestView003Service.getCashFlowDetail(param);
    }

}
