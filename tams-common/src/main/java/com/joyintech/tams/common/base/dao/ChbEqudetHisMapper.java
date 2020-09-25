package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbEqudetHis;
import com.joyintech.tams.common.base.entity.ChbEqudetHisKey;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbEqudetHisMapper {
    int deleteByPrimaryKey(ChbEqudetHisKey key);

    int insert(ChbEqudetHis record);

    int insertSelective(ChbEqudetHis record);

    int insertHisByBasicInfo(ChbEqudetHis record);

    int insertHisByWorkflowId(ChbEqudetHis record);

    ChbEqudetHis selectByPrimaryKey(ChbEqudetHisKey key);

    int updateByPrimaryKeySelective(ChbEqudetHis record);

    int updateByPrimaryKey(ChbEqudetHis record);
}