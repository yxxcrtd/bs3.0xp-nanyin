package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RskSpecificAssetType;
import org.springframework.stereotype.Repository;

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