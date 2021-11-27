package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbMonthAssetPosition;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbMonthAssetPositionMapper {
    int deleteByPrimaryKey(String posuuid);

    int insert(ChbMonthAssetPosition record);

    int insertSelective(ChbMonthAssetPosition record);

    int insertBasicByBasicInfo(ChbMonthAssetPosition record);

    ChbMonthAssetPosition selectByPrimaryKey(String posuuid);

    int updateByPrimaryKeySelective(ChbMonthAssetPosition record);

    int updateByPrimaryKey(ChbMonthAssetPosition record);
}