package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbSysRoleModuleHis;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbSysRoleModuleHisMapper {
    int insert(ChbSysRoleModuleHis record);

    int insertSelective(ChbSysRoleModuleHis record);

    int insertHisByBasicInfo(ChbSysRoleModuleHis record);

    int insertHisByWorkflowId(ChbSysRoleModuleHis record);
}