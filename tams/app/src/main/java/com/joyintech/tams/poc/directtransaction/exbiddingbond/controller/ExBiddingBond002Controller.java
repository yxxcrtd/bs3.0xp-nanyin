package com.joyintech.tams.poc.directtransaction.exbiddingbond.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 股票竞价交易下单
 * 〈功能详细描述〉
 * @author 
 * @version 1.0 2019年2月11日
 * @see ExBiddingBond002Controller
 * @since 1.0
 */
@PageId("ExBiddingBond002")
@RequestMapping("/invest/trade/exbiddingbond/ExBiddingBond002")
@Controller
public class ExBiddingBond002Controller extends BaseController{
	
	@Override
    public String init(Model model) {

          return super.init(model);
    }
}
