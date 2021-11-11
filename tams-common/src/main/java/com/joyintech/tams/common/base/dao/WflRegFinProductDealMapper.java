package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.WflRegFinProductDeal;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface WflRegFinProductDealMapper {
    int deleteByPrimaryKey(String tradeId);

    int deleteByWorkflowId(String workflowId);

    int updateByWorkflowIdSelective(WflRegFinProductDeal record);

    int updateByWorkflowId(WflRegFinProductDeal record);

    int insertBasicByWorkflowId(Map paraMap);

    int insert(WflRegFinProductDeal record);

    int insertSelective(WflRegFinProductDeal record);

    int insertBasicByBasicInfo(WflRegFinProductDeal record);

    WflRegFinProductDeal selectByPrimaryKey(String tradeId);

    int updateByPrimaryKeySelective(WflRegFinProductDeal record);

    int updateByPrimaryKey(WflRegFinProductDeal record);
}