package com.joyintech.tams.common.base.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.WflBusinessNode;
import com.joyintech.tams.common.base.entity.WflBusinessNodeKey;

@Repository
public interface WflBusinessNodeMapper {
    int deleteByPrimaryKey(WflBusinessNodeKey key);

    int deleteByWorkflowId(String workflowId);

    int updateByWorkflowIdSelective(WflBusinessNode record);

    int updateByWorkflowId(WflBusinessNode record);

    int insertBasicByWorkflowId(Map<String, Object> paraMap);

    int insert(WflBusinessNode record);

    int insertSelective(WflBusinessNode record);

    int insertBasicByBasicInfo(WflBusinessNode record);

    WflBusinessNode selectByPrimaryKey(WflBusinessNodeKey key);

    int updateByPrimaryKeySelective(WflBusinessNode record);

    int updateByPrimaryKey(WflBusinessNode record);
}