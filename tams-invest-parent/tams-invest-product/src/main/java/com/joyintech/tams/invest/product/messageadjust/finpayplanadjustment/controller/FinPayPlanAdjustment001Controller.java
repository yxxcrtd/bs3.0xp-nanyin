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

import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.product.messageadjust.finpayplanadjustment.dto.FinPayPlanAdjustmentDto;
import com.joyintech.tams.invest.product.messageadjust.finpayplanadjustment.service.FinPayPlanAdjustmentService;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

/**
 * 信息调整管理_付费计划调整                                                        
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年10月18日
 * @see FinPayPlanAdjustment001Controller
 * @since 1.0
 */
@Controller
@PageId("FinPayPlanAdjustment001")
@RequestMapping("/invest/product/messageadjust/finpayplanadjustment/FinPayPlanAdjustment001")
public class FinPayPlanAdjustment001Controller extends BaseController{
    
    /**
     * 付费计划调整service
     */
    @Autowired
    private FinPayPlanAdjustmentService finPayPlanAdjustmentService;
    
    /**
     * 业务编号组件
     */
    /*@Autowired(required=false)
    private BaseGetBusinessId bussGetBussinessIdService;*/
    
    @Override
    public String init(Model model) {
        
        String workflowId = getParam("workflowId");
        if(StringUtil.isNotEmpty(workflowId)){
            FinPayPlanAdjustmentDto finPayPlanAdjustmentDto = finPayPlanAdjustmentService.getBussinessEntityByWorkflowId(workflowId);
            model.addAttribute("entity", finPayPlanAdjustmentDto);
            model.addAttribute("workflowId", workflowId);
        }
        return super.init(model);
    }
    
    /**
     * 产品代码联想下拉框
     * @return List<SelectOptionDto>
     */
    @ResponseBody
    @RequestMapping("getFinprodId")
    public List<SelectOptionDto> getProdId() {
        return finPayPlanAdjustmentService.getFinprodIds(RequestUtil.getPageParam());
    }
    
    /**
     * 产品代码联想下拉框
     * @return List<SelectOptionDto>
     */
    @ResponseBody
    @RequestMapping("getProdType")
    public List<SelectOptionDto> getProdType() {
        return finPayPlanAdjustmentService.getProdType(RequestUtil.getPageParam());
    }
    /**
     * 
     * 根据产品代码获取费用名称
     * <功能详细描述>
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getFeeName")
    public List<SelectOptionDto> getFeeName(){
        Map<String, Object> param = RequestUtil.getPageParam();
        //param.put("finprodId", param.get("finprodId"));
        return finPayPlanAdjustmentService.getFeeName(param);
    }
    
    /**
     * 
     * 根据产品代码和费用名称，联动带出产品对应费用的付费计划
     * <功能详细描述>
     * @return Page<FinPayPlanAdjustmentDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public List<FinPayPlanAdjustmentDto> selectForPage() throws Exception{
        Map<String,Object> param = RequestUtil.getParameters(getRequest());
        return finPayPlanAdjustmentService.selectForPage(param);
    }
    
    /**
     * 文件导出 <功能详细描述>
     * 
     * @return 导出返回参数
     * @throws Exception 抛出异常
     * @see
     * @since 1.0
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception {
        return finPayPlanAdjustmentService.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));
    }
    
    /**
     * 保存按钮点击时的check
     *
     * @param dto 业务实体
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) FinPayPlanAdjustmentDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    
    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @param dto 业务实体
     * @param br AjaxResult
     * @return AjaxResul
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({ValidateGroupEdit.class}) FinPayPlanAdjustmentDto dto, BindingResult br) throws Exception{
        if(br.hasErrors()){
            return new AjaxResult(br);
        }
        ProcessParameter<FinPayPlanAdjustmentDto> processParameter = new ProcessParameter<FinPayPlanAdjustmentDto>();
        WorkFlowParameter<FinPayPlanAdjustmentDto> params = new WorkFlowParameter<FinPayPlanAdjustmentDto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        //业务编号
        params.setBusinessCode(dto.getCashId());
        params.setBusinessType(DictCodeList.共通业务类型_付费计划调整.getValue());
        //业务说明："产品代码" + "(" + 画面.产品代码 + ")" + "-" + "费用名称" + "(" + 画面.费用名称 + ")"
        params.setComment(entity -> this.getMessage("FinPayPlanAdjustment001.finprodId") + "("
            + entity.getFinprodMarketId() +")" +this.getMessage("FinProductRateQuotation002.feeName") + "("
            + entity.getFeeName() +")");
        processParameter.setWorkFlowParameter(params);
        String msg = finPayPlanAdjustmentService.start(processParameter, dto);
        return new AjaxResult(msg);
    }
    
    /**
     * 
     * 获取未支付最小计提开始日
     * <功能详细描述>
     * @return AjaxResult
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getVDate")
    public String getVDate(){
        Map<String,Object> param = RequestUtil.getParameters(getRequest());
        Date vdate = finPayPlanAdjustmentService.selectForList(param);
        return DateUtil.format(vdate);
    }
}
