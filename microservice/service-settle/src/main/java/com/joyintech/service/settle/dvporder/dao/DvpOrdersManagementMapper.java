package com.joyintech.service.settle.dvporder.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.invest.common.dto.settle.dvporder.DvpOrdersDto;
import com.joyintech.tams.invest.common.dto.settle.dvporder.DvpOrdersManagementDto;

import java.util.List;
import java.util.Map;

@Repository
public interface DvpOrdersManagementMapper {
    List<DvpOrdersManagementDto> findTransferTableData(Map<String, Object> param);

    List<DvpOrdersManagementDto> findDvpTransferTableData(Map<String, Object> param);

    List<DvpOrdersManagementDto> findTradeData(Map<String, Object> bizCodeList);

    DvpOrdersDto findDvpOrdersByOrderId(Map<String, Object> orderId);

    int findMatchedNum(List<DvpOrdersManagementDto> tradeDatas);

    DvpOrdersManagementDto printPayOrders(Map<String, Object> params);
    
    int updateStlDvpOrdersStatus(Map<String, Object> param);
    
    int updateStlDvpTradeMatchStatus(Map<String, Object> param);
    
    int deleteStlDvpOrdersStatus(Map<String, Object> param);
    
    int deleteStlDvpTradeMatchStatus(Map<String, Object> param);
}
