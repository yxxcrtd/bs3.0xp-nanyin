package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.WflEtrTarget;
import com.joyintech.tams.common.base.entity.WflEtrTargetKey;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface WflEtrTargetMapper {
    int deleteByPrimaryKey(WflEtrTargetKey key);

    int deleteByWorkflowId(String workflowId);

    int updateByWorkflowIdSelective(WflEtrTarget record);

    int updateByWorkflowId(WflEtrTarget record);

    int insertBasicByWorkflowId(Map paraMap);

    int insert(WflEtrTarget record);

    int insertSelective(WflEtrTarget record);

    int insertBasicByBasicInfo(WflEtrTarget record);

    WflEtrTarget selectByPrimaryKey(WflEtrTargetKey key);

    int updateByPrimaryKeySelective(WflEtrTarget record);

    int updateByPrimaryKey(WflEtrTarget record);
}