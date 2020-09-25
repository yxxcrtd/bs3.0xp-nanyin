package com.joyintech.service.valuation.basedata.marketdata.rateswapvaluationinfo.dao;

import java.util.Map;

import com.joyintech.service.valuation.basedata.marketdata.rateswapvaluationinfo.dto.ValRateSwapValuationInfo002Dto;
import org.springframework.stereotype.Repository;

/**
 * @author zhangwei
 * @createTime 2019-07-26 11:23
 */
@Repository("serviceValRateSwapValuationInfo002Mapper")
public interface ValRateSwapValuationInfo002Mapper {


    ValRateSwapValuationInfo002Dto selectByPrimaryKey(Map<String, Object> map);
}
