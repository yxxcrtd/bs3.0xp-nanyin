
package com.joyintech.service.period.trdoptionduration.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.invest.common.dto.common.FinAssetIntInfo;
import com.joyintech.tams.invest.common.dto.period.trdoptionduration.OptionDurationDetailDto;
import com.joyintech.tams.invest.common.dto.period.trdoptionduration.TrdOptionDurationDto;
import com.joyintech.tams.invest.common.dto.period.trdstockbonus.TrdStockBonusDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 股票存续期
 * @author panbin
 * @version 2019年6月19日
 * @see TrdOptionDurationMapper
 */
@Repository
public interface TrdOptionDurationMapper {

    List<TrdOptionDurationDto> findPage(Map<String, Object> param);

    List<SelectOptionDto> getFinprodMarketId(Map<String, Object> param);

	List<SelectOptionDto> getSecManageAcctId(Map<String, Object> param);

	List<SelectOptionDto> getBusiCode(Map<String, Object> param);

	int revokeCheck(Map<String, Object> param);

	List<TrdProductDeal> finPrdDealInfo(String busiCode);

	PtlTrade findPtlTradeInfo(String tradeId);

	TrdProductDealAdd findPrdDealAddInfo(String tradeId);

	List<StlPayOrders> findStlPayOrdInfo(String tradeId);

	List<FinAssetIntInfo> findDividDetailInfo(String busiCode);

	List<FinAssetIntInfo> findividInfo(Map<String, Object> param);

	TrdStockBonusDto findEffectiveInfo(Map<String, Object> param);

	TrdStockBonusDto findProcessInfo(Map<String, Object> param);


    List<FinAssetIntInfo> findProcessDividInfo(String busiCode);

    List<String> findTradeId(Map<String, Object> bizCode);

	List<TrdProductDeal> getRTrdProductDeals(Map<String, Object> param);

	List<SelectOptionDto> getoption(Map<String, Object> param);

	OptionDurationDetailDto getEntityByMap(Map<String, Object> param);
}
