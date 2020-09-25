package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RepAssetInterLend;
import org.springframework.stereotype.Repository;

@Repository
public interface RepAssetInterLendMapper {
    int deleteByPrimaryKey(String repFinprodId);

    int insert(RepAssetInterLend record);

    int insertSelective(RepAssetInterLend record);

    int insertBasicByBasicInfo(RepAssetInterLend record);

    RepAssetInterLend selectByPrimaryKey(String repFinprodId);

    int updateByPrimaryKeySelective(RepAssetInterLend record);

    int updateByPrimaryKey(RepAssetInterLend record);
}