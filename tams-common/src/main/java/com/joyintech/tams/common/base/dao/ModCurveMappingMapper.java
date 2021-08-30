package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ModCurveMapping;
import org.springframework.stereotype.Repository;

@Repository
public interface ModCurveMappingMapper {
    int insert(ModCurveMapping record);

    int insertSelective(ModCurveMapping record);

    int insertBasicByBasicInfo(ModCurveMapping record);
}