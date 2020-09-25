package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SttMappingDet;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface SttMappingDetMapper {
    int deleteByPrimaryKey(String detId);

    int deleteByWorkflowId(String workflowId);

    int insert(SttMappingDet record);

    int insertSelective(SttMappingDet record);

    int insertBasicByBasicInfo(SttMappingDet record);

    int insertBasicByWorkflowId(Map paraMap);

    SttMappingDet selectByPrimaryKey(String detId);

    int updateByPrimaryKeySelective(SttMappingDet record);

    int updateByWorkflowIdSelective(SttMappingDet record);

    int updateByWorkflowId(SttMappingDet record);

    int updateByPrimaryKey(SttMappingDet record);
}