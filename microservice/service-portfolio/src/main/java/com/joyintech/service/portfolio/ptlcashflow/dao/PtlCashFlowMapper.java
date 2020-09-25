/*
 * 文件名：PtlCashFlowMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年10月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.portfolio.ptlcashflow.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.PtlTrade;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.portfolio.ptlcashflow.dto.PtlCashFlowDto;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2018年10月26日
 * @see PtlCashFlowMapper
 * @since 1.0
 */
@Repository
public interface PtlCashFlowMapper {

    /**
     * 获取业务编号
     * <功能详细描述>
     * @param param param
     * @return SuggestDto
     * @see
     * @since 1.0
     */
    List<SuggestDto> getBusiNo(Map<String,Object> param);

    /**
     * 获取组合代码的联想文本框
     * <功能详细描述>
     * @param map map
     * @return SuggestDto
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getPortfolioId(Map<String,Object> map);
    /**
     * 获取组合代码的联想文本框
     * <功能详细描述>
     * @param map map
     * @return SuggestDto
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getPortfolioIdToNew(Map<String, Object> map);

    
    /**
     * 获得资产代码
     * <功能详细描述>
     * @param map map
     * @return SuggestDto
     * @see
     * @since 1.0
     */
    List<SuggestDto> getFinprodMarketId(Map<String,Object> map);

    /**
     * 分页查询
     * <功能详细描述>
     * @param param param
     * @return PtlCashFlowDto
     * @see
     * @since 1.0
     */
    List<PtlCashFlowDto> selectForPage(Map<String, Object> param);

    /**
     * 查询交易对手
     * <功能详细描述>
     * @param param param
     * @return SuggestDto
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getCounterId(Map<String, Object> param);

    /**
     * 新建画面的资产代码的联想文本框
     * <功能详细描述>
     * @param param param
     * @return SuggestDto
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getFinprodIdToNew(Map<String, Object> param);

    /**
     * 查看和撤销页面展示entity
     * <功能详细描述>
     * @param map map
     * @return PtlCashFlowDto
     * @see
     * @since 1.0
     */
    PtlCashFlowDto getEntity(Map<String, Object> map);

    /**
     * 修改组合交易表
     * <功能详细描述>
     * @param ptlTrade ptlTrade
     * @return int int
     * @see
     * @since 1.0
     */
    int updatePtlTrade(PtlTrade ptlTrade);

    /**
     * 关联业务编号联想文本框
     * <功能详细描述>
     * @param map map
     * @return SuggestDto
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getFTradeId(Map<String, Object> map);

    /**
     * 根据组合代码查询产品登记编号
     * <功能详细描述>
     * @param portfolioId portfolioId
     * @return prodRegistCode
     * @see
     * @since 1.0
     */
    String getProdRegistCode(String portfolioId);

}
