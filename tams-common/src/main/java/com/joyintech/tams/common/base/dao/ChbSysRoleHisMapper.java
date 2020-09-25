package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbSysRoleHis;
import com.joyintech.tams.common.base.entity.ChbSysRoleHisKey;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbSysRoleHisMapper {
    int deleteByPrimaryKey(ChbSysRoleHisKey key);

    int insert(ChbSysRoleHis record);

    int insertSelective(ChbSysRoleHis record);

    int insertHisByBasicInfo(ChbSysRoleHis record);

    int insertHisByWorkflowId(ChbSysRoleHis record);

    ChbSysRoleHis selectByPrimaryKey(ChbSysRoleHisKey key);

    int updateByPrimaryKeySelective(ChbSysRoleHis record);

    int updateByPrimaryKey(ChbSysRoleHis record);
}