package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.InterfBonusInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfBonusInfoMapper {
    int insert(InterfBonusInfo record);

    int insertSelective(InterfBonusInfo record);

    int insertBasicByBasicInfo(InterfBonusInfo record);
}