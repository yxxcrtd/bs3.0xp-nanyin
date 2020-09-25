package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RepAssetAstQdiiRev;
import org.springframework.stereotype.Repository;

@Repository
public interface RepAssetAstQdiiRevMapper {
    int deleteByPrimaryKey(String repFinprodId);

    int insert(RepAssetAstQdiiRev record);

    int insertSelective(RepAssetAstQdiiRev record);

    int insertBasicByBasicInfo(RepAssetAstQdiiRev record);

    RepAssetAstQdiiRev selectByPrimaryKey(String repFinprodId);

    int updateByPrimaryKeySelective(RepAssetAstQdiiRev record);

    int updateByPrimaryKey(RepAssetAstQdiiRev record);
}