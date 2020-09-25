package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbInterfLog;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbInterfLogMapper {
    int deleteByPrimaryKey(String logId);

    int deleteByWorkflowId(String workflowId);

    int insert(ChbInterfLog record);

    int insertSelective(ChbInterfLog record);

    int insertBasicByBasicInfo(ChbInterfLog record);

    int insertBasicByWorkflowId(Map paraMap);

    ChbInterfLog selectByPrimaryKey(String logId);

    int updateByPrimaryKeySelective(ChbInterfLog record);

    int updateByWorkflowIdSelective(ChbInterfLog record);

    int updateByWorkflowId(ChbInterfLog record);

    int updateByPrimaryKey(ChbInterfLog record);
}