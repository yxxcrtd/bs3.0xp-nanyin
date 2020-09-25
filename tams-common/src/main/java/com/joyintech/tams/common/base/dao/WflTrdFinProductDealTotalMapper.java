package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.WflTrdFinProductDealTotal;
import com.joyintech.tams.common.base.entity.WflTrdFinProductDealTotalKey;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface WflTrdFinProductDealTotalMapper {
    int deleteByPrimaryKey(WflTrdFinProductDealTotalKey key);

    int deleteByWorkflowId(String workflowId);

    int updateByWorkflowIdSelective(WflTrdFinProductDealTotal record);

    int updateByWorkflowId(WflTrdFinProductDealTotal record);

    int insertBasicByWorkflowId(Map paraMap);

    int insert(WflTrdFinProductDealTotal record);

    int insertSelective(WflTrdFinProductDealTotal record);

    int insertBasicByBasicInfo(WflTrdFinProductDealTotal record);

    WflTrdFinProductDealTotal selectByPrimaryKey(WflTrdFinProductDealTotalKey key);

    int updateByPrimaryKeySelective(WflTrdFinProductDealTotal record);

    int updateByPrimaryKey(WflTrdFinProductDealTotal record);
}