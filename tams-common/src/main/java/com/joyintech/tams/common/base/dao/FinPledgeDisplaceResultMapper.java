package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.FinPledgeDisplaceResult;
import com.joyintech.tams.common.base.entity.FinPledgeDisplaceResultKey;
import org.springframework.stereotype.Repository;

@Repository
public interface FinPledgeDisplaceResultMapper {
    int deleteByPrimaryKey(FinPledgeDisplaceResultKey key);

    int insert(FinPledgeDisplaceResult record);

    int insertSelective(FinPledgeDisplaceResult record);

    int insertBasicByBasicInfo(FinPledgeDisplaceResult record);

    FinPledgeDisplaceResult selectByPrimaryKey(FinPledgeDisplaceResultKey key);

    int updateByPrimaryKeySelective(FinPledgeDisplaceResult record);

    int updateByPrimaryKey(FinPledgeDisplaceResult record);
}