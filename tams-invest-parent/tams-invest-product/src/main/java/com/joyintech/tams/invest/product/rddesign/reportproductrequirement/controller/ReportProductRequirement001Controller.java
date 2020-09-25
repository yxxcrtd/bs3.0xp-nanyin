package com.joyintech.tams.invest.product.rddesign.reportproductrequirement.controller;

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
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.product.rddesign.colproductrequirement.dto.ColProductRequirement001Dto;
import com.joyintech.tams.invest.product.rddesign.reportproductrequirement.dto.ReportProductRequirement001Dto;
import com.joyintech.tams.invest.product.rddesign.reportproductrequirement.service.ReportProductRequirement001Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
/**
 * 产品需求收集controller
 * 
 * @author yaozhenguo
 * @version 1.0 2019年9月27日
 * @see ColProductRequirement001Dto
 * @since 1.0
 */
@Controller
@PageId(value = "ReportProductRequirement001")
@RequestMapping("/invest/product/rddesign/reportproductrequirement/ReportProductRequirement001")
public class ReportProductRequirement001Controller extends BaseController {
	
	@Autowired
	private ReportProductRequirement001Service reportProductRequirement001Service;
	
	@Override
	public String init(Model model) {
		model.addAttribute("dataStatus_effective_no",DictCodeListExt1.有效状态_待处理.getValue());
		setSelectOptionToModel(model, DictCode.有效状态,DictCode.是否,DictCode.需求发起端,DictCode.数据状态,DictCode.初评结果);
		return super.init(model);
	}
	
	/**
	 * 页面查询
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/selectForPage")
	public Page<ReportProductRequirement001Dto> selectForPage() {
		Page<ReportProductRequirement001Dto> page = reportProductRequirement001Service.selectForPage(RequestUtil.getPageParam());
		return page;
	}
	
	/**
     * 导出信息excel
     * 
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception {
        return reportProductRequirement001Service.export(this.getPageId(),
            RequestUtil.getParameters(getRequest()));
    }
    
}
