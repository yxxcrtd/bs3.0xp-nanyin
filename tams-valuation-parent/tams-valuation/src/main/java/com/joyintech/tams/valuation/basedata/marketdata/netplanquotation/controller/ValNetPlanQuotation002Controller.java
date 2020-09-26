/*
 * 文件名：ValNetPlanQuotation002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.basedata.marketdata.netplanquotation.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.valuation.basedata.marketdata.netplanquotation.dto.ValNetPlanQuotation002Dto;
import com.joyintech.tams.valuation.basedata.marketdata.netplanquotation.service.ValNetPlanQuotation002Service;

/**
 *  计划类资产净值信息双击查看
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author lishicheng
 * @version 1.0 2018年10月15日
 * @see ValNetPlanQuotation002Controller
 * @since 1.0
 */
@Controller
@PageId("ValNetPlanQuotation002")
@RequestMapping("/valuation/basedata/marketdata/netplanquotation/ValNetPlanQuotation002")
public class ValNetPlanQuotation002Controller  extends BaseController {
	
	
	@Autowired
	private ValNetPlanQuotation002Service  valNetPlanQuotation002Service;
	
	
	@Override
	public String init(Model model) {
		//this.setSelectOptionToModel(model, DictCode.估值来源);
		String finprodId = this.getParam("finprodId");
	    //行情日期
		String valueDate = this.getParam("valueDate");
	    model.addAttribute("finprodId", finprodId);
	    model.addAttribute("valueDate",(DateUtil.format(DateUtil.parse(valueDate),"yyyy-MM-dd")));    
		return super.init(model);
	}
	
	@RequestMapping("searchList")
	@ResponseBody
	public List<ValNetPlanQuotation002Dto> searchList() {
		ValNetPlanQuotation002Dto dto = new ValNetPlanQuotation002Dto();
		//计划类资产净值信息资产代码
		dto.setFinprodId(this.getParam("finprodId"));
//		String finprodId = this.getParam("finprodId");
	    //行情日期
		dto.setValueDate(this.getParam("valueDate"));
//		String valueDate = this.getParam("valueDate");
		
		return valNetPlanQuotation002Service.searchList(dto);
	}

}
