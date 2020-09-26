package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValPrdInfoC;

@Repository
public interface ValPrdInfoCMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValPrdInfoC record);

    int insertSelective(ValPrdInfoC record);

    int insertBasicByBasicInfo(ValPrdInfoC record);

    ValPrdInfoC selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValPrdInfoC record);

    int updateByPrimaryKey(ValPrdInfoC record);
}