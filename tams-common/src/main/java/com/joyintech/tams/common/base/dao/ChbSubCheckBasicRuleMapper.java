package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbSubCheckBasicRule;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbSubCheckBasicRuleMapper {
    int deleteByPrimaryKey(String ruleId);

    int deleteByWorkflowId(String workflowId);

    int insert(ChbSubCheckBasicRule record);

    int insertSelective(ChbSubCheckBasicRule record);

    int insertBasicByBasicInfo(ChbSubCheckBasicRule record);

    int insertBasicByWorkflowId(Map paraMap);

    ChbSubCheckBasicRule selectByPrimaryKey(String ruleId);

    int updateByPrimaryKeySelective(ChbSubCheckBasicRule record);

    int updateByWorkflowIdSelective(ChbSubCheckBasicRule record);

    int updateByWorkflowId(ChbSubCheckBasicRule record);

    int updateByPrimaryKey(ChbSubCheckBasicRule record);
}