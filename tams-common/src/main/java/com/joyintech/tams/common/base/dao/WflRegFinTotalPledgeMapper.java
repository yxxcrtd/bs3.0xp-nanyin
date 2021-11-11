package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.WflRegFinTotalPledge;
import com.joyintech.tams.common.base.entity.WflRegFinTotalPledgeKey;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface WflRegFinTotalPledgeMapper {
    int deleteByPrimaryKey(WflRegFinTotalPledgeKey key);

    int deleteByWorkflowId(String workflowId);

    int updateByWorkflowIdSelective(WflRegFinTotalPledge record);

    int updateByWorkflowId(WflRegFinTotalPledge record);

    int insertBasicByWorkflowId(Map paraMap);

    int insert(WflRegFinTotalPledge record);

    int insertSelective(WflRegFinTotalPledge record);

    int insertBasicByBasicInfo(WflRegFinTotalPledge record);

    WflRegFinTotalPledge selectByPrimaryKey(WflRegFinTotalPledgeKey key);

    int updateByPrimaryKeySelective(WflRegFinTotalPledge record);

    int updateByPrimaryKey(WflRegFinTotalPledge record);
}