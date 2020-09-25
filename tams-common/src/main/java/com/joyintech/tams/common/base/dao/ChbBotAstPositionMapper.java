package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbBotAstPosition;

public interface ChbBotAstPositionMapper {
    int deleteByPrimaryKey(String posId);

    int insert(ChbBotAstPosition record);

    int insertSelective(ChbBotAstPosition record);

    ChbBotAstPosition selectByPrimaryKey(String posId);

    int updateByPrimaryKeySelective(ChbBotAstPosition record);

    int updateByPrimaryKey(ChbBotAstPosition record);
}