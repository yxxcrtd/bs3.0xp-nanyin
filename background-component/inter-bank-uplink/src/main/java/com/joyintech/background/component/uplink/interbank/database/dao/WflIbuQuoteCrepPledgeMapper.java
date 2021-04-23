package com.joyintech.background.component.uplink.interbank.database.dao;


import java.util.Map;
import org.springframework.stereotype.Repository;

import com.joyintech.background.component.uplink.interbank.database.entity.WflIbuQuoteCrepPledge;
import com.joyintech.background.component.uplink.interbank.database.entity.WflIbuQuoteCrepPledgeKey;

@Repository
public interface WflIbuQuoteCrepPledgeMapper {
    int deleteByPrimaryKey(WflIbuQuoteCrepPledgeKey key);

    int deleteByWorkflowId(String workflowId);

    int updateByWorkflowIdSelective(WflIbuQuoteCrepPledge record);

    int updateByWorkflowId(WflIbuQuoteCrepPledge record);

    int insertBasicByWorkflowId(Map paraMap);

    int insert(WflIbuQuoteCrepPledge record);

    int insertSelective(WflIbuQuoteCrepPledge record);

    int insertBasicByBasicInfo(WflIbuQuoteCrepPledge record);

    WflIbuQuoteCrepPledge selectByPrimaryKey(WflIbuQuoteCrepPledgeKey key);

    int updateByPrimaryKeySelective(WflIbuQuoteCrepPledge record);

    int updateByPrimaryKey(WflIbuQuoteCrepPledge record);
}