package com.joyintech.service.valuation.trademanage.investtradequery.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.service.valuation.trademanage.investtradequery.dao.ValOverduePenalty001Mapper;
import com.joyintech.service.valuation.trademanage.investtradequery.dto.OverduePenaltyDto;


/**
 *  逾期交易查询
 * @author tanxuan
 * @version 1.0 2019年12月23日
 * @see ValOverduePenalty001Service
 * @since 1.0
 */
@Service(value="serviceValOverduePenalty001Service")
public class ValOverduePenalty001Service {

    @Autowired
    private ValOverduePenalty001Mapper valOverduePenalty001Mapper;
    
    
    public OverduePenaltyDto findByTradeId(Map<String, Object> params) {
        return valOverduePenalty001Mapper.selectByTradeId(params);
    }
    
}
