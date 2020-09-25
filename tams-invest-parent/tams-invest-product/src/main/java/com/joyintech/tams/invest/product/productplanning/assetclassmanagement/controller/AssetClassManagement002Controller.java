package com.joyintech.tams.invest.product.productplanning.assetclassmanagement.controller;

import java.util.HashMap;
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
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.product.productplanning.assetclassmanagement.dto.AssetClassManagement001Dto;
import com.joyintech.tams.invest.product.productplanning.assetclassmanagement.service.AssetClassManagement002Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 新建
 * 
 * @author dongzhikang
 * @version 1.0 2019年12月10日
 * @see AssetClassManagement002Controller
 * @since 1.0
 */

@Controller
@PageId(value = "AssetClassManagement002")
@RequestMapping("/invest/product/productplanning/assetclassmanagement/AssetClassManagement002")
public class AssetClassManagement002Controller extends BaseController {

	@Autowired
	private AssetClassManagement002Service assetClassManagement002Service;

	@Override
	public String init(Model model) {
		setSelectOptionToModel(model, new DictCode[] { DictCode.资产二级分类, DictCode.资产三级分类, DictCode.资产四级分类,
				DictCode.资产五级分类, DictCode.资产一级分类, DictCode.资产大类, DictCode.是否 });
		String workflowId = getParam("workflowId");
		String assetTypeTwo = getParam("customAssetTypeTwo");
		if (WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(assetTypeTwo)) {
			Map<String, Object> param = new HashMap<>();
			// 将需要的数据字典和参数添加到map中
			param.put("customAssetTypeTwo", getParam("customAssetTypeTwo"));
			param.put("customAssetTypeThree", getParam("customAssetTypeThree"));
			param.put("customAssetTypeFour", getParam("customAssetTypeFour"));
			param.put("customAssetTypeFive", getParam("customAssetTypeFive"));
			AssetClassManagement001Dto dto = assetClassManagement002Service.selectByPrimaryKey(param);
			// 设置到model中
			model.addAttribute("entity", dto);
		}
		if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
			AssetClassManagement001Dto dto = assetClassManagement002Service.getBussinessEntityByWorkflowId(workflowId);
			model.addAttribute("entity", dto);
		}
		return super.init(model);
	}

	/*
	 * 画面点击保存按钮时check
	 * 
	 * @param dto 实体类
	 * 
	 * @param result
	 * 
	 * @return check结果
	 */
	@ResponseBody
	@RequestMapping("/check")
	public AjaxResult check(@RequestBody @Validated({ ValidateGroupEdit.class }) AssetClassManagement001Dto dto,
			BindingResult result) {
		return commonCheck(result, dto.getOperateType());
	}

	/**
	 * 保存操作
	 *
	 * @param dto
	 *            dto
	 * @param br
	 *            BindingResult
	 * @return 结果
	 * @throws Exception
	 *             异常
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping("save")
	public AjaxResult save(@RequestBody @Validated({ ValidateGroupEdit.class }) AssetClassManagement001Dto dto,
			BindingResult br) throws Exception {

		if (br.hasErrors()) {
			return new AjaxResult(br);
		}

		ProcessParameter<AssetClassManagement001Dto> processParameter = new ProcessParameter<AssetClassManagement001Dto>();
		WorkFlowParameter<AssetClassManagement001Dto> params = new WorkFlowParameter<AssetClassManagement001Dto>();
		params.setElementId(dto.getElementId());
		params.setPageId(this.getPageId());
		params.setBusinessCode(dto.getCustomAssetTypeTwo());
		params.setBusinessType(DictCodeListExt3.共通业务类型_资产分类管理.getValue());
		// 设置工作流注解
		params.setComment(entity -> this.getMessage("AssetClassManagement.customAssetTypeTwo") + "("
				+ entity.getCustomAssetTypeTwo() + ")" + this.getMessage("AssetClassManagement.customAssetTypeThree")
				+ "(" + entity.getCustomAssetTypeThree() + ")");
		processParameter.setWorkFlowParameter(params);
		/*
		 * if (StringUtil.isEmpty(dto.o())) { // 业务代码生成参数 BussinessCodeParameter
		 * parameter = new BussinessCodeParameter();
		 * parameter.setCommonBusinessType(DictCodeList.共通业务类型_产品排期管理); String code =
		 * bussGetBussinessIdService != null ?
		 * bussGetBussinessIdService.buildBussinessCode( parameter) :
		 * IDGenerator.generate(); dto.setFinprodId(code); }
		 */
		// 调用工作流事件
		String msg = assetClassManagement002Service.start(processParameter, dto);
		// 返回工作流错误信息
		return new AjaxResult(msg);
	}
}
