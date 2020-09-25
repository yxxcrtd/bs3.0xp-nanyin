package com.joyintech.tams.common.base.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.RepAssetIndex;




@Repository
public interface RepAssetIndexMapper {
    int deleteByPrimaryKey(String seqNo);

    int insert(RepAssetIndex record);

    int insertSelective(RepAssetIndex record);

    int insertBasicByBasicInfo(RepAssetIndex record);

    RepAssetIndex selectByPrimaryKey(String seqNo);

    int updateByPrimaryKeySelective(RepAssetIndex record);

    int updateByPrimaryKey(RepAssetIndex record);
}