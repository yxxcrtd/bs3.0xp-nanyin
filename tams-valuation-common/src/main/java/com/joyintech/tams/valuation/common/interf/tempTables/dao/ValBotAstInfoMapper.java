package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValBotAstInfo;

@Repository
public interface ValBotAstInfoMapper {
    int insert(ValBotAstInfo record);

    int insertSelective(ValBotAstInfo record);

    int insertBasicByBasicInfo(ValBotAstInfo record);
}