package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.AccpoolRule;
import com.joyintech.tams.common.base.entity.AccpoolRuleKey;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface AccpoolRuleMapper {
    int deleteByPrimaryKey(AccpoolRuleKey key);

    int deleteByWorkflowId(String workflowId);

    int updateByWorkflowIdSelective(AccpoolRule record);

    int updateByWorkflowId(AccpoolRule record);

    int insertBasicByWorkflowId(Map paraMap);

    int insert(AccpoolRule record);

    int insertSelective(AccpoolRule record);

    int insertBasicByBasicInfo(AccpoolRule record);

    AccpoolRule selectByPrimaryKey(AccpoolRuleKey key);

    int updateByPrimaryKeySelective(AccpoolRule record);

    int updateByPrimaryKey(AccpoolRule record);
}