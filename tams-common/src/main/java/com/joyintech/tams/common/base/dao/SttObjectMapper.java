package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SttObject;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface SttObjectMapper {
    int deleteByPrimaryKey(String objectId);

    int deleteByWorkflowId(String workflowId);

    int insert(SttObject record);

    int insertSelective(SttObject record);

    int insertBasicByBasicInfo(SttObject record);

    int insertBasicByWorkflowId(Map paraMap);

    SttObject selectByPrimaryKey(String objectId);

    int updateByPrimaryKeySelective(SttObject record);

    int updateByWorkflowIdSelective(SttObject record);

    int updateByWorkflowId(SttObject record);

    int updateByPrimaryKey(SttObject record);
}