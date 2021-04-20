package com.joyintech.tams.invest.bankuplink.basic.errorcodeinfocompare.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.bankuplink.basic.errorcodeinfocompare.dto.ErrorCodeInfoCompare001Dto;
import com.joyintech.tams.invest.bankuplink.basic.errorcodeinfocompare.service.ErrorCodeInfoCompare001Service;

@Controller
@PageId("ErrorCodeInfoCompare002")
@RequestMapping("/invest/bankuplink/basic/errorcodeinfocompare/ErrorCodeInfoCompare002")
public class ErrorCodeInfoCompare002Controller extends BaseController{
	
	/**
	 * 错误码信息查询 Service
	 */
	@Autowired
	ErrorCodeInfoCompare001Service errorCodeInfoCompare001Service;	
	
	@Override
	public String init(Model model){
		String errCode = getParam("errCode");
		String workflowId = getParam("workflowId");
		if(!StringUtil.isNotEmpty(workflowId)){
			ErrorCodeInfoCompare001Dto entity = errorCodeInfoCompare001Service.selectByErrCode(errCode);
			model.addAttribute("entity", entity);
		}
		return super.init(model);
	}

}
