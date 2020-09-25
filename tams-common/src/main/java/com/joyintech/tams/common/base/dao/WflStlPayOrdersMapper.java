package com.joyintech.tams.common.base.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.WflStlPayOrders;
import com.joyintech.tams.common.base.entity.WflStlPayOrdersKey;

@Repository
public interface WflStlPayOrdersMapper {
    int deleteByPrimaryKey(WflStlPayOrdersKey key);

    int deleteByWorkflowId(String workflowId);

    int updateByWorkflowIdSelective(WflStlPayOrders record);

    int updateByWorkflowId(WflStlPayOrders record);

    int insertBasicByWorkflowId(Map<String, Object> paraMap);

    int insert(WflStlPayOrders record);

    int insertSelective(WflStlPayOrders record);

    int insertBasicByBasicInfo(WflStlPayOrders record);

    WflStlPayOrders selectByPrimaryKey(WflStlPayOrdersKey key);

    int updateByPrimaryKeySelective(WflStlPayOrders record);

    int updateByPrimaryKey(WflStlPayOrders record);
}