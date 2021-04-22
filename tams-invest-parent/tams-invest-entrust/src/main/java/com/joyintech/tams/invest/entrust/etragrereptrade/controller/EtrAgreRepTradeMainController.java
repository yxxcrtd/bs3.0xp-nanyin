/*
 * 文件名：EtrTradePlace001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：chendijun
 * 修改时间：2020年7月1日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.entrust.etragrereptrade.controller;

import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.entrust.etrexchangetrade.dto.EtrExchangeTrade001Dto;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;

/**
 * 交易所指令交易（债券股票回购基金场内指令交易）
 * @author chendijun
 * @version 1.0 2020年7月1日
 * @see EtrAgreRepTradeMainController
 * @since 1.0
 */
@Controller
@PageId("EtrAgreRepTradeMain")
@RequestMapping("/invest/entrust/etragrereptrade/EtrAgreRepTradeMain")
public class EtrAgreRepTradeMainController extends BaseController{
    @Override
    public String init(Model model) {
        model.addAttribute("ENTRUST_STATUS_05", DictCodeList.委托状态_已退回.getValue());
        model.addAttribute("ENTRUST_STATUS_07", DictCodeList.委托状态_已撤回.getValue());
        
        //流程id
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        model.addAttribute("workflowId", workflowId);
        String entrustId = getParam("entrustId");
        
        if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
       	 //流程代办
           model.addAttribute("isFromToDoList", !WorkFlowUtil.isNotFromToDoList());
       }
        model.addAttribute("FINPROD_TYPE_TG_F01", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        model.addAttribute("FINPROD_TYPE_TG_F02", DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue());
        model.addAttribute("FINPROD_TYPE_TG_F03", DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue());
        model.addAttribute("FINPROD_TYPE_TG_F06", DictCodeListExt1.金融产品类型_投管用_基金.getValue());
        model.addAttribute("FINPROD_TYPE_TG_F10", DictCodeListExt1.金融产品类型_投管用_股票.getValue());
        model.addAttribute("FINPROD_TYPE_TG_ZYSHG", "ZYSHG");
        model.addAttribute("entrustId", entrustId);
        return super.init(model);
    }
}
