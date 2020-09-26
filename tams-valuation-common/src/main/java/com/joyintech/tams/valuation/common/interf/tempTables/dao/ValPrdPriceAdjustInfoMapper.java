package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValPrdPriceAdjustInfo;

@Repository
public interface ValPrdPriceAdjustInfoMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValPrdPriceAdjustInfo record);

    int insertSelective(ValPrdPriceAdjustInfo record);

    int insertBasicByBasicInfo(ValPrdPriceAdjustInfo record);

    ValPrdPriceAdjustInfo selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValPrdPriceAdjustInfo record);

    int updateByPrimaryKey(ValPrdPriceAdjustInfo record);
}