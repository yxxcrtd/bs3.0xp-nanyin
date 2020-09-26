package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValMngBonusReinvestN;

@Repository
public interface ValMngBonusReinvestNMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValMngBonusReinvestN record);

    int insertSelective(ValMngBonusReinvestN record);

    int insertBasicByBasicInfo(ValMngBonusReinvestN record);

    ValMngBonusReinvestN selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValMngBonusReinvestN record);

    int updateByPrimaryKey(ValMngBonusReinvestN record);
}