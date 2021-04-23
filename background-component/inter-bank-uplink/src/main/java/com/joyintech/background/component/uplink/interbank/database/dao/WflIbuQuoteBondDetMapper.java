package com.joyintech.background.component.uplink.interbank.database.dao;

import java.util.Map;
import org.springframework.stereotype.Repository;
import com.joyintech.background.component.uplink.interbank.database.entity.WflIbuQuoteBondDet;

@Repository
public interface WflIbuQuoteBondDetMapper {
    int deleteByPrimaryKey(String detId);

    int deleteByWorkflowId(String workflowId);

    int updateByWorkflowIdSelective(WflIbuQuoteBondDet record);

    int updateByWorkflowId(WflIbuQuoteBondDet record);

    int insertBasicByWorkflowId(Map paraMap);

    int insert(WflIbuQuoteBondDet record);

    int insertSelective(WflIbuQuoteBondDet record);

    int insertBasicByBasicInfo(WflIbuQuoteBondDet record);

    WflIbuQuoteBondDet selectByPrimaryKey(String detId);

    int updateByPrimaryKeySelective(WflIbuQuoteBondDet record);

    int updateByPrimaryKey(WflIbuQuoteBondDet record);
}