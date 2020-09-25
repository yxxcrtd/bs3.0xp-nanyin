package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SttStressLayering;
import com.joyintech.tams.common.base.entity.SttStressLayeringKey;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface SttStressLayeringMapper {
    int deleteByPrimaryKey(SttStressLayeringKey key);

    int deleteByWorkflowId(String workflowId);

    int insert(SttStressLayering record);

    int insertSelective(SttStressLayering record);

    int insertBasicByBasicInfo(SttStressLayering record);

    int insertBasicByWorkflowId(Map paraMap);

    SttStressLayering selectByPrimaryKey(SttStressLayeringKey key);

    int updateByPrimaryKeySelective(SttStressLayering record);

    int updateByWorkflowIdSelective(SttStressLayering record);

    int updateByWorkflowId(SttStressLayering record);

    int updateByPrimaryKey(SttStressLayering record);
}