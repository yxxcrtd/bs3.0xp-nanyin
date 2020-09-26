package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValPrdDelDealN;

@Repository
public interface ValPrdDelDealNMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValPrdDelDealN record);

    int insertSelective(ValPrdDelDealN record);

    int insertBasicByBasicInfo(ValPrdDelDealN record);

    ValPrdDelDealN selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValPrdDelDealN record);

    int updateByPrimaryKey(ValPrdDelDealN record);
}