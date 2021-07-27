package com.joyintech.tams.common.base.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.RskSpecificAssetType;

@Repository
public interface RskSpecificAssetTypeMapper {
    int deleteByPrimaryKey(String specificId);

    int insert(RskSpecificAssetType record);

    int insertSelective(RskSpecificAssetType record);

    int insertBasicByBasicInfo(RskSpecificAssetType record);

    RskSpecificAssetType selectByPrimaryKey(String specificId);

    int updateByPrimaryKeySelective(RskSpecificAssetType record);

    int updateByPrimaryKey(RskSpecificAssetType record);
}