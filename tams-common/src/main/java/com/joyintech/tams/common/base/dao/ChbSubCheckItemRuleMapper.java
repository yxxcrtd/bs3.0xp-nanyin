package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbSubCheckItemRule;
import com.joyintech.tams.common.base.entity.ChbSubCheckItemRuleKey;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbSubCheckItemRuleMapper {
    int deleteByPrimaryKey(ChbSubCheckItemRuleKey key);

    int deleteByWorkflowId(String workflowId);

    int insert(ChbSubCheckItemRule record);

    int insertSelective(ChbSubCheckItemRule record);

    int insertBasicByBasicInfo(ChbSubCheckItemRule record);

    int insertBasicByWorkflowId(Map paraMap);

    ChbSubCheckItemRule selectByPrimaryKey(ChbSubCheckItemRuleKey key);

    int updateByPrimaryKeySelective(ChbSubCheckItemRule record);

    int updateByWorkflowIdSelective(ChbSubCheckItemRule record);

    int updateByWorkflowId(ChbSubCheckItemRule record);

    int updateByPrimaryKey(ChbSubCheckItemRule record);
}