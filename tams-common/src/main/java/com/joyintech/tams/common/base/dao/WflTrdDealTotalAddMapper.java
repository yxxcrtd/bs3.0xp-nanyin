package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.WflTrdDealTotalAdd;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface WflTrdDealTotalAddMapper {
    int deleteByPrimaryKey(String totleTradeId);

    int deleteByWorkflowId(String workflowId);

    int updateByWorkflowIdSelective(WflTrdDealTotalAdd record);

    int updateByWorkflowId(WflTrdDealTotalAdd record);

    int insertBasicByWorkflowId(Map paraMap);

    int insert(WflTrdDealTotalAdd record);

    int insertSelective(WflTrdDealTotalAdd record);

    int insertBasicByBasicInfo(WflTrdDealTotalAdd record);

    WflTrdDealTotalAdd selectByPrimaryKey(String totleTradeId);

    int updateByPrimaryKeySelective(WflTrdDealTotalAdd record);

    int updateByPrimaryKey(WflTrdDealTotalAdd record);
}