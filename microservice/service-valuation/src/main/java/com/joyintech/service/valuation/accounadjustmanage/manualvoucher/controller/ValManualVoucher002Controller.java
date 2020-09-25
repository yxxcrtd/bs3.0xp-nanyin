/*
 * 文件名：ValManualVoucher002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.valuation.accounadjustmanage.manualvoucher.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.accounadjustmanage.manualvoucher.dto.ValManualVoucher001Dto;
import com.joyintech.service.valuation.accounadjustmanage.manualvoucher.dto.ValManualVoucher002Dto;
import com.joyintech.service.valuation.accounadjustmanage.manualvoucher.service.ValManualVoucher001Service;
import com.joyintech.service.valuation.accounadjustmanage.manualvoucher.service.ValManualVoucher002Service;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;

/**
 * 模板示例<br>
 * 手工凭证详情查看
 * 
 * @author weipeng
 * @version 2.0 2018年5月3日
 * @see ValManualVoucher002Controller
 * @since 2.0
 */
@RestController(value="serviceValManualVoucher002Controller")
@RequestMapping("ValManualVoucher002")
public class ValManualVoucher002Controller extends BaseController {

    /**
     * ValManualVoucher002Service
     */
    @Autowired
    private ValManualVoucher002Service valManualVoucher002Service;
    
    /**
     * ValManualVoucher001Service
     */
//    @Autowired
//    private ValManualVoucher001Service valManualVoucher001Service;

//    /**
//     * 重写init方法，加载页面 {@inheritDoc}
//     */
//    @Override
//    public String init(Model model) {
//        String tradeId = this.getParam("tradeId");
//        model.addAttribute("tradeId", tradeId);
//        
//        // 工作流ID
//        String workflowId = this.getParam(GlobalConstant.WORKFLOW_ID);
//        if(StringUtil.isNotEmpty(workflowId)){
//            ValManualVoucher001Dto entity= valManualVoucher001Service.getBussinessEntityByWorkflowId(workflowId);
//            model.addAttribute("tradeId", entity.getTradeId());
//        }
//        
//        
//        return super.init(model);
//    }

    /**
     * 根据手工凭证编号bookId获取对应相同编号的所有数据
     *
     * @return 记账手工凭证数据
     * @throws Exception
     *             异常
     * @see
     */
    @PostMapping("/searchList")
    public List<ValManualVoucher002Dto> searchList(@RequestBody String tradeId) {
        // 手工凭证编号
//        String tradeId = this.getParam("tradeId");
        return valManualVoucher002Service.searchList(tradeId);
    }
    
}
