package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValAccountInfo;

@Repository
public interface ValAccountInfoMapper {
    int deleteByPrimaryKey(String acctId);

    int insert(ValAccountInfo record);

    int insertSelective(ValAccountInfo record);

    int insertBasicByBasicInfo(ValAccountInfo record);

    ValAccountInfo selectByPrimaryKey(String acctId);

    int updateByPrimaryKeySelective(ValAccountInfo record);

    int updateByPrimaryKey(ValAccountInfo record);
}