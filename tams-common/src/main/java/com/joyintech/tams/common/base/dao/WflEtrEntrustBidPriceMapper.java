package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.WflEtrEntrustBidPrice;
import com.joyintech.tams.common.base.entity.WflEtrEntrustBidPriceKey;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface WflEtrEntrustBidPriceMapper {
    int deleteByPrimaryKey(WflEtrEntrustBidPriceKey key);

    int deleteByWorkflowId(String workflowId);

    int updateByWorkflowIdSelective(WflEtrEntrustBidPrice record);

    int updateByWorkflowId(WflEtrEntrustBidPrice record);

    int insertBasicByWorkflowId(Map paraMap);

    int insert(WflEtrEntrustBidPrice record);

    int insertSelective(WflEtrEntrustBidPrice record);

    int insertBasicByBasicInfo(WflEtrEntrustBidPrice record);

    WflEtrEntrustBidPrice selectByPrimaryKey(WflEtrEntrustBidPriceKey key);

    int updateByPrimaryKeySelective(WflEtrEntrustBidPrice record);

    int updateByPrimaryKey(WflEtrEntrustBidPrice record);
}