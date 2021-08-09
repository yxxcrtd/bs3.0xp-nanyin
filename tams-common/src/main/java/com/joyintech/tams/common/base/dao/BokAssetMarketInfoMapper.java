package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.BokAssetMarketInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface BokAssetMarketInfoMapper {
    int deleteByPrimaryKey(String seqNo);

    int insert(BokAssetMarketInfo record);

    int insertSelective(BokAssetMarketInfo record);

    int insertBasicByBasicInfo(BokAssetMarketInfo record);

    BokAssetMarketInfo selectByPrimaryKey(String seqNo);

    int updateByPrimaryKeySelective(BokAssetMarketInfo record);

    int updateByPrimaryKey(BokAssetMarketInfo record);
}