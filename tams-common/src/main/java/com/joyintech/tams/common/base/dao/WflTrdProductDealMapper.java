package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.WflTrdProductDeal;
import com.joyintech.tams.common.base.entity.WflTrdProductDealKey;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface WflTrdProductDealMapper {
    int deleteByPrimaryKey(WflTrdProductDealKey key);

    int deleteByWorkflowId(String workflowId);

    int updateByWorkflowIdSelective(WflTrdProductDeal record);

    int updateByWorkflowId(WflTrdProductDeal record);

    int insertBasicByWorkflowId(Map paraMap);

    int insert(WflTrdProductDeal record);

    int insertSelective(WflTrdProductDeal record);

    int insertBasicByBasicInfo(WflTrdProductDeal record);

    WflTrdProductDeal selectByPrimaryKey(WflTrdProductDealKey key);

    int updateByPrimaryKeySelective(WflTrdProductDeal record);

    int updateByPrimaryKey(WflTrdProductDeal record);
}