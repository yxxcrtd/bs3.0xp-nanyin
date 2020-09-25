package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RepAssetBond;
import org.springframework.stereotype.Repository;

@Repository
public interface RepAssetBondMapper {
    int deleteByPrimaryKey(String repFinprodId);

    int insert(RepAssetBond record);

    int insertSelective(RepAssetBond record);

    int insertBasicByBasicInfo(RepAssetBond record);

    RepAssetBond selectByPrimaryKey(String repFinprodId);

    int updateByPrimaryKeySelective(RepAssetBond record);

    int updateByPrimaryKey(RepAssetBond record);
}