package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.WflRegFinPledge;
import com.joyintech.tams.common.base.entity.WflRegFinPledgeKey;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface WflRegFinPledgeMapper {
    int deleteByPrimaryKey(WflRegFinPledgeKey key);

    int deleteByWorkflowId(String workflowId);

    int updateByWorkflowIdSelective(WflRegFinPledge record);

    int updateByWorkflowId(WflRegFinPledge record);

    int insertBasicByWorkflowId(Map paraMap);

    int insert(WflRegFinPledge record);

    int insertSelective(WflRegFinPledge record);

    int insertBasicByBasicInfo(WflRegFinPledge record);

    WflRegFinPledge selectByPrimaryKey(WflRegFinPledgeKey key);

    int updateByPrimaryKeySelective(WflRegFinPledge record);

    int updateByPrimaryKey(WflRegFinPledge record);
}