package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.InfTruCashDaily;
import com.joyintech.tams.common.base.entity.InfTruCashDailyKey;
import org.springframework.stereotype.Repository;

@Repository
public interface InfTruCashDailyMapper {
    int deleteByPrimaryKey(InfTruCashDailyKey key);

    int insert(InfTruCashDaily record);

    int insertSelective(InfTruCashDaily record);

    int insertBasicByBasicInfo(InfTruCashDaily record);

    InfTruCashDaily selectByPrimaryKey(InfTruCashDailyKey key);

    int updateByPrimaryKeySelective(InfTruCashDaily record);

    int updateByPrimaryKey(InfTruCashDaily record);
}