package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.FinPledgeDispTemp;
import org.springframework.stereotype.Repository;

@Repository
public interface FinPledgeDispTempMapper {
    int deleteByPrimaryKey(String tradeId);

    int insert(FinPledgeDispTemp record);

    int insertSelective(FinPledgeDispTemp record);

    int insertBasicByBasicInfo(FinPledgeDispTemp record);

    FinPledgeDispTemp selectByPrimaryKey(String tradeId);

    int updateByPrimaryKeySelective(FinPledgeDispTemp record);

    int updateByPrimaryKey(FinPledgeDispTemp record);
}