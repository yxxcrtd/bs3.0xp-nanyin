package com.joyintech.tams.poc.directtransaction.uptrquotedsec.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.RequestUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * 请求报价确认
 * 〈功能详细描述〉
 * @author 
 * @version 1.0 2018年12月18日
 * @see
 * @since 1.0
 */
@PageId("UpTrQuoTedSec003")
@RequestMapping("/invest/trade/uptrquotedsec/UpTrQuoTedSec003")
@Controller
public class UpTrQuoTedSec003Controller extends BaseController{
	@Override
    public String init(Model model) {
		Map<String, Object> map = RequestUtil.getPageParam();
		model.addAttribute("quotationNum", map.get("quotationNum"));
		model.addAttribute("tradeCounter", map.get("tradeCounter"));
		model.addAttribute("rivalTraders", map.get("rivalTraders"));
		model.addAttribute("bondNum", map.get("bondNum"));
		model.addAttribute("finprodName", "江苏银行股份有限公司2019年第020期同业存单");
		model.addAttribute("totalArea", map.get("totalArea"));
		model.addAttribute("proName", map.get("proName"));
		model.addAttribute("unitIntAmt", "2.47");
		model.addAttribute("fullPrice", "102.17");
		model.addAttribute("tradeAmt", "2043400");
		model.addAttribute("intAmt", "49400");
		model.addAttribute("settleAmt", "2043400");
		model.addAttribute("repoRate", map.get("repoRate"));
        return super.init(model);
    }
}
