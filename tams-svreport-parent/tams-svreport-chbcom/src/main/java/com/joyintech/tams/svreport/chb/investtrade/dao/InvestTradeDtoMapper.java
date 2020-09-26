package com.joyintech.tams.svreport.chb.investtrade.dao;

import com.joyintech.tams.svreport.chb.investtrade.dto.InvestTradeDto;

public interface InvestTradeDtoMapper {
    int deleteByPrimaryKey(String tradeSeqno);

    int insert(InvestTradeDto record);

    int insertSelective(InvestTradeDto record);

    InvestTradeDto selectByPrimaryKey(String tradeSeqno);

    int updateByPrimaryKeySelective(InvestTradeDto record);

    int updateByPrimaryKey(InvestTradeDto record);
}