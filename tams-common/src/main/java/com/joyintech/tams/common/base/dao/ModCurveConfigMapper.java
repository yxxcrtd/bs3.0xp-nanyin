package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ModCurveConfig;
import org.springframework.stereotype.Repository;

@Repository
public interface ModCurveConfigMapper {
    int insert(ModCurveConfig record);

    int insertSelective(ModCurveConfig record);

    int insertBasicByBasicInfo(ModCurveConfig record);
}