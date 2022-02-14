package com.joyintech.tams.invest.trade.exchangetransmatch.common.dao;

import com.joyintech.tams.common.base.entity.SettleTransData;
import com.joyintech.tams.common.base.entity.SettleTransTgMapping;
import com.joyintech.tams.common.base.entity.TrdDealTotalAdd;
import com.joyintech.tams.invest.common.transmatch.dto.ExchangeTransMatchCommonEntrustDto;
import com.joyintech.tams.invest.common.transmatch.dto.ExchangeTransMatchCommonRevokeDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface ExchangeTransMatchCommonMapper {

    /**
     * 查询委托信息--根据idlIST
     * @param entrustMap
     * @return
     */
    List<ExchangeTransMatchCommonEntrustDto> getEtrEntrustByIdList(Map<String,Object> entrustMap);

    /**
     * 获取下一工作日
     */
    Date getNextWorkDay(@Param("nowday") Date nowday, @Param("calendarId") String calendarId);

    /**
     * 查询前置交易编号
     * @param paraMap 查询参数
     * @return 前置交易编号
     */
    String selectBTradeId(Map<String, String> paraMap);

    /**
     * 根据交易编号查询汇总附属信息
     *
     * @param tradeId 交易编号
     * @return 汇总附属信息
     * @see
     * @since 1.0
     */
    TrdDealTotalAdd queryTotalAddByTradeId(@Param("tradeId") String tradeId);

    /**
     * 根据汇总流水号获取成交明细
     *
     * @param paraMap 流水号
     * @return 汇总附属信息
     * @see
     * @since 1.0
     */
    List<SettleTransData> getSettleTransDataList(Map<String, Object> paraMap);


    /**
     * 汇总成交数据匹配结果表（SETTLE_TRANS_DATA_TOTAL_MATCH）：数据删除
     * @param paraMap
     */
    void deleteSettleTransDataTotalMatch(Map<String, String> paraMap);

    /**
     * 删除.成交数据与投管交易关系（SETTLE_TRANS_TG_MAPPING）
     * @param tradeId
     */
    void deleteSettleTransTgMapping(@Param("tradeId") String tradeId);

    /**
     * 获取汇总流水号
     * @param tradeId
     * @return
     */
    String getTotalSeqId(@Param("tradeId") String tradeId);

    /**
     * 获取指令编号
     * @param tradeId
     * @return
     */
    String getEntrunstId(@Param("tradeId") String tradeId);

    /**
     * 获取剩余份额
     * @return
     */
    BigDecimal getRemainNum(@Param("totalSeqId") String totalSeqId);

    /**
     * 查询成交数据与投管交易关系（SETTLE_TRANS_TG_MAPPING）
     * @param paraMap
     * @return
     */
    List<ExchangeTransMatchCommonRevokeDto> getSettleTransTgMapping(Map<String, Object> paraMap);
}
