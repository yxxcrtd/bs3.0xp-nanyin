/*
 * 文件名：TrdPlanRateInvestTradeMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年11月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.trade.trdplanrateinvesttrade.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.trade.trdplanrateinvesttrade.dto.TrdPlanRateInvestTradeDto;
import com.joyintech.tams.common.base.entity.FinCashPayRule;
import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.common.base.entity.FinProductType;
import com.joyintech.tams.invest.common.asset.dto.FinCashCalcRuleDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author fanshuanghu
 * @version 1.0 2018年11月22日
 * @see TrdPlanRateInvestTradeMapper
 * @since 1.0
 */
@Repository
public interface TrdPlanRateInvestTradeMapper {
    /**
     * 分页查询
     * @param map 查询参数
     * @return 分页查询结果
     * @see
     * @since 1.0
     */
    List<TrdPlanRateInvestTradeDto> findPage(Map<String,Object> map);

    /**
     * 汇总分页查询
     * @param map 查询参数
     * @return 分页查询结果
     * @see
     * @since 1.0
     */
    List<TrdPlanRateInvestTradeDto> findTotalPage(Map<String,Object> map);
    
    /**
     * 获取资产代码下拉框
     * @param map 请求参数
     * @return 下拉框集合类
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getFinprodMarketId(Map<String,Object> map);
    /**
     * 获取金融产品类型
     * @param map 金融产品代码
     * @return 金融产品类型
     * @see
     * @since 1.0
     */
    FinProductType getFinprodType(Map<String,Object> map);
    /**
     * 获取净投资收益率
     * @param map 资产代码
     * @return 净投资收益率
     * @see
     * @since 1.0
     */
    String getInvestRate(Map<String,Object> map);
    /**
     * 获取登记编号
     * @param map 查询参数
     * @return 登记编号
     * @see
     * @since 1.0
     */
    String getProdRegistCode(Map<String,Object> map);
    /**
     * 获取收盘价
     * @param map 日期、币种
     * @return 汇率（收盘价）
     * @see
     * @since 1.0
     */
    BigDecimal getClosePrice( Map<String,Object> map);
    /**
     * 查询交易员
     * @param map 委托代码，接受状态
     * @return 交易员
     * @see
     * @since 1.0
     */
    String getTrader(Map<String,Object> map);
    /**
     * 获取计息基础
     * @param finprodId 金融产品代码
     * @return 计息基础
     * @see
     * @since 1.0
     */
    String getBasis(@Param("finprodId")String finprodId);
    /**
     * 币种
     * @param map 资产代码
     * @return 币种
     * @see
     * @since 1.0
     */
    String getCcy(Map<String,Object> map);
    /**
     * 获取上次付息日
     * @param map 请求参数
     * @return 上次付息日
     * @see
     * @since 1.0
     */
    FinCashPlan getLastVDate(Map<String,Object> map);
    /**
     * 获取计算规则
     * @param map 请求参数
     * @return 计算规则集合
     * @see
     * @since 1.0
     */
    List<FinCashCalcRuleDto> getFinCashCalcRule(Map<String,Object> map);

    FinCashPayRule getFinCashPayRule(Map<String, Object> map);

    /**
     * 是否还本不带息
     * @param map 资产代码
     * @return 现金流种类
     * @see
     * @since 1.0
     */
    String getIsPayInt(Map<String,Object> map);
}
