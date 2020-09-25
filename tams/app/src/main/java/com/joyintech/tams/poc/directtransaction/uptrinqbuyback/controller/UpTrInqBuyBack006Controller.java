package com.joyintech.tams.poc.directtransaction.uptrinqbuyback.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 添加质押券
 * 〈功能详细描述〉
 * @author 
 * @version 1.0 2018年12月18日
 * @see UpTrInqBuyBack006Controller
 * @since 1.0
 */
@PageId("UpTrInqBuyBack006")
@RequestMapping("/invest/trade/uptrinqbuyback/UpTrInqBuyBack006")
@Controller
public class UpTrInqBuyBack006Controller extends BaseController{
	@Override
    public String init(Model model) {

          return super.init(model);
    }
}
