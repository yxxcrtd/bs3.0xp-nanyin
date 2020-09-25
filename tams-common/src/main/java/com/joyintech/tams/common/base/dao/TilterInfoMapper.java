package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.TilterInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface TilterInfoMapper {
    int deleteByPrimaryKey(String filterCode);

    int insert(TilterInfo record);

    int insertSelective(TilterInfo record);

    int insertBasicByBasicInfo(TilterInfo record);

    TilterInfo selectByPrimaryKey(String filterCode);

    int updateByPrimaryKeySelective(TilterInfo record);

    int updateByPrimaryKey(TilterInfo record);

    TilterInfo selectByUserId(String userId);
}