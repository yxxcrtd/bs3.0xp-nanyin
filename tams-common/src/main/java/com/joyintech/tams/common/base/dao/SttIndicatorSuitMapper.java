package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SttIndicatorSuit;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface SttIndicatorSuitMapper {
    int deleteByPrimaryKey(String seqNo);

    int deleteByWorkflowId(String workflowId);

    int insert(SttIndicatorSuit record);

    int insertSelective(SttIndicatorSuit record);

    int insertBasicByBasicInfo(SttIndicatorSuit record);

    int insertBasicByWorkflowId(Map paraMap);

    SttIndicatorSuit selectByPrimaryKey(String seqNo);

    int updateByPrimaryKeySelective(SttIndicatorSuit record);

    int updateByWorkflowIdSelective(SttIndicatorSuit record);

    int updateByWorkflowId(SttIndicatorSuit record);

    int updateByPrimaryKey(SttIndicatorSuit record);
}