/*
 * 文件名：FinProductModule002Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：何阳阳
 * 修改时间：2018年8月31日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.period.trdbondlendtargetduration.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.invest.common.dto.common.FinAssetIntInfo;
import com.joyintech.tams.invest.common.dto.period.trdbondlendtargetduration.TrdBondLendTargetDurationDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 债券存续期管理
 *
 * @author 强成西
 * @version 1.0 2018年8月31日
 * @see TrdBondLendTargetDurationMapper
 * @since 1.0
 */
@Repository
public interface TrdBondLendTargetDurationMapper {

    List<SelectOptionDto> getPortfolioId(Map<String, Object> param);

    List<SelectOptionDto> getSecManageAcctId(Map<String, Object> param);

    List<SelectOptionDto> getFinprodMarketId(Map<String, Object> param);

    List<TrdBondLendTargetDurationDto> findPage(Map<String, Object> param);

    TrdBondLendTargetDurationDto findEffectiveData(Map<String, Object> param);

    List<SelectOptionDto> getBusiCode(Map<String, Object> param);

    List<FinAssetIntInfo> findAssetIntInfo(Map<String, Object> params);

    List<PtlSecPositionFlow> findAmountGroupByInv(@Param("tradeId") String tradeId, @Param("portfolioId") String portfolioId);

    List<TrdProductDeal> selectTrdProductDealList(String totalTradeId);

    List<TrdProductDealAdd> selectDealAdds(Collection<String> values);

    List<PaTrdProductDeal> selectPaDeals(Collection<String> tradeIds);

    List<PtlTrade> selectPtlDeals(Collection<String> tradeIds);

    List<StlPayOrders> selectStlPayOrders(String totalTradeId);
}
