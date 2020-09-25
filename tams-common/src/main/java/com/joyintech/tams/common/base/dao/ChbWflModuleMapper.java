package com.joyintech.tams.common.base.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.ChbWflModule;

@Repository
public interface ChbWflModuleMapper {
    int deleteByPrimaryKey(String seqNo);

    int deleteByWorkflowId(String workflowId);

    int insert(ChbWflModule record);

    int insertSelective(ChbWflModule record);

    int insertBasicByBasicInfo(ChbWflModule record);

    int insertBasicByWorkflowId(Map paraMap);

    ChbWflModule selectByPrimaryKey(String seqNo);

    int updateByPrimaryKeySelective(ChbWflModule record);

    int updateByWorkflowIdSelective(ChbWflModule record);

    int updateByWorkflowId(ChbWflModule record);

    int updateByPrimaryKey(ChbWflModule record);
}