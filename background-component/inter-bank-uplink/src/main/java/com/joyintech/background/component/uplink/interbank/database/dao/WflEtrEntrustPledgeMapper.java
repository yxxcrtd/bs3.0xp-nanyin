package com.joyintech.background.component.uplink.interbank.database.dao;

import com.joyintech.background.component.uplink.interbank.database.entity.WflEtrEntrustPledge;
import com.joyintech.background.component.uplink.interbank.database.entity.WflEtrEntrustPledgeKey;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface WflEtrEntrustPledgeMapper {
    int deleteByPrimaryKey(WflEtrEntrustPledgeKey key);

    int deleteByWorkflowId(String workflowId);

    int updateByWorkflowIdSelective(WflEtrEntrustPledge record);

    int updateByWorkflowId(WflEtrEntrustPledge record);

    int insertBasicByWorkflowId(Map paraMap);

    int insert(WflEtrEntrustPledge record);

    int insertSelective(WflEtrEntrustPledge record);

    int insertBasicByBasicInfo(WflEtrEntrustPledge record);

    WflEtrEntrustPledge selectByPrimaryKey(WflEtrEntrustPledgeKey key);

    int updateByPrimaryKeySelective(WflEtrEntrustPledge record);

    int updateByPrimaryKey(WflEtrEntrustPledge record);
}