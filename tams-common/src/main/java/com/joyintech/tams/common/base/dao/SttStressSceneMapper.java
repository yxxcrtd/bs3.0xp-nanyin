package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SttStressScene;
import com.joyintech.tams.common.base.entity.SttStressSceneKey;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface SttStressSceneMapper {
    int deleteByPrimaryKey(SttStressSceneKey key);

    int deleteByWorkflowId(String workflowId);

    int insert(SttStressScene record);

    int insertSelective(SttStressScene record);

    int insertBasicByBasicInfo(SttStressScene record);

    int insertBasicByWorkflowId(Map paraMap);

    SttStressScene selectByPrimaryKey(SttStressSceneKey key);

    int updateByPrimaryKeySelective(SttStressScene record);

    int updateByWorkflowIdSelective(SttStressScene record);

    int updateByWorkflowId(SttStressScene record);

    int updateByPrimaryKey(SttStressScene record);
}