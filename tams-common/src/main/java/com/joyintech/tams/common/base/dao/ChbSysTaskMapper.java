package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbSysTask;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbSysTaskMapper {
    int deleteByPrimaryKey(String taskId);

    int deleteByWorkflowId(String workflowId);

    int insert(ChbSysTask record);

    int insertSelective(ChbSysTask record);

    int insertBasicByBasicInfo(ChbSysTask record);

    int insertBasicByWorkflowId(Map paraMap);

    ChbSysTask selectByPrimaryKey(String taskId);

    int updateByPrimaryKeySelective(ChbSysTask record);

    int updateByWorkflowIdSelective(ChbSysTask record);

    int updateByWorkflowId(ChbSysTask record);

    int updateByPrimaryKey(ChbSysTask record);
}