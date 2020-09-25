package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.FinPledgeDisplace;
import org.springframework.stereotype.Repository;

@Repository
public interface FinPledgeDisplaceMapper {
    int deleteByPrimaryKey(String tradeId);

    int insert(FinPledgeDisplace record);

    int insertSelective(FinPledgeDisplace record);

    int insertBasicByBasicInfo(FinPledgeDisplace record);

    FinPledgeDisplace selectByPrimaryKey(String tradeId);

    int updateByPrimaryKeySelective(FinPledgeDisplace record);

    int updateByPrimaryKey(FinPledgeDisplace record);
}