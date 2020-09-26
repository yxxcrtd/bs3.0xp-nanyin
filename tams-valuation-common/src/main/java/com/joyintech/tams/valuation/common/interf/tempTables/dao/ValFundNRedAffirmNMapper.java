package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValFundNRedAffirmN;

@Repository
public interface ValFundNRedAffirmNMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValFundNRedAffirmN record);

    int insertSelective(ValFundNRedAffirmN record);

    int insertBasicByBasicInfo(ValFundNRedAffirmN record);

    ValFundNRedAffirmN selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValFundNRedAffirmN record);

    int updateByPrimaryKey(ValFundNRedAffirmN record);
}