/*
 * 文件名：PtlFundAllocationMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2019年1月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.portfolio.ptlfundallocation.dao;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.PtlCashPositionFlow;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.portfolio.ptlfundallocation.dto.PtlFundAllocationDto;

/**
 * 资金划拨
 * @author fanshuanghu
 * @version 1.0 2019年1月29日
 * @see PtlFundAllocationMapper
 * @since 1.0
 */
@Repository
public interface PtlFundAllocationMapper {
    /**
     * 获取账号
     * @param map 请求参数
     * @return 账号下拉框集合
     * @see
     * @since 1.0
     */
    List<SuggestDto> getAcct(Map<String,Object> map);
    /**
     * 画面查询方法
     * @param map 请求参数
     * @return 查询结果集
     */
    List<PtlFundAllocationDto> findPage(Map<String,Object> map);
    
    /**
     * 查询组合
     * @param map 请求参数
     * @return 组合集合
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getPortfolioId(Map<String,Object> map);
    
    /**
     * 查询组合（资金划拨管理）
     * @param map 请求参数
     * @return 组合集合
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getPortfolioList(Map<String,Object> map);

    /**
     * 查询收款账号
     * @param map 请求参数
     * @return 收款账号集合
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getCounterAcctNo(Map<String,Object> map);
    
    /**
     * 查询付款账号
     * @param map 请求参数
     * @return 付款账号集合
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getMyAcctNo(Map<String,Object> map);
    /**
     * 获取现金流水中的流水号
     * @param tradeId 交易编号
     * @return 流水号
     * @see
     * @since 1.0
     */
    String selectSeq(Map<String,Object> map);
    /**
     * 获取划款指令
     * @param map 交易编号
     * @return 划款指令编号
     * @see
     * @since 1.0
     */
    String getPayOrderId(Map<String,Object> map);
    
    /**
     * 资金的划拨管理，银证转账的收款方账户，应该关联系统中的证券托管户配置的交易结算户；
     * 证银转账，付款方账户关联系统中证券托管户配置的交易结算户
     * @param map 请求参数
     * @return 付款账号集合
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getPtlSecTrdAcctNo(Map<String,Object> map);
    
    /**
     * 根据指令编号查询之前的组合现金流水记录
     *
     * @param paramMap 参数map
     * @return 组合现金流水记录
     * @see
     * @since 1.0
     */
    List<PtlCashPositionFlow> selectPtlCashPositionFlowList(Map<String, Object> paramMap);

    int deleteFlowByOrderId(@Param("orderId") String orderId, @Param("holdingType") String holdingType);
}
