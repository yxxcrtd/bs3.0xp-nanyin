package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.WflFinProductDealAdd;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface WflFinProductDealAddMapper {
    int deleteByPrimaryKey(String tradeId);

    int deleteByWorkflowId(String workflowId);

    int updateByWorkflowIdSelective(WflFinProductDealAdd record);

    int updateByWorkflowId(WflFinProductDealAdd record);

    int insertBasicByWorkflowId(Map paraMap);

    int insert(WflFinProductDealAdd record);

    int insertSelective(WflFinProductDealAdd record);

    int insertBasicByBasicInfo(WflFinProductDealAdd record);

    WflFinProductDealAdd selectByPrimaryKey(String tradeId);

    int updateByPrimaryKeySelective(WflFinProductDealAdd record);

    int updateByPrimaryKey(WflFinProductDealAdd record);
}