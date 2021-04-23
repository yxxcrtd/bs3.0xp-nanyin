package com.joyintech.background.component.uplink.interbank.database.dao;

import com.joyintech.background.component.uplink.interbank.database.entity.IbuQuoteEntCrepPledgeHis;
import com.joyintech.background.component.uplink.interbank.database.entity.IbuQuoteEntCrepPledgeHisKey;
import org.springframework.stereotype.Repository;

@Repository
public interface IbuQuoteEntCrepPledgeHisMapper {
    int deleteByPrimaryKey(IbuQuoteEntCrepPledgeHisKey key);

    int insert(IbuQuoteEntCrepPledgeHis record);

    int insertSelective(IbuQuoteEntCrepPledgeHis record);

    int insertHisByBasicInfo(IbuQuoteEntCrepPledgeHis record);

    IbuQuoteEntCrepPledgeHis selectByPrimaryKey(IbuQuoteEntCrepPledgeHisKey key);

    int updateByPrimaryKeySelective(IbuQuoteEntCrepPledgeHis record);

    int updateByPrimaryKey(IbuQuoteEntCrepPledgeHis record);
}