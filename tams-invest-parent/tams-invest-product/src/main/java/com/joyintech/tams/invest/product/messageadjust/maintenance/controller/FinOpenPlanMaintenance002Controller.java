/*
 * 文件名：FinOpenPlanMaintenance001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.messageadjust.maintenance.controller;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.product.messageadjust.maintenance.dto.FinOpenPlanMaintenance002Dto;
import com.joyintech.tams.invest.product.messageadjust.maintenance.dto.OpenPlanParamDto;
import com.joyintech.tams.invest.product.messageadjust.maintenance.service.FinOpenPlanMaintenance002Service;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 开放计划调整 〈功能详细描述〉
 * 
 * @author wangzehui
 * @version 1.0 2018年10月22日
 * @see FinOpenPlanMaintenance002Controller
 * @since 1.0
 */
@Controller
@PageId("FinOpenPlanMaintenance002")
@RequestMapping("/invest/product/messageadjust/maintenance/FinOpenPlanMaintenance002")
public class FinOpenPlanMaintenance002Controller extends BaseController {
	
	@Autowired
	private FinOpenPlanMaintenance002Service finOpenPlanMaintenance002Service;
	
	@Autowired
    private IPlatformDate iPlatformDate;

	public String init(Model model) {
		
		Date platDate = iPlatformDate.getSysDate(); // 平台日
		// 下拉框初始化
        this.setSelectOptionToModel(model, DictCode.处理模式,DictCode.净值日规则);
		model.addAttribute("PROFIT_TYPE_02", DictCodeList.收益类型_收益型.getValue());
		model.addAttribute("operationType", DictCodeList.开放类型_开放式.getValue());
		model.addAttribute("DISPOSE_PATTERN_01", DictCodeList.处理模式_普通.getValue());
		
		model.addAttribute("pageModel", StringUtil.defaultString(this.getParam("pageModel")));
		model.addAttribute("CALC_RULE_01", DictCodeList.推算方式_起息日向后.getValue());
		
		FinOpenPlanMaintenance002Dto entity = new FinOpenPlanMaintenance002Dto();
		entity.setNextOpenDay(DateUtil.addDay(platDate, 1));
		
		String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        // 工作流获取entity
        if (!WorkFlowUtil.isNotFromToDoList() && StringUtils.isNotEmpty(workflowId)) {
            // 根据工作流与留痕处理提供的共通方法获取业务实体
        	entity = finOpenPlanMaintenance002Service.getBussinessEntityByWorkflowId(workflowId);
        	
        	OpenPlanParamDto oppd = finOpenPlanMaintenance002Service.selectOpenPlanParamDto(entity.getFinprodId());
        	entity.setDealMode(oppd.getDealMode());
        	entity.setTermType(oppd.getTermType());
        	entity.setInvestmentCycle(oppd.getInvestmentCycle());
        	
            // 设置到model中
            model.addAttribute("entity", entity);
            model.addAttribute("workflowId", workflowId);
        } else {
        	model.addAttribute("entity", entity);
        }
                
        return super.init(model);
	}
	
    /**
     * 画面check
     * 
     * @param dto 验证对象
     * @param br 验证信息
     * @return 验证结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) FinOpenPlanMaintenance002Dto dto, BindingResult result) {
    	if (result != null && result.hasErrors()) {
			return new AjaxResult(result);
		}
		return this.commonCheck(result, dto.getOperateType());
    }
	
    /**
     * 保存
     * 
     * @param dto 保存对象
     * @param br 验证信息
     * @return 操作结果
     * @throws Exception 异常信息
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({ValidateGroupEdit.class}) FinOpenPlanMaintenance002Dto dto, BindingResult br) throws Exception{
        if(br.hasErrors()){
            return new AjaxResult(br);
        }
        ProcessParameter<FinOpenPlanMaintenance002Dto> processParameter = new ProcessParameter<FinOpenPlanMaintenance002Dto>();
        WorkFlowParameter<FinOpenPlanMaintenance002Dto> params = new WorkFlowParameter<FinOpenPlanMaintenance002Dto>();
        params.setPageId(this.getPageId());
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getFinprodId());
        params.setBusinessType(DictCodeList.共通业务类型_开放计划调整.getValue());
        params.setComment(entity -> this.getMessage("FinOpenPlanMaintenance001.finprodId") + "("
            + entity.getFinprodId() +")" + this.getMessage("FinOpenPlanMaintenance001.finprodAbbr") + "("
            + entity.getFinprodAbbr() + ")" + this.getMessage("FinOpenPlanMaintenance002.nextOpenDay") + "("
            + DateUtil.format(entity.getNextOpenDay()) + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = finOpenPlanMaintenance002Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }
    
    /**
     * 开放计划信息参数查询
     */
    @ResponseBody
    @RequestMapping("selectOpenPlanParamDto")
    public OpenPlanParamDto selectOpenPlanParamDto(String finprodId) throws Exception{
    	return finOpenPlanMaintenance002Service.selectOpenPlanParamDto(finprodId);
    }
    
    /**
     * 产品代码联想框
     */
    @ResponseBody
    @RequestMapping("productCodeList")
    public List<SelectOptionDto> productCodeList(){
    	return finOpenPlanMaintenance002Service.productCodeList(RequestUtil.getPageParam());
    }
}
