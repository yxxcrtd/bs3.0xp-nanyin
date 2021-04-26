package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SettleTransDataPledge;
import org.springframework.stereotype.Repository;

@Repository
public interface SettleTransDataPledgeMapper {
    int deleteByPrimaryKey(String tradeId);

    int insert(SettleTransDataPledge record);

    int insertSelective(SettleTransDataPledge record);

    int insertBasicByBasicInfo(SettleTransDataPledge record);

    SettleTransDataPledge selectByPrimaryKey(String tradeId);

    int updateByPrimaryKeySelective(SettleTransDataPledge record);

    int updateByPrimaryKey(SettleTransDataPledge record);
}