package com.joyintech.service.settle.netting.dao;

import com.joyintech.tams.invest.common.dto.settle.dvporder.DvpOrdersDto;
import com.joyintech.tams.invest.common.dto.settle.dvporder.DvpOrdersManagementDto;
import com.joyintech.tams.invest.common.dto.settle.netting.NettingManagementDto;
import com.joyintech.tams.invest.common.dto.settle.netting.NettingOrdersDto;
import com.joyintech.tams.invest.common.dto.settle.netting.TransferMessageDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author 姚轶文
 * @create 2019- 11-06 15:22
 */
@Repository
public interface NettingManagementMapper {

    NettingOrdersDto findNettingOrdersByOrderId(Map<String, Object> orderId);

    List<NettingManagementDto> findNettingTableData(Map<String, Object> param);

    List<TransferMessageDto> transferMessage(Map<String, Object> param);

    List<NettingManagementDto> findTradeData(Map<String, Object> bizCodeList);

    int findMatchedNum(List<NettingManagementDto> tradeDatas);

    String findOrderIdByTotleTradeId(String TOTLE_TRADE_ID);

    int updateStlDvpOrdersStatus(Map<String, Object> param);

    int updateStlDvpTradeMatchStatus(Map<String, Object> param);
}
