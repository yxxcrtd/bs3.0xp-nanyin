/*
 * 文件名：PlanningStrategyPool002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：niuqianyu
 * 修改时间：2019年12月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.productplanning.planningstrategypool.controller;

import java.util.ArrayList;
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
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.product.productplanning.planningstrategypool.dto.PlanningStrategyPool001Dto;
import com.joyintech.tams.invest.product.productplanning.planningstrategypool.dto.PlanningStrategyPoolDetailDto;
import com.joyintech.tams.invest.product.productplanning.planningstrategypool.service.PlanningStrategyPool002Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 规划策略池维护新建
 * 
 * @author niuqianyu
 * @version 1.0 2019年12月17日
 * @see PlanningStrategyPool002Controller
 * @since 1.0
 */
@Controller
@PageId("PlanningStrategyPool002")
@RequestMapping("/invest/product/productplanning/planningstrategypool/PlanningStrategyPool002")
public class PlanningStrategyPool002Controller extends BaseController {

	@Autowired
	private PlanningStrategyPool002Service planningStrategyPool002Service;

	/**
	 * 业务编号共通组件
	 */
	@Autowired(required = false)
	private BaseGetBusinessId baseGetBussinessId;

	/**
	 * 界面初始化 {@inheritDoc}
	 */
	@Override
	@RequestMapping(PAGE_INIT)
	public String init(Model model) {
		super.setSelectOptionToModel(model, DictCode.使用状态, DictCode.资产大类, DictCode.投资策略类型);
		String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
		String strategyId = getParam("strategyId");
		// 流程中
		if (!WorkFlowUtil.isNotFromToDoList() && StringUtils.isNotEmpty(workflowId)) {
			// 根据工作流与留痕处理提供的共通方法获取业务实体
			PlanningStrategyPool001Dto entity = planningStrategyPool002Service
					.getBussinessEntityByWorkflowId(workflowId);
			// 设置到model中
			model.addAttribute("entity", entity);
			model.addAttribute("workflowId", workflowId);
		}
		// 修改
		else if (WorkFlowUtil.isNotFromToDoList() && StringUtils.isNotEmpty(strategyId)) {
			PlanningStrategyPool001Dto entity = planningStrategyPool002Service.getPlanningStrategyPool(strategyId);
			// 设置到model中
			model.addAttribute("entity", entity);
		}
		return super.init(model);
	}

	/**
	 * 根据策略代码查询策略明细
	 *
	 * @return
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping("queryPlanningStrategyPoolDetail")
	public List<PlanningStrategyPoolDetailDto> queryLayAmtDetailByFinprodId() {
		// 流程中
		if (StringUtil.isNotEmpty(getParam(GlobalConstant.WORKFLOW_ID))) {
			ProcessMark pro = planningStrategyPool002Service.getBusinessInfo(this.getParam(GlobalConstant.WORKFLOW_ID));
			// ProcessMark不为空时,获取业务实体
			if (pro != null) {
				PlanningStrategyPool001Dto entity = JsonUtils.json2Object(pro.getBusinessData(),
						PlanningStrategyPool001Dto.class);
				return entity.getPlanningStrategyPoolDetailList();
			}
		}
		// 修改
		else if (StringUtil.isNotEmpty(this.getParam("strategyId"))) {
			return planningStrategyPool002Service.getPlanningStrategyPoolDetail(this.getParam("strategyId"));
		}
		return new ArrayList<PlanningStrategyPoolDetailDto>();
	}

	/**
	 * 新建修改校验
	 *
	 * @param dto
	 *            实体类
	 * @param result
	 * @return check结果
	 */
	@ResponseBody
	@RequestMapping("check")
	public AjaxResult check(@RequestBody @Validated({ ValidateGroupEdit.class }) PlanningStrategyPool001Dto dto,
			BindingResult result) {
		return commonCheck(result, dto.getOperateType());
	}

	/**
	 * 保存
	 * 
	 * @param dto
	 *            dto
	 * @param br
	 *            br
	 * @return 结果
	 * @throws Exception
	 *             异常
	 */
	@ResponseBody
	@RequestMapping("save")
	public AjaxResult save(@RequestBody @Validated({ ValidateGroupEdit.class }) PlanningStrategyPool001Dto dto,
			BindingResult br) throws Exception {
		// 新建、修改校验
		if (br.hasErrors()) {
			return new AjaxResult(br);
		}
		// 调用共通自动生成策略代码
		if (!StringUtil.isNotEmpty(dto.getStrategyId())) {
			// 调用[详细设计-业务编号共通.xlsx]取得交易编号
			BussinessCodeParameter parameter = new BussinessCodeParameter();
			parameter.setCommonBusinessType(DictCodeList.共通业务类型_规划策略池维护);
			// 组合策略代码
			String strategyId = baseGetBussinessId.buildBussinessCode(parameter);
			dto.setStrategyId(strategyId);
		}
		ProcessParameter<PlanningStrategyPool001Dto> processParameter = new ProcessParameter<PlanningStrategyPool001Dto>();
		WorkFlowParameter<PlanningStrategyPool001Dto> params = new WorkFlowParameter<PlanningStrategyPool001Dto>();
		params.setPageId("PlanningStrategyPool002");
		params.setElementId(this.getParam(GlobalConstant.ELEMENT_ID));
		params.setBusinessCode(item -> item.getStrategyId());
		params.setBusinessType(DictCodeList.共通业务类型_规划策略池维护.getValue());
		params.setComment(
				entity -> this.getMessage("PlanningStrategyPool001.strategyId") + "(" + entity.getStrategyId() + ")");
		processParameter.setWorkFlowParameter(params);
		String msg = planningStrategyPool002Service.start(processParameter, dto);
		return new AjaxResult(msg);
	}

}
