package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbAutosendBatchLog;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbAutosendBatchLogMapper {
    int deleteByPrimaryKey(String batchExeId);

    int deleteByWorkflowId(String workflowId);

    int insert(ChbAutosendBatchLog record);

    int insertSelective(ChbAutosendBatchLog record);

    int insertBasicByBasicInfo(ChbAutosendBatchLog record);

    int insertBasicByWorkflowId(Map paraMap);

    ChbAutosendBatchLog selectByPrimaryKey(String batchExeId);

    int updateByPrimaryKeySelective(ChbAutosendBatchLog record);

    int updateByWorkflowIdSelective(ChbAutosendBatchLog record);

    int updateByWorkflowId(ChbAutosendBatchLog record);

    int updateByPrimaryKeyWithBLOBs(ChbAutosendBatchLog record);

    int updateByPrimaryKey(ChbAutosendBatchLog record);
}