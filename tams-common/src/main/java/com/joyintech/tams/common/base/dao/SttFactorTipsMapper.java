package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SttFactorTips;
import com.joyintech.tams.common.base.entity.SttFactorTipsKey;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface SttFactorTipsMapper {
    int deleteByPrimaryKey(SttFactorTipsKey key);

    int deleteByWorkflowId(String workflowId);

    int insert(SttFactorTips record);

    int insertSelective(SttFactorTips record);

    int insertBasicByBasicInfo(SttFactorTips record);

    int insertBasicByWorkflowId(Map paraMap);

    SttFactorTips selectByPrimaryKey(SttFactorTipsKey key);

    int updateByPrimaryKeySelective(SttFactorTips record);

    int updateByWorkflowIdSelective(SttFactorTips record);

    int updateByWorkflowId(SttFactorTips record);

    int updateByPrimaryKey(SttFactorTips record);
}