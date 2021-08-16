package com.joyintech.service.basicdata.commarketmanagement.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.basicdata.commarketmanagement.dto.ComMarketManageDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository 
public interface ComMarketManagementMapper {

    List<ComMarketManageDto> queryForPage(Map<String, Object> pageParam);

 
	List<SuggestDto> selectFinprodName(Map<String, Object> param);

 
    List<SelectOptionDto> getGradeResult(Map<String, Object> param);
	 

	 


	 


	 

}
