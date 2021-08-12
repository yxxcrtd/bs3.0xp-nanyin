package com.joyintech.tams.invest.risk.creditrisklimitmanagement.singlelimitoccupation.controller;

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
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.singlelimitoccupation.dto.SingleLimitOccupationDto;
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.singlelimitoccupation.service.SingleLimitOccupation002Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 单笔资产额度校验规则
 * 
 * @author chengzhong
 * @version 1.0 2020年02月26日
 * @see SingleLimitOccupation001Controller
 * @since 1.0
 */
@Controller
@PageId("SingleLimitOccupation002")
@RequestMapping("/invest/risk/creditrisklimitmanagement/singlelimitoccupation/SingleLimitOccupation002")
public class SingleLimitOccupation002Controller extends BaseController {

	/**
	 * SingleLimitOccupation002Service
	 */
	@Autowired(required = false)
	private SingleLimitOccupation002Service singleLimitOccupation002Service;

	/**
	 * 初始化的查询条件
	 */
	private static final IDictCode[] DICT_CODES = new DictCode[] { DictCode.扣减对象 };

	@Override
	public String init(Model model) {
		setSelectOptionToModel(model, DICT_CODES);
		String workflowId = getParam("workflowId");
		// 待办跳转
		if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
			model.addAttribute("workflowId", workflowId);
			SingleLimitOccupationDto dto = singleLimitOccupation002Service
					.getBussinessEntityByWorkflowId(workflowId);
			model.addAttribute("entity", dto);
		} else if (WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(this.getParam("creditRuleId"))) {
			 String creditRuleId = this.getParam("creditRuleId");
			 SingleLimitOccupationDto dto =
			 singleLimitOccupation002Service.getBussinessEntityByIndId(creditRuleId);
			
			 model.addAttribute(GlobalConstant.ENTITY, dto);
		}
		return super.init(model);
	}

	/**
	 * 保存按钮点击时的check
	 * 
	 * @return check结果
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping("check")
	public AjaxResult check(@RequestBody @Validated({ ValidateGroupEdit.class }) SingleLimitOccupationDto dto,
			BindingResult result) {
		return commonCheck(result, dto.getOperateType());
	}

	/**
	 * 保存
	 * 
	 * @param dto
	 * @throws Exception
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping(PAGE_SAVE)
	public AjaxResult save(@RequestBody @Validated({ ValidateGroupEdit.class }) SingleLimitOccupationDto dto,
			BindingResult br) throws Exception {
		if (br.hasErrors()) {
			return new AjaxResult(br);
		}
		if (StringUtil.isEmpty(dto.getCreditRuleId())) {
			dto.setCreditRuleId(IDGenerator.generate());
		}
		// BussinessCodeParameter parameter = new BussinessCodeParameter();
		// parameter.setCommonBusinessType(DictCodeListExt3.共通业务类型_单笔资产额度校验规则);
		ProcessParameter<SingleLimitOccupationDto> processParameter = new ProcessParameter<>();
		WorkFlowParameter<SingleLimitOccupationDto> params = new WorkFlowParameter<>();
		params.setPageId(this.getPageId());
		params.setElementId(dto.getElementId());
		// 授权代码作为业务编号
		String businessCode = dto.getCreditRuleId();
		params.setBusinessCode(businessCode);
		params.setBusinessType(DictCodeListExt3.共通业务类型_单笔资产额度校验规则.getValue());
		dto.setCreditTagId(dto.getCreditTag());
		// 设置流程业务说明, entity即传入的dto
		params.setComment(entity -> {
			return WorkFlowUtil.concatComment(getMessage("SingleLimitOccupation001.finprodId"),
					GlobalConstant.OPEN_PARENTHESIS, dto.getFinprodId(), GlobalConstant.CLOSE_PARENTHESIS,
					getMessage("SingleLimitOccupation001.creditTag"), GlobalConstant.OPEN_PARENTHESIS,
					entity.getCreditTag(), GlobalConstant.CLOSE_PARENTHESIS);
		});
		processParameter.setWorkFlowParameter(params);
		String msg = singleLimitOccupation002Service.start(processParameter, dto);
		return new AjaxResult(msg);
	}
}
