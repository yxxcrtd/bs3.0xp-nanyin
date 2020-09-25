/*
 * 文件名：FinProductModule002Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：何阳阳
 * 修改时间：2018年8月31日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.period.trdbuyoutduration.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.base.entity.*;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.invest.common.dto.period.trdrepoduration.RepoInfo;
import com.joyintech.tams.invest.common.dto.period.trdrepoduration.TrdRepoDurationDto;

/**
 * 债券存续期管理
 *
 * @author 强成西
 * @version 1.0 2018年8月31日
 * @see TrdRepoBuyoutDurationMapper
 * @since 1.0
 */
@Repository
public interface TrdRepoBuyoutDurationMapper {

    List<SelectOptionDto> getPortfolioId(Map<String, Object> param);

    List<TrdRepoDurationDto> findPage(Map<String, Object> param);

    TrdRepoDurationDto findEffectiveData(Map<String, Object> param);

    List<RepoInfo> findRepoInfo(Map<String, Object> params);

    List<FinCashPlan> findFinCashInfo(String finprodId);

    WflTrdFinProductDealTotal findTotalByWorkflowId(String workflowId);

    List<WflStlPayOrders> findOrdersByWorkflowId(String workflowId);

    WflPaTrdFinDealTotal findPaTotalByWorkflowId(String workflowId);

    List<WflTrdProductDeal> findDealByWorkflowId(String workflowId);

    String findPrdRegCode(Map<String, Object> params);

    List<SelectOptionDto> getBusiCode(Map<String, Object> param);

    void insertSelect04SecFlow(Map<String, Object> params);

    void delete04Flow(Map<String, Object> params);

    void insertSelect04Sec(PtlSecPositionFlow params);

    List<PtlSecPositionFlow> selectSecFlow(Map<String, Object> params);

    BigDecimal genPrinAmtByVal(Map<String, Object> param);
    
    //回购存续期根据交易编号待处理额度
    String getFinprodIdByTradeId(@Param("tradeId") String tradeId);
    // 授信使用
    BigDecimal getPrinAmtByTradeId(@Param("tradeId") String tradeId);
}
