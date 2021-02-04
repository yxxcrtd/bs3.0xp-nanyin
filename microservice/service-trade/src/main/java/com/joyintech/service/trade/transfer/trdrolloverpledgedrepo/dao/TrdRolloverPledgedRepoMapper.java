package com.joyintech.service.trade.transfer.trdrolloverpledgedrepo.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.trade.transfer.trdassetsproject.dto.FinDealPledgeDto;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TrdRolloverPledgedRepoMapper {

    List<FinDealPledgeDto> selectFinDealPledgeList(Map<String, Object> param);

    List<FinDealPledgeDto> selectAlFinDealPledgeList(Map<String, Object> param);

    List<SelectOptionDto> selectPortfolioId(Map<String, Object> param);

    List<TrdProductDeal> selectDealBySplitTradeId(String splitTradeId);
}
