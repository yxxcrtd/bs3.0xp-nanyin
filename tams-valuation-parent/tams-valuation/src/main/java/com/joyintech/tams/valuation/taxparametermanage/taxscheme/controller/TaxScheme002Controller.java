/*
 * 文件名：TaxScheme002.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2018年10月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.valuation.taxparametermanage.taxscheme.controller;

import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.valuation.taxparametermanage.taxscheme.dto.TaxScheme002Dto;
import com.joyintech.tams.valuation.taxparametermanage.taxscheme.service.TaxScheme001Service;
import com.joyintech.tams.valuation.taxparametermanage.taxscheme.service.TaxScheme002Service;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 税务方案 
 * 
 * @author zhangwei
 * @version 1.0 2018年10月10日
 * @see TaxScheme002Controller
 * @since 1.0
 */
@Controller
@PageId("TaxScheme002")
@RequestMapping("/valuation/taxparametermanage/taxscheme/TaxScheme002")
public class TaxScheme002Controller extends BaseController {

	@Autowired
	private TaxScheme001Service taxScheme001Service;

	@Autowired
	private TaxScheme002Service taxScheme002Service;

	/**
	 * 税务方案 - 页面初始化 {@inheritDoc}
	 */
	@Override
	public String init(Model model) {
		String schId = getParam("schId");
		String operateType = getParam("operateType");
		TaxScheme002Dto dto = new TaxScheme002Dto();
		
		String workflowId = this.getParam(GlobalConstant.WORKFLOW_ID);// 工作流ID
		
		if (StringUtils.isNotEmpty(schId)) {// 修改
			dto = taxScheme002Service.findBySchId(schId);
		} else if(StringUtil.isNotEmpty(workflowId)) { // 流程查看页面
            dto = taxScheme002Service.getBussinessEntityByWorkflowId(workflowId);
        } 
		dto.setOperateType(operateType);
		model.addAttribute("entity", dto);
		
		super.setSelectOptionToModel(model, DictCode.是否); // 是否默认
		super.setSelectOptionToModel(model, DictCode.复制范围);
		return super.init(model);
	}

	/**
	 * 提交/保存 check
	 * 
	 * @param dto
	 * @param result
	 * @param operateType
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("check")
	public AjaxResult check(@RequestBody @Validated({ValidateGroupEdit.class}) TaxScheme002Dto dto, BindingResult bindingResult) throws Exception {
		String msg = StringUtil.EMPTY;
		String operateType = dto.getOperateType();
		

//		 唯一性验证：方案代码、方案名称
		if ( !bindingResult.hasErrors()) {// 基础验证通过
		    Map<String, Object> params = Maps.newHashMap();
			params.put("schId", dto.getSchId());
			params.put("schName", dto.getSchName());
		    if (GlobalConstant.DETAIL_PAGE_MODEL_EDIT.equals(dto.getPageModel())) {// 修改时忽略本身
		            params.put("owner", dto.getSchId());
		    }
			long records = taxScheme001Service.findSchemeByPage(params).getRecords(); // 总记录数
			if (records > 0) {
				msg = "方案代码或方案名称已存在！请重新输入。";
				return new AjaxResult(msg, MessageType.错误消息);
			}
		}

		return commonCheck(bindingResult, operateType);
	}

	/**
     * 新建或修改 并启动流程
     * @param dto
     * @param bindingResult
     * @return
     */
    @ResponseBody
    @RequestMapping(value = PAGE_SAVE, method = RequestMethod.POST)
    public AjaxResult save(@RequestBody TaxScheme002Dto dto) {
      dto.setSchemeType(DictCodeListExt1.配置方案类型_税务方案.getValue());
      String msg = StringUtil.EMPTY;
        
        ProcessParameter<TaxScheme002Dto> processParameter = new ProcessParameter<>();
        WorkFlowParameter<TaxScheme002Dto> workFlowParameter = new WorkFlowParameter<>();
        
        try {
            workFlowParameter.setPageId(this.getPageId()); // 页面id
            workFlowParameter.setElementId(this.getParam(GlobalConstant.ELEMENT_ID)); // 元素id
            workFlowParameter.setBusinessCode(dto.getSchId());// 业务代码编号
            workFlowParameter.setBusinessType(DictCodeList.共通业务类型_税务方案.getValue());// 业务类型
            workFlowParameter.setComment(entity -> WorkFlowUtil.concatComment(
                "税务方案", entity.getSchId(), entity.getSchName()));// 当前工作流备注
            processParameter.setWorkFlowParameter(workFlowParameter);// 塞入流程参数
            
            taxScheme002Service.start(processParameter, dto);
            msg = getMessage("COMMON_I_0001"); // 处理正常结束
        } catch (Exception e) {
            this.getLogger().error("启动估值方案流程失败：", e);
            msg = e.getMessage();
        }
        
        return new AjaxResult(msg);
    }

}
