package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SttStressView;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface SttStressViewMapper {
    int deleteByPrimaryKey(String tmplId);

    int deleteByWorkflowId(String workflowId);

    int insert(SttStressView record);

    int insertSelective(SttStressView record);

    int insertBasicByBasicInfo(SttStressView record);

    int insertBasicByWorkflowId(Map paraMap);

    SttStressView selectByPrimaryKey(String tmplId);

    int updateByPrimaryKeySelective(SttStressView record);

    int updateByWorkflowIdSelective(SttStressView record);

    int updateByWorkflowId(SttStressView record);

    int updateByPrimaryKey(SttStressView record);
}