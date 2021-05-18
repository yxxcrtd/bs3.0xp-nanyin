package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbMstCounterPartyErrordata;
import com.joyintech.tams.common.base.entity.ChbMstCounterPartyErrordataWithBLOBs;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbMstCounterPartyErrordataMapper {
    int deleteByPrimaryKey(String datauuid);

    int insert(ChbMstCounterPartyErrordataWithBLOBs record);

    int insertSelective(ChbMstCounterPartyErrordataWithBLOBs record);

    int insertBasicByBasicInfo(ChbMstCounterPartyErrordataWithBLOBs record);

    ChbMstCounterPartyErrordataWithBLOBs selectByPrimaryKey(String datauuid);

    int updateByPrimaryKeySelective(ChbMstCounterPartyErrordataWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ChbMstCounterPartyErrordataWithBLOBs record);

    int updateByPrimaryKey(ChbMstCounterPartyErrordata record);
}