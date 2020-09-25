package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.WflPaTrdProductDeal;
import com.joyintech.tams.common.base.entity.WflPaTrdProductDealKey;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface WflPaTrdProductDealMapper {
    int deleteByPrimaryKey(WflPaTrdProductDealKey key);

    int deleteByWorkflowId(String workflowId);

    int updateByWorkflowIdSelective(WflPaTrdProductDeal record);

    int updateByWorkflowId(WflPaTrdProductDeal record);

    int insertBasicByWorkflowId(Map paraMap);

    int insert(WflPaTrdProductDeal record);

    int insertSelective(WflPaTrdProductDeal record);

    int insertBasicByBasicInfo(WflPaTrdProductDeal record);

    WflPaTrdProductDeal selectByPrimaryKey(WflPaTrdProductDealKey key);

    int updateByPrimaryKeySelective(WflPaTrdProductDeal record);

    int updateByPrimaryKey(WflPaTrdProductDeal record);
}