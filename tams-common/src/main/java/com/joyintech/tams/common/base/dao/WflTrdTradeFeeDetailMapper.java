package com.joyintech.tams.common.base.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.WflTrdTradeFeeDetail;
import com.joyintech.tams.common.base.entity.WflTrdTradeFeeDetailKey;


@Repository
public interface WflTrdTradeFeeDetailMapper {
    int deleteByPrimaryKey(WflTrdTradeFeeDetailKey key);

    int deleteByWorkflowId(String workflowId);

    int updateByWorkflowIdSelective(WflTrdTradeFeeDetail record);

    int updateByWorkflowId(WflTrdTradeFeeDetail record);

    int insertBasicByWorkflowId(Map paraMap);

    int insert(WflTrdTradeFeeDetail record);

    int insertSelective(WflTrdTradeFeeDetail record);

    int insertBasicByBasicInfo(WflTrdTradeFeeDetail record);

    WflTrdTradeFeeDetail selectByPrimaryKey(WflTrdTradeFeeDetailKey key);

    int updateByPrimaryKeySelective(WflTrdTradeFeeDetail record);

    int updateByPrimaryKey(WflTrdTradeFeeDetail record);
}