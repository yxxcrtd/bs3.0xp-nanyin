package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbSysDepartment;
import com.joyintech.tams.common.base.entity.ChbSysDepartmentKey;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbSysDepartmentMapper {
    int deleteByPrimaryKey(ChbSysDepartmentKey key);

    int deleteByWorkflowId(String workflowId);

    int insert(ChbSysDepartment record);

    int insertSelective(ChbSysDepartment record);

    int insertBasicByBasicInfo(ChbSysDepartment record);

    int insertBasicByWorkflowId(Map paraMap);

    ChbSysDepartment selectByPrimaryKey(ChbSysDepartmentKey key);

    int updateByPrimaryKeySelective(ChbSysDepartment record);

    int updateByWorkflowIdSelective(ChbSysDepartment record);

    int updateByWorkflowId(ChbSysDepartment record);

    int updateByPrimaryKey(ChbSysDepartment record);
}