package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.FlowForecastConfig;
import com.joyintech.tams.common.base.entity.FlowForecastConfigKey;
import org.springframework.stereotype.Repository;

@Repository
public interface FlowForecastConfigMapper {
    int deleteByPrimaryKey(FlowForecastConfigKey key);

    int insert(FlowForecastConfig record);

    int insertSelective(FlowForecastConfig record);

    int insertBasicByBasicInfo(FlowForecastConfig record);

    FlowForecastConfig selectByPrimaryKey(FlowForecastConfigKey key);

    int updateByPrimaryKeySelective(FlowForecastConfig record);

    int updateByPrimaryKey(FlowForecastConfig record);
}