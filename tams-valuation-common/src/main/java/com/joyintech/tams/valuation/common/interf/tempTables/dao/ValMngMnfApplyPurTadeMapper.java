package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValMngMnfApplyPurTade;

@Repository
public interface ValMngMnfApplyPurTadeMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValMngMnfApplyPurTade record);

    int insertSelective(ValMngMnfApplyPurTade record);

    int insertBasicByBasicInfo(ValMngMnfApplyPurTade record);

    ValMngMnfApplyPurTade selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValMngMnfApplyPurTade record);

    int updateByPrimaryKey(ValMngMnfApplyPurTade record);
}