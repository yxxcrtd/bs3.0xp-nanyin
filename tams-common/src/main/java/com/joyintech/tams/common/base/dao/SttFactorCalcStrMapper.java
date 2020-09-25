package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.SttFactorCalcStr;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface SttFactorCalcStrMapper {
    int deleteByPrimaryKey(String strId);

    int deleteByWorkflowId(String workflowId);

    int insert(SttFactorCalcStr record);

    int insertSelective(SttFactorCalcStr record);

    int insertBasicByBasicInfo(SttFactorCalcStr record);

    int insertBasicByWorkflowId(Map paraMap);

    SttFactorCalcStr selectByPrimaryKey(String strId);

    int updateByPrimaryKeySelective(SttFactorCalcStr record);

    int updateByWorkflowIdSelective(SttFactorCalcStr record);

    int updateByWorkflowId(SttFactorCalcStr record);

    int updateByPrimaryKey(SttFactorCalcStr record);
}