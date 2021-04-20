package com.joyintech.background.component.uplink.interbank.database.dao;

import java.util.Map;

import com.joyintech.background.component.uplink.interbank.database.entity.WflIbuQuoteEntCrepPledge;
import com.joyintech.background.component.uplink.interbank.database.entity.WflIbuQuoteEntCrepPledgeKey;
import org.springframework.stereotype.Repository;

@Repository
public interface WflIbuQuoteEntCrepPledgeMapper {
    int deleteByPrimaryKey(WflIbuQuoteEntCrepPledgeKey key);

    int deleteByWorkflowId(String workflowId);

    int updateByWorkflowIdSelective(WflIbuQuoteEntCrepPledge record);

    int updateByWorkflowId(WflIbuQuoteEntCrepPledge record);

    int insertBasicByWorkflowId(Map paraMap);

    int insert(WflIbuQuoteEntCrepPledge record);

    int insertSelective(WflIbuQuoteEntCrepPledge record);

    int insertBasicByBasicInfo(WflIbuQuoteEntCrepPledge record);

    WflIbuQuoteEntCrepPledge selectByPrimaryKey(WflIbuQuoteEntCrepPledgeKey key);

    int updateByPrimaryKeySelective(WflIbuQuoteEntCrepPledge record);

    int updateByPrimaryKey(WflIbuQuoteEntCrepPledge record);
}