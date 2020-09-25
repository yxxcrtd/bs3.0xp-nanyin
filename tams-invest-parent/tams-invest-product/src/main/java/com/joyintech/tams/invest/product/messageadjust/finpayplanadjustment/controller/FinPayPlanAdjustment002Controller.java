/*
 * 文件名：FinPayPlanAdjustment001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年10月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.product.messageadjust.finpayplanadjustment.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.component.business.prod.BussFinProductFeeInfoService;
import com.joyintech.tams.component.business.prod.dto.BussFinProductFeeInfoDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.product.messageadjust.finpayplanadjustment.dto.FinPayPlanAdjustment002Dto;
import com.joyintech.tams.invest.product.messageadjust.finpayplanadjustment.dto.FinPayPlanAdjustmentDto;
import com.joyintech.tams.invest.product.messageadjust.finpayplanadjustment.service.FinPayPlanAdjustment002Service;
import com.joyintech.tams.invest.product.messageadjust.finpayplanadjustment.service.FinPayPlanAdjustmentService;
import com.joyintech.tams.invest.product.messageadjust.finproductratequotation.dto.FinProductRateQuotationDto;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 信息调整管理_付费计划调整                                                        
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年10月18日
 * @see FinPayPlanAdjustment002Controller
 * @since 1.0
 */
@Controller
@PageId("FinPayPlanAdjustment002")
@RequestMapping("/invest/product/messageadjust/finpayplanadjustment/FinPayPlanAdjustment002")
public class FinPayPlanAdjustment002Controller extends BaseController{
    
    /**
     * 付费计划调整service
     */
    @Autowired
    private FinPayPlanAdjustment002Service finPayPlanAdjustment002Service;
    
    /**
     * 业务编号组件
     */
    /*@Autowired(required=false)
    private BaseGetBusinessId bussGetBussinessIdService;*/
    
    @Override
    public String init(Model model) {
        setSelectOptionToModel(model, DictCode.计费基数_费用品种,DictCode.计息基础,DictCode.基数日期类型
        		,DictCode.频率,DictCode.营业日准则);
        List<SelectOptionDto> listtype = new ArrayList<SelectOptionDto>();
        SelectOptionDto dto1 = new SelectOptionDto();
        dto1.setId(DictCodeList.计息基础_A_365.getValue());
        dto1.setText(DictCodeList.计息基础_A_365.getCodeName());
        SelectOptionDto dto2 = new SelectOptionDto();
        dto2.setId(DictCodeList.计息基础_A_360.getValue());
        dto2.setText(DictCodeList.计息基础_A_360.getCodeName());
        listtype.add(dto1);
        listtype.add(dto2);
        model.addAttribute("BASIS_SELECT", JsonUtils.objectArray2Json(listtype.toArray()));
        model.addAttribute("FEE_TYPE_05", DictCodeList.费用品种类型_浮动差额中收.getValue());
        model.addAttribute("BASIC_BILL_04", DictCodeList.计费基数_费用品种_存续本金.getValue());
        model.addAttribute("BASE_DATE_TYPE_01", DictCodeList.基数日期类型_当日.getValue());
        model.addAttribute("BASE_DATE_TYPE_02", DictCodeList.基数日期类型_前一自然日.getValue());
        model.addAttribute("CHARGE_TYPE_02", DictCodeList.收费类型_不规则费用.getValue());
        model.addAttribute("BILL_BASE_FEE_KIND_03", DictCodeList.计费基数_费用品种_资产规模.getValue());
        model.addAttribute("BASE_DATE_TYPE_03", DictCodeList.基数日期类型_前一工作日.getValue());
        
        model.addAttribute("PAY_RULE_03", DictCodeList.频率_月.getValue());
        model.addAttribute("PAY_RULE_04", DictCodeList.频率_季.getValue());
        model.addAttribute("PAY_RULE_05", DictCodeList.频率_年.getValue());
        model.addAttribute("PAY_RULE_06", DictCodeList.频率_半年.getValue());
        model.addAttribute("PAY_RULE_08", DictCodeList.频率_不规则.getValue());
        model.addAttribute("PAY_RULE_07", DictCodeList.频率_到期一次.getValue());
        model.addAttribute("FEE_TYPE_05", DictCodeList.费用品种类型_浮动差额中收.getValue());
        model.addAttribute("FEE_TYPE_05NAME", DictCodeList.费用品种类型_浮动差额中收.getCodeName());
        String workflowId = getParam("workflowId");
        if(!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)){
        	FinPayPlanAdjustment002Dto entity = finPayPlanAdjustment002Service
        			.getBussinessEntityByWorkflowId(workflowId);
        	model.addAttribute(GlobalConstant.ENTITY,entity);
        	model.addAttribute("workflowId",workflowId);
        }
        model.addAttribute("WORK_FLAG",WorkFlowUtil.isNotFromToDoList());
        return super.init(model);
    }
    
    /**
     * 
     * 新建页面产品代码下拉框
     * <功能详细描述>
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getSelectFinprodId")
    public Page<SelectOptionDto> getSelectFinprodId(){
        return finPayPlanAdjustment002Service.getSelectFinprodId(RequestUtil.getPageParam()); 
    }
    
    /**
     * 产品款型下拉框
     * @return
     */
    @ResponseBody
    @RequestMapping("getSelectProdStyle")
    public Page<SelectOptionDto> getSelectProdStyle(){
    	return finPayPlanAdjustment002Service.getSelectProdStyle(RequestUtil.getPageParam());
    }
    /**
     * 查询费用信息列表
     * 
     * @return 费用信息列表
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getFeeInfo")
    public List<FinPayPlanAdjustment002Dto> getFeeInfo() {
        String finprodId = this.getParam("finprodId");
        String workflowId = getParam("workflowId");
        if(StringUtil.isNotEmpty(workflowId)){
        	List<FinPayPlanAdjustment002Dto> entity = finPayPlanAdjustment002Service
        			.getBussinessEntityByWorkflowId(workflowId).getSelectedItem();
        	return entity;
        }else if(StringUtil.isNotEmpty(finprodId)) {
        	return finPayPlanAdjustment002Service.queryFeeInfoByFinprodId(RequestUtil.getPageParam());
		}
        return Collections.emptyList();
    }
    
    /**
     * save
     * @param dto
     * @param br
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody FinPayPlanAdjustment002Dto dto, BindingResult br) throws Exception{
        if(br.hasErrors()){
            return new AjaxResult(br);
        }
        ProcessParameter<FinPayPlanAdjustment002Dto> processParameter = new ProcessParameter<FinPayPlanAdjustment002Dto>();
        WorkFlowParameter<FinPayPlanAdjustment002Dto> params = new WorkFlowParameter<FinPayPlanAdjustment002Dto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getFinprodMarketId());
        params.setBusinessType(DictCodeList.共通业务类型_付费计划调整.getValue());
        params.setComment(entity -> this.getMessage("FinProductRateQuotation001.finprodId") + "("
            + entity.getFinprodMarketId() +")"+this.getMessage("FinPayPlanAdjustment002.updateTime") + "("
            + DateUtil.format(entity.getUpdateTime()) + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = finPayPlanAdjustment002Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }
    
    /**
     * 预览验证
     * @param dto
     * @param result
     * @return
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) FinPayPlanAdjustment002Dto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    
    /**
     * 根据产品Id查询产品
     * @param finprodId
     * @return
     */
    @ResponseBody
    @RequestMapping("selectFinProductByFinprodId")
    public FinProduct selectFinProductByFinprodId(String finprodId) {
    	 return finPayPlanAdjustment002Service.selectFinProductByFinprodId(finprodId);
    }
}
