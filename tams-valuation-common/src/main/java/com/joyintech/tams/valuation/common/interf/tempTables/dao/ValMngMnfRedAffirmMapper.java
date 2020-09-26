package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValMngMnfRedAffirm;

@Repository
public interface ValMngMnfRedAffirmMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValMngMnfRedAffirm record);

    int insertSelective(ValMngMnfRedAffirm record);

    int insertBasicByBasicInfo(ValMngMnfRedAffirm record);

    ValMngMnfRedAffirm selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValMngMnfRedAffirm record);

    int updateByPrimaryKey(ValMngMnfRedAffirm record);
}