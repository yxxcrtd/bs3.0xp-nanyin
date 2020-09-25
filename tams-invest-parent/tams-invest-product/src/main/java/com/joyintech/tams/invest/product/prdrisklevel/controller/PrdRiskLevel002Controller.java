/*
 * 文件名：FinProductPayInt001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.prdrisklevel.controller;

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
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.product.prdrisklevel.dto.Page2Dto;
import com.joyintech.tams.invest.product.prdrisklevel.dto.PrdRiskLevel002Dto;
import com.joyintech.tams.invest.product.prdrisklevel.service.PrdRiskLevel002Service;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 2019-10-28 yangxiaofeng 新发产品风险等级评定查询
 */
@Controller
@PageId("PrdRiskLevel002")
@RequestMapping("/invest/product/rddesign/prdrisklevel/PrdRiskLevel002")
public class PrdRiskLevel002Controller extends BaseController {

	@Autowired
	private PrdRiskLevel002Service prdRiskLevel002Service;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String init(Model model) {
		
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        String pk = getParam("pk");
        
        // 工作流获取entity
        if (!WorkFlowUtil.isNotFromToDoList() && StringUtils.isNotEmpty(workflowId)) {
            // 根据工作流与留痕处理提供的共通方法获取业务实体
        	PrdRiskLevel002Dto entity = prdRiskLevel002Service.getBussinessEntityByWorkflowId(
                workflowId);
            // 设置到model中
            model.addAttribute("entity", entity);
            model.addAttribute("workflowId", workflowId);
        }
        // 主键获取entity
        if (WorkFlowUtil.isNotFromToDoList() && StringUtils.isNotEmpty(pk)) {
        	PrdRiskLevel002Dto entity = prdRiskLevel002Service.selectByPk(pk);
            // 设置到model中
            model.addAttribute("entity", entity);
        }
        
        this.setSelectOptionToModel(model, DictCode.资产风险等级);
        
        return super.init(model);
	}

	/**
	 * check验证
	 */
	@ResponseBody
	@RequestMapping("check")
	public AjaxResult check(@RequestBody @Validated PrdRiskLevel002Dto dto, BindingResult br) {
		if (br != null && br.hasErrors()) {
			return new AjaxResult(br);
		}
		return this.commonCheck(br, dto.getOperateType());
	}
	
	/**
	 * 删除的check
	 */
	@ResponseBody
	@RequestMapping("checkDelete")
	public AjaxResult checkDelete(@RequestBody @Validated PrdRiskLevel002Dto dto, BindingResult br) {
		if (br != null && br.hasErrors()) {
			return new AjaxResult(br);
		}
		return this.commonCheck(br, "delete");
	}

	/**
	 * 新建一条记录
	 */
	@ResponseBody
	@RequestMapping("insert")
	public AjaxResult insert(@RequestBody PrdRiskLevel002Dto dto) throws Exception {
		
        ProcessParameter<PrdRiskLevel002Dto> processParameter = new ProcessParameter<PrdRiskLevel002Dto>();
        WorkFlowParameter<PrdRiskLevel002Dto> params = new WorkFlowParameter<PrdRiskLevel002Dto>();
        
        dto.setGenerateTime(null); // 平台日
        
        params.setPageId(this.getPageId()); // 画面ID
        params.setElementId(dto.getElementId()); // 要素ID
        params.setBusinessCode(dto.getFinProductCode()); // 业务编码（主键）
        params.setBusinessType(DictCodeListExt2.共通业务类型_新发产品风险等级评定.getValue()); // 业务类型
        params.setComment(entity -> "金融产品代码"+"("+entity.getFinProductCode()+")" // 工作流备注
                +"风险等级"+"("+entity.getRiskLevel()+")"+"调整后风险等级"+"("+entity.getAjustRiskLevel()+")");
        processParameter.setWorkFlowParameter(params);

        String msg = prdRiskLevel002Service.start(processParameter, dto);
        return new AjaxResult(msg);
		
	}
	
	// 验证流程，删除数据
    @ResponseBody
    @RequestMapping("delete")
    public AjaxResult delete(@RequestBody PrdRiskLevel002Dto dto,BindingResult br) throws Exception{

        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
    	
		ProcessParameter<PrdRiskLevel002Dto> processParameter = new ProcessParameter<PrdRiskLevel002Dto>();
		WorkFlowParameter<PrdRiskLevel002Dto> params = new WorkFlowParameter<PrdRiskLevel002Dto>();
		
		params.setPageId(this.getPageId()); // 画面ID
        params.setElementId(dto.getElementId()); // 要素ID
        params.setBusinessCode(dto.getFinProductCode()); // 业务编码（主键）
        params.setBusinessType(DictCodeListExt2.共通业务类型_新发产品风险等级评定.getValue()); // 业务类型
        
        params.setComment(entity -> "金融产品代码"+"("+entity.getFinProductCode()+")" // 工作流备注
                +"风险等级"+"("+entity.getRiskLevel()+")"+"调整后风险等级"+"("+entity.getAjustRiskLevel()+")");
        processParameter.setOperationType(OperationType.DELETE);
        processParameter.setWorkFlowParameter(params);
        
        String msg = prdRiskLevel002Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }
	
    // 新增页面填充
    @ResponseBody
    @RequestMapping("selectPageParam")
    public Page2Dto selectPageParam(String finProductCode) throws Exception{
    	
    	Page2Dto p2 = prdRiskLevel002Service.selectPageParam(finProductCode);
    	String level = prdRiskLevel002Service.calcRiskGrade(finProductCode);
    	
    	if(null!=p2) {
    		p2.setRiskLevel(level);
        	p2.setAjustRiskLevel(level);
        	p2.setGenerateTime(null);
    	}
    	return p2;
    }

}
