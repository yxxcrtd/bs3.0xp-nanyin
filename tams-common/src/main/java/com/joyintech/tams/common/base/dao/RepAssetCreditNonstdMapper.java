package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RepAssetCreditNonstd;
import org.springframework.stereotype.Repository;

@Repository
public interface RepAssetCreditNonstdMapper {
    int deleteByPrimaryKey(String repFinprodId);

    int insert(RepAssetCreditNonstd record);

    int insertSelective(RepAssetCreditNonstd record);

    int insertBasicByBasicInfo(RepAssetCreditNonstd record);

    RepAssetCreditNonstd selectByPrimaryKey(String repFinprodId);

    int updateByPrimaryKeySelective(RepAssetCreditNonstd record);

    int updateByPrimaryKey(RepAssetCreditNonstd record);
}