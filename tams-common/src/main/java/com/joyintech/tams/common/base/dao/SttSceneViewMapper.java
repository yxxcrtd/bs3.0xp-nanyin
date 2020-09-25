package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SttSceneView;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface SttSceneViewMapper {
    int deleteByPrimaryKey(String sceneId);

    int deleteByWorkflowId(String workflowId);

    int insert(SttSceneView record);

    int insertSelective(SttSceneView record);

    int insertBasicByBasicInfo(SttSceneView record);

    int insertBasicByWorkflowId(Map paraMap);

    SttSceneView selectByPrimaryKey(String sceneId);

    int updateByPrimaryKeySelective(SttSceneView record);

    int updateByWorkflowIdSelective(SttSceneView record);

    int updateByWorkflowId(SttSceneView record);

    int updateByPrimaryKey(SttSceneView record);
}