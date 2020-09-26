package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValMngPurApplyN;

@Repository
public interface ValMngPurApplyNMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValMngPurApplyN record);

    int insertSelective(ValMngPurApplyN record);

    int insertBasicByBasicInfo(ValMngPurApplyN record);

    ValMngPurApplyN selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValMngPurApplyN record);

    int updateByPrimaryKey(ValMngPurApplyN record);
}