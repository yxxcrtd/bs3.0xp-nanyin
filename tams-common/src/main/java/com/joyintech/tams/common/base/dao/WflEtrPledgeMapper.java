package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.WflEtrPledge;
import com.joyintech.tams.common.base.entity.WflEtrPledgeKey;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface WflEtrPledgeMapper {
    int deleteByPrimaryKey(WflEtrPledgeKey key);

    int deleteByWorkflowId(String workflowId);

    int updateByWorkflowIdSelective(WflEtrPledge record);

    int updateByWorkflowId(WflEtrPledge record);

    int insertBasicByWorkflowId(Map paraMap);

    int insert(WflEtrPledge record);

    int insertSelective(WflEtrPledge record);

    int insertBasicByBasicInfo(WflEtrPledge record);

    WflEtrPledge selectByPrimaryKey(WflEtrPledgeKey key);

    int updateByPrimaryKeySelective(WflEtrPledge record);

    int updateByPrimaryKey(WflEtrPledge record);
}