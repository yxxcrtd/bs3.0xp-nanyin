package com.joyintech.service.product.forecast.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.service.product.forecast.dto.FinFlowForecastConfig;
import com.joyintech.service.product.forecast.dto.FinFlowForecastConfigKey;
import com.joyintech.service.product.forecast.dto.FinFlowForecastDealAmontDto;

 
 

@Repository(value = "serviceFlowForecastConfigMapper")
public interface FinFlowForecastConfigMapper {

    int deleteByPrimaryKey(FinFlowForecastConfigKey key);

    int insert(FinFlowForecastConfig record);
    
    int isExistFlowForecast(Map<String, Object> param);

    List<FinFlowForecastConfig> queryPageList(Map<String, Object> param);

    List<FinFlowForecastConfig> findReserveDetailList(Map<String, Object> param);
    
    List<FinFlowForecastDealAmontDto> findNetValueOpenforecast(Map<String, Object> param);
    
    List<FinFlowForecastDealAmontDto> queryProductRedemptionData(Map<String, Object> param);
}