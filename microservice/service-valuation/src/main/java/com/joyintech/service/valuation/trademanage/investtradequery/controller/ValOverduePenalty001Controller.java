package com.joyintech.service.valuation.trademanage.investtradequery.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.base.BaseController;
import com.joyintech.service.valuation.trademanage.investtradequery.dto.OverduePenaltyDto;
import com.joyintech.service.valuation.trademanage.investtradequery.service.ValOverduePenalty001Service;

/**
 * 查看逾期交易
 * @author tanxuan
 * @version 1.0 2019年12月23日
 * @see ValOverduePenalty001Controller
 * @since 1.0
 */
@RestController(value="serviceValOverduePenalty001Controller")
@RequestMapping("/ValOverduePenalty001")
public class ValOverduePenalty001Controller extends BaseController{

    @Autowired
    private ValOverduePenalty001Service valOverduePenalty001Service;

    @PostMapping("/findByTradeId")
    public OverduePenaltyDto findByTradeId(@RequestBody Map<String, Object> param) throws Exception {
        return valOverduePenalty001Service.findByTradeId(param);
    }
    
    
}
