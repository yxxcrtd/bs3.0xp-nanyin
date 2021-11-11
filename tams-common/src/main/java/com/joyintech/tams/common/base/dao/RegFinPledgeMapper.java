package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RegFinPledge;
import com.joyintech.tams.common.base.entity.RegFinPledgeKey;
import org.springframework.stereotype.Repository;

@Repository
public interface RegFinPledgeMapper {
    int deleteByPrimaryKey(RegFinPledgeKey key);

    int insert(RegFinPledge record);

    int insertSelective(RegFinPledge record);

    int insertBasicByBasicInfo(RegFinPledge record);

    RegFinPledge selectByPrimaryKey(RegFinPledgeKey key);

    int updateByPrimaryKeySelective(RegFinPledge record);

    int updateByPrimaryKey(RegFinPledge record);
}