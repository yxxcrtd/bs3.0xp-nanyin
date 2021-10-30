package com.joyintech.tams.invest.trade.exchangetransmatch.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.tams.invest.trade.exchangetransmatch.service.ExchangeTransMatch001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@PageId("ExchangeTransMatch002")
@RequestMapping("/invest/trade/exchangetransmatch/ExchangeTransMatch002")
public class ExchangeTransMatch002Controller extends BaseController {

    /**
     * 成交匹配service
     */
    @Autowired
    private ExchangeTransMatch001Service exchangeTransMatch001Service;
    /**
     * 初始化方法
     * {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        return super.init(model);
    }

}
