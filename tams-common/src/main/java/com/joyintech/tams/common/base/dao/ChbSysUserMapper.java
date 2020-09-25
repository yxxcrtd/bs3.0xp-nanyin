package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbSysUser;
import com.joyintech.tams.common.base.entity.ChbSysUserKey;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbSysUserMapper {
    int deleteByPrimaryKey(ChbSysUserKey key);

    int deleteByWorkflowId(String workflowId);

    int insert(ChbSysUser record);

    int insertSelective(ChbSysUser record);

    int insertBasicByBasicInfo(ChbSysUser record);

    int insertBasicByWorkflowId(Map paraMap);

    ChbSysUser selectByPrimaryKey(ChbSysUserKey key);

    int updateByPrimaryKeySelective(ChbSysUser record);

    int updateByWorkflowIdSelective(ChbSysUser record);

    int updateByWorkflowId(ChbSysUser record);

    int updateByPrimaryKey(ChbSysUser record);
}