package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SttFactor;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface SttFactorMapper {
    int deleteByPrimaryKey(String ftrId);

    int deleteByWorkflowId(String workflowId);

    int insert(SttFactor record);

    int insertSelective(SttFactor record);

    int insertBasicByBasicInfo(SttFactor record);

    int insertBasicByWorkflowId(Map paraMap);

    SttFactor selectByPrimaryKey(String ftrId);

    int updateByPrimaryKeySelective(SttFactor record);

    int updateByWorkflowIdSelective(SttFactor record);

    int updateByWorkflowId(SttFactor record);

    int updateByPrimaryKey(SttFactor record);
}