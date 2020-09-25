package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SttFactorSuit;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface SttFactorSuitMapper {
    int deleteByPrimaryKey(String seqNo);

    int deleteByWorkflowId(String workflowId);

    int insert(SttFactorSuit record);

    int insertSelective(SttFactorSuit record);

    int insertBasicByBasicInfo(SttFactorSuit record);

    int insertBasicByWorkflowId(Map paraMap);

    SttFactorSuit selectByPrimaryKey(String seqNo);

    int updateByPrimaryKeySelective(SttFactorSuit record);

    int updateByWorkflowIdSelective(SttFactorSuit record);

    int updateByWorkflowId(SttFactorSuit record);

    int updateByPrimaryKey(SttFactorSuit record);
}