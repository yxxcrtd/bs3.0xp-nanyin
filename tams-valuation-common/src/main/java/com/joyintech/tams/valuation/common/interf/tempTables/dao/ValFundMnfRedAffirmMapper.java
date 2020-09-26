package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValFundMnfRedAffirm;

@Repository
public interface ValFundMnfRedAffirmMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValFundMnfRedAffirm record);

    int insertSelective(ValFundMnfRedAffirm record);

    int insertBasicByBasicInfo(ValFundMnfRedAffirm record);

    ValFundMnfRedAffirm selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValFundMnfRedAffirm record);

    int updateByPrimaryKey(ValFundMnfRedAffirm record);
}