package com.joyintech.service.disclosure.information.manage.estimatereveal.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.service.disclosure.information.manage.estimatereveal.dto.EstimateReveal001Dto;

@Repository(value="serviceEstimateReveal001Mapper")
public interface EstimateReveal001Mapper {

	List<EstimateReveal001Dto> findPage(Map<String, Object> params);

}
