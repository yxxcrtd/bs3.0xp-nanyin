package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValFundMnfPayRule;

@Repository
public interface ValFundMnfPayRuleMapper {
    int deleteByPrimaryKey(String assetId);

    int insert(ValFundMnfPayRule record);

    int insertSelective(ValFundMnfPayRule record);

    int insertBasicByBasicInfo(ValFundMnfPayRule record);

    ValFundMnfPayRule selectByPrimaryKey(String assetId);

    int updateByPrimaryKeySelective(ValFundMnfPayRule record);

    int updateByPrimaryKey(ValFundMnfPayRule record);
}