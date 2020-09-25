package com.joyintech.tams.invest.product.rddesign.reportproductrequirement.controller;

import org.apache.commons.codec.binary.StringUtils;
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
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.product.rddesign.colproductrequirement.dto.ColProductRequirement002Dto;
import com.joyintech.tams.invest.product.rddesign.reportproductrequirement.dto.ReportProductRequirement002Dto;
import com.joyintech.tams.invest.product.rddesign.reportproductrequirement.service.ReportProductRequirement002Service;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

@Controller
@PageId("ReportProductRequirement002")
@RequestMapping("/invest/product/rddesign/reportproductrequirement/ReportProductRequirement002")
public class ReportProductRequirement002Controller extends BaseController {

	@Autowired
	private ReportProductRequirement002Service reportProductRequirement002Service;


	@Override
	public String init(Model model) {
		setSelectOptionToModel(model, DictCode.需求发起端,DictCode.是否,DictCode.初评结果);
		String workflowId = this.getParam("workflowId");
		if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
			ReportProductRequirement002Dto entity = reportProductRequirement002Service
					.getBussinessEntityByWorkflowId(workflowId);
			model.addAttribute(GlobalConstant.ENTITY, entity);
		}
		else if (StringUtils.equals(this.getParam(GlobalConstant.DETAIL_PAGE_MODEL_KEY),
				GlobalConstant.DETAIL_PAGE_MODEL_REFERENCE) || 
				StringUtils.equals(this.getParam(GlobalConstant.DETAIL_PAGE_MODEL_KEY),GlobalConstant.DETAIL_PAGE_MODEL_EDIT)) {
			ReportProductRequirement002Dto entity = reportProductRequirement002Service
					.queryRequirementByRequireId(this.getParam("requirementId"));
			model.addAttribute(GlobalConstant.ENTITY, entity);
		}
		return super.init(model);
	}

	/**
	 * 画面点击保存按钮时check
	 * 
	 * @param dto
	 *            实体类
	 * @param result
	 * @return check结果
	 */
	@ResponseBody
	@RequestMapping("/check")
	public AjaxResult check(@RequestBody @Validated({ ValidateGroupEdit.class }) ReportProductRequirement002Dto dto,
			BindingResult result) {
		return commonCheck(result, dto.getOperateType());
	}

	/**
	 * 保存
	 * 
	 * @param dto
	 * @param br
	 * @return 结果
	 * @throws Exception 异常
	 */
	@ResponseBody
	@RequestMapping("/save")
	public AjaxResult save(@RequestBody @Validated({ ValidateGroupEdit.class }) ReportProductRequirement002Dto dto,
			BindingResult br) throws Exception {
		if (br.hasErrors()) {
			return new AjaxResult(br);
		}
		ProcessParameter<ReportProductRequirement002Dto> processParameter = new ProcessParameter<>();
		WorkFlowParameter<ReportProductRequirement002Dto> params = new WorkFlowParameter<>();
		params.setPageId("ReportProductRequirement002");
		params.setElementId(dto.getElementId());
		params.setBusinessCode(item -> item.getRequirementId());
		params.setBusinessType(DictCodeList.共通业务类型_产品需求上报.getValue());
		params.setComment(entity -> this.getMessage("ReportProductRequirement002.requirementId") + "("
				+ entity.getRequirementId() + ")-" + this.getMessage("ReportProductRequirement002.reportStatus") + "-" + 
				"(" + entity.getDataStatusStr() + ")-" + this.getMessage("ReportProductRequirement002.requirementTime") + "("
				+ entity.getRequirementTimeStr() + ")");
		processParameter.setWorkFlowParameter(params);
		String msg = reportProductRequirement002Service.start(processParameter, dto);
		return new AjaxResult(msg);
	}
}
