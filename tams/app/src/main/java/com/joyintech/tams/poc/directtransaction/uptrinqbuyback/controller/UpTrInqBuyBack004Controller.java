package com.joyintech.tams.poc.directtransaction.uptrinqbuyback.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.RequestUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * 回购交易修改报价
 * 〈功能详细描述〉
 * @author 
 * @version 1.0 2018年12月18日
 * @see UpTrInqBuyBack004Controller
 * @since 1.0
 */
@PageId("UpTrInqBuyBack004")
@RequestMapping("/invest/trade/uptrinqbuyback/UpTrInqBuyBack004")
@Controller
public class UpTrInqBuyBack004Controller extends BaseController{
	@Override
    public String init(Model model) {

		Map<String, Object> map = RequestUtil.getPageParam();
		model.addAttribute("quotationNum", map.get("quotationNum"));
		model.addAttribute("tradeCounter", map.get("tradeCounter"));
		model.addAttribute("rivalTraders", map.get("rivalTraders"));
		model.addAttribute("bondNum", map.get("bondNum"));
		model.addAttribute("finprodName", "江苏银行股份有限公司2019年第020期同业存单");
		model.addAttribute("netPrice", map.get("netPrice"));
		model.addAttribute("yields", map.get("yields"));
		model.addAttribute("totalArea", map.get("totalArea"));
		model.addAttribute("proName", map.get("proName"));
		model.addAttribute("tradeDirection", map.get("tradeDirection"));
		model.addAttribute("unitIntAmt", "2.47");
		model.addAttribute("fullPrice", "102.17");
		model.addAttribute("tradeAmt", "2043400");
		model.addAttribute("intAmt", "49400");
		model.addAttribute("settleAmt", "2043400");
		model.addAttribute("repoRate", map.get("repoRate"));
		model.addAttribute("ccy", "人民币(CNY)");
		model.addAttribute("tradeMoney", map.get("tradeMoney"));
        return super.init(model);
    }
}
