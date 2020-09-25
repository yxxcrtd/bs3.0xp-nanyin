package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RepAssetEquity;
import org.springframework.stereotype.Repository;

@Repository
public interface RepAssetEquityMapper {
    int deleteByPrimaryKey(String repFinprodId);

    int insert(RepAssetEquity record);

    int insertSelective(RepAssetEquity record);

    int insertBasicByBasicInfo(RepAssetEquity record);

    RepAssetEquity selectByPrimaryKey(String repFinprodId);

    int updateByPrimaryKeySelective(RepAssetEquity record);

    int updateByPrimaryKey(RepAssetEquity record);
}