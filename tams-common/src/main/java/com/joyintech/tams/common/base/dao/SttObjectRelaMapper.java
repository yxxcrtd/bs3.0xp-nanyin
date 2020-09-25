package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SttObjectRela;
import com.joyintech.tams.common.base.entity.SttObjectRelaKey;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface SttObjectRelaMapper {
    int deleteByPrimaryKey(SttObjectRelaKey key);

    int deleteByWorkflowId(String workflowId);

    int insert(SttObjectRela record);

    int insertSelective(SttObjectRela record);

    int insertBasicByBasicInfo(SttObjectRela record);

    int insertBasicByWorkflowId(Map paraMap);

    SttObjectRela selectByPrimaryKey(SttObjectRelaKey key);

    int updateByPrimaryKeySelective(SttObjectRela record);

    int updateByWorkflowIdSelective(SttObjectRela record);

    int updateByWorkflowId(SttObjectRela record);

    int updateByPrimaryKey(SttObjectRela record);
}