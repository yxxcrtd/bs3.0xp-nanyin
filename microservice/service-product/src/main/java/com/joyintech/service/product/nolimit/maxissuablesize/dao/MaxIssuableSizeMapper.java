package com.joyintech.service.product.nolimit.maxissuablesize.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.nolimit.maxissuablesize.dto.MaxIssuableSizeDto;

@Repository
public interface MaxIssuableSizeMapper {
	/**
	 * 分页查询
	 * @param pageParam
	 * @return
	 */
	List<MaxIssuableSizeDto>selectForPage(Map<String, Object> pageParam);
	
	/**
	 * 产品规划系列下拉联想框
	 * @param map
	 * @return
	 */
	List<SelectOptionDto> queryPrdPlanSerisIdInfo(Map<String, Object> pageParam);

}
