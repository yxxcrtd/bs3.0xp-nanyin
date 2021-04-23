/*
 * 文件名：SecPosAutoGenerationMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：刘二通
 * 修改时间：2018年11月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.busiAutoAddSecInfo.dao;

import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.valuation.common.batch.finproduct.entity.TargetCashCalcRule;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 债券自动上市流通批处理
 *
 * @author sjj
 * @version 1.0 2021年04月07日
 * @see BusiAutoAddSecInfoMapper
 * @since 1.0
 */
@Repository
public interface BusiAutoAddSecInfoMapper {

    /**
     * 获取未上市债券信息
     *
     * @return 执行结果
     * @see
     * @since 1.0
     */
    List<FinProduct> selectUnlistedSecList(Map<String, Object> map);

    /**
     * 获取接口表债券信息
     *
     * @return 执行结果
     * @see
     * @since 1.0
     */
    String getSecId(FinProduct finProduct);

    List<FinCashInfo> selectFinCashInfo(@Param("finprodId") String finprodId);

    /**
     * 查询标的现金流计算规则list
     *
     * @param paMap 传递参数
     * @return List<FinCashCalcRule>
     * @see
     * @since 1.0
     */
    List<FinCashCalcRule> getTargetCashCalcRuleList(Map<String, Object> paMap);
    /**
     * 查询标的现金流利率信息list
     *
     * @param paMap 传递参数
     * @return List<FinRateInfo>
     * @see
     * @since 1.0
     */
    List<FinRateInfo> getFinRateInfoList(Map<String, Object> paMap);

    /**
     * 查询标的现金流支付规则list
     *
     * @param paMap 传递参数
     * @return List<FinCashPayRule>
     * @see
     * @since 1.0
     */
    List<FinCashPayRule> getFinCashPayRuleList(Map<String, Object> paMap);
    /**
     * 查询标的现金流计划表list
     *
     * @param paMap 传递参数
     * @return List<FinCashPlan>
     * @see
     * @since 1.0
     */
    List<FinCashPlan> getFinCashPlanList(Map<String, Object> paMap);

    /**
     * 查询标的行权计划表list
     *
     * @param paMap 传递参数
     * @return List<FinExercisePlan>
     * @see
     * @since 1.0
     */
    List<FinExercisePlan> getFinExercisePlanList(Map<String, Object> paMap);

    /**
     * 查询指令持仓list
     *
     * @param tempMap 传递参数
     * @return List<EtrSecPosition>
     * @see
     * @since 1.0
     */
    List<EtrSecPosition> getEtrSecPositionList(Map<String, String> tempMap);

    /**
     * 查询組合持仓list
     *
     * @param tempMap 传递参数
     * @return List<EtrSecPosition>
     * @see
     * @since 1.0
     */
    List<PtlSecPosition> getPtlSecPositionList(Map<String, String> tempMap);

    void updateByPrimaryKey(FinProductAdd finProductAdd);
}
