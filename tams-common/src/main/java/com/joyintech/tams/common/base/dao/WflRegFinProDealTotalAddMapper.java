package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.WflRegFinProDealTotalAdd;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface WflRegFinProDealTotalAddMapper {
    int deleteByPrimaryKey(String totleTradeId);

    int deleteByWorkflowId(String workflowId);

    int updateByWorkflowIdSelective(WflRegFinProDealTotalAdd record);

    int updateByWorkflowId(WflRegFinProDealTotalAdd record);

    int insertBasicByWorkflowId(Map paraMap);

    int insert(WflRegFinProDealTotalAdd record);

    int insertSelective(WflRegFinProDealTotalAdd record);

    int insertBasicByBasicInfo(WflRegFinProDealTotalAdd record);

    WflRegFinProDealTotalAdd selectByPrimaryKey(String totleTradeId);

    int updateByPrimaryKeySelective(WflRegFinProDealTotalAdd record);

    int updateByPrimaryKey(WflRegFinProDealTotalAdd record);
}