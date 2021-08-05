package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.RepCashYieldErrordata;
import com.joyintech.tams.common.base.entity.RepCashYieldErrordataWithBLOBs;
import org.springframework.stereotype.Repository;

@Repository
public interface RepCashYieldErrordataMapper {
    int deleteByPrimaryKey(String datauuid);

    int insert(RepCashYieldErrordataWithBLOBs record);

    int insertSelective(RepCashYieldErrordataWithBLOBs record);

    int insertBasicByBasicInfo(RepCashYieldErrordataWithBLOBs record);

    RepCashYieldErrordataWithBLOBs selectByPrimaryKey(String datauuid);

    int updateByPrimaryKeySelective(RepCashYieldErrordataWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(RepCashYieldErrordataWithBLOBs record);

    int updateByPrimaryKey(RepCashYieldErrordata record);
}