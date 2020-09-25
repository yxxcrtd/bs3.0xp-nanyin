package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbBotAstPositionSub;

public interface ChbBotAstPositionSubMapper {
    int deleteByPrimaryKey(String registSeqno);

    int insert(ChbBotAstPositionSub record);

    int insertSelective(ChbBotAstPositionSub record);

    ChbBotAstPositionSub selectByPrimaryKey(String registSeqno);

    int updateByPrimaryKeySelective(ChbBotAstPositionSub record);

    int updateByPrimaryKey(ChbBotAstPositionSub record);
}