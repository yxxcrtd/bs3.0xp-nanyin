package com.joyintech.background.component.uplink.interbank.database.dao;

import com.joyintech.background.component.uplink.interbank.database.entity.IbuQuoteCrep;
import org.springframework.stereotype.Repository;

@Repository
public interface IbuQuoteCrepMapper {
    int deleteByPrimaryKey(String quoteId);

    int insert(IbuQuoteCrep record);

    int insertSelective(IbuQuoteCrep record);

    int insertBasicByBasicInfo(IbuQuoteCrep record);

    IbuQuoteCrep selectByPrimaryKey(String quoteId);

    int updateByPrimaryKeySelective(IbuQuoteCrep record);

    int updateByPrimaryKey(IbuQuoteCrep record);
}