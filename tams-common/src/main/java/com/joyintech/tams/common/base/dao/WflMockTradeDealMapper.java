package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.WflMockTradeDeal;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface WflMockTradeDealMapper {
    int deleteByPrimaryKey(String tradeId);

    int deleteByWorkflowId(String workflowId);

    int updateByWorkflowIdSelective(WflMockTradeDeal record);

    int updateByWorkflowId(WflMockTradeDeal record);

    int insertBasicByWorkflowId(Map paraMap);

    int insert(WflMockTradeDeal record);

    int insertSelective(WflMockTradeDeal record);

    int insertBasicByBasicInfo(WflMockTradeDeal record);

    WflMockTradeDeal selectByPrimaryKey(String tradeId);

    int updateByPrimaryKeySelective(WflMockTradeDeal record);

    int updateByPrimaryKey(WflMockTradeDeal record);
}