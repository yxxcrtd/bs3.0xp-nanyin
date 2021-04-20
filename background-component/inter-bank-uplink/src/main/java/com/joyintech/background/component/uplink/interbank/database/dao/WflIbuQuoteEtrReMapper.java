package com.joyintech.background.component.uplink.interbank.database.dao;

import com.joyintech.background.component.uplink.interbank.database.entity.WflIbuQuoteEtrRe;
import com.joyintech.background.component.uplink.interbank.database.entity.WflIbuQuoteEtrReKey;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface WflIbuQuoteEtrReMapper {
    int deleteByPrimaryKey(WflIbuQuoteEtrReKey key);

    int deleteByWorkflowId(String workflowId);

    int updateByWorkflowIdSelective(WflIbuQuoteEtrRe record);

    int updateByWorkflowId(WflIbuQuoteEtrRe record);

    int insertBasicByWorkflowId(Map paraMap);

    int insert(WflIbuQuoteEtrRe record);

    int insertSelective(WflIbuQuoteEtrRe record);

    int insertBasicByBasicInfo(WflIbuQuoteEtrRe record);

    WflIbuQuoteEtrRe selectByPrimaryKey(WflIbuQuoteEtrReKey key);

    int updateByPrimaryKeySelective(WflIbuQuoteEtrRe record);

    int updateByPrimaryKey(WflIbuQuoteEtrRe record);
}