package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SttSceneTips;
import com.joyintech.tams.common.base.entity.SttSceneTipsKey;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface SttSceneTipsMapper {
    int deleteByPrimaryKey(SttSceneTipsKey key);

    int deleteByWorkflowId(String workflowId);

    int insert(SttSceneTips record);

    int insertSelective(SttSceneTips record);

    int insertBasicByBasicInfo(SttSceneTips record);

    int insertBasicByWorkflowId(Map paraMap);

    SttSceneTips selectByPrimaryKey(SttSceneTipsKey key);

    int updateByPrimaryKeySelective(SttSceneTips record);

    int updateByWorkflowIdSelective(SttSceneTips record);

    int updateByWorkflowId(SttSceneTips record);

    int updateByPrimaryKey(SttSceneTips record);
}