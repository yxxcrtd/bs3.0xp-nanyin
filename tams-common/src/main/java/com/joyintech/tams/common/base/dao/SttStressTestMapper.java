package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SttStressTest;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface SttStressTestMapper {
    int deleteByPrimaryKey(String testId);

    int deleteByWorkflowId(String workflowId);

    int insert(SttStressTest record);

    int insertSelective(SttStressTest record);

    int insertBasicByBasicInfo(SttStressTest record);

    int insertBasicByWorkflowId(Map paraMap);

    SttStressTest selectByPrimaryKey(String testId);

    int updateByPrimaryKeySelective(SttStressTest record);

    int updateByWorkflowIdSelective(SttStressTest record);

    int updateByWorkflowId(SttStressTest record);

    int updateByPrimaryKey(SttStressTest record);
}