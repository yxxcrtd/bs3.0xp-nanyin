/*
 * 文件名：PayTheVatQuery002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangqidong
 * 修改时间：2019年2月25日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.trademanage.paythevatquery.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.valuation.trademanage.paythevatquery.dto.PayTheVatQuery002Dto;
import com.joyintech.tams.valuation.trademanage.paythevatquery.dto.PayTheVatQuery002TableDto;
import com.joyintech.tams.valuation.trademanage.paythevatquery.service.PayTheVatQuery002Service;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 纳税增值税新建
 * @author wangqidong
 * @version 1.0 2019年2月25日
 * @see PayTheVatQuery002Controller
 * @since 1.0
 */
@Controller
@PageId("PayTheVatQuery002")
@RequestMapping("/valuation/trademanage/paythevatquery/PayTheVatQuery002")
public class PayTheVatQuery002Controller extends BaseController{
    
    @Autowired
    private PayTheVatQuery002Service payTheVatQuery002Service;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.交易方向);
        // 工作流ID
        String workflowId = this.getParam(GlobalConstant.WORKFLOW_ID);
        PayTheVatQuery002Dto dto = new PayTheVatQuery002Dto();
        if (StringUtil.isNotEmpty(workflowId)) {
            dto = payTheVatQuery002Service.getBussinessEntityByWorkflowId(
                workflowId);
        }
        else{
            dto.setTaxidenType(DictCodeList.交易方向_税费缴税.getValue());
        }
        model.addAttribute("workflowId", workflowId);
        model.addAttribute("entity", dto);
        return super.init(model);
    }
    /**
     * 查询
     * @param params
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("search")
    @ResponseBody
    public List<PayTheVatQuery002TableDto> search(Map<String, Object> params) throws Exception {
        // 工作流ID
        String workflowId = this.getParam(GlobalConstant.WORKFLOW_ID);
        if (StringUtil.isNotEmpty(workflowId)) {
            PayTheVatQuery002Dto dto = new PayTheVatQuery002Dto();
            dto = payTheVatQuery002Service.getBussinessEntityByWorkflowId(
                workflowId);
            return dto.getListpayTheVatQuery002TableDto();
        }
        return payTheVatQuery002Service.search(RequestUtil.getPageParam());
    }
    
    
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Valid PayTheVatQuery002Dto dto, BindingResult result, String operateType) throws Exception {
        return commonCheck(result, operateType);
    }
    /**
     * 提交
     * @param dto
     * @param result
     * @return
     * @throws Exception 
     * @see
     * @since 1.0
     */
    @RequestMapping(value = "docommit", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult docommit(@RequestBody PayTheVatQuery002Dto dto,
                               BindingResult result) throws Exception {
        
     // 启动流程
        ProcessParameter<PayTheVatQuery002Dto> proParam = new ProcessParameter<>();
        WorkFlowParameter<PayTheVatQuery002Dto> params = new WorkFlowParameter<>();

        // 业务代码（交易编号
        params.setBusinessCode(dto.getBooksetId());
        // 业务类型
        params.setBusinessType(DictCodeList.共通业务类型_缴纳增值税.getValue());
        // 页面id
        params.setPageId(this.getPageId());
        // 元素id
        params.setElementId(this.getParam(GlobalConstant.ELEMENT_ID));

        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> WorkFlowUtil.concatComment("缴纳增值税", entity.getPayment2(),
            SysDataUtil.getNameFromDataDict(DictCode.交易方向, entity.getTaxidenType())));

        // 塞入流程参数
        proParam.setWorkFlowParameter(params);

        String msg = getMessage("COMMON_I_0001");
        
//        PayTheVatQuery002Dto dto1 = new PayTheVatQuery002Dto();
//        dto1.setBooksetId(dto.getBooksetId());
//        dto1.setPayment1(dto.getPayment1());
//        dto1.setTaxidenType(dto.getTaxidenType());
//        dto1.setPayment2(dto.getPayment2());
//        Map<String, Object> map = new HashMap<>();
//        map.put("booksetId", dto.getBooksetId());
//        map.put("taxidenType", dto.getTaxidenType());
//        map.put("payment1", dto.getPayment1());
//      //产品多选
//        if (StringUtil.isNotEmpty(map.get("booksetId"))) {
//            String booksetId = (String) map.get("booksetId");
//            String[] booksetIdArr = null;
//            booksetIdArr = booksetId.split(",");
//            map.put("booksetIdArr", booksetIdArr);
//        }
//        dto1.setListpayTheVatQuery002TableDto(payTheVatQuery002Mapper.search(map));
        try {
            msg = payTheVatQuery002Service.start(proParam, dto);
        }
        catch (Exception e) {
            getLogger().error("启动流程失败：", e);
            return new AjaxResult(e.getMessage());
        }

        return new AjaxResult(msg);
    }
}












