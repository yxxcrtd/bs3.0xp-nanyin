package com.joyintech.service.trade.transfer.buyoutrepo.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.trade.transfer.buyoutrepo.dto.BuyoutRepoResolute001Dto;
import com.joyintech.tams.common.base.entity.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 买断式回购交易拆分
 * @author liuwei
 * @version 1.0 2019年11月20日
 * @see BuyoutRepoResoluteMapper
 * @since 1.0
 */
@Repository
public interface BuyoutRepoResoluteMapper {

    /**
     * 由资产代码带出各种表单元素
     * @param map 资产代码
     * @return 资产类型、计息基础等
     * @see
     * @since 1.0
     */
    BuyoutRepoResolute001Dto selectTradeInfo(Map<String, Object> map);

    /**
     * 查询已拆分金额
     *
     * @return 拆分前交易编号
     * @see
     * @since 1.0
     */
    BigDecimal getDistAmt(@Param("tradeId") String tradeId);

    /**
     * 获取拆分信息—组合代码
     * @return 联想文本框实体类集合
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getPortfolioId(Map<String, Object> map);

    /**
     * 查询托管划款指令
     * <功能详细描述>
     * @param tradeId 查询条件
     * @see
     * @since 1.0
     */
    StlPayOrders selectStlPayOrder(@Param("tradeId") String tradeId);

    /**
     * 查询组合托管户
     * @return 组合托管户
     * @see
     * @since 1.0
     */
    String getAccountInfo(Map<String, Object> map);

    /** 根据交易编号删除质押品信息
     * @param tradeId 交易编号
     * @return Db
     */
    int deleteFinDealPledgeByTradeId(String tradeId);

    /** 根据交易编号删除交易类金融产品
     * @param tradeId 交易编号
     * @return Db
     */
    int deleteFinTradeProductByTradeId(String tradeId);

    /**
     * 根据工作流代码获取交易编号
     * @param workflowId 工作流代码
     * @return Db
     */
    List<WflTrdProductDeal> selectWflTrdProductDeal(String workflowId);

    long deletePtlSecPositionFlow(Map<String, Object> param);

    void updatePosition(PtlSecPosition position);

    /**
     * 根据交易编号检索组合持仓流水（持仓类型为实际）
     *
     * @param param param
     * @return List<PtlSecPositionFlowDto>
     * @see
     * @since 1.0
     */
    List<PtlSecPositionFlow> selectPtlSecPositionFlow(Map<String, Object> param);

    void updatePositionDetail(PtlSecPositionDetail positionDetail);
}
