package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbAstValue;

public interface ChbAstValueMapper {
    int deleteByPrimaryKey(String valId);

    int insert(ChbAstValue record);

    int insertSelective(ChbAstValue record);

    ChbAstValue selectByPrimaryKey(String valId);

    int updateByPrimaryKeySelective(ChbAstValue record);

    int updateByPrimaryKey(ChbAstValue record);
}