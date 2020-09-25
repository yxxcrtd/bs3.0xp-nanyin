package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbWflBusinessNode;
import com.joyintech.tams.common.base.entity.ChbWflBusinessNodeKey;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbWflBusinessNodeMapper {
    int deleteByPrimaryKey(ChbWflBusinessNodeKey key);

    int deleteByWorkflowId(String workflowId);

    int insert(ChbWflBusinessNode record);

    int insertSelective(ChbWflBusinessNode record);

    int insertBasicByBasicInfo(ChbWflBusinessNode record);

    int insertBasicByWorkflowId(Map paraMap);

    ChbWflBusinessNode selectByPrimaryKey(ChbWflBusinessNodeKey key);

    int updateByPrimaryKeySelective(ChbWflBusinessNode record);

    int updateByWorkflowIdSelective(ChbWflBusinessNode record);

    int updateByWorkflowId(ChbWflBusinessNode record);

    int updateByPrimaryKey(ChbWflBusinessNode record);
}