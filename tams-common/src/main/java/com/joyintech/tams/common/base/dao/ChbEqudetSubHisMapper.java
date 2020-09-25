package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbEqudetSubHis;
import com.joyintech.tams.common.base.entity.ChbEqudetSubHisKey;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbEqudetSubHisMapper {
    int deleteByPrimaryKey(ChbEqudetSubHisKey key);

    int insert(ChbEqudetSubHis record);

    int insertSelective(ChbEqudetSubHis record);

    int insertHisByBasicInfo(ChbEqudetSubHis record);

    int insertHisByWorkflowId(ChbEqudetSubHis record);

    ChbEqudetSubHis selectByPrimaryKey(ChbEqudetSubHisKey key);

    int updateByPrimaryKeySelective(ChbEqudetSubHis record);

    int updateByPrimaryKey(ChbEqudetSubHis record);
}