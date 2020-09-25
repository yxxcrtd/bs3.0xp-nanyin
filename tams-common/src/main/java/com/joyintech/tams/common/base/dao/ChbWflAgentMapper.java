package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbWflAgent;
import com.joyintech.tams.common.base.entity.ChbWflAgentKey;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbWflAgentMapper {
    int deleteByPrimaryKey(ChbWflAgentKey key);

    int deleteByWorkflowId(String workflowId);

    int insert(ChbWflAgent record);

    int insertSelective(ChbWflAgent record);

    int insertBasicByBasicInfo(ChbWflAgent record);

    int insertBasicByWorkflowId(Map paraMap);

    ChbWflAgent selectByPrimaryKey(ChbWflAgentKey key);

    int updateByPrimaryKeySelective(ChbWflAgent record);

    int updateByWorkflowIdSelective(ChbWflAgent record);

    int updateByWorkflowId(ChbWflAgent record);

    int updateByPrimaryKey(ChbWflAgent record);
}