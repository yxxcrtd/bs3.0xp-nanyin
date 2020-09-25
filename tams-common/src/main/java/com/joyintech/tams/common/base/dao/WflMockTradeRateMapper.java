package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.WflMockTradeRate;
import com.joyintech.tams.common.base.entity.WflMockTradeRateKey;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface WflMockTradeRateMapper {
    int deleteByPrimaryKey(WflMockTradeRateKey key);

    int deleteByWorkflowId(String workflowId);

    int updateByWorkflowIdSelective(WflMockTradeRate record);

    int updateByWorkflowId(WflMockTradeRate record);

    int insertBasicByWorkflowId(Map paraMap);

    int insert(WflMockTradeRate record);

    int insertSelective(WflMockTradeRate record);

    int insertBasicByBasicInfo(WflMockTradeRate record);

    WflMockTradeRate selectByPrimaryKey(WflMockTradeRateKey key);

    int updateByPrimaryKeySelective(WflMockTradeRate record);

    int updateByPrimaryKey(WflMockTradeRate record);
}