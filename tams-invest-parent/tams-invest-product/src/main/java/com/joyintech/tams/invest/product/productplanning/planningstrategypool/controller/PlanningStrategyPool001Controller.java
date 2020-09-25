/*
 * 文件名：PlanningStrategyPool001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：niuqianyu
 * 修改时间：2019年12月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.product.productplanning.planningstrategypool.controller;

import java.io.File;
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
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.product.productplanning.planningstrategypool.dto.PlanningStrategyPool001Dto;
import com.joyintech.tams.invest.product.productplanning.planningstrategypool.service.PlanningStrategyPool001Service;
import com.joyintech.tams.invest.product.productplanning.planningstrategypool.service.PlanningStrategyPool002Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

/**
 * 规划策略池维护
 * 
 * @author niuqianyu
 * @version 1.0 2019年12月13日
 * @see PlanningStrategyPool001Controller
 * @since 1.0
 */
@Controller
@PageId("PlanningStrategyPool001")
@RequestMapping("/invest/product/productplanning/planningstrategypool/PlanningStrategyPool001")
public class PlanningStrategyPool001Controller extends BaseController {

	@Autowired
	private PlanningStrategyPool001Service planningStrategyPool001Service;

	@Autowired
	private PlanningStrategyPool002Service planningStrategyPool002Service;

	/**
	 * 界面初始化 {@inheritDoc}
	 */
	@Override
	@RequestMapping(PAGE_INIT)
	public String init(Model model) {
		super.setSelectOptionToModel(model, DictCode.使用状态, DictCode.资产大类);
		return super.init(model);
	}

	/**
	 * 策略代码下拉框
	 * 
	 * @return List<SelectOptionDto>
	 * @see
	 * @since 1.0
	 */
	@RequestMapping("/getStrategyId")
	@ResponseBody
	public Page<SelectOptionDto> getStrategyId() {
		return planningStrategyPool001Service.getStrategyId(RequestUtil.getPageParam());
	}

	/**
	 * 分页查询
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("findPage")
	public Page<PlanningStrategyPool001Dto> findPage() throws Exception {
		return planningStrategyPool001Service.selectForPage(RequestUtil.getParameters(getRequest()));

	}

	/**
	 * 导出信息excel
	 * 
	 * @return excel
	 * @throws Exception
	 *             异常
	 * @see
	 * @since 1.0
	 */
	@RequestMapping(ACTION_EXPORT)
	public ResponseEntity<byte[]> export() throws Exception {
		Map<String, Object> paraMap = RequestUtil.getPageParam();
		List<PlanningStrategyPool001Dto> listdto = planningStrategyPool001Service.selectForExport(paraMap);

		File outFile = planningStrategyPool001Service.makeFile(
				"/com/joyintech/tams/invest/product/productplanning/planningstrategypool/conf/export_conf.xml",
				listdto);
		return ExportUtil.export(this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
				+ DateUtil.format(new Date()) + SystemGlobalConstant.CLOSE_PARENTHESIS + SystemGlobalConstant.POINT
				+ FileNameUtil.getFileExtName(outFile), outFile, true);
	}

	/**
	 * 删除校验
	 * 
	 * @param dto
	 *            dto
	 * @param result
	 *            BindingResult
	 * @return check结果
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping("deleteCheck")
	public AjaxResult deleteCheck(@RequestBody @Validated({ ValidateGroupDelete.class }) PlanningStrategyPool001Dto dto,
			BindingResult result) {
		return commonCheck(result, dto.getOperateType());
	}

	/**
	 * 删除
	 * 
	 * @param dto
	 *            业务实体
	 * @param br
	 *            br
	 * @return AjaxResult
	 * @throws Exception
	 *             Exception
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping(ACTION_DELETE)
	public AjaxResult delete(@RequestBody @Validated({ ValidateGroupDelete.class }) PlanningStrategyPool001Dto dto,
			BindingResult br) throws Exception {
		// 删除前校验
		if (br.hasErrors()) {
			return new AjaxResult(br);
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
