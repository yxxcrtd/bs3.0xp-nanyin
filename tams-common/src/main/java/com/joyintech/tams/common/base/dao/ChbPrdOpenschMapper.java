package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbPrdOpensch;

public interface ChbPrdOpenschMapper {
    int deleteByPrimaryKey(String seqno);

    int insert(ChbPrdOpensch record);

    int insertSelective(ChbPrdOpensch record);

    ChbPrdOpensch selectByPrimaryKey(String seqno);

    int updateByPrimaryKeySelective(ChbPrdOpensch record);

    int updateByPrimaryKey(ChbPrdOpensch record);
}