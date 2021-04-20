package com.joyintech.background.component.uplink.interbank.database.dao;

import com.joyintech.background.component.uplink.interbank.database.entity.IbuQuoteEntCrepPledge;
import com.joyintech.background.component.uplink.interbank.database.entity.IbuQuoteEntCrepPledgeKey;
import org.springframework.stereotype.Repository;

@Repository
public interface IbuQuoteEntCrepPledgeMapper {
    int deleteByPrimaryKey(IbuQuoteEntCrepPledgeKey key);

    int insert(IbuQuoteEntCrepPledge record);

    int insertSelective(IbuQuoteEntCrepPledge record);

    int insertBasicByBasicInfo(IbuQuoteEntCrepPledge record);

    IbuQuoteEntCrepPledge selectByPrimaryKey(IbuQuoteEntCrepPledgeKey key);

    int updateByPrimaryKeySelective(IbuQuoteEntCrepPledge record);

    int updateByPrimaryKey(IbuQuoteEntCrepPledge record);
}