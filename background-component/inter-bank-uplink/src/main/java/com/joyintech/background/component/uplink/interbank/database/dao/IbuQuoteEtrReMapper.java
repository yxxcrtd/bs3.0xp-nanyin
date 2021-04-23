package com.joyintech.background.component.uplink.interbank.database.dao;

import com.joyintech.background.component.uplink.interbank.database.entity.IbuQuoteEtrRe;
import com.joyintech.background.component.uplink.interbank.database.entity.IbuQuoteEtrReKey;
import org.springframework.stereotype.Repository;

@Repository
public interface IbuQuoteEtrReMapper {
    int deleteByPrimaryKey(IbuQuoteEtrReKey key);

    int insert(IbuQuoteEtrRe record);

    int insertSelective(IbuQuoteEtrRe record);

    int insertBasicByBasicInfo(IbuQuoteEtrRe record);

    IbuQuoteEtrRe selectByPrimaryKey(IbuQuoteEtrReKey key);

    int updateByPrimaryKeySelective(IbuQuoteEtrRe record);

    int updateByPrimaryKey(IbuQuoteEtrRe record);
}