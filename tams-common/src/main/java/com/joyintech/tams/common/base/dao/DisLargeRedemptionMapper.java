package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.DisLargeRedemption;
import org.springframework.stereotype.Repository;

@Repository
public interface DisLargeRedemptionMapper {
    int deleteByPrimaryKey(String prodRegistNo);

    int insert(DisLargeRedemption record);

    int insertSelective(DisLargeRedemption record);

    int insertBasicByBasicInfo(DisLargeRedemption record);

    DisLargeRedemption selectByPrimaryKey(String prodRegistNo);

    int updateByPrimaryKeySelective(DisLargeRedemption record);

    int updateByPrimaryKey(DisLargeRedemption record);
}