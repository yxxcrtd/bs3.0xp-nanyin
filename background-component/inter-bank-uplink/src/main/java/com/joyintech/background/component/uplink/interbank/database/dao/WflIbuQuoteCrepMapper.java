package com.joyintech.background.component.uplink.interbank.database.dao;

import java.util.Map;
import org.springframework.stereotype.Repository;
import com.joyintech.background.component.uplink.interbank.database.entity.WflIbuQuoteCrep;

@Repository
public interface WflIbuQuoteCrepMapper {
    int deleteByPrimaryKey(String quoteId);

    int deleteByWorkflowId(String workflowId);

    int updateByWorkflowIdSelective(WflIbuQuoteCrep record);

    int updateByWorkflowId(WflIbuQuoteCrep record);

    int insertBasicByWorkflowId(Map paraMap);

    int insert(WflIbuQuoteCrep record);

    int insertSelective(WflIbuQuoteCrep record);

    int insertBasicByBasicInfo(WflIbuQuoteCrep record);

    WflIbuQuoteCrep selectByPrimaryKey(String quoteId);

    int updateByPrimaryKeySelective(WflIbuQuoteCrep record);

    int updateByPrimaryKey(WflIbuQuoteCrep record);
}