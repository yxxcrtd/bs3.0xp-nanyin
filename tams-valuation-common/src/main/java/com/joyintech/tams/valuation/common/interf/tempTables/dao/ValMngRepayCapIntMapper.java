package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValMngRepayCapInt;

@Repository
public interface ValMngRepayCapIntMapper {
    int deleteByPrimaryKey(String assetId);

    int insert(ValMngRepayCapInt record);

    int insertSelective(ValMngRepayCapInt record);

    int insertBasicByBasicInfo(ValMngRepayCapInt record);

    ValMngRepayCapInt selectByPrimaryKey(String assetId);

    int updateByPrimaryKeySelective(ValMngRepayCapInt record);

    int updateByPrimaryKey(ValMngRepayCapInt record);
}