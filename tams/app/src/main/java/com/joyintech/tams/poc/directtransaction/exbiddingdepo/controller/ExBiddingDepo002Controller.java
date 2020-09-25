package com.joyintech.tams.poc.directtransaction.exbiddingdepo.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 回购竞价交易下单
 * 〈功能详细描述〉
 * @author 
 * @version 1.0 2019年2月11日
 * @see ExBiddingDepo002Controller
 * @since 1.0
 */
@PageId("ExBiddingDepo002")
@RequestMapping("/invest/trade/exbiddingdepo/ExBiddingDepo002")
@Controller
public class ExBiddingDepo002Controller extends BaseController{
	@Override
    public String init(Model model) {

          return super.init(model);
    }
}
