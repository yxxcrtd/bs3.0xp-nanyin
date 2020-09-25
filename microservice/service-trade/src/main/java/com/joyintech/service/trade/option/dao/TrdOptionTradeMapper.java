package com.joyintech.service.trade.option.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.trade.option.dto.TrdOptionTradeDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Repository
public interface TrdOptionTradeMapper {

    /**
     * 分页查询列表
     * @param pageParam
     * @return
     */
    List<TrdOptionTradeDto> selectDeal(Map<String, Object> pageParam);

    /**
     * 合约标的下拉框
     * @param pageParam
     * @return
     */
    List<SelectOptionDto> selectFinprodIdAbbr(Map<String, Object> pageParam);

    TrdOptionTradeDto getTrdOptionTradeDto(Map<String, Object> map);

    String selectTradeIdByWorkflowId(String workflowId);

    /**
     * 根据交易编号查询流程中数据条数
     *
     * @param tradeId 交易编号
     * @return 流程中数据条数
     * @see
     * @since 1.0
     */
    int selectInFlowDataCount(@Param("tradeId") String tradeId);

    /**
     * 根据交易编号查询存续期数据条数
     *
     * @param param
     * @return 存续期数据条数
     * @see
     * @since 1.0
     */
    int selectDurationDataCount(Map<String,Object> param);

    /**
     * 根据交易编号查询已撤销数据条数
     *
     * @param paramMap 参数
     * @return 已撤销数据条数
     * @see
     * @since 1.0
     */
    int selectRevokedDataCount(Map<String, Object> paramMap);

    /**
     * 根据交易编号查询已流出资金核对数据条数
     *
     * @param paramMap 参数
     * @return 已做流出资金核对数据条数
     * @see
     * @since 1.0
     */
    int selectLqdPipedTrade(Map<String, Object> paramMap);

    /**
     * 根据标的代码查询标的类型
     * @param targetId 标的代码
     * @return 标的类型
     */
    String selectTargetType(@Param("targetId")String targetId);

    /**
     /**
     * 根据委托编号查询信息
     *
     * @param entrustId 委托编号
     * @return 数据
     * @see
     * @since 1.0
     */
    TrdOptionTradeDto findTradeInfoByEntrustId(@Param("entrustId")String entrustId);

    /**
     * 查询委托所做交易的期权费总额
     *
     * @param map 委托编号
     * @return 数量
     * @see
     * @since 1.0
     */
    BigDecimal getPrinAmt(Map<String, Object> map);
}
