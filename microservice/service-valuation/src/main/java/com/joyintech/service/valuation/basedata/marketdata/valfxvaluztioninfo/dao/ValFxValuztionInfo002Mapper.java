package com.joyintech.service.valuation.basedata.marketdata.valfxvaluztioninfo.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.valuation.basedata.marketdata.valfxvaluztioninfo.dto.ValFxValuztionInfo002Dto;
import org.springframework.stereotype.Repository;

/**
 * @author zhangwei
 * @createTime 2019-07-26 11:23
 */
@Repository("serviceValFxValuztionInfo002Mapper")
public interface ValFxValuztionInfo002Mapper {


    ValFxValuztionInfo002Dto selectByPrimaryKey(Map<String, Object> map);

	String getForeignexChangeType(Map<String, Object> params);

	List<SuggestDto> getFinprodId2(Map<String, Object> params);
	
}
