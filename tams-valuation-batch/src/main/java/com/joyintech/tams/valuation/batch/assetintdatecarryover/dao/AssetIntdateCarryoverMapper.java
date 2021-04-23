/*
 * 文件名：AssetIntdateCarryoverMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：chengzhong
 * 修改时间：2018年5月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.assetintdatecarryover.dao;

import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.valuation.batch.assetintdatecarryover.dto.AssetIntdateCarryoverExecReaderDetail;
import com.joyintech.tams.valuation.batch.assetintdatecarryover.dto.AssetIntdateCarryoverReaderDetail;
import com.joyintech.tams.valuation.batch.assetintdatecarryover.dto.SecIsnotPledgeDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 资产理论结息日结转 - 数据库数据获取
 * 
 * @author chengzhong
 * @version 1.0 2018年6月7日
 * @see AssetIntdateCarryoverMapper
 * @since 1.0
 */
@Repository
public interface AssetIntdateCarryoverMapper {

    /**
     * 资产理论结息日结转明细查询 <功能详细描述>
     * 
     * @param branch
     * @param taskId
     * @return
     * @see
     * @since 1.0
     */
    List<AssetIntdateCarryoverReaderDetail> queryAssetIntdateCarryoverReaderDetail(Map<String, Object> param);

    /**
     * 是否存在已交易生成 <功能详细描述>
     * 
     * @param branch
     * @param taskId
     * @return
     * @see
     * @since 1.0
     */
    int queryDealCount(@Param("finprodId") String finprodId,
                       @Param("finprodType") String finprodType, @Param("mdate") Date mdate,
                       @Param("booksetId") String booksetId, @Param("ps") String ps);

    /**
     * 查询要删除的交易 
     * 
     * @param branch
     * @return
     * @see
     * @since 1.0
     */
    List<String> queryDelDeal(Map<String, Object> params);
    
    /**
     * 
     * 入账明细表删除
     * <功能详细描述>
     * @param tradeList
     * @return
     * @see
     * @since 1.0
     */
    int delValDealAccounting(@Param("tradeList") List<String> tradeList);
    
    /**
     * 
     * 入账账务明细表删除
     * <功能详细描述>
     * @param tradeList
     * @return
     * @see
     * @since 1.0
     */
    int delValDealAccountingDetail(@Param("tradeList") List<String> tradeList);
    
    /**
     * 
     * 账务明细代码表删除
     * <功能详细描述>
     * @param tradeList
     * @return
     * @see
     * @since 1.0
     */
    int delBokDetailId(@Param("tradeList") List<String> tradeList);
    
    
    /**
     * 
     * 金融产品交易删除
     * <功能详细描述>
     * @param tradeList
     * @return
     * @see
     * @since 1.0
     */
    int delValFinProductDeal(@Param("tradeList") List<String> tradeList);
    
    
    /**
     * 
     * 估值核算交易扩展表删除
     * <功能详细描述>
     * @param tradeList
     * @return
     * @see
     * @since 1.0
     */
    int delValFinProductDealExt(@Param("tradeList") List<String> tradeList);
    
    /**
     * 插入入账信息表
     * @param valDealAccounting 入账信息表
     * @see
     * @since 1.0
     */
    void insertValDealAccounting(List<BokDealAccounting> list);
    
    /**
     * 插入入账账务明细表
     * @param valDealAccountingDetail 入账账务明细表
     * @see
     * @since 1.0
     */
    void insertValDealAccountingDetail(List<BokDealAccountingDetail> list);
    
    /**
     * 插入金融产品交易
     * @param ValFinProductDeal 金融产品交易
     * @see
     * @since 1.0
     */
    void insertValFinProductDeal(List<TrdProductDeal> list);
    
    /**
     * 插入估值核算交易扩展表 
     * @param valFinProductDealExt 估值核算交易扩展表 
     * @see
     * @since 1.0
     */
    //一体化问题12:表名变更(金融产品交易扩展表(TRD_PRODUCT_DEAL_EXT) -> 金融产品交易附属表(TRD_PRODUCT_DEAL_ADD))
    void insertValFinProductDealExt(List<TrdProductDealAdd> list);
    
    /**
     * 插入交易类金融产品
     * @param valTradeFinProduct 交易类金融产品
     * @see
     * @since 1.0
     */
    void insertValTradeFinProduct(List<FinTradeProduct> list);
    
    /**
     * 插入账务明细代码表
     * @param bokDetailId 账务明细代码表
     * @see
     * @since 1.0
     */
    void insertBokDetailId(List<BokDetailId> list);
    
    
    /**
     * 查询本次收息还本的债券是否被质押
     * 
     * @param branch
     * @return
     * @see
     * @since 1.0
     */
    List<SecIsnotPledgeDto> querySecIsnotPledge(@Param("finprodId") String finprodId,
                                                @Param("finprodType") String finprodType,
                                                @Param("trdFinprodTypeList") List<String> trdFinprodTypeList,
                                                @Param("mdate") Date mdate);
    
    /**
     * 查询本次收息还本的债券是否被质押
     * 
     * @param branch
     * @return
     * @see
     * @since 1.0
     */
    int queryPledgeCount(@Param("finprodId") String finprodId,
                                                @Param("finprodType") String finprodType,
                                                @Param("booksetId") String booksetId,
                                                @Param("mdate") Date mdate,
                                                @Param("busiTable") String busiTable,
                                                @Param("ps") String ps);

    /**
     * 查询要删除的交易(交易类) 
     * 
     * @param branch
     * @return
     * @see
     * @since 1.0
     */
    List<String> queryDelDealTransaction(Map<String,Object> paramsMap);
 
    /**
     * 资产理论结息日结转明细查询(交易类) <功能详细描述>
     * 
     * @param branch
     * @param taskId
     * @return
     * @see
     * @since 1.0
     */
    List<AssetIntdateCarryoverReaderDetail> queryAssetIntdateCarryoverReaderTransaction(Map<String,Object> paramsMap);

    /**
     * 查询债券行权计划
     * @param paramsMap
     * @return
     */
    List<AssetIntdateCarryoverExecReaderDetail> selectSecExerciseList(Map<String,Object> paramsMap);

    /**
     * 查询债券行权计划交易数量
     * @param paramsMap
     * @return
     */
    Integer selectExecTradeCount(Map<String,Object> paramsMap);

}
