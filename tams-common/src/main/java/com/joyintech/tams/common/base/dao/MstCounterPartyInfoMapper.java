package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.MstCounterPartyInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface MstCounterPartyInfoMapper {
    int insert(MstCounterPartyInfo record);

    int insertSelective(MstCounterPartyInfo record);

    int insertBasicByBasicInfo(MstCounterPartyInfo record);
}