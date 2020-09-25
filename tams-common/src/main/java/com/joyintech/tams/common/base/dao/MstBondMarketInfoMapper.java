package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.MstBondMarketInfo;
import com.joyintech.tams.common.base.entity.MstBondMarketInfoKey;
import org.springframework.stereotype.Repository;

@Repository
public interface MstBondMarketInfoMapper {
    int deleteByPrimaryKey(MstBondMarketInfoKey key);

    int insert(MstBondMarketInfo record);
    
    int insertFull(MstBondMarketInfo record);

    int insertSelective(MstBondMarketInfo record);

    int insertBasicByBasicInfo(MstBondMarketInfo record);

    MstBondMarketInfo selectByPrimaryKey(MstBondMarketInfoKey key);

    int updateByPrimaryKeySelective(MstBondMarketInfo record);

    int updateByPrimaryKey(MstBondMarketInfo record);
}