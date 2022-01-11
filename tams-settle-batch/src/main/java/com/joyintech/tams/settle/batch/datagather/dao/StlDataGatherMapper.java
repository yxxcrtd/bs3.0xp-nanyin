package com.joyintech.tams.settle.batch.datagather.dao;

import com.joyintech.tams.common.base.entity.PtlSecPosition;
import com.joyintech.tams.common.base.entity.PtlSecPositionFlow;
import com.joyintech.tams.common.base.entity.TrdDealTotalAdd;
import com.joyintech.tams.settle.batch.datagather.dto.EtrEntrustDto;
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
     * 获取申购确认成交明细数据
     * @param paraMap
     */
    List<SettleTransDataDto> selectCashBonusSettleTransData1(Map<String, Object> paraMap);
    
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


    List<EtrEntrustDto> listEtrEntrust(@Param("finprodType") List<String> finprodType,@Param("entrustDate") Date entrustDate);

    /**
     * 债券存续期
     * @param paraMap
     * @return
     */
    List<SettleTransDataDto> listBondSettleTransData(Map<String, Object> paraMap);

    /**
     * 回购存续期
     * @param paraMap
     * @return
     */
    List<SettleTransDataDto> listRepoSettleTransData(Map<String, Object> paraMap);

    /**
     * 持仓余额
     * @param param
     * @return
     */
    List<PtlSecPosition> listPtlSecPosition(Map<String,Object> param);

    /**
     * 债券申购确认
     * @param paraMap
     * @return
     */
    List<SettleTransDataDto> listBondPurConfirSettleTransData(Map<String, Object> paraMap);

    /**
     * 债券股票上市流通
     * @param paraMap
     * @return
     */
    List<SettleTransDataDto> listBoundSharesMarketTradeData(Map<String, Object> paraMap);

    /**
     * 组合持仓流水
     * @param param
     * @return
     */
    List<PtlSecPositionFlow> listPtlSecPositionFolw(Map<String, Object> param);

    /**
     * 获取现金分红成交明细数据
     * @param paraMap
     */
    List<SettleTransDataDto> selectFundSettleTransData(Map<String, Object> paraMap);

    List<String> selectCqTotleTradeId(Map<String, Object> map);

    List<String> selectCqtradeId(String id);

    void deleteCqTrdProductDealAdd(String tradeId);

    void deleteCqPaTrdProductDeal(String tradeId);

    void deleteCqPtlTrade(String tradeId);

    void deleteCqTrdFinProductDealTotal(String totleTradeId);

    void deleteCqTrdProductDeal(String totleTradeId);

    void deleteCqPaTrdFinProductDealTotal(String totleTradeId);

    void deleteCqTrdDealTotalAdd(String totleTradeId);

    String selectCqBtradeId(@Param("secAcctId") String secAcctId, @Param("finprodId") String finprodId,@Param("dealDate") Date dealDate);

    int selectDzcjList(Map<String, Object> searchParam);
}
