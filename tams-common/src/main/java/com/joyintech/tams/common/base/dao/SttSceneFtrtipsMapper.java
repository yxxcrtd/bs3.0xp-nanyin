package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SttSceneFtrtips;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface SttSceneFtrtipsMapper {
    int deleteByPrimaryKey(String sceneFtrtipsId);

    int deleteByWorkflowId(String workflowId);

    int insert(SttSceneFtrtips record);

    int insertSelective(SttSceneFtrtips record);

    int insertBasicByBasicInfo(SttSceneFtrtips record);

    int insertBasicByWorkflowId(Map paraMap);

    SttSceneFtrtips selectByPrimaryKey(String sceneFtrtipsId);

    int updateByPrimaryKeySelective(SttSceneFtrtips record);

    int updateByWorkflowIdSelective(SttSceneFtrtips record);

    int updateByWorkflowId(SttSceneFtrtips record);

    int updateByPrimaryKey(SttSceneFtrtips record);
}