package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbAutoSendBatchExeTime;
import com.joyintech.tams.common.base.entity.ChbAutoSendBatchExeTimeKey;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbAutoSendBatchExeTimeMapper {
    int deleteByPrimaryKey(ChbAutoSendBatchExeTimeKey key);

    int deleteByWorkflowId(String workflowId);

    int insert(ChbAutoSendBatchExeTime record);

    int insertSelective(ChbAutoSendBatchExeTime record);

    int insertBasicByBasicInfo(ChbAutoSendBatchExeTime record);

    int insertBasicByWorkflowId(Map paraMap);

    ChbAutoSendBatchExeTime selectByPrimaryKey(ChbAutoSendBatchExeTimeKey key);

    int updateByPrimaryKeySelective(ChbAutoSendBatchExeTime record);

    int updateByWorkflowIdSelective(ChbAutoSendBatchExeTime record);

    int updateByWorkflowId(ChbAutoSendBatchExeTime record);

    int updateByPrimaryKey(ChbAutoSendBatchExeTime record);
}