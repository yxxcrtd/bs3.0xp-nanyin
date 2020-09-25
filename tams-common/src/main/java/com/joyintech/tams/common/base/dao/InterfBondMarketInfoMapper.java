package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.InterfBondMarketInfo;
import com.joyintech.tams.common.base.entity.InterfBondMarketInfoKey;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfBondMarketInfoMapper {
    int deleteByPrimaryKey(InterfBondMarketInfoKey key);

    int insert(InterfBondMarketInfo record);

    int insertSelective(InterfBondMarketInfo record);

    int insertBasicByBasicInfo(InterfBondMarketInfo record);

    InterfBondMarketInfo selectByPrimaryKey(InterfBondMarketInfoKey key);

    int updateByPrimaryKeySelective(InterfBondMarketInfo record);

    int updateByPrimaryKey(InterfBondMarketInfo record);
}