package com.joyintech.service.valuation.lossmodel.assetimpairmentmanagement.dao;

import com.joyintech.service.valuation.lossmodel.assetimpairmentmanagement.dto.AssetLossResult;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetLossResultMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(AssetLossResult record);

    int insertSelective(AssetLossResult record);

    int insertBasicByBasicInfo(AssetLossResult record);

    AssetLossResult selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(AssetLossResult record);

    int updateByPrimaryKey(AssetLossResult record);
}