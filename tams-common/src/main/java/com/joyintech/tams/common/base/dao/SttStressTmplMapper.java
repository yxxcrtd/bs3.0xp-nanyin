package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SttStressTmpl;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface SttStressTmplMapper {
    int deleteByPrimaryKey(String tmplId);

    int deleteByWorkflowId(String workflowId);

    int insert(SttStressTmpl record);

    int insertSelective(SttStressTmpl record);

    int insertBasicByBasicInfo(SttStressTmpl record);

    int insertBasicByWorkflowId(Map paraMap);

    SttStressTmpl selectByPrimaryKey(String tmplId);

    int updateByPrimaryKeySelective(SttStressTmpl record);

    int updateByWorkflowIdSelective(SttStressTmpl record);

    int updateByWorkflowId(SttStressTmpl record);

    int updateByPrimaryKey(SttStressTmpl record);
}