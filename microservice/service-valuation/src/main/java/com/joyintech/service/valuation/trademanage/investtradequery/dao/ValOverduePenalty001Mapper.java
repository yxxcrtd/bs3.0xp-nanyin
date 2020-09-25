package com.joyintech.service.valuation.trademanage.investtradequery.dao;

import java.util.Map;
import org.springframework.stereotype.Repository;
import com.joyintech.service.valuation.trademanage.investtradequery.dto.OverduePenaltyDto;

/**
 * 逾期交易查询
 * @author tanxuan
 * @version 1.0 2019年12月23日
 * @see ValOverduePenalty001Mapper
 * @since 1.0
 */
@Repository(value="ValOverduePenalty001Mapper")
public interface ValOverduePenalty001Mapper {
    
	OverduePenaltyDto selectByTradeId(Map<String, Object> params);

}
