package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.WflRegFinProductDealAdd;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface WflRegFinProductDealAddMapper {
    int deleteByPrimaryKey(String tradeId);

    int deleteByWorkflowId(String workflowId);

    int updateByWorkflowIdSelective(WflRegFinProductDealAdd record);

    int updateByWorkflowId(WflRegFinProductDealAdd record);

    int insertBasicByWorkflowId(Map paraMap);

    int insert(WflRegFinProductDealAdd record);

    int insertSelective(WflRegFinProductDealAdd record);

    int insertBasicByBasicInfo(WflRegFinProductDealAdd record);

    WflRegFinProductDealAdd selectByPrimaryKey(String tradeId);

    int updateByPrimaryKeySelective(WflRegFinProductDealAdd record);

    int updateByPrimaryKey(WflRegFinProductDealAdd record);
}