package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.ChbInvestTradeErrodataWithBLOBs;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface ChbInvestTradeErrodataMapper {
    int deleteByWorkflowId(String workflowId);

    int updateByWorkflowIdSelective(ChbInvestTradeErrodataWithBLOBs record);

    int updateByWorkflowId(ChbInvestTradeErrodataWithBLOBs record);

    int insertBasicByWorkflowId(Map paraMap);

    int insert(ChbInvestTradeErrodataWithBLOBs record);

    int insertSelective(ChbInvestTradeErrodataWithBLOBs record);

    int insertBasicByBasicInfo(ChbInvestTradeErrodataWithBLOBs record);
}