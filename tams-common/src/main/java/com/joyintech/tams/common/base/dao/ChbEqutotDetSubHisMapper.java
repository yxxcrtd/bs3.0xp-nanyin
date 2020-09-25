package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbEqutotDetSubHis;
import com.joyintech.tams.common.base.entity.ChbEqutotDetSubHisKey;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbEqutotDetSubHisMapper {
    int deleteByPrimaryKey(ChbEqutotDetSubHisKey key);

    int insert(ChbEqutotDetSubHis record);

    int insertSelective(ChbEqutotDetSubHis record);

    int insertHisByBasicInfo(ChbEqutotDetSubHis record);

    int insertHisByWorkflowId(ChbEqutotDetSubHis record);

    ChbEqutotDetSubHis selectByPrimaryKey(ChbEqutotDetSubHisKey key);

    int updateByPrimaryKeySelective(ChbEqutotDetSubHis record);

    int updateByPrimaryKey(ChbEqutotDetSubHis record);
}