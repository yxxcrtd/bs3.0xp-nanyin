package com.joyintech.tams.invest.product.productplanning.tacticconfig.controller;

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
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.product.productplanning.tacticconfig.dto.TacticConfig001Dto;
import com.joyintech.tams.invest.product.productplanning.tacticconfig.service.TacticConfig001Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
/**
 * yaozhenguo 
 * @author Administrator
 *	20191228 战术配置优化
 */
@Controller
@PageId("TacticConfig001")
@RequestMapping("/invest/product/productplanning/tacticconfig/TacticConfig001")
public class TacticConfig001Controller extends BaseController{

	@Autowired
	private TacticConfig001Service tacticConfig001Service;
	
	@Override
	public String init(Model model) {
		setSelectOptionToModel(model,DictCode.修正场景);
		return super.init(model);
	}
	
	/**
	 * 
	 * 页面列表查询
	 * @return
	 */
	@ResponseBody
	@RequestMapping("selectForPage")
	public Page<TacticConfig001Dto> selectForPage(){
		return tacticConfig001Service.selectForPage(RequestUtil.getPageParam());
	}
	
	/**
	 * 导出
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("export")
	public ResponseEntity<byte[]> export() throws Exception {
		return tacticConfig001Service.export(this.getPageId(), RequestUtil.getParameters(getRequest()));
	}
	
	/**
	 * 删除
	 * 
	 * @param dto
	 * @param br
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/delete")
	public AjaxResult delete(@RequestBody @Validated({ ValidateGroupEdit.class }) TacticConfig001Dto dto,
			BindingResult br) throws Exception {
		if (br.hasErrors()) {
			return new AjaxResult(br);
		}
		ProcessParameter<TacticConfig001Dto> processParameter = new ProcessParameter<>();
		WorkFlowParameter<TacticConfig001Dto> params = new WorkFlowParameter<>();
		params.setPageId("TacticConfig002");
		// 生成战术配置ID
		if (StringUtil.isEmpty(dto.getTacticConfigCode())) {
			dto.setTacticConfigCode(IDGenerator.generate());
		}
		params.setElementId(dto.getElementId());
		params.setBusinessCode(item -> item.getTacticConfigCode());
		params.setBusinessType(DictCodeListExt3.共通业务类型_战术配置优化.getValue());
		params.setComment(entity -> this.getMessage("TacticConfig001.tacticConfigCode") + "("
				+ entity.getTacticConfigCode() + ")-" + this.getMessage("TacticConfig001.vdate") + "("
				+ DateUtil.format(entity.getVdate()) + ")-" + this.getMessage("TacticConfig001.edate") + "(" + DateUtil.format(entity.getEdate()) + ")");
		processParameter.setWorkFlowParameter(params);
		String msg = tacticConfig001Service.start(processParameter, dto);
		return new AjaxResult(msg);
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
	public AjaxResult check(@RequestBody @Validated({ ValidateGroupEdit.class }) TacticConfig001Dto dto,
			BindingResult result) {
		return commonCheck(result, dto.getOperateType());
	}
	
}
