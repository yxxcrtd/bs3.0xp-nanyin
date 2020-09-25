package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.InterfOpicsTradHis;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfOpicsTradHisMapper {
    int insert(InterfOpicsTradHis record);

    int insertSelective(InterfOpicsTradHis record);

    int insertHisByBasicInfo(InterfOpicsTradHis record);
}