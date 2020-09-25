/*
 * 文件名：TrdDepositInvestTrade001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年10月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.trade.trddepositinvesttrade.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.trade.trddepositinvesttrade.dto.TrdDepositInvestTrade001Dto;
import com.joyintech.tams.common.base.entity.InterfSendOpics;
import com.joyintech.tams.common.base.entity.PaTrdProductDeal;
import com.joyintech.tams.common.base.entity.PtlTrade;
import com.joyintech.tams.common.base.entity.StlPayOrders;

/**
 * 存款类投资交易
 * @author fanshuanghu
 * @version 1.0 2018年10月10日
 * @see TrdDepositInvestTrade001Mapper
 * @since 1.0
 */
@Repository
public interface TrdDepositInvestTrade001Mapper {
    /**
     * 分页查询
     * @param map 查询参数
     * @return 存款类投资交易实体类
     * @see
     * @since 1.0
     */
    List<TrdDepositInvestTrade001Dto> selectForPage(Map<String,Object> map);
    /**
     * 获取委托代码联想框集合
     * @param entrustId 委托代码入参
     * @return 联想文本框实体类
     * @see
     * @since 1.0
     */
    List<SuggestDto> getEntrustId(@Param("entrustId")String entrustId);
    /**
     * 获取委组合代码联想框集合
     * @param portfolidId 组合代码入参
     * @return 联想文本框实体类
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getPortfolioId(Map<String,Object> map);
    /**
     * 获取资产代码联想框集合
     * @param finprodMarketId 资产代码入参
     * @return 联想文本框实体类
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getCommonFinprodMarketId2(Map<String,Object> map);
    /**
     * 获取资产代码联想框集合
     * @param finprodMarketId 资产代码入参
     * @return 联想文本框实体类
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getFinprodMarketId2(Map<String,Object> map);
    /**
     * 获取资产代码联想框集合
     * @param finprodMarketId 资产代码入参
     * @return 联想文本框实体类
     * @see
     * @since 1.0
     */
    List<SuggestDto> getFinprodMarketId(Map<String,Object> map);
    /**
     * 获取资产提供行选择下拉框
     * @return 下拉框集合
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getAssetProvider(Map<String,Object> map);
    /**
     * 资产代码进行带出
     * @param map 资产代码，现金流一级类型，现金流二级类型
     * @return 资产类型、资产名称等
     * @see
     * @since 1.0
     */
    TrdDepositInvestTrade001Dto getFinInfo(Map<String,Object> map);
    /**
     * 是否保本
     * @param portfolioId 组合代码
     * @return 是否
     * @see
     * @since 1.0
     */
    String getIsGuarantee(@Param("portfolioId")String portfolioId);
    /**
     * 查询交易员
     * @param map 委托代码，接受状态
     * @return 交易员
     * @see
     * @since 1.0
     */
    String getTrader(Map<String,Object> map);
    /**
     * 获取账户账号
     * @param map 查询参数
     * @return 账户账号
     * @see
     * @since 1.0
     */
    String getAcctNo(Map<String,Object> map);
    /**
     * 撤销组合交易
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
     * @param tradeId 交易编号
     * @return 还款指令实体类
     * @see
     * @since 1.0
     */
    List<StlPayOrders> stlInfo(@Param("tradeId")String tradeId);
    /**
     * 获取登记编号
     * @param map 组合代码和金融产品类型
     * @return 登记编号
     * @see
     * @since 1.0
     */
    String getProdRegistCode(Map<String,Object> map);
    /**
     * 汇率收盘价
     * @param map 查询条件，日期和币种
     * @return 利率
     * @see
     * @since 1.0
     */
    BigDecimal getClosePrice(Map<String,Object> map);
    /**
     * 获取opics数据
     * @param tradeId 交易代码
     * @return opics实体类
     * @see
     * @since 1.0
     */
    List<InterfSendOpics> getOpics(@Param("tradeId")String tradeId);
    /**
     * 获取组合代码
     * @return 联想文本框
     * @see
     * @since 1.0
     */
    List<SuggestDto> getPortfolioIdSug(Map<String,Object> map);
    /**
     * 获取opics下拉框
     * @param map 请求类型
     * @return 下拉框
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getSelectOptions(Map<String,Object> map);

    /**
     * 获取当日应记利息
     * @param map 请求参数
     * @return 当日应记利息
     */
    BigDecimal getTdyIntincexp(Map<String,Object> map);

    /**
     * 验证同一组合同一标的的交易
     * @param map 请求参数
     * @return 个数
     */
    List<String> checkTradeById(Map<String,Object> map);
    
    /**
     * 验证标的是否做过终止协议交易
     * @param map 请求参数
     * @return 个数
     */
    List<String> checkIsCleanById(Map<String,Object> map);
//    /**
//     * 删除金融产品附属表数据
//     * @param workflowId 工作流代码
//     * @return 是否
//     * @see
//     * @since 1.0
//     */
//    int deleteWflPaTrdProductDeal(@Param("workflowId") String workflowId);
//    /**
//     * 删除划款指令表数据
//     * @param workflowId 工作流代码
//     * @return 是否
//     * @see
//     * @since 1.0
//     */
//    int deleteWflStlPayOrders(@Param("workflowId") String workflowId);
//    /**
//     * 删除金融产品表数据
//     * @param workflowId 工作流代码
//     * @return 是否
//     * @see
//     * @since 1.0
//     */
//    int deleteWflTrdProductDeal(@Param("workflowId") String workflowId);
//    /**
//     * 删除金融产品扩展表数据
//     * @param workflowId 工作流代码
//     * @return 是否
//     * @see
//     * @since 1.0
//     */
//    int deleteWflTrdProductDealAdd(@Param("workflowId") String workflowId);
}
