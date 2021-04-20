package com.joyintech.background.component.uplink.interbank.database.dao;

import com.joyintech.background.component.uplink.interbank.database.entity.IbuQuoteEtrReHis;
import com.joyintech.background.component.uplink.interbank.database.entity.IbuQuoteEtrReHisKey;
import org.springframework.stereotype.Repository;

@Repository
public interface IbuQuoteEtrReHisMapper {
    int deleteByPrimaryKey(IbuQuoteEtrReHisKey key);

    int insert(IbuQuoteEtrReHis record);

    int insertSelective(IbuQuoteEtrReHis record);

    int insertHisByBasicInfo(IbuQuoteEtrReHis record);

    IbuQuoteEtrReHis selectByPrimaryKey(IbuQuoteEtrReHisKey key);

    int updateByPrimaryKeySelective(IbuQuoteEtrReHis record);

    int updateByPrimaryKey(IbuQuoteEtrReHis record);
}