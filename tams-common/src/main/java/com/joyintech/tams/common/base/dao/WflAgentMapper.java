package com.joyintech.tams.common.base.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.WflAgent;
import com.joyintech.tams.common.base.entity.WflAgentKey;

@Repository
public interface WflAgentMapper {
    int deleteByPrimaryKey(WflAgentKey key);

    int deleteByWorkflowId(String workflowId);

    int updateByWorkflowIdSelective(WflAgent record);

    int updateByWorkflowId(WflAgent record);

    int insertBasicByWorkflowId(Map<String, Object> paraMap);

    int insert(WflAgent record);

    int insertSelective(WflAgent record);

    int insertBasicByBasicInfo(WflAgent record);

    WflAgent selectByPrimaryKey(WflAgentKey key);

    int updateByPrimaryKeySelective(WflAgent record);

    int updateByPrimaryKey(WflAgent record);
}