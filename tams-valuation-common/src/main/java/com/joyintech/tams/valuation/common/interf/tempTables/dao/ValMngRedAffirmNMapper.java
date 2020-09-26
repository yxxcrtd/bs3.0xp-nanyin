package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValMngRedAffirmN;

@Repository
public interface ValMngRedAffirmNMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValMngRedAffirmN record);

    int insertSelective(ValMngRedAffirmN record);

    int insertBasicByBasicInfo(ValMngRedAffirmN record);

    ValMngRedAffirmN selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValMngRedAffirmN record);

    int updateByPrimaryKey(ValMngRedAffirmN record);
}