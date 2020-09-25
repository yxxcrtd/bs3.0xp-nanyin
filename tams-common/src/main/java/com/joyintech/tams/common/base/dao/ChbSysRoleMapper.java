package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbSysRole;
import com.joyintech.tams.common.base.entity.ChbSysRoleKey;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbSysRoleMapper {
    int deleteByPrimaryKey(ChbSysRoleKey key);

    int deleteByWorkflowId(String workflowId);

    int insert(ChbSysRole record);

    int insertSelective(ChbSysRole record);

    int insertBasicByBasicInfo(ChbSysRole record);

    int insertBasicByWorkflowId(Map paraMap);

    ChbSysRole selectByPrimaryKey(ChbSysRoleKey key);

    int updateByPrimaryKeySelective(ChbSysRole record);

    int updateByWorkflowIdSelective(ChbSysRole record);

    int updateByWorkflowId(ChbSysRole record);

    int updateByPrimaryKey(ChbSysRole record);
}