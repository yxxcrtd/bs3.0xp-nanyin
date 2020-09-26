package com.joyintech.tams.valuation.trademanage.investtradequery.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.trademanage.investtradequery.dto.OverduePenaltyDto;

/**
 * 查看逾期交易
 * @author tanxuan
 * @version 1.0 2019年12月23日
 * @see ValOverduePenalty001Service
 * @since 1.0
 */
@Service
public class ValOverduePenalty001Service extends BaseService<OverduePenaltyDto> {

    public OverduePenaltyDto findByTradeId(Map<String, Object> params) {
        return restTemplate.postForObject(
            ServiceApplication.VALUATION_SERVICE_NAME + "/ValOverduePenalty001/findByTradeId", params, OverduePenaltyDto.class);
    }

}
