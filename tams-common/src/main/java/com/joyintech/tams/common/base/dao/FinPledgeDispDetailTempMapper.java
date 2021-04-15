package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.FinPledgeDispDetailTemp;
import org.springframework.stereotype.Repository;

@Repository
public interface FinPledgeDispDetailTempMapper {
    int deleteByPrimaryKey(String seqNo);

    int insert(FinPledgeDispDetailTemp record);

    int insertSelective(FinPledgeDispDetailTemp record);

    int insertBasicByBasicInfo(FinPledgeDispDetailTemp record);

    FinPledgeDispDetailTemp selectByPrimaryKey(String seqNo);

    int updateByPrimaryKeySelective(FinPledgeDispDetailTemp record);

    int updateByPrimaryKey(FinPledgeDispDetailTemp record);
}