package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbInvestTradeErrordata;
import com.joyintech.tams.common.base.entity.ChbInvestTradeErrordataWithBLOBs;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbInvestTradeErrordataMapper {
    int deleteByPrimaryKey(String datauuid);

    int insert(ChbInvestTradeErrordataWithBLOBs record);

    int insertSelective(ChbInvestTradeErrordataWithBLOBs record);

    int insertBasicByBasicInfo(ChbInvestTradeErrordataWithBLOBs record);

    ChbInvestTradeErrordataWithBLOBs selectByPrimaryKey(String datauuid);

    int updateByPrimaryKeySelective(ChbInvestTradeErrordataWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ChbInvestTradeErrordataWithBLOBs record);

    int updateByPrimaryKey(ChbInvestTradeErrordata record);
}