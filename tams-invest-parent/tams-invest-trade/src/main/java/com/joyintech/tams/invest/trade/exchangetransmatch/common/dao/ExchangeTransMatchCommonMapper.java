package com.joyintech.tams.invest.trade.exchangetransmatch.common.dao;

import com.joyintech.tams.common.base.entity.TrdDealTotalAdd;
import com.joyintech.tams.invest.common.transmatch.dto.ExchangeTransMatchCommonEntrustDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface ExchangeTransMatchCommonMapper {

    /**
     * 查询委托信息--根据idlIST
     * @param entrustIdList
     * @return
     */
    List<ExchangeTransMatchCommonEntrustDto> getEtrEntrustByIdList(List<String> entrustIdList);

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

}
