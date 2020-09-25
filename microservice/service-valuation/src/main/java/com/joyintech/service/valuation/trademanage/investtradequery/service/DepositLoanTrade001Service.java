package com.joyintech.service.valuation.trademanage.investtradequery.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.service.valuation.trademanage.investtradequery.dao.DepositLoanTrade001Mapper;
import com.joyintech.service.valuation.trademanage.investtradequery.dto.DepositLoanTradeDto;
/**
 * 存借款交易查询-service
 * 
 * @author zhangzhao
 * @version 1.0 2018年7月25日
 * @see DepositLoanTrade001Service
 * @since 1.0
 */

@Service(value="serviceDepositLoanTrade001Service")
public class DepositLoanTrade001Service {

    @Autowired
    private DepositLoanTrade001Mapper depositLoanTrade001Mapper;
    
    
    public DepositLoanTradeDto findByTradeId(Map<String, Object> params) {
        return depositLoanTrade001Mapper.selectByTradeId(params);
    }
    
}
