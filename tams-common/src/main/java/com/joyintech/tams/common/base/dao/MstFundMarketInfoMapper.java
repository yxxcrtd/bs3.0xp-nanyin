package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.MstFundMarketInfo;
import com.joyintech.tams.common.base.entity.MstFundMarketInfoKey;
import org.springframework.stereotype.Repository;

@Repository
public interface MstFundMarketInfoMapper {
    int deleteByPrimaryKey(MstFundMarketInfoKey key);

    int insert(MstFundMarketInfo record);

    int insertSelective(MstFundMarketInfo record);

    int insertBasicByBasicInfo(MstFundMarketInfo record);

    MstFundMarketInfo selectByPrimaryKey(MstFundMarketInfoKey key);

    int updateByPrimaryKeySelective(MstFundMarketInfo record);

    int updateByPrimaryKey(MstFundMarketInfo record);
}