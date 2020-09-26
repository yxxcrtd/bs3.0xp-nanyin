package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValPrdFeeDealC;

@Repository
public interface ValPrdFeeDealCMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValPrdFeeDealC record);

    int insertSelective(ValPrdFeeDealC record);

    int insertBasicByBasicInfo(ValPrdFeeDealC record);

    ValPrdFeeDealC selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValPrdFeeDealC record);

    int updateByPrimaryKey(ValPrdFeeDealC record);
}