package com.joyintech.background.component.uplink.interbank.database.dao;

import com.joyintech.background.component.uplink.interbank.database.entity.IbuQuoteBond;
import org.springframework.stereotype.Repository;

@Repository
public interface IbuQuoteBondMapper {
    int deleteByPrimaryKey(String quoteId);

    int insert(IbuQuoteBond record);

    int insertSelective(IbuQuoteBond record);

    int insertBasicByBasicInfo(IbuQuoteBond record);

    IbuQuoteBond selectByPrimaryKey(String quoteId);

    int updateByPrimaryKeySelective(IbuQuoteBond record);

    int updateByPrimaryKey(IbuQuoteBond record);
}