package com.joyintech.tams.invest.trade.exchangetransmatch.common.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.tams.invest.common.transmatch.dto.ExchangeTransMatchCommonDto;
import com.joyintech.tams.invest.trade.exchangetransmatch.common.service.ExchangeTransMatchCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/invest/trade/exchangetransmatch/ExchangeTransMatchCommon")
public class ExchangeTransMatchCommonController {
    @Autowired
    private ExchangeTransMatchCommonService exchangeTransMatchCommonService;

    /**
     * 批量处理
     * @param dtoList
     */
    @ResponseBody
    @RequestMapping("transactionMacthBatch")
    public void transactionMacthBatch(List<ExchangeTransMatchCommonDto> dtoList){
        exchangeTransMatchCommonService.transactionMacthBatch(dtoList);
    }
}
