package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbSysUserRoleHis;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbSysUserRoleHisMapper {
    int insert(ChbSysUserRoleHis record);

    int insertSelective(ChbSysUserRoleHis record);

    int insertHisByBasicInfo(ChbSysUserRoleHis record);

    int insertHisByWorkflowId(ChbSysUserRoleHis record);
}