package com.joyintech.tams.disclosure.information.manage.estimatereveal.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.tams.disclosure.information.manage.estimatereveal.dto.EstimateReveal001Dto;

public interface EstimateReveal001Mapper {

	List<EstimateReveal001Dto> findPage(Map<String, Object> params);

}
