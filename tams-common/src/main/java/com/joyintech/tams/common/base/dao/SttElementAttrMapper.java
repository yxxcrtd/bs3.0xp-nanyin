package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SttElementAttr;
import com.joyintech.tams.common.base.entity.SttElementAttrKey;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface SttElementAttrMapper {
    int deleteByPrimaryKey(SttElementAttrKey key);

    int deleteByWorkflowId(String workflowId);

    int insert(SttElementAttr record);

    int insertSelective(SttElementAttr record);

    int insertBasicByBasicInfo(SttElementAttr record);

    int insertBasicByWorkflowId(Map paraMap);

    SttElementAttr selectByPrimaryKey(SttElementAttrKey key);

    int updateByPrimaryKeySelective(SttElementAttr record);

    int updateByWorkflowIdSelective(SttElementAttr record);

    int updateByWorkflowId(SttElementAttr record);

    int updateByPrimaryKey(SttElementAttr record);
}