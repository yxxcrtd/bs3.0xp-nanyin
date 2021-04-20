package com.joyintech.background.component.uplink.interbank.database.dao;

import com.joyintech.background.component.uplink.interbank.database.entity.IbuQuoteBondDetHis;
import com.joyintech.background.component.uplink.interbank.database.entity.IbuQuoteBondDetHisKey;
import org.springframework.stereotype.Repository;

@Repository
public interface IbuQuoteBondDetHisMapper {
    int deleteByPrimaryKey(IbuQuoteBondDetHisKey key);

    int insert(IbuQuoteBondDetHis record);

    int insertSelective(IbuQuoteBondDetHis record);

    int insertHisByBasicInfo(IbuQuoteBondDetHis record);

    IbuQuoteBondDetHis selectByPrimaryKey(IbuQuoteBondDetHisKey key);

    int updateByPrimaryKeySelective(IbuQuoteBondDetHis record);

    int updateByPrimaryKey(IbuQuoteBondDetHis record);
}