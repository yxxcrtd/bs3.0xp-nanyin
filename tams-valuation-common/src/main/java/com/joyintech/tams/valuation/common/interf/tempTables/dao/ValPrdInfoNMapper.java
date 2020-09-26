package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValPrdInfoN;

@Repository
public interface ValPrdInfoNMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValPrdInfoN record);

    int insertSelective(ValPrdInfoN record);

    int insertBasicByBasicInfo(ValPrdInfoN record);

    ValPrdInfoN selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValPrdInfoN record);

    int updateByPrimaryKey(ValPrdInfoN record);
}