package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SttFactorMutex;
import com.joyintech.tams.common.base.entity.SttFactorMutexKey;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface SttFactorMutexMapper {
    int deleteByPrimaryKey(SttFactorMutexKey key);

    int deleteByWorkflowId(String workflowId);

    int insert(SttFactorMutex record);

    int insertSelective(SttFactorMutex record);

    int insertBasicByBasicInfo(SttFactorMutex record);

    int insertBasicByWorkflowId(Map paraMap);

    SttFactorMutex selectByPrimaryKey(SttFactorMutexKey key);

    int updateByPrimaryKeySelective(SttFactorMutex record);

    int updateByWorkflowIdSelective(SttFactorMutex record);

    int updateByWorkflowId(SttFactorMutex record);

    int updateByPrimaryKey(SttFactorMutex record);
}