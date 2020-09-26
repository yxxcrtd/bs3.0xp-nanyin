package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValMngRedAccountN;

@Repository
public interface ValMngRedAccountNMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ValMngRedAccountN record);

    int insertSelective(ValMngRedAccountN record);

    int insertBasicByBasicInfo(ValMngRedAccountN record);

    ValMngRedAccountN selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ValMngRedAccountN record);

    int updateByPrimaryKey(ValMngRedAccountN record);
}