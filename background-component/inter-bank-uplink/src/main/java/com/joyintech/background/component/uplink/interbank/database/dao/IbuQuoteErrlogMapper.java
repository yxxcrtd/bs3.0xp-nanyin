package com.joyintech.background.component.uplink.interbank.database.dao;

import com.joyintech.background.component.uplink.interbank.database.entity.IbuQuoteErrlog;
import com.joyintech.background.component.uplink.interbank.database.entity.IbuQuoteErrlogKey;
import org.springframework.stereotype.Repository;

@Repository
public interface IbuQuoteErrlogMapper {
    int deleteByPrimaryKey(IbuQuoteErrlogKey key);

    int insert(IbuQuoteErrlog record);

    int insertSelective(IbuQuoteErrlog record);

    int insertBasicByBasicInfo(IbuQuoteErrlog record);

    IbuQuoteErrlog selectByPrimaryKey(IbuQuoteErrlogKey key);

    int updateByPrimaryKeySelective(IbuQuoteErrlog record);

    int updateByPrimaryKey(IbuQuoteErrlog record);
}