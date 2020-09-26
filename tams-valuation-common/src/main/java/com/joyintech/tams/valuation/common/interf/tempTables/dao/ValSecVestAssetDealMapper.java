package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValSecVestAssetDeal;
import org.springframework.stereotype.Repository;

@Repository
public interface ValSecVestAssetDealMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValSecVestAssetDeal record);

    int insertSelective(ValSecVestAssetDeal record);

    int insertBasicByBasicInfo(ValSecVestAssetDeal record);

    ValSecVestAssetDeal selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValSecVestAssetDeal record);

    int updateByPrimaryKey(ValSecVestAssetDeal record);
}