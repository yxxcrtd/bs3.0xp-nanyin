package com.joyintech.tams.invest.trade.exchangetransmatch.manualmatch.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.tams.invest.trade.exchangetransmatch.manualmatch.dto.ExchangeTransMatch002Dto;
import com.joyintech.tams.invest.trade.exchangetransmatch.manualmatch.dto.ExchangeTransMatch003Dto;
import com.joyintech.tams.invest.trade.exchangetransmatch.manualmatch.service.ExchangeTransMatch001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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
        String seqNo = this.getParam("seqNo");
        List<ExchangeTransMatch003Dto> entrustList = exchangeTransMatch001Service.findEntrustList(seqNo);
        model.addAttribute("entrustList",entrustList);
        List<ExchangeTransMatch002Dto> dealList = exchangeTransMatch001Service.findDealList(seqNo);
        model.addAttribute("dealList",dealList);
        return super.init(model);
    }

}
