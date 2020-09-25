package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.InterfAptmtBuyRedemInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfAptmtBuyRedemInfoMapper {
    int insert(InterfAptmtBuyRedemInfo record);

    int insertSelective(InterfAptmtBuyRedemInfo record);

    int insertBasicByBasicInfo(InterfAptmtBuyRedemInfo record);
}