package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SttScene;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface SttSceneMapper {
    int deleteByPrimaryKey(String sceneId);

    int deleteByWorkflowId(String workflowId);

    int insert(SttScene record);

    int insertSelective(SttScene record);

    int insertBasicByBasicInfo(SttScene record);

    int insertBasicByWorkflowId(Map paraMap);

    SttScene selectByPrimaryKey(String sceneId);

    int updateByPrimaryKeySelective(SttScene record);

    int updateByWorkflowIdSelective(SttScene record);

    int updateByWorkflowId(SttScene record);

    int updateByPrimaryKey(SttScene record);
}