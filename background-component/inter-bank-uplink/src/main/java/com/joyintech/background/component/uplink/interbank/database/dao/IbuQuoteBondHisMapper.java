package com.joyintech.background.component.uplink.interbank.database.dao;

import com.joyintech.background.component.uplink.interbank.database.entity.IbuQuoteBondHis;
import com.joyintech.background.component.uplink.interbank.database.entity.IbuQuoteBondHisKey;
import org.springframework.stereotype.Repository;

@Repository
public interface IbuQuoteBondHisMapper {
    int deleteByPrimaryKey(IbuQuoteBondHisKey key);

    int insert(IbuQuoteBondHis record);

    int insertSelective(IbuQuoteBondHis record);

    int insertHisByBasicInfo(IbuQuoteBondHis record);

    IbuQuoteBondHis selectByPrimaryKey(IbuQuoteBondHisKey key);

    int updateByPrimaryKeySelective(IbuQuoteBondHis record);

    int updateByPrimaryKey(IbuQuoteBondHis record);
}