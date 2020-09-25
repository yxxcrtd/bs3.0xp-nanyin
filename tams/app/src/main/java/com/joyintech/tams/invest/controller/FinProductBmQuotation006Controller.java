package com.joyintech.tams.invest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
@Controller
@PageId(value ="FinProductBmQuotation006")
@RequestMapping("/invest/product/messageadjust/bmquotation/FinProductBmQuotation006")
public class FinProductBmQuotation006Controller extends BaseController{
	   @Override
	    public String init(Model model) {
	        String finprodId = this.getParam("finprodId");

	        return super.init(model);
	    }
}
