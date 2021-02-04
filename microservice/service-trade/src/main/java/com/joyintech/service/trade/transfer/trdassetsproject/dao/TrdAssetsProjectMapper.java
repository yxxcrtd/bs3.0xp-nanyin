package com.joyintech.service.trade.transfer.trdassetsproject.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TrdAssetsProjectMapper {
    com.joyintech.service.trade.transfer.trdassetsproject.dto.ProjSplitInfoDto selectSplitInfo(Map<String, Object> param);

    List<SelectOptionDto> selectPortfolioId(Map<String, Object> param);

    List<BussTrdTransferOrderDto> selectBussTrdTraOrderByTradeId(Map<String, Object> param);
}
