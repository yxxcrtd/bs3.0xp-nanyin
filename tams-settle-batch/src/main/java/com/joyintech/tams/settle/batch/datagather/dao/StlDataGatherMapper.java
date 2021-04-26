package com.joyintech.tams.settle.batch.datagather.dao;

import com.joyintech.tams.common.base.entity.TrdDealTotalAdd;
import com.joyintech.tams.settle.batch.datagather.dto.SettleDelDealDetailDto;
import com.joyintech.tams.settle.batch.datagather.dto.SettleTransDataDto;
import com.joyintech.tams.settle.batch.datagather.dto.TotalTrdProductDealDto;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 何阳阳
 * @date 2020/7/17
 * @see StlDataGatherMapper
 * @since 1.0
 */
@Repository
public interface StlDataGatherMapper {

    /**
     * 删除参数
     */
    int deleteParam(@Param("dealDate") Date dealDate);
    /**
     * 获取成交明细数据
     * @param paraMap 查询参数
     * @return 成交明细列表
     */
    List<SettleTransDataDto> selectSettleTransData(Map<String, Object> paraMap);

    /**
     * 查询前置交易编号
     * @param paraMap 查询参数
     * @return 前置交易编号
     */
    String selectBTradeId(Map<String, String> paraMap);

    /**
     * 获取成交明细数据
     * @param paraMap 查询参数
     * @return 成交明细列表
     */
    List<SettleTransDataDto> selectContinuationSettleTransData(Map<String, Object> paraMap);

    /**
     * 获取成交明细数据
     * @param paraMap 查询参数
     * @return 成交明细列表
     */
    List<SettleTransDataDto> selectEndSettleTransData(Map<String, Object> paraMap);

    /**
     * 获取成交明细数据
     * @param paraMap 查询参数
     * @return 成交明细列表
     */
    List<SettleTransDataDto> selectBiddingSettleTransData(Map<String, Object> paraMap);

    /**
     * 获取基金除权成交明细数据
     * @param paraMap 查询参数
     * @return 成交明细列表
     */
    List<SettleDelDealDetailDto> selectFundDelSettleTransData(Map<String, Object> paraMap);

    /**
     * 获取股票除权交易明细
     * @param paraMap 查询参数
     * @return 除权交易明细
     */
    List<SettleDelDealDetailDto> selectSettleDelDealDetail(Map<String, Object> paraMap);

    /**
     * 获取现金分红成交明细数据
     * @param paraMap
     */
    List<SettleTransDataDto> selectCashBonusSettleTransData(Map<String, Object> paraMap);
    
    /**
     * 更新SETTLE_TRANS_TG_MAPPING
     * @param paraMap 更新参数
     * @return 更新结果
     */
    int updateSettleTransTgMapping(Map<String, Object> paraMap);
    
    /**
     * 检索非货基现金分红对应的除权交易
     * @param paraMap Map<String, Object>
     * @return 非货基现金分红对应的除权交易
     */
    List<TotalTrdProductDealDto> selectNhfTotalTrdProductDealDto(Map<String, Object> paraMap);
    
    /**
     * 根据交易编号查询汇总附属信息
     * 
     * @param tradeId 交易编号
     * @return 汇总附属信息
     * @see
     * @since 1.0
     */
    TrdDealTotalAdd queryTotalAddByTradeId(@Param("tradeId") String tradeId);
}
