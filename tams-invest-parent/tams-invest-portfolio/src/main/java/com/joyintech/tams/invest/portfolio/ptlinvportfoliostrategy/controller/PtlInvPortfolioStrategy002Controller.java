/*
 * 文件名：PtlInvPortfolioStrategy002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：niuqianyu
 * 修改时间：2019年7月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.portfolio.ptlinvportfoliostrategy.controller;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.PtlPortfolio;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.component.business.prod.BussBenchMarketInfoService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.portfolio.ptlinvportfoliostrategy.dto.PtlIncomeIndexDto;
import com.joyintech.tams.invest.portfolio.ptlinvportfoliostrategy.dto.PtlInvPortfolioStrategy001Dto;
import com.joyintech.tams.invest.portfolio.ptlinvportfoliostrategy.service.PtlInvPortfolioStrategy001Service;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 
 * 投资组合策略管理新建
 * 
 * @author niuqianyu
 * @version 1.0 2019年7月26日
 * @see PtlInvPortfolioStrategy002Controller
 * @since 1.0
 */
@Controller
@PageId("PtlInvPortfolioStrategy002")
@RequestMapping("/invest/portfolio/ptlinvportfoliostrategy/PtlInvPortfolioStrategy002")
public class PtlInvPortfolioStrategy002Controller extends BaseController {

	@Autowired
	private PtlInvPortfolioStrategy001Service ptlInvPortfolioStrategy001Service;

	/**
	 * 开放计划组件
	 */
	@Autowired
	private BussBenchMarketInfoService bussBenchMarketInfoService;

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
		this.setSelectOptionToModel(model, DictCode.投资策略类型, DictCode.评价指标, DictCode.评价指标收益目标类型, DictCode.是或否,
				DictCode.利率行情);

		String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
		String ptlStgyId = this.getParam("ptlStgyId");
		if (StringUtil.isNotEmpty(ptlStgyId)) {
			Map<String, Object> params = new HashMap<>();
			params.put("ptlStgyId", ptlStgyId);
			PtlInvPortfolioStrategy001Dto dto = ptlInvPortfolioStrategy001Service.getPtlInvPortfolioStrategy(params);
			model.addAttribute("entity", dto);
			Map<String,Object> pageParam = new HashMap<>();
			pageParam.put("indexId", dto.getStrategyId());
            model.addAttribute("ptlIncomeIndexList", dto.getPtlIncomeIndexList());
			model.addAttribute("indexId", this.getParam("indexId"));

		} else
		    if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
			PtlInvPortfolioStrategy001Dto dto = ptlInvPortfolioStrategy001Service
					.getBussinessEntityByWorkflowId(workflowId);
			model.addAttribute("entity", dto);
            model.addAttribute("ptlIncomeIndexList", dto.getPtlIncomeIndexList());
			model.addAttribute("indexId", dto.getIndexId());
            model.addAttribute("workflowId", workflowId);
		}
		return super.init(model);
	}

	/**
	 * 通过组合代码，获取组合名称
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("getPortfolioName")
	public PtlPortfolio getPortfolioName() throws Exception {
		return ptlInvPortfolioStrategy001Service.getPortfolioName(RequestUtil.getPageParam());
	}

	/**
	 * 通过策略代码，获取策略名称和策略描述
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("getStrategy")
	public PtlInvPortfolioStrategy001Dto getStrategy() throws Exception {
		return ptlInvPortfolioStrategy001Service.getStrategy(RequestUtil.getPageParam());
	}

	/**
	 * 获取收益目标列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("getPtlIncomeIndexFloatList")
	public List<PtlIncomeIndexDto> getPtlIncomeIndexFloatList() {
		List<PtlIncomeIndexDto> list = new ArrayList<>();
		Map<String, Object> pageParam = RequestUtil.getPageParam();
		String workflowId = (String) pageParam.get("workflowId");
		if (StringUtil.isNotEmpty(workflowId)) {
			PtlInvPortfolioStrategy001Dto ptlInvPortfolioStrategy001Dto = ptlInvPortfolioStrategy001Service
					.getBussinessEntityByWorkflowId(workflowId);

			list = ptlInvPortfolioStrategy001Dto.getPtlIncomeIndexList();
		} else {
			list = ptlInvPortfolioStrategy001Service.getPtlIncomeIndexFloatList(pageParam);
		}
		return list;
	}

	/**
	 * 新建-列表-基准下拉框
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("selectBaseRuleId")
	public List<SelectOptionDto> selectBaseRuleId() {
		// 浮动利率下拉框（含利率行情、复合基准四个部分）
		List<SelectOptionDto> listFloatRate = new ArrayList<SelectOptionDto>();
		// 利率行情
		SelectDto selectDto = this.getSelectDataFromDataDic(DictCode.利率行情, null);
		for (SelectOptionDto dto : selectDto.getOptions()) {
			SelectOptionDto dto1 = new SelectOptionDto();
			dto1.setId(dto.getId());
			dto1.setText(dto.getText());
			dto1.setObject(DictCodeListExt1.基准类型_2_利率);
			listFloatRate.add(dto1);
		}
		// 复合基准
		List<SelectOptionDto> finBaseRuleInfoList = bussBenchMarketInfoService.getFinBaseRuleInfo();
		for (SelectOptionDto dto : finBaseRuleInfoList) {
			SelectOptionDto dto1 = new SelectOptionDto();
			dto1.setId(dto.getId());
			dto1.setText(dto.getText());
			dto1.setObject(DictCodeListExt1.基准类型_2_复合);
			listFloatRate.add(dto1);
		}

		return listFloatRate;
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
	@RequestMapping("check")
	public AjaxResult check(@RequestBody @Validated({ ValidateGroupEdit.class }) PtlInvPortfolioStrategy001Dto dto,
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
	public AjaxResult save(@RequestBody @Validated({ ValidateGroupEdit.class }) PtlInvPortfolioStrategy001Dto dto,
			BindingResult br) throws Exception {
		if (br.hasErrors()) {
			return new AjaxResult(br);
		}
		if (!StringUtil.isNotEmpty(dto.getPtlStgyId())) {
			// 调用[详细设计-业务编号共通.xlsx]取得交易编号
			BussinessCodeParameter parameter = new BussinessCodeParameter();
			parameter.setCommonBusinessType(DictCodeList.共通业务类型_投资组合策略关联);
			// 组合策略代码
			String ptlStgyId = baseGetBussinessId.buildBussinessCode(parameter);

			dto.setPtlStgyId(ptlStgyId);

		}
		ProcessParameter<PtlInvPortfolioStrategy001Dto> processParameter = new ProcessParameter<PtlInvPortfolioStrategy001Dto>();
		WorkFlowParameter<PtlInvPortfolioStrategy001Dto> params = new WorkFlowParameter<PtlInvPortfolioStrategy001Dto>();
		params.setPageId("PtlInvPortfolioStrategy002");
		params.setElementId(this.getParam(GlobalConstant.ELEMENT_ID));
		params.setBusinessCode(item -> item.getPtlStgyId());
		params.setBusinessType(DictCodeList.共通业务类型_投资组合策略关联.getValue());
		params.setComment(entity -> this.getMessage("PtlInvPortfolioStrategy001.portfolioId") + "("
				+ entity.getPortfolioId() + ")" + "-" + this.getMessage("PtlInvPortfolioStrategy001.strategyId") + "("
				+ entity.getStrategyId() + ")" + "-" + this.getMessage("PtlInvPortfolioStrategy001.effDate") + "("
				+ entity.getEffDate() + ")");
		processParameter.setWorkFlowParameter(params);
		String msg = ptlInvPortfolioStrategy001Service.start(processParameter, dto);
		return new AjaxResult(msg);
	}
}
