package com.joyintech.tams.invest.product.productplanning.assetclassmanagement.controller;

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
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.component.business.core.service.BaseGetBusinessId;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.product.productplanning.assetclassmanagement.dto.AssetClassManagement001Dto;
import com.joyintech.tams.invest.product.productplanning.assetclassmanagement.service.AssetClassManagement001Service;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

/**
 * 资产分类管理
 * 
 * @author wangqidong
 * @version 1.0 2019年12月10日
 * @see AssetClassManagement001Controller
 * @since 1.0
 */
@Controller
@PageId(value = "AssetClassManagement001")
@RequestMapping("/invest/product/productplanning/assetclassmanagement/AssetClassManagement001")
public class AssetClassManagement001Controller extends BaseController {

	@Autowired
	private AssetClassManagement001Service assetClassManagement001Service;

	/**
	 * 业务编号组件
	 */
	@Autowired
	private BaseGetBusinessId baseGetBusinessId;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String init(Model model) {
		setSelectOptionToModel(model, new DictCode[] { DictCode.资产二级分类, DictCode.资产三级分类, DictCode.资产四级分类,
				DictCode.资产五级分类, DictCode.资产一级分类, DictCode.资产大类, DictCode.是否 });
		return super.init(model);
	}

	/**
	 * 查询
	 * 
	 * @return
	 * @throws Exception
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping("selectForPage")
	public Page<AssetClassManagement001Dto> selectAll() throws Exception {
		Page<AssetClassManagement001Dto> page = assetClassManagement001Service
				.selectForPage(RequestUtil.getParameters(getRequest()));
		return page;
	}

	/**
	 * 导出
	 * 
	 * @return
	 * @throws Exception
	 * @see
	 * @since 1.0
	 */
	@RequestMapping("export")
	public ResponseEntity<byte[]> export() throws Exception {
		return assetClassManagement001Service.export(this.getPageId(), RequestUtil.getParameters(getRequest()));
	}

	/**
	 * 流程delete
	 */
	@ResponseBody
	@RequestMapping("delete")
	public AjaxResult delete(@RequestBody AssetClassManagement001Dto dto) throws Exception {
		ProcessParameter<AssetClassManagement001Dto> processParameter = new ProcessParameter<AssetClassManagement001Dto>();
		WorkFlowParameter<AssetClassManagement001Dto> params = new WorkFlowParameter<AssetClassManagement001Dto>();
		// BussinessCodeParameter parameter=new BussinessCodeParameter();
		// parameter.setCommonBusinessType(DictCodeList.共通业务类型_资产分类管理);
		// String code=baseGetBusinessId.buildBussinessCode(parameter);
		params.setPageId("AssetClassManagement002");
		params.setElementId(dto.getElementId());
		params.setBusinessCode(DictCodeListExt3.共通业务类型_资产分类管理.getValue());
		params.setBusinessType(DictCodeListExt3.共通业务类型_资产分类管理.getValue());
		processParameter.setOperationType(OperationType.DELETE);
		// 业务说明：资产类型
		params.setComment(
				entity -> this.getMessage("AssetClassManagement.assetType") + "(" + entity.getAssetType() + ")");
		processParameter.setWorkFlowParameter(params);
		String msg = assetClassManagement001Service.start(processParameter,
				dto.getselectedItem().toArray(new AssetClassManagement001Dto[dto.getselectedItem().size()]));
		return new AjaxResult(msg);
	}

	/**
	 * 流程check
	 */
	@ResponseBody
	@RequestMapping("check")
	public AjaxResult check(@RequestBody @Validated({ ValidateGroupDelete.class }) AssetClassManagement001Dto dto,
			BindingResult result) {
		return commonCheck(result, dto.getOperateType());
	}

}
