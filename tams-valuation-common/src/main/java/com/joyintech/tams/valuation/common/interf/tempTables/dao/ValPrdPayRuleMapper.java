package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValPrdPayRule;

@Repository
public interface ValPrdPayRuleMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValPrdPayRule record);

    int insertSelective(ValPrdPayRule record);

    int insertBasicByBasicInfo(ValPrdPayRule record);

    ValPrdPayRule selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValPrdPayRule record);

    int updateByPrimaryKey(ValPrdPayRule record);
}