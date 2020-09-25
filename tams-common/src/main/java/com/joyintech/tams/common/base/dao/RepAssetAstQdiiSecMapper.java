package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RepAssetAstQdiiSec;
import org.springframework.stereotype.Repository;

@Repository
public interface RepAssetAstQdiiSecMapper {
    int deleteByPrimaryKey(String repFinprodId);

    int insert(RepAssetAstQdiiSec record);

    int insertSelective(RepAssetAstQdiiSec record);

    int insertBasicByBasicInfo(RepAssetAstQdiiSec record);

    RepAssetAstQdiiSec selectByPrimaryKey(String repFinprodId);

    int updateByPrimaryKeySelective(RepAssetAstQdiiSec record);

    int updateByPrimaryKey(RepAssetAstQdiiSec record);
}