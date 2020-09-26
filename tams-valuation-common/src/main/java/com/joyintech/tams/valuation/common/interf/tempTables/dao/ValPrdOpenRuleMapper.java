package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValPrdOpenRule;

@Repository
public interface ValPrdOpenRuleMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValPrdOpenRule record);

    int insertSelective(ValPrdOpenRule record);

    int insertBasicByBasicInfo(ValPrdOpenRule record);

    ValPrdOpenRule selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValPrdOpenRule record);

    int updateByPrimaryKey(ValPrdOpenRule record);
}