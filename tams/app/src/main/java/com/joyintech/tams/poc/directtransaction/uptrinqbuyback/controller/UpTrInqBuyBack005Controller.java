package com.joyintech.tams.poc.directtransaction.uptrinqbuyback.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 回购交易回复报价
 * 〈功能详细描述〉
 * @author 
 * @version 1.0 2018年12月18日
 * @see UpTrInqBuyBack005Controller
 * @since 1.0
 */
@PageId("UpTrInqBuyBack005")
@RequestMapping("/invest/trade/uptrinqbuyback/UpTrInqBuyBack005")
@Controller
public class UpTrInqBuyBack005Controller extends BaseController{
	@Override
    public String init(Model model) {

          return super.init(model);
    }
}
