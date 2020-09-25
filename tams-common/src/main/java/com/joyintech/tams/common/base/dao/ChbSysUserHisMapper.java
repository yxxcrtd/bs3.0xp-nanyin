package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbSysUserHis;
import com.joyintech.tams.common.base.entity.ChbSysUserHisKey;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbSysUserHisMapper {
    int deleteByPrimaryKey(ChbSysUserHisKey key);

    int insert(ChbSysUserHis record);

    int insertSelective(ChbSysUserHis record);

    int insertHisByBasicInfo(ChbSysUserHis record);

    int insertHisByWorkflowId(ChbSysUserHis record);

    ChbSysUserHis selectByPrimaryKey(ChbSysUserHisKey key);

    int updateByPrimaryKeySelective(ChbSysUserHis record);

    int updateByPrimaryKey(ChbSysUserHis record);
}