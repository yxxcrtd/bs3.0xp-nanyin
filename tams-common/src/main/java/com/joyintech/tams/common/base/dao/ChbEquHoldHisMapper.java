package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbEquHoldHis;
import com.joyintech.tams.common.base.entity.ChbEquHoldHisKey;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbEquHoldHisMapper {
    int deleteByPrimaryKey(ChbEquHoldHisKey key);

    int insert(ChbEquHoldHis record);

    int insertSelective(ChbEquHoldHis record);

    int insertHisByBasicInfo(ChbEquHoldHis record);

    int insertHisByWorkflowId(ChbEquHoldHis record);

    ChbEquHoldHis selectByPrimaryKey(ChbEquHoldHisKey key);

    int updateByPrimaryKeySelective(ChbEquHoldHis record);

    int updateByPrimaryKey(ChbEquHoldHis record);
}