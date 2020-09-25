package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SttObjectAttr;
import com.joyintech.tams.common.base.entity.SttObjectAttrKey;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface SttObjectAttrMapper {
    int deleteByPrimaryKey(SttObjectAttrKey key);

    int deleteByWorkflowId(String workflowId);

    int insert(SttObjectAttr record);

    int insertSelective(SttObjectAttr record);

    int insertBasicByBasicInfo(SttObjectAttr record);

    int insertBasicByWorkflowId(Map paraMap);

    SttObjectAttr selectByPrimaryKey(SttObjectAttrKey key);

    int updateByPrimaryKeySelective(SttObjectAttr record);

    int updateByWorkflowIdSelective(SttObjectAttr record);

    int updateByWorkflowId(SttObjectAttr record);

    int updateByPrimaryKey(SttObjectAttr record);
}