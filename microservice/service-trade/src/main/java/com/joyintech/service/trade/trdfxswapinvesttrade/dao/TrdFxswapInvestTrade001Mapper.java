/*
 * 文件名：TrdFxswapInvestTrade001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：renping
 * 修改时间：2019年6月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.trade.trdfxswapinvesttrade.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.service.trade.trdfxswapinvesttrade.dto.TrdFxswapInvestTrade001Dto;
import com.joyintech.tams.common.base.entity.PaTrdProductDeal;
import com.joyintech.tams.common.base.entity.PtlTrade;
import com.joyintech.tams.common.base.entity.StlPayOrders;

/**
 * 外汇掉期投资交易
 * 
 * @author renping
 * @version 1.0 2019年6月25日
 * @see TrdFxswapInvestTrade001Mapper
 * @since 1.0
 */
@Repository
public interface TrdFxswapInvestTrade001Mapper {

    /**
     * 分页查询
     * 
     * @param map 查询参数
     * @return 外汇掉期投资交易实体类
     * @see
     * @since 1.0
     */
    List<TrdFxswapInvestTrade001Dto> selectForPage(Map<String, Object> map);

    /**
     * 委托代码带出交易信息
     * 
     * @param map 委托代码
     * @return 交易信息
     * @see
     * @since 1.0
     */
    TrdFxswapInvestTrade001Dto getDealInfoByEntrustId(@Param("entrustId") String entrustId);

    /**
     * 查询交易员
     * 
     * @param map 委托代码，接受状态
     * @return 交易员
     * @see
     * @since 1.0
     */
    String getTrader(Map<String, Object> map);

    /**
     * 获取账户账号
     * 
     * @param map 查询参数
     * @return 账户账号
     * @see
     * @since 1.0
     */
    String getAcctNo(Map<String, Object> map);

    /**
     * 撤销组合交易
     * 
     * @see
     * @since 1.0
     */
    int updatePtlTrade(PtlTrade dto);

    /**
     * 撤销金融产品扩展的交易
     */
    int updateTrdProduct(PaTrdProductDeal dto);

    /**
     * 获取划款指令实体类
     * 
     * @param tradeId 交易编号
     * @return 还款指令实体类
     * @see
     * @since 1.0
     */
    List<StlPayOrders> stlInfo(@Param("tradeId") String tradeId);

    /**
     * 获取到期交易交易编号
     * 
     * @param map 参数
     * @return 交易编号
     * @see
     * @since 1.0
     */
    String getTradeIdDur(Map<String, Object> map);
}
