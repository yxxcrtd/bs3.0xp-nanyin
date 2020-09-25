package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SttStressIndicator;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface SttStressIndicatorMapper {
    int deleteByPrimaryKey(String seqNo);

    int deleteByWorkflowId(String workflowId);

    int insert(SttStressIndicator record);

    int insertSelective(SttStressIndicator record);

    int insertBasicByBasicInfo(SttStressIndicator record);

    int insertBasicByWorkflowId(Map paraMap);

    SttStressIndicator selectByPrimaryKey(String seqNo);

    int updateByPrimaryKeySelective(SttStressIndicator record);

    int updateByWorkflowIdSelective(SttStressIndicator record);

    int updateByWorkflowId(SttStressIndicator record);

    int updateByPrimaryKey(SttStressIndicator record);
}