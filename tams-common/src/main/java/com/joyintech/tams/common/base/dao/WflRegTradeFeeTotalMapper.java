package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.WflRegTradeFeeTotal;
import com.joyintech.tams.common.base.entity.WflRegTradeFeeTotalKey;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface WflRegTradeFeeTotalMapper {
    int deleteByPrimaryKey(WflRegTradeFeeTotalKey key);

    int deleteByWorkflowId(String workflowId);

    int updateByWorkflowIdSelective(WflRegTradeFeeTotal record);

    int updateByWorkflowId(WflRegTradeFeeTotal record);

    int insertBasicByWorkflowId(Map paraMap);

    int insert(WflRegTradeFeeTotal record);

    int insertSelective(WflRegTradeFeeTotal record);

    int insertBasicByBasicInfo(WflRegTradeFeeTotal record);

    WflRegTradeFeeTotal selectByPrimaryKey(WflRegTradeFeeTotalKey key);

    int updateByPrimaryKeySelective(WflRegTradeFeeTotal record);

    int updateByPrimaryKey(WflRegTradeFeeTotal record);
}