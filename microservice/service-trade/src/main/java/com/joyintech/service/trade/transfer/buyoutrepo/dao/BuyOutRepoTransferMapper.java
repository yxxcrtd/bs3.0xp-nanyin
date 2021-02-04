package com.joyintech.service.trade.transfer.buyoutrepo.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.trade.transfer.buyoutrepo.dto.BuyOutRepoTransfer003Dto;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 买断式回购转仓Mapper
 * @author liuwei
 * @version 1.0 2020年1月6日
 * @see BuyOutRepoTransferMapper
 * @since 1.0
 */
@Repository
public interface BuyOutRepoTransferMapper {

    /**
     * 获取交易界面
     * @param tradeId 交易编号
     * @return 交易信息
     * @see
     * @since 1.0
     */
    BuyOutRepoTransfer003Dto getTradeInfo(Map<String,Object> map);

    /**
     * 获取转仓信息—组合代码
     * @return 联想文本框实体类集合
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getPortfolioId(Map<String,Object> map);

    /**
     * 获取债券数量
     * @return 债券数量
     * @see
     * @since 1.0
     */
    BigDecimal selectPosition(Map<String,Object> map);

    /**
     * 获取待转仓产品组合持有债券总持仓（针对债券和交易）
     * @return 持仓值
     *
     * @see
     * @since 1.0
     */
    BigDecimal getBondAmount(Map<String,Object> map);

    /**
     * 获取待转仓产品组合总持仓（针对交易）
     * @return 持仓值
     *
     * @see
     * @since 1.0
     */
    BigDecimal getTradeAmount(Map<String,Object> map);

    /**
     * 根据交易编号删除金融产品分类表
     * @param tradeId 交易编号
     * @return Db
     */
    int deleteFinProductTypeByTradeId(String tradeId);
}
