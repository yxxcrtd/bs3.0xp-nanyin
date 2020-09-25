package com.joyintech.service.settle.order.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.invest.common.dto.settle.order.StlOrderManagementDto;

import java.util.List;
import java.util.Map;

@Repository
public interface StlOrderManagementMapper {
    List<StlOrderManagementDto> findOrderData(Map<String, Object> params);

    /**
     * 根据接口给的交易编号查询对应的指令编号
     * 
     * @param params 参数
     * @return 指令编号
     * @see
     * @since 1.0
     */
    List<StlOrderManagementDto> findOrderIdByTradeId(Map<String, Object> params);
}
