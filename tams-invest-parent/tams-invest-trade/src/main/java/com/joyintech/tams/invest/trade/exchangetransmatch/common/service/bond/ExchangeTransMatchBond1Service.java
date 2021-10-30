package com.joyintech.tams.invest.trade.exchangetransmatch.common.service.bond;

import com.joyintech.tams.invest.common.dto.BaseTradeProductDealDto;
import com.joyintech.tams.invest.common.transmatch.dto.ExchangeTransMatchCommonDto;
import com.joyintech.tams.invest.common.transmatch.dto.ExchangeTransMatchCommonEntrustDto;
import org.springframework.stereotype.Service;

/**
 * 债券买入、卖出、行权回售
 */
@Service
public class ExchangeTransMatchBond1Service extends ExchangeTransMatchBondCommonService {

    @Override
    public BaseTradeProductDealDto genFinProductTradeDto(ExchangeTransMatchCommonDto dealDto, ExchangeTransMatchCommonEntrustDto entrustDto){
        BaseTradeProductDealDto baseTradeProductDealDto = new BaseTradeProductDealDto();
        return baseTradeProductDealDto;
    }

}
