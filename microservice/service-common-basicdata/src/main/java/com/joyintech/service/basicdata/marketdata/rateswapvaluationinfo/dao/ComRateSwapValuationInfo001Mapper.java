package com.joyintech.service.basicdata.marketdata.rateswapvaluationinfo.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.basicdata.marketdata.rateswapvaluationinfo.dto.ComRateSwapValuationInfo001Dto;
import org.springframework.stereotype.Repository;

/**
 * @author zhangwei
 * @createTime 2019-07-26 11:23
 */
@Repository("serviceComRateSwapValuationInfo001Mapper")
public interface ComRateSwapValuationInfo001Mapper {

    List<ComRateSwapValuationInfo001Dto> selectList(Map<String, Object> params);

	List<SelectOptionDto> getFinprodId2(Map<String, Object> params);

}
