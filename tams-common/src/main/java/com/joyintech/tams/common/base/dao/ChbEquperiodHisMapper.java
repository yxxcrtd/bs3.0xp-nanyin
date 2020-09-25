package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbEquperiodHis;
import com.joyintech.tams.common.base.entity.ChbEquperiodHisKey;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbEquperiodHisMapper {
    int deleteByPrimaryKey(ChbEquperiodHisKey key);

    int insert(ChbEquperiodHis record);

    int insertSelective(ChbEquperiodHis record);

    int insertHisByBasicInfo(ChbEquperiodHis record);

    int insertHisByWorkflowId(ChbEquperiodHis record);

    ChbEquperiodHis selectByPrimaryKey(ChbEquperiodHisKey key);

    int updateByPrimaryKeySelective(ChbEquperiodHis record);

    int updateByPrimaryKey(ChbEquperiodHis record);
}