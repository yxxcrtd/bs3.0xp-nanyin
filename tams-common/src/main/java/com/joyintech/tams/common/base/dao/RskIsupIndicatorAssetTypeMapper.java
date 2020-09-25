package com.joyintech.tams.common.base.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.RskIsupIndicatorAssetType;
import com.joyintech.tams.common.base.entity.RskIsupIndicatorAssetTypeKey;

@Repository
public interface RskIsupIndicatorAssetTypeMapper {

    int deleteByPrimaryKey(RskIsupIndicatorAssetTypeKey key);

    int insert(RskIsupIndicatorAssetType record);

    int insertSelective(RskIsupIndicatorAssetType record);

    int insertBasicByBasicInfo(RskIsupIndicatorAssetType record);

    RskIsupIndicatorAssetType selectByPrimaryKey(RskIsupIndicatorAssetTypeKey key);

    int updateByPrimaryKeySelective(RskIsupIndicatorAssetType record);

    int updateByPrimaryKey(RskIsupIndicatorAssetType record);
}