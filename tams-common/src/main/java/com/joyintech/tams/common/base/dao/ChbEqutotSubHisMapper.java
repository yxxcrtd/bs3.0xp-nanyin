package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbEqutotSubHis;
import com.joyintech.tams.common.base.entity.ChbEqutotSubHisKey;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbEqutotSubHisMapper {
    int deleteByPrimaryKey(ChbEqutotSubHisKey key);

    int insert(ChbEqutotSubHis record);

    int insertSelective(ChbEqutotSubHis record);

    int insertHisByBasicInfo(ChbEqutotSubHis record);

    int insertHisByWorkflowId(ChbEqutotSubHis record);

    ChbEqutotSubHis selectByPrimaryKey(ChbEqutotSubHisKey key);

    int updateByPrimaryKeySelective(ChbEqutotSubHis record);

    int updateByPrimaryKey(ChbEqutotSubHis record);
}