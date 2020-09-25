package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RepAssetDeri;
import org.springframework.stereotype.Repository;

@Repository
public interface RepAssetDeriMapper {
    int deleteByPrimaryKey(String repFinprodId);

    int insert(RepAssetDeri record);

    int insertSelective(RepAssetDeri record);

    int insertBasicByBasicInfo(RepAssetDeri record);

    RepAssetDeri selectByPrimaryKey(String repFinprodId);

    int updateByPrimaryKeySelective(RepAssetDeri record);

    int updateByPrimaryKey(RepAssetDeri record);
}