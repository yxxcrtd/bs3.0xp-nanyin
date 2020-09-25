package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RskValtblAssetSubMap;
import org.springframework.stereotype.Repository;

@Repository
public interface RskValtblAssetSubMapMapper {
    int deleteByPrimaryKey(String id);

    int insert(RskValtblAssetSubMap record);

    int insertSelective(RskValtblAssetSubMap record);

    int insertBasicByBasicInfo(RskValtblAssetSubMap record);

    RskValtblAssetSubMap selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RskValtblAssetSubMap record);

    int updateByPrimaryKey(RskValtblAssetSubMap record);
}