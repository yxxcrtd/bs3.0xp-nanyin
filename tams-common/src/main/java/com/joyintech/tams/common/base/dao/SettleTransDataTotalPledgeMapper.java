package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SettleTransDataTotalPledge;
import org.springframework.stereotype.Repository;

@Repository
public interface SettleTransDataTotalPledgeMapper {
    int deleteByPrimaryKey(String tradeId);

    int insert(SettleTransDataTotalPledge record);

    int insertSelective(SettleTransDataTotalPledge record);

    int insertBasicByBasicInfo(SettleTransDataTotalPledge record);

    SettleTransDataTotalPledge selectByPrimaryKey(String tradeId);

    int updateByPrimaryKeySelective(SettleTransDataTotalPledge record);

    int updateByPrimaryKey(SettleTransDataTotalPledge record);
}