package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbSysRoleModule;
import com.joyintech.tams.common.base.entity.ChbSysRoleModuleKey;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbSysRoleModuleMapper {
    int deleteByPrimaryKey(ChbSysRoleModuleKey key);

    int deleteByWorkflowId(String workflowId);

    int insert(ChbSysRoleModule record);

    int insertSelective(ChbSysRoleModule record);

    int insertBasicByBasicInfo(ChbSysRoleModule record);

    int insertBasicByWorkflowId(Map paraMap);

    ChbSysRoleModule selectByPrimaryKey(ChbSysRoleModuleKey key);

    int updateByPrimaryKeySelective(ChbSysRoleModule record);

    int updateByWorkflowIdSelective(ChbSysRoleModule record);

    int updateByWorkflowId(ChbSysRoleModule record);

    int updateByPrimaryKey(ChbSysRoleModule record);
}