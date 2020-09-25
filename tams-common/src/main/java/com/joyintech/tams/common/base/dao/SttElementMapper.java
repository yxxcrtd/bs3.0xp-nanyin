package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SttElement;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface SttElementMapper {
    int deleteByPrimaryKey(String elementId);

    int deleteByWorkflowId(String workflowId);

    int insert(SttElement record);

    int insertSelective(SttElement record);

    int insertBasicByBasicInfo(SttElement record);

    int insertBasicByWorkflowId(Map paraMap);

    SttElement selectByPrimaryKey(String elementId);

    int updateByPrimaryKeySelective(SttElement record);

    int updateByWorkflowIdSelective(SttElement record);

    int updateByWorkflowId(SttElement record);

    int updateByPrimaryKey(SttElement record);
}