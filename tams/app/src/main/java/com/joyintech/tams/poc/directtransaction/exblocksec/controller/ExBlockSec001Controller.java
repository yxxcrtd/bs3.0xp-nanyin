package com.joyintech.tams.poc.directtransaction.exblocksec.controller;


import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.tams.invest.common.constant.BussinessDictCode;
import com.joyintech.tams.poc.directtransaction.exblocksec.dto.ExBlockSec001Dto;
import com.joyintech.tams.poc.directtransaction.exblocksec.service.ExBlockSec001Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 债券大宗及固收交易
 * 〈功能详细描述〉
 * @author 
 * @version 1.0 2019年2月11日
 * @see ExBlockSec001Controller
 * @since 1.0
 */
@PageId("ExBlockSec001")
@RequestMapping("/invest/trade/exblocksec/ExBlockSec001")
@Controller
public class ExBlockSec001Controller extends BaseController{
	
	@Autowired
	private ExBlockSec001Service exBlockSec001Service;
	@Override
    public String init(Model model) {
		this.setSelectOptionToModel(model, BussinessDictCode.交易场所);
		this.setSelectOptionToModel(model, BussinessDictCode.交易平台);
		String entrustId = this.getParam("entrustId");
		
		ExBlockSec001Dto entity = exBlockSec001Service.search(entrustId);
		
		model.addAttribute("entity", entity);
          return super.init(model);
    }
}
