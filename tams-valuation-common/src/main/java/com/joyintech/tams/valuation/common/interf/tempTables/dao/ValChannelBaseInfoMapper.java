package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValChannelBaseInfo;

@Repository
public interface ValChannelBaseInfoMapper {
    int deleteByPrimaryKey(String channelId);

    int insert(ValChannelBaseInfo record);

    int insertSelective(ValChannelBaseInfo record);

    int insertBasicByBasicInfo(ValChannelBaseInfo record);

    ValChannelBaseInfo selectByPrimaryKey(String channelId);

    int updateByPrimaryKeySelective(ValChannelBaseInfo record);

    int updateByPrimaryKey(ValChannelBaseInfo record);
}