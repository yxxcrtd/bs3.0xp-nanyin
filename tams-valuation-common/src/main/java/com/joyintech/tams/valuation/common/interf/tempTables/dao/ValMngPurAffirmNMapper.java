package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValMngPurAffirmN;

@Repository
public interface ValMngPurAffirmNMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValMngPurAffirmN record);

    int insertSelective(ValMngPurAffirmN record);

    int insertBasicByBasicInfo(ValMngPurAffirmN record);

    ValMngPurAffirmN selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValMngPurAffirmN record);

    int updateByPrimaryKey(ValMngPurAffirmN record);
}