package com.joyintech.tams.invest.product.productplanning.tacticconfig.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.TacticConfigOptDetail;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.product.productplanning.tacticconfig.dto.TacticConfig002Dto;
import com.joyintech.tams.invest.product.productplanning.tacticconfig.service.TacticConfig002Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
/**
 * yaozhenguo 
 * @author Administrator
 *	20191228 战术配置优化
 */
@Controller
@PageId("TacticConfig002")
@RequestMapping("/invest/product/productplanning/tacticconfig/TacticConfig002")
public class TacticConfig002Controller extends BaseController {

	@Autowired
	private TacticConfig002Service tacticConfig002Service;
	/**
	 * 主键生成类
	 */
	@Autowired(required = false)
	private BaseGetBusinessId baseGetBusinessId;

	@Override
	public String init(Model model) {
		setSelectOptionToModel(model, DictCode.修正场景,DictCode.资产大类);
		model.addAttribute("COR_SCENE_1", DictCodeListExt3.修正场景_高悲观.getValue());
		model.addAttribute("COR_SCENE_2", DictCodeListExt3.修正场景_中悲观.getValue());
		model.addAttribute("COR_SCENE_3", DictCodeListExt3.修正场景_轻悲观.getValue());
		model.addAttribute("COR_SCENE_4", DictCodeListExt3.修正场景_中性.getValue());
		model.addAttribute("COR_SCENE_5", DictCodeListExt3.修正场景_轻乐观.getValue());
		model.addAttribute("COR_SCENE_6", DictCodeListExt3.修正场景_中乐观.getValue());
		model.addAttribute("COR_SCENE_7", DictCodeListExt3.修正场景_高乐观.getValue());
		model.addAttribute("assetType", this.getParam("assetType"));
		String workflowId = this.getParam("workflowId");
		String isTodoList = this.getParam("isTodoList");
		model.addAttribute("isTodoList",isTodoList);
		if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
			TacticConfig002Dto entity = tacticConfig002Service.getBussinessEntityByWorkflowId(workflowId);
			if(!StringUtil.equals(entity.getOperateType(), "delete")) {
				model.addAttribute("workflowId", workflowId);
			}
			model.addAttribute(GlobalConstant.ENTITY, entity);
		}else if(StringUtil.isNotEmpty(this.getParam("tacticConfigCode"))) {
			TacticConfig002Dto entity = tacticConfig002Service.selectByTacticConfigCode(this.getParam("tacticConfigCode"));
			model.addAttribute(GlobalConstant.ENTITY, entity);
		}
		model.addAttribute("platDate",DateUtil.platDateFromCache());
		model.addAttribute("lastDay",getYearLastDay(DateUtil.platDateFromCache()));
		return super.init(model);
	}
	
	/**
	 * 获取传入日期所在年的最后一天
	 * @param todayDate
	 * @return
	 */
	public Date getYearLastDay(Date todayDate) {
        Calendar calendar = Calendar.getInstance();
        // 把平台日期赋值给Calendar
        calendar.setTime(todayDate);
        int last = calendar.getActualMaximum(Calendar.DAY_OF_YEAR);
        calendar.set(Calendar.DAY_OF_YEAR, last);
        return calendar.getTime();
    }

	/**
	 * 资产大类下拉框
	 * 
	 * @return
	 */
	@RequestMapping("/selectAllAssetType")
	@ResponseBody
	public Page<SelectOptionDto> selectAllAssetType() {
		return tacticConfig002Service.selectAllAssetType(RequestUtil.getPageParam());
	}

	/**
	 * 战术配置明细列表
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getTacticConfigOptDetailList")
	public List<TacticConfigOptDetail> getTacticConfigOptDetailList() {
		String tacticConfigCode = this.getParam("tacticConfigCode");
		String workflowId = this.getParam("workflowId");
		if (StringUtil.isNotEmpty(workflowId)) {
			return tacticConfig002Service.getBussinessEntityByWorkflowId(workflowId).getTacticConfigOptDetails();
		}
		else if (StringUtil.isNotEmpty(tacticConfigCode)) {
			return tacticConfig002Service.getTacticConfigOptDetailList(tacticConfigCode);
		}
		SelectDto selectDto = getSelectDataFromDataDic(DictCode.资产大类,null);
		List<TacticConfigOptDetail> list = new ArrayList<TacticConfigOptDetail>();
		if(StringUtil.isNotEmpty(selectDto) && !StringUtil.isEmptyList(selectDto.getOptions())) {
			for(SelectOptionDto selectOptionDto : selectDto.getOptions()) {
				TacticConfigOptDetail tacticConfigOptDetail = new TacticConfigOptDetail();
				tacticConfigOptDetail.setAssetType(selectOptionDto.getId());
				list.add(tacticConfigOptDetail);
			}
		}
		return list;
	}
	
	/**
	 * 画面提交按钮校验
	 * 
	 * @param dto
	 * @param result
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/check")
	public AjaxResult check(@RequestBody @Validated({ ValidateGroupEdit.class }) TacticConfig002Dto dto,
			BindingResult result) {
		return commonCheck(result, dto.getOperateType());
	}

	/**
	 * 保存
	 * 
	 * @param dto
	 * @param br
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/save")
	public AjaxResult save(@RequestBody @Validated({ ValidateGroupEdit.class }) TacticConfig002Dto dto,
			BindingResult br) throws Exception {
		if (br.hasErrors()) {
			return new AjaxResult(br);
		}
		ProcessParameter<TacticConfig002Dto> processParameter = new ProcessParameter<>();
		WorkFlowParameter<TacticConfig002Dto> params = new WorkFlowParameter<>();
		params.setPageId("TacticConfig002");
		// 生成战术配置ID
		if (StringUtil.isEmpty(dto.getTacticConfigCode())) {
			BussinessCodeParameter bussinessCodeParameter = new BussinessCodeParameter();
			bussinessCodeParameter.setCommonBusinessExtType(DictCodeListExt3.fromDictCodeList(DictCodeListExt3.共通业务类型_战术配置优化));
			if (baseGetBusinessId != null) {
				String tacticConfigCode = baseGetBusinessId.buildBussinessCode(bussinessCodeParameter) != null
						? baseGetBusinessId.buildBussinessCode(bussinessCodeParameter)
						: IDGenerator.generate();
				dto.setTacticConfigCode(tacticConfigCode);
			}
		}
		params.setElementId(dto.getElementId());
		params.setBusinessCode(item -> item.getTacticConfigCode());
		params.setBusinessType(DictCodeListExt3.共通业务类型_战术配置优化.getValue());
		params.setComment(entity -> this.getMessage("TacticConfig001.tacticConfigCode") + "("
				+ entity.getTacticConfigCode() + ")-" + this.getMessage("TacticConfig001.vdate") + "("
				+ DateUtil.format(entity.getVdate()) + ")-" + this.getMessage("TacticConfig001.edate") + "(" + DateUtil.format(entity.getEdate()) + ")");
		processParameter.setWorkFlowParameter(params);
		String msg = tacticConfig002Service.start(processParameter, dto);
		return new AjaxResult(msg);
	}
}
