package com.joyintech.service.trade.transfer.common.dao;

import com.joyintech.service.trade.transfer.common.dto.TransferCommonDto;
import com.joyintech.tams.common.base.entity.WflTrdProductDeal;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TransferCommonMapper {

    List<WflTrdProductDeal> selectDealByworkflowId(String workflowId);

    List<TransferCommonDto> findAvaPortfolioInfo(Map<String, Object> paramMap);
}
