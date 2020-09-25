package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SttMapping;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface SttMappingMapper {
    int deleteByPrimaryKey(String mappingId);

    int deleteByWorkflowId(String workflowId);

    int insert(SttMapping record);

    int insertSelective(SttMapping record);

    int insertBasicByBasicInfo(SttMapping record);

    int insertBasicByWorkflowId(Map paraMap);

    SttMapping selectByPrimaryKey(String mappingId);

    int updateByPrimaryKeySelective(SttMapping record);

    int updateByWorkflowIdSelective(SttMapping record);

    int updateByWorkflowId(SttMapping record);

    int updateByPrimaryKey(SttMapping record);
}