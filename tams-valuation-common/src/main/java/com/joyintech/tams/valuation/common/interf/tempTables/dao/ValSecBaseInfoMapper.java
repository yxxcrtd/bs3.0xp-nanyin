package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValSecBaseInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface ValSecBaseInfoMapper {
    int deleteByPrimaryKey(String assetId);

    int insert(ValSecBaseInfo record);

    int insertSelective(ValSecBaseInfo record);

    int insertBasicByBasicInfo(ValSecBaseInfo record);

    ValSecBaseInfo selectByPrimaryKey(String assetId);

    int updateByPrimaryKeySelective(ValSecBaseInfo record);

    int updateByPrimaryKey(ValSecBaseInfo record);
}