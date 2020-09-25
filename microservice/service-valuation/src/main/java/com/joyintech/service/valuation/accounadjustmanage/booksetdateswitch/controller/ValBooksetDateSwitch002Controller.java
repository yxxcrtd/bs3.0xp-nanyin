/*
 * 文件名：CustomerReportConfig002Controller.java
 * 版权：Copyright 2017-2019 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：陈卓
 * 修改时间：2018年1月11日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.service.valuation.accounadjustmanage.booksetdateswitch.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.system.customerreport.config.controller.CustomerReportConfig002Controller;
import com.joyintech.service.valuation.accounadjustmanage.booksetdateswitch.dto.ValBooksetDateSwitch001Dto;
import com.joyintech.service.valuation.accounadjustmanage.booksetdateswitch.dto.ValBooksetDateSwitch002Dto;
import com.joyintech.service.valuation.accounadjustmanage.booksetdateswitch.service.ValBooksetDateSwitch002Service;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 报表规则定义新建画面
 * 
 * @author 陈卓
 * @version 1.0 2018年1月11日
 * @see CustomerReportConfig002Controller
 * @since 1.0
 */
//@RestController(value="serviceValBooksetDateSwitch002Controller")
@RestController("valBooksetDateSwitch002Ctl_dtx")
@RequestMapping("ValBooksetDateSwitch002")
public class ValBooksetDateSwitch002Controller extends BaseController {
    
    /**
     * 报表规则定义新建服务
     */
    @Autowired
    private ValBooksetDateSwitch002Service valBooksetDateSwitch002Service;
    
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public String init(Model model) {
//        String datas = this.getParam("datas");
//        if (datas != null) {
//            ValBooksetDateSwitch001Dto dto = JsonUtils.json2Object(datas, ValBooksetDateSwitch001Dto.class);
//            model.addAttribute("booksetId", dto.getBooksetId());
//            model.addAttribute("booksetName", dto.getBooksetName());
//            model.addAttribute("booksetDate", dto.getBooksetDate());
//        }
//        
//        
//        // 工作流ID
//        String workflowId = this.getParam(GlobalConstant.WORKFLOW_ID);
//        if (StringUtil.isNotEmpty(workflowId)) {
//            ValBooksetDateSwitch002Dto dto = valBooksetDateSwitch002Service.getBussinessEntityByWorkflowId(workflowId);
//            model.addAttribute("booksetId", dto.getBooksetId());
//            model.addAttribute("booksetName", dto.getBooksetName());
//            model.addAttribute("booksetDate", dto.getBooksetDate());
//        }
//        return super.init(model);
//    }
//    
//    /**
//     * 修改凭证摘要
//     * @param dto 参数
//     * @param result 校验结果
//     * @return AjaxResult
//     * @see
//     * @since 1.0
//     */
//    @ResponseBody
//    @RequestMapping(value = "doUpdate", method=RequestMethod.POST)
//    public AjaxResult doUpdate(ValBooksetDateSwitch002Dto dto) {
//        ProcessParameter<ValBooksetDateSwitch002Dto> proParam = new ProcessParameter<>();
//        WorkFlowParameter<ValBooksetDateSwitch002Dto> params = new WorkFlowParameter<>();
//        // 业务代码
//        params.setBusinessCode(dto.getBooksetId());
//        // 业务类型
//        params.setBusinessType(DictCodeList.共通业务类型_账套日期.getValue());
//        params.setPageId(this.getPageId());
//        params.setElementId(this.getParam(GlobalConstant.ELEMENT_ID));
//      //设置流程业务说明, entity即传入的dto
//        params.setComment(entity -> WorkFlowUtil.concatComment(entity.getBooksetId(),
//            entity.getBooksetDate()));
//        
//        proParam.setWorkFlowParameter(params);
//        
//        String msg = getMessage("COMMON_I_0001");
//        try {
//            msg = valBooksetDateSwitch002Service.start(proParam, dto);
//        }
//        catch (Exception e) {
//            getLogger().error("启动流程失败：", e);
//            return new AjaxResult(e.getMessage());
//        }
//        //valBooksetDateSwitch002Service.doUpdate(dto);
//        return new AjaxResult(msg);
//    }
//    
//    /**
//     * 保存按钮点击时的check
//     *
//     * @param dto dto
//     * @param result BindingResult
//     * @param operateType 操作类型
//     * @return check结果
//     * @see
//     * @since 1.0
//     */
//    @ResponseBody
//    @RequestMapping("check")
//    public AjaxResult check(@Valid ValBooksetDateSwitch002Dto dto, BindingResult result, String operateType) {
//        return commonCheck(result, operateType);
//    }
    @Transactional
    @DTXTransaction
    @PostMapping("/update")
    public int update(@RequestBody ValBooksetDateSwitch002Dto entity) {
        return valBooksetDateSwitch002Service.update(entity);
    }
}
