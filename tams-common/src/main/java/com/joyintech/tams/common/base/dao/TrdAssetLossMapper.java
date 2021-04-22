package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.TrdAssetLoss;
import org.springframework.stereotype.Repository;

@Repository
public interface TrdAssetLossMapper {
    int deleteByPrimaryKey(String tradeId);

    int insert(TrdAssetLoss record);

    int insertSelective(TrdAssetLoss record);

    int insertBasicByBasicInfo(TrdAssetLoss record);

    TrdAssetLoss selectByPrimaryKey(String tradeId);

    int updateByPrimaryKeySelective(TrdAssetLoss record);

    int updateByPrimaryKey(TrdAssetLoss record);
}