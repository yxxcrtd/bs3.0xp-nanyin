package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.PbcMonthAssetPositiondetail;
import org.springframework.stereotype.Repository;

@Repository
public interface PbcMonthAssetPositiondetailMapper {
    int deleteByPrimaryKey(String posuuid);

    int insert(PbcMonthAssetPositiondetail record);

    int insertSelective(PbcMonthAssetPositiondetail record);

    int insertBasicByBasicInfo(PbcMonthAssetPositiondetail record);

    PbcMonthAssetPositiondetail selectByPrimaryKey(String posuuid);

    int updateByPrimaryKeySelective(PbcMonthAssetPositiondetail record);

    int updateByPrimaryKey(PbcMonthAssetPositiondetail record);
}