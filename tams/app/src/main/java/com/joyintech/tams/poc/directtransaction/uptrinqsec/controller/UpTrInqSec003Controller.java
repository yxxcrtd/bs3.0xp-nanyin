package com.joyintech.tams.poc.directtransaction.uptrinqsec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;

/**
 * 现券买卖修改报价
 * 〈功能详细描述〉
 * @author 
 * @version 1.0 2018年12月18日
 * @see UpTrInqSec001Controller
 * @since 1.0
 */
@PageId("UpTrInqSec003")
@RequestMapping("/invest/trade/uptrinqsec/UpTrInqSec003")
@Controller
public class UpTrInqSec003Controller extends BaseController{
	
	@Override
    public String init(Model model) {
        return super.init(model);
    }
}
