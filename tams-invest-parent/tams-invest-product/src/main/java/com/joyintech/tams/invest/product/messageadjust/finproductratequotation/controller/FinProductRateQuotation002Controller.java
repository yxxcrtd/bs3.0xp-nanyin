/*
 * 文件名：FinProductRateQuotation002.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.product.messageadjust.finproductratequotation.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.product.messageadjust.finproductratequotation.dto.FinProductRateQuotationDto;
import com.joyintech.tams.invest.product.messageadjust.finproductratequotation.service.FinProductRateQuotationService;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 信息调整管理_产品费率报价管理Controller
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年10月11日
 * @see FinProductRateQuotation002Controller
 * @since 1.0
 */
@Controller
@PageId("FinProductRateQuotation002")
@RequestMapping("/invest/product/messageadjust/finproductratequotation/FinProductRateQuotation002")
public class FinProductRateQuotation002Controller extends BaseController{
    
    /**
     * 
     * 产品费率报价管理service
     */
    @Autowired
    private FinProductRateQuotationService finProductRateQuotationService;
    
    /**
     * 业务编号组件
     */
    @Autowired(required=false)
    private BaseGetBusinessId bussGetBussinessIdService;
    
    @Override
    public String init(Model model) {
        
        String cashId = getParam("cashId");
        String finprodId = getParam("finprodId"); 
        String workflowId = getParam("workflowId");
        String effDate = getParam("effDateStr");
        //工作流待办跳转
        if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
            FinProductRateQuotationDto dto = finProductRateQuotationService.getBussinessEntityByWorkflowId(workflowId);
            model.addAttribute("entity", dto);
            model.addAttribute("workflowId", workflowId);
            model.addAttribute("finprodId", dto.getFinprodId());
            if(StringUtil.isNotEmpty(dto.getEffDateOld())){
                model.addAttribute("effDateOld", dto.getEffDateOld());
            }
            else{
                model.addAttribute("effDateOld", dto.getEffDate());
            }
        }//查询画面双击查看或修改
        else if (WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(cashId)) {
            FinProductRateQuotationDto dto = finProductRateQuotationService.selectDetail(cashId, finprodId, effDate);
            model.addAttribute("entity", dto);
            if(StringUtil.isNotEmpty(dto.getEffDateOld())){
                model.addAttribute("effDateOld", dto.getEffDateOld());
            }
            else{
                model.addAttribute("effDateOld", dto.getEffDate());
            }
        }//新建
        else if (WorkFlowUtil.isNotFromToDoList() && !StringUtil.isNotEmpty(cashId)) {
            Date sysDate = DateUtil.platDateFromCache();
            FinProductRateQuotationDto finProductRateQuotationDto = new FinProductRateQuotationDto();
            finProductRateQuotationDto.setEffDate(sysDate);
            model.addAttribute("entity", finProductRateQuotationDto);
        }
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
    public List<SelectOptionDto> getSelectFinprodId(){
        return finProductRateQuotationService.getSelectFinprodId(RequestUtil.getPageParam()); 
    }
    
    /**
     * 
     * 新建页面产品代码联动适用款型下拉框
     * <功能详细描述>
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getSelectProdStyleByFinprodId")
    public List<SelectOptionDto> getSelectProdStyleByFinprodId(){
        return finProductRateQuotationService.getSelectProdStyleByFinprodId(RequestUtil.getPageParam()); 
    }
    
    /**
     * 新建页面画面联动
     * @return Page<FinProductRateQuotationDto>
     * @throws Exception Exception
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<FinProductRateQuotationDto> selectOther() throws Exception{
        Map<String,Object> param = RequestUtil.getParameters(getRequest());
        return finProductRateQuotationService.selectOther(param);
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
    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) FinProductRateQuotationDto dto, BindingResult result) {
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
    public AjaxResult save(@RequestBody @Validated({ValidateGroupEdit.class}) FinProductRateQuotationDto dto, BindingResult br) throws Exception{
        if(br.hasErrors()){
            return new AjaxResult(br);
        }
        ProcessParameter<FinProductRateQuotationDto> processParameter = new ProcessParameter<FinProductRateQuotationDto>();
        WorkFlowParameter<FinProductRateQuotationDto> params = new WorkFlowParameter<FinProductRateQuotationDto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(entity->{
            BussinessCodeParameter param = new BussinessCodeParameter();
            param.setCommonBusinessType(DictCodeList.共通业务类型_产品费率报价调整);
            String code = bussGetBussinessIdService !=null ? bussGetBussinessIdService.buildBussinessCode(param):IDGenerator.generate();
            return code;
        });
        params.setBusinessType(DictCodeList.共通业务类型_产品费率报价调整.getValue());
        //业务说明："产品代码" + "(" + 产品代码 + ")" + "-"  + "费用名称" + "(" + 画面.费用名称 + ")"
        //+ "-"  + "费用品种" + "(" + 画面.费用品种 + ")" + "-"  + "报价生效日期" + "(" + 画面.生效日 + ")"  
        params.setComment(entity -> this.getMessage("FinProductRateQuotation001.finprodId") + "("
            + entity.getFinprodMarketId() +")"+this.getMessage("FinProductRateQuotation001.effDate") + "("
            + DateUtil.format(entity.getEffDate()) + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = finProductRateQuotationService.start(processParameter, dto);
        /*String msg = finProductRateQuotationService.start(processParameter, 
            dto.getSelectedItem().toArray(new FinProductRateQuotationDto[dto.getSelectedItem().size()]));*/
        return new AjaxResult(msg);
    }
}
