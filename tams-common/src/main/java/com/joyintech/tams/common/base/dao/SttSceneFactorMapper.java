package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SttSceneFactor;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface SttSceneFactorMapper {
    int deleteByPrimaryKey(String sceneFtrId);

    int deleteByWorkflowId(String workflowId);

    int insert(SttSceneFactor record);

    int insertSelective(SttSceneFactor record);

    int insertBasicByBasicInfo(SttSceneFactor record);

    int insertBasicByWorkflowId(Map paraMap);

    SttSceneFactor selectByPrimaryKey(String sceneFtrId);

    int updateByPrimaryKeySelective(SttSceneFactor record);

    int updateByWorkflowIdSelective(SttSceneFactor record);

    int updateByWorkflowId(SttSceneFactor record);

    int updateByPrimaryKey(SttSceneFactor record);
}