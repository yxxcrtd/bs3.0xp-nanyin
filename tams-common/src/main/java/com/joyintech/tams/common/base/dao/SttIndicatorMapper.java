package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SttIndicator;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface SttIndicatorMapper {
    int deleteByPrimaryKey(String indId);

    int deleteByWorkflowId(String workflowId);

    int insert(SttIndicator record);

    int insertSelective(SttIndicator record);

    int insertBasicByBasicInfo(SttIndicator record);

    int insertBasicByWorkflowId(Map paraMap);

    SttIndicator selectByPrimaryKey(String indId);

    int updateByPrimaryKeySelective(SttIndicator record);

    int updateByWorkflowIdSelective(SttIndicator record);

    int updateByWorkflowId(SttIndicator record);

    int updateByPrimaryKey(SttIndicator record);
}