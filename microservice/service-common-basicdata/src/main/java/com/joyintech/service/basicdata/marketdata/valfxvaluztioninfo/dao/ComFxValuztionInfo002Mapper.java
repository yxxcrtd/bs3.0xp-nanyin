package com.joyintech.service.basicdata.marketdata.valfxvaluztioninfo.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.basicdata.marketdata.valfxvaluztioninfo.dto.ComFxValuztionInfo002Dto;
import org.springframework.stereotype.Repository;

/**
 * @author zhangwei
 * @createTime 2019-07-26 11:23
 */
@Repository("serviceComFxValuztionInfo002Mapper")
public interface ComFxValuztionInfo002Mapper {


    ComFxValuztionInfo002Dto selectByPrimaryKey(Map<String, Object> map);

	String getForeignexChangeType(Map<String, Object> params);

	List<SuggestDto> getFinprodId2(Map<String, Object> params);
	
}
