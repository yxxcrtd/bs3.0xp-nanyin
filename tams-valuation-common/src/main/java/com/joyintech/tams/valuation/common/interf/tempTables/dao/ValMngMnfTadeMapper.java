package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValMngMnfTade;

@Repository
public interface ValMngMnfTadeMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValMngMnfTade record);

    int insertSelective(ValMngMnfTade record);

    int insertBasicByBasicInfo(ValMngMnfTade record);

    ValMngMnfTade selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValMngMnfTade record);

    int updateByPrimaryKey(ValMngMnfTade record);
}