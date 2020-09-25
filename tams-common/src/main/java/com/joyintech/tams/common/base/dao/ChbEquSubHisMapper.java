package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbEquSubHis;
import com.joyintech.tams.common.base.entity.ChbEquSubHisKey;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbEquSubHisMapper {
    int deleteByPrimaryKey(ChbEquSubHisKey key);

    int insert(ChbEquSubHis record);

    int insertSelective(ChbEquSubHis record);

    int insertHisByBasicInfo(ChbEquSubHis record);

    int insertHisByWorkflowId(ChbEquSubHis record);

    ChbEquSubHis selectByPrimaryKey(ChbEquSubHisKey key);

    int updateByPrimaryKeySelective(ChbEquSubHis record);

    int updateByPrimaryKey(ChbEquSubHis record);
}