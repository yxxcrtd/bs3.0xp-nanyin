package com.joyintech.tams.invest.trade.transfer.assign.dao;

import com.joyintech.tams.invest.trade.transfer.assign.dto.TrdAssetAssignDto;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface TrdAssetAssignDao {

	int getMaxPayDate(@Param("finprodId") String finprodId, @Param("payDate") Date payDate);

	TrdAssetAssignDto getInterestTradeInfo(@Param("portfolioId") String portfolioId, @Param("finprodId") String finprodId, @Param("settleDate") Date settleDate);

}
