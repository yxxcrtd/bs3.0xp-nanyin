package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SttFileTmpl;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface SttFileTmplMapper {
    int deleteByPrimaryKey(String tmplId);

    int deleteByWorkflowId(String workflowId);

    int insert(SttFileTmpl record);

    int insertSelective(SttFileTmpl record);

    int insertBasicByBasicInfo(SttFileTmpl record);

    int insertBasicByWorkflowId(Map paraMap);

    SttFileTmpl selectByPrimaryKey(String tmplId);

    int updateByPrimaryKeySelective(SttFileTmpl record);

    int updateByWorkflowIdSelective(SttFileTmpl record);

    int updateByWorkflowId(SttFileTmpl record);

    int updateByPrimaryKey(SttFileTmpl record);
}