package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SttModelInput;
import com.joyintech.tams.common.base.entity.SttModelInputKey;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface SttModelInputMapper {
    int deleteByPrimaryKey(SttModelInputKey key);

    int deleteByWorkflowId(String workflowId);

    int insert(SttModelInput record);

    int insertSelective(SttModelInput record);

    int insertBasicByBasicInfo(SttModelInput record);

    int insertBasicByWorkflowId(Map paraMap);

    SttModelInput selectByPrimaryKey(SttModelInputKey key);

    int updateByPrimaryKeySelective(SttModelInput record);

    int updateByWorkflowIdSelective(SttModelInput record);

    int updateByWorkflowId(SttModelInput record);

    int updateByPrimaryKey(SttModelInput record);
}