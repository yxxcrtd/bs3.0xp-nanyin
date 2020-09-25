package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RepAssetBankDeposit;
import org.springframework.stereotype.Repository;

@Repository
public interface RepAssetBankDepositMapper {
    int deleteByPrimaryKey(String repFinprodId);

    int insert(RepAssetBankDeposit record);

    int insertSelective(RepAssetBankDeposit record);

    int insertBasicByBasicInfo(RepAssetBankDeposit record);

    RepAssetBankDeposit selectByPrimaryKey(String repFinprodId);

    int updateByPrimaryKeySelective(RepAssetBankDeposit record);

    int updateByPrimaryKey(RepAssetBankDeposit record);
}