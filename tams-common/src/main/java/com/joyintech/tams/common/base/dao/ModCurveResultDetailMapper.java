package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ModCurveResultDetail;
import org.springframework.stereotype.Repository;

@Repository
public interface ModCurveResultDetailMapper {
    int insert(ModCurveResultDetail record);

    int insertSelective(ModCurveResultDetail record);

    int insertBasicByBasicInfo(ModCurveResultDetail record);
}