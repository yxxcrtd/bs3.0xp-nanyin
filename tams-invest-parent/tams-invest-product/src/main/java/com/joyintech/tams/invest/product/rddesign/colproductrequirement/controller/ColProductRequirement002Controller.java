package com.joyintech.tams.invest.product.rddesign.colproductrequirement.controller;

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
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.product.rddesign.colproductrequirement.dto.ColProductRequirement002Dto;
import com.joyintech.tams.invest.product.rddesign.colproductrequirement.service.ColProductRequirement002Service;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

@Controller
@PageId("ColProductRequirement002")
@RequestMapping("/invest/product/rddesign/colproductrequirement/ColProductRequirement002")
public class ColProductRequirement002Controller extends BaseController {

	@Autowired
	private ColProductRequirement002Service colProductRequirement002Service;

	/**
	 * 主键生成类
	 */
	@Autowired(required = false)
	private BaseGetBusinessId baseGetBusinessId;

	@Override
	public String init(Model model) {
		String workflowId = this.getParam("workflowId");
		if (StringUtils.equals(this.getParam(GlobalConstant.DETAIL_PAGE_MODEL_KEY),
				GlobalConstant.DETAIL_PAGE_MODEL_REFERENCE) || 
				StringUtils.equals(this.getParam(GlobalConstant.DETAIL_PAGE_MODEL_KEY),GlobalConstant.DETAIL_PAGE_MODEL_EDIT)) {
			ColProductRequirement002Dto entity = colProductRequirement002Service
					.queryRequirementByRequireId(this.getParam("requirementId"));
			model.addAttribute(GlobalConstant.ENTITY, entity);
		}
		if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
			ColProductRequirement002Dto entity = colProductRequirement002Service
					.getBussinessEntityByWorkflowId(workflowId);
			if(OperationType.DELETE.toString().equalsIgnoreCase(entity.getOperateType())) {
				entity = colProductRequirement002Service.queryRequirementByRequireId(entity.getRequirementId());
			}
			model.addAttribute(GlobalConstant.ENTITY, entity);
		}
		setSelectOptionToModel(model, DictCode.需求发起端);
		model.addAttribute("requirementUser",SecurityUtil.getLoginUser().getUserName());
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
	public AjaxResult check(@RequestBody @Validated({ ValidateGroupEdit.class }) ColProductRequirement002Dto dto,
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
	public AjaxResult save(@RequestBody @Validated({ ValidateGroupEdit.class }) ColProductRequirement002Dto dto,
			BindingResult br) throws Exception {
		if (br.hasErrors()) {
			return new AjaxResult(br);
		}
		ProcessParameter<ColProductRequirement002Dto> processParameter = new ProcessParameter<>();
		WorkFlowParameter<ColProductRequirement002Dto> params = new WorkFlowParameter<>();
		params.setPageId("ColProductRequirement002");
		// 生成需求ID
		if (dto.getRequirementId() == null || dto.getRequirementId().isEmpty()) {
			BussinessCodeParameter bussinessCodeParameter = new BussinessCodeParameter();
			bussinessCodeParameter.setCommonBusinessType(DictCodeList.共通业务类型_产品需求收集);
			if (baseGetBusinessId != null) {
				String requirementId = baseGetBusinessId.buildBussinessCode(bussinessCodeParameter) != null
						? baseGetBusinessId.buildBussinessCode(bussinessCodeParameter)
						: IDGenerator.generate();
				dto.setRequirementId(requirementId);
			}
		}
		params.setElementId(dto.getElementId());
		params.setBusinessCode(item -> item.getRequirementId());
		params.setBusinessType(DictCodeList.共通业务类型_产品需求收集.getValue());
		params.setComment(entity -> this.getMessage("ColProductRequirement002.requirementId") + "("
				+ entity.getRequirementId() + ")-" + this.getMessage("ColProductRequirement002.requirementTime") + "("
				+ entity.getRequirementTimeStr() + ")");
		processParameter.setWorkFlowParameter(params);
		String msg = colProductRequirement002Service.start(processParameter, dto);
		return new AjaxResult(msg);
	}
}
