package com.joyintech.tams.common.base.dao;

import com.joyintech.tams.common.base.entity.WflTrdProductDealAdd;
import com.joyintech.tams.common.base.entity.WflTrdProductDealAddKey;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public interface WflTrdProductDealAddMapper {
    int deleteByPrimaryKey(WflTrdProductDealAddKey key);

    int deleteByWorkflowId(String workflowId);

    int updateByWorkflowIdSelective(WflTrdProductDealAdd record);

    int updateByWorkflowId(WflTrdProductDealAdd record);

    int insertBasicByWorkflowId(Map paraMap);

    int insert(WflTrdProductDealAdd record);

    int insertSelective(WflTrdProductDealAdd record);

    int insertBasicByBasicInfo(WflTrdProductDealAdd record);

    WflTrdProductDealAdd selectByPrimaryKey(WflTrdProductDealAddKey key);

    int updateByPrimaryKeySelective(WflTrdProductDealAdd record);

    int updateByPrimaryKey(WflTrdProductDealAdd record);
}