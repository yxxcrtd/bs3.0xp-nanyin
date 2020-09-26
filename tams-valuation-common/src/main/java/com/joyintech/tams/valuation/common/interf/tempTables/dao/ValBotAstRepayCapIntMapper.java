package com.joyintech.tams.valuation.common.interf.tempTables.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValBotAstRepayCapInt;

@Repository
public interface ValBotAstRepayCapIntMapper {
    int deleteByPrimaryKey(String assetId);

    int insert(ValBotAstRepayCapInt record);

    int insertSelective(ValBotAstRepayCapInt record);

    int insertBasicByBasicInfo(ValBotAstRepayCapInt record);

    ValBotAstRepayCapInt selectByPrimaryKey(String assetId);

    int updateByPrimaryKeySelective(ValBotAstRepayCapInt record);

    int updateByPrimaryKey(ValBotAstRepayCapInt record);
}