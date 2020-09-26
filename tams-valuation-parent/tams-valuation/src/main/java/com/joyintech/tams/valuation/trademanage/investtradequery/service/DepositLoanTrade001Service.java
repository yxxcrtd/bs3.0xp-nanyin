package com.joyintech.tams.valuation.trademanage.investtradequery.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.trademanage.investtradequery.dto.DepositLoanTradeDto;
/**
 * 存借款交易查询-service
 * 
 * @author zhangzhao
 * @version 1.0 2018年7月25日
 * @see DepositLoanTrade001Service
 * @since 1.0
 */

@Service
public class DepositLoanTrade001Service extends BaseService<DepositLoanTradeDto> {
    
    
    public DepositLoanTradeDto findByTradeId(Map<String, Object> params) {
        return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME + "/DepositLoanTrade001/findByTradeId",
            params, DepositLoanTradeDto.class);
    }
    
}
