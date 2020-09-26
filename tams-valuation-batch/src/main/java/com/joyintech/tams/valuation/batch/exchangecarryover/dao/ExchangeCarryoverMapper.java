package com.joyintech.tams.valuation.batch.exchangecarryover.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.tams.valuation.batch.exchangecarryover.dto.ExchangeCarryoverEntity;
import com.joyintech.tams.valuation.batch.exchangecarryover.dto.InterBalanceDto;
import com.joyintech.tams.valuation.batch.profitandlossaccounting.dto.ProfitAndLossAccountingSubject;

/**
 * 汇兑损益结转
 *
 * @author zhangwei
 * @createTime 2019-07-08 15:17
 */
public interface ExchangeCarryoverMapper {

    List<ExchangeCarryoverEntity> selectBooksetId(Map<String, Object> map);

    List<InterBalanceDto> getInterBalance(Map<String, Object> interParam);

    List<ProfitAndLossAccountingSubject> getExBalance(Map<String, Object> losParam);
}
