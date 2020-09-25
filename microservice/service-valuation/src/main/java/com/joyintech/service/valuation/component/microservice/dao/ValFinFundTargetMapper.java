package com.joyintech.service.valuation.component.microservice.dao;

import java.util.Map;

import com.joyintech.service.valuation.component.microservice.dto.ValBussFinIntPayDto;

public interface ValFinFundTargetMapper {
	
	/**
     * 给分红计划组件赋值
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    ValBussFinIntPayDto getIntPayByFinprodId(Map<String,Object> param);

}
