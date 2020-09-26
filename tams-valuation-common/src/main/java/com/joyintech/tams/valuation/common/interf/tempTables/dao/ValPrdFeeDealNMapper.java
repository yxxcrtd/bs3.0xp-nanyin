package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValPrdFeeDealN;

@Repository
public interface ValPrdFeeDealNMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValPrdFeeDealN record);

    int insertSelective(ValPrdFeeDealN record);

    int insertBasicByBasicInfo(ValPrdFeeDealN record);

    ValPrdFeeDealN selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValPrdFeeDealN record);

    int updateByPrimaryKey(ValPrdFeeDealN record);
}