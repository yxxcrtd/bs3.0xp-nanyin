package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokMatchRlat;
import org.springframework.stereotype.Repository;

@Repository
public interface BokMatchRlatMapper {
    int insert(BokMatchRlat record);

    int insertSelective(BokMatchRlat record);

    int insertBasicByBasicInfo(BokMatchRlat record);
}