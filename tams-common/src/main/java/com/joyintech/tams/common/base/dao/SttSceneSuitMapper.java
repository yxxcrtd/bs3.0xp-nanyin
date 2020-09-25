package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SttSceneSuit;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface SttSceneSuitMapper {
    int deleteByPrimaryKey(String seqNo);

    int deleteByWorkflowId(String workflowId);

    int insert(SttSceneSuit record);

    int insertSelective(SttSceneSuit record);

    int insertBasicByBasicInfo(SttSceneSuit record);

    int insertBasicByWorkflowId(Map paraMap);

    SttSceneSuit selectByPrimaryKey(String seqNo);

    int updateByPrimaryKeySelective(SttSceneSuit record);

    int updateByWorkflowIdSelective(SttSceneSuit record);

    int updateByWorkflowId(SttSceneSuit record);

    int updateByPrimaryKey(SttSceneSuit record);
}