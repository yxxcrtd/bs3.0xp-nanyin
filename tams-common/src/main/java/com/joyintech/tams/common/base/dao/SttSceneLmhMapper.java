package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SttSceneLmh;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface SttSceneLmhMapper {
    int deleteByPrimaryKey(String sceneFtrId);

    int deleteByWorkflowId(String workflowId);

    int insert(SttSceneLmh record);

    int insertSelective(SttSceneLmh record);

    int insertBasicByBasicInfo(SttSceneLmh record);

    int insertBasicByWorkflowId(Map paraMap);

    SttSceneLmh selectByPrimaryKey(String sceneFtrId);

    int updateByPrimaryKeySelective(SttSceneLmh record);

    int updateByWorkflowIdSelective(SttSceneLmh record);

    int updateByWorkflowId(SttSceneLmh record);

    int updateByPrimaryKey(SttSceneLmh record);
}