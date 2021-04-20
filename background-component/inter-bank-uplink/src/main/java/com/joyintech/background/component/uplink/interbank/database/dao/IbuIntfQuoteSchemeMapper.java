package com.joyintech.background.component.uplink.interbank.database.dao;

import com.joyintech.background.component.uplink.interbank.database.entity.IbuIntfQuoteScheme;
import com.joyintech.background.component.uplink.interbank.database.entity.IbuIntfQuoteSchemeKey;
import org.springframework.stereotype.Repository;

@Repository
public interface IbuIntfQuoteSchemeMapper {
    int deleteByPrimaryKey(IbuIntfQuoteSchemeKey key);

    int insert(IbuIntfQuoteScheme record);

    int insertSelective(IbuIntfQuoteScheme record);

    int insertBasicByBasicInfo(IbuIntfQuoteScheme record);

    IbuIntfQuoteScheme selectByPrimaryKey(IbuIntfQuoteSchemeKey key);

    int updateByPrimaryKeySelective(IbuIntfQuoteScheme record);

    int updateByPrimaryKey(IbuIntfQuoteScheme record);
}