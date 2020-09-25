package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokPositionPrdfee;
import org.springframework.stereotype.Repository;

@Repository
public interface BokPositionPrdfeeMapper {
    int insert(BokPositionPrdfee record);

    int insertSelective(BokPositionPrdfee record);

    int insertBasicByBasicInfo(BokPositionPrdfee record);
}