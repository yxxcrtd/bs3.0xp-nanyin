package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SttObjectConattr;
import com.joyintech.tams.common.base.entity.SttObjectConattrKey;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface SttObjectConattrMapper {
    int deleteByPrimaryKey(SttObjectConattrKey key);

    int deleteByWorkflowId(String workflowId);

    int insert(SttObjectConattr record);

    int insertSelective(SttObjectConattr record);

    int insertBasicByBasicInfo(SttObjectConattr record);

    int insertBasicByWorkflowId(Map paraMap);

    SttObjectConattr selectByPrimaryKey(SttObjectConattrKey key);

    int updateByPrimaryKeySelective(SttObjectConattr record);

    int updateByWorkflowIdSelective(SttObjectConattr record);

    int updateByWorkflowId(SttObjectConattr record);

    int updateByPrimaryKey(SttObjectConattr record);
}