package com.joyintech.tams.common.base.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.WflBusinessList;

@Repository
public interface WflBusinessListMapper {
    int deleteByPrimaryKey(String seqNo);

    int deleteByWorkflowId(String workflowId);

    int updateByWorkflowIdSelective(WflBusinessList record);

    int updateByWorkflowId(WflBusinessList record);

    int insertBasicByWorkflowId(Map<String, Object> paraMap);

    int insert(WflBusinessList record);

    int insertSelective(WflBusinessList record);

    int insertBasicByBasicInfo(WflBusinessList record);

    WflBusinessList selectByPrimaryKey(String seqNo);

    int updateByPrimaryKeySelective(WflBusinessList record);

    int updateByPrimaryKey(WflBusinessList record);
}