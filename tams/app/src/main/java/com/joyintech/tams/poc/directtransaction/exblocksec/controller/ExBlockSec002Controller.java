package com.joyintech.tams.poc.directtransaction.exblocksec.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 债券大宗及固收交易下单
 * 〈功能详细描述〉
 * @author 
 * @version 1.0 2019年2月11日
 * @see ExBlockSec002Controller
 * @since 1.0
 */
@PageId("ExBlockSec002")
@RequestMapping("/invest/trade/exblocksec/ExBlockSec002")
@Controller
public class ExBlockSec002Controller extends BaseController{
	@Override
    public String init(Model model) {

          return super.init(model);
    }
}
