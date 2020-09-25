/*
 * 文件名：ChannelAssetQueryMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨智
 * 修改时间：2018年09月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.trade.channel.feepay.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.base.entity.*;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.invest.common.dto.StlPayOrdersDto;
import com.joyintech.tams.invest.common.dto.channel.ChannelFeePayDto;
import com.joyintech.tams.invest.common.dto.product.addreduce.ProductTradeInfoDto;

/**
 * 通道资产查询
 *  
 * @author yangjm
 * @version 1.0 2019年3月18日
 * @see ChannelFeePayMapper
 * @since 1.0
 */
@Repository
public interface ChannelFeePayMapper {

    List<ChannelFeePayDto> findPage(Map<String, Object> param);

    List<SelectOptionDto> findChannelId(Map<String, Object> param);

    ChannelFeePayDto searchTheoryAmt(Map<String, Object> param);

    ProductTradeInfoDto findAllTrade(String tradeId);

    List<TrdProductDealAdd> selectAllDealAdds(List<TrdProductDeal> trdProductDeals);

    List<PaTrdProductDeal> selectAllPaDeals(List<TrdProductDeal> trdProductDeals);

    List<StlPayOrdersDto> selectAllOrders(String tradeId);

    List<TrdProductDealAdd> selectAllEfDealAdds(List<TrdProductDeal> asList);

    List<PaTrdProductDeal> selectAllEfPaDeals(List<TrdProductDeal> asList);

    List<StlPayOrders> selectAllEfOrders(String tradeId);

    String findChannelFeePaied(Map<String, Object> param);

    List<PtlTrade> findPtlTrades(String tradeId);
}
