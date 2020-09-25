package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.EtrEntrustPledge;
import com.joyintech.tams.common.base.entity.EtrEntrustPledgeKey;
import org.springframework.stereotype.Repository;

@Repository
public interface EtrEntrustPledgeMapper {
    int deleteByPrimaryKey(EtrEntrustPledgeKey key);

    int insert(EtrEntrustPledge record);

    int insertSelective(EtrEntrustPledge record);

    int insertBasicByBasicInfo(EtrEntrustPledge record);

    EtrEntrustPledge selectByPrimaryKey(EtrEntrustPledgeKey key);

    int updateByPrimaryKeySelective(EtrEntrustPledge record);

    int updateByPrimaryKey(EtrEntrustPledge record);
}