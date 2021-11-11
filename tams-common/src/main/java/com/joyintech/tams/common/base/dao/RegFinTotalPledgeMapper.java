package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RegFinTotalPledge;
import com.joyintech.tams.common.base.entity.RegFinTotalPledgeKey;
import org.springframework.stereotype.Repository;

@Repository
public interface RegFinTotalPledgeMapper {
    int deleteByPrimaryKey(RegFinTotalPledgeKey key);

    int insert(RegFinTotalPledge record);

    int insertSelective(RegFinTotalPledge record);

    int insertBasicByBasicInfo(RegFinTotalPledge record);

    RegFinTotalPledge selectByPrimaryKey(RegFinTotalPledgeKey key);

    int updateByPrimaryKeySelective(RegFinTotalPledge record);

    int updateByPrimaryKey(RegFinTotalPledge record);
}