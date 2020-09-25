package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbSysUserRole;
import com.joyintech.tams.common.base.entity.ChbSysUserRoleKey;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbSysUserRoleMapper {
    int deleteByPrimaryKey(ChbSysUserRoleKey key);

    int deleteByWorkflowId(String workflowId);

    int insert(ChbSysUserRole record);

    int insertSelective(ChbSysUserRole record);

    int insertBasicByBasicInfo(ChbSysUserRole record);

    int insertBasicByWorkflowId(Map paraMap);

    ChbSysUserRole selectByPrimaryKey(ChbSysUserRoleKey key);

    int updateByPrimaryKeySelective(ChbSysUserRole record);

    int updateByWorkflowIdSelective(ChbSysUserRole record);

    int updateByWorkflowId(ChbSysUserRole record);

    int updateByPrimaryKey(ChbSysUserRole record);
}