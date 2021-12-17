package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbMonthAssetPositiondetail;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbMonthAssetPositiondetailMapper {
    int deleteByPrimaryKey(String posuuid);

    int insert(ChbMonthAssetPositiondetail record);

    int insertSelective(ChbMonthAssetPositiondetail record);

    int insertBasicByBasicInfo(ChbMonthAssetPositiondetail record);

    ChbMonthAssetPositiondetail selectByPrimaryKey(String posuuid);

    int updateByPrimaryKeySelective(ChbMonthAssetPositiondetail record);

    int updateByPrimaryKey(ChbMonthAssetPositiondetail record);
}