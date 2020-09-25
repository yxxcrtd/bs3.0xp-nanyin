/*
 * 文件名：chenyunlong.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：审批页面原型展示
 * 修改人：chenyunlong
 * 修改时间：2019年8月24日
 * 修改内容：新建
 * 系统名称：AMAS
 */

package com.joyintech.tams.invest.risk.stresstestmanage.riskfactor.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.tams.invest.risk.stresstestmanage.riskfactor.dto.RiskFactor001Dto;
import com.joyintech.tams.invest.risk.stresstestmanage.riskfactor.service.RiskFactor002Service;



/**
 * @author chenyunlong
 * @version 1.0 2019年8月24日
 * @see RiskFactor002Controller
 * @since 1.0
 * 审批页面原型展示
 */
@Controller
@PageId("RiskFactor002")
@RequestMapping("/invest/risk/stresstestmanage/riskfactor/RiskFactor002")
public class RiskFactor002Controller extends BaseController {
    @Autowired(required = false)
    private RiskFactor002Service RiskFactor002Service;
	@Override
	public String init(Model model) {
	    RiskFactor001Dto RiskFactor001Dto=new RiskFactor001Dto();
	    String workflowId = getParam("workflowId");
        model.addAttribute("workflowId", workflowId);
        RiskFactor001Dto = RiskFactor002Service.getBussinessEntityByWorkflowId(workflowId);
        if(RiskFactor001Dto.getEffstatus1().equals("01")){
            RiskFactor001Dto.setBoost("停止"); 
        }else{
            RiskFactor001Dto.setBoost("启动"); 
        }
        model.addAttribute(GlobalConstant.ENTITY, RiskFactor001Dto);
       
        model.addAttribute("formTodoList", true);
	   
		return super.init(model);
	}
	
	
}
