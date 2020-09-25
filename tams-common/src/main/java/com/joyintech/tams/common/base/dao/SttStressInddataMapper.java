package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SttStressInddata;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface SttStressInddataMapper {
    int deleteByPrimaryKey(String seqNo);

    int deleteByWorkflowId(String workflowId);

    int insert(SttStressInddata record);

    int insertSelective(SttStressInddata record);

    int insertBasicByBasicInfo(SttStressInddata record);

    int insertBasicByWorkflowId(Map paraMap);

    SttStressInddata selectByPrimaryKey(String seqNo);

    int updateByPrimaryKeySelective(SttStressInddata record);

    int updateByWorkflowIdSelective(SttStressInddata record);

    int updateByWorkflowId(SttStressInddata record);

    int updateByPrimaryKey(SttStressInddata record);
}