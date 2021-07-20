
package com.joyintech.service.period.trdstockconnectbonus.dao;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.common.base.entity.PtlTrade;
import com.joyintech.tams.common.base.entity.StlPayOrders;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.invest.common.dto.common.FinAssetIntInfo;
import com.joyintech.tams.invest.common.dto.period.trdstockconnectbonus.TrdStockConnectBonusDto;


/**
 * 港股通股票存续期
 * @see TrdStockConnectBonusMapper
 */
@Repository
public interface TrdStockConnectBonusMapper {

    List<TrdStockConnectBonusDto> findPage(Map<String, Object> param);

    List<SelectOptionDto> getFinprodMarketId(Map<String, Object> param);

	List<SelectOptionDto> getSecManageAcctId(Map<String, Object> param);

	List<SelectOptionDto> getBusiCode(Map<String, Object> param);

	int revokeCheck(Map<String, Object> param);

	List<TrdProductDeal> finPrdDealInfo(String busiCode);

	PtlTrade findPtlTradeInfo(String tradeId);

	TrdProductDealAdd findPrdDealAddInfo(String tradeId);

	List<StlPayOrders> findStlPayOrdInfo(String tradeId);

	/**
	 * 待处理
	 */
	TrdStockConnectBonusDto findNeedDealInfo(Map<String, Object> param);

	TrdStockConnectBonusDto findEffectiveInfo(Map<String, Object> param);

	TrdStockConnectBonusDto findProcessInfo(Map<String, Object> param);

	/**
	 * 待处理明细
	 */
	List<FinAssetIntInfo> findNeedDealDetailInfo(Map<String, Object> param);

	List<FinAssetIntInfo> findDividDetailInfo(String busiCode);

	List<FinAssetIntInfo> findividInfo(Map<String, Object> param);

	List<FinAssetIntInfo> findProcessDividInfo(String busiCode);


    List<String> findTradeId(Map<String, Object> bizCode);

    List<TrdStockConnectBonusDto> getRTrdProductDeals(Map<String, Object> param);

    List<TrdProductDeal> findEffectiveT13Deal(Map<String, Object> param);
}
