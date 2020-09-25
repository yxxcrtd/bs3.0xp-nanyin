package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.InterfFMarketInfo;
import com.joyintech.tams.common.base.entity.InterfFMarketInfoKey;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfFMarketInfoMapper {
    int deleteByPrimaryKey(InterfFMarketInfoKey key);

    int insert(InterfFMarketInfo record);

    int insertSelective(InterfFMarketInfo record);

    int insertBasicByBasicInfo(InterfFMarketInfo record);

    InterfFMarketInfo selectByPrimaryKey(InterfFMarketInfoKey key);

    int updateByPrimaryKeySelective(InterfFMarketInfo record);

    int updateByPrimaryKey(InterfFMarketInfo record);
}