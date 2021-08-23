package com.joyintech.tams.invest.bankuplink.bond.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@PageId("CfetsBondDealQuotedPriceMuti")
@RequestMapping("/invest/bankuplink/bond/CfetsBondDealQuotedPriceMuti")
public class CfetsBondDealQuotedPriceMutiController extends BaseController {


    @Override
    public String init(Model model) {
        return super.init(model);
    }


}
