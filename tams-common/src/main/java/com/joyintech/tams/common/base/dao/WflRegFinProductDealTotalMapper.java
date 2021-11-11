package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.WflRegFinProductDealTotal;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface WflRegFinProductDealTotalMapper {
    int deleteByPrimaryKey(String totleTradeId);

    int deleteByWorkflowId(String workflowId);

    int updateByWorkflowIdSelective(WflRegFinProductDealTotal record);

    int updateByWorkflowId(WflRegFinProductDealTotal record);

    int insertBasicByWorkflowId(Map paraMap);

    int insert(WflRegFinProductDealTotal record);

    int insertSelective(WflRegFinProductDealTotal record);

    int insertBasicByBasicInfo(WflRegFinProductDealTotal record);

    WflRegFinProductDealTotal selectByPrimaryKey(String totleTradeId);

    int updateByPrimaryKeySelective(WflRegFinProductDealTotal record);

    int updateByPrimaryKey(WflRegFinProductDealTotal record);
}