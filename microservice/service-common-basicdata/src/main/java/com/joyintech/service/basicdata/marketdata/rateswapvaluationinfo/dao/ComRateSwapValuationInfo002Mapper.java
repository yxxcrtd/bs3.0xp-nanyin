package com.joyintech.service.basicdata.marketdata.rateswapvaluationinfo.dao;

import java.util.Map;

import com.joyintech.service.basicdata.marketdata.rateswapvaluationinfo.dto.ComRateSwapValuationInfo002Dto;
import org.springframework.stereotype.Repository;

/**
 * @author zhangwei
 * @createTime 2019-07-26 11:23
 */
@Repository("serviceComRateSwapValuationInfo002Mapper")
public interface ComRateSwapValuationInfo002Mapper {


    ComRateSwapValuationInfo002Dto selectByPrimaryKey(Map<String, Object> map);
}
