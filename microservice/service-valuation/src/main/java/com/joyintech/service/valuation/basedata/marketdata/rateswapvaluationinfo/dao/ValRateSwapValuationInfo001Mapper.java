package com.joyintech.service.valuation.basedata.marketdata.rateswapvaluationinfo.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.basedata.marketdata.rateswapvaluationinfo.dto.ValRateSwapValuationInfo001Dto;
import org.springframework.stereotype.Repository;

/**
 * @author zhangwei
 * @createTime 2019-07-26 11:23
 */
@Repository("serviceValRateSwapValuationInfo001Mapper")
public interface ValRateSwapValuationInfo001Mapper {

    List<ValRateSwapValuationInfo001Dto> selectList(Map<String, Object> params);

	List<SelectOptionDto> getFinprodId2(Map<String, Object> params);

}
