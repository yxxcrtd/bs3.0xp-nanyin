/*
 * 文件名：LiquidityPredictReportMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：weipeng
 * 修改时间：2019年8月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.product.flowforecastreport.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.service.product.flowforecastreport.dto.FlowCommonDto;
import com.joyintech.service.product.flowforecastreport.dto.FlowQueryDataDto;
import com.joyintech.tams.common.base.entity.FlowForecastConfig;

/**
 * 流动性预测报表mapper
 * 
 * @author weipeng
 * @version 1.0 2019年8月13日
 * @see FlowForecastReportMapper
 * @since 1.0
 */
@Repository
public interface FlowForecastReportMapper {

    /**
     * 根据时间和种类获取流动储备/预测维护
     * 
     * @param queryDate 查询时间
     * @param 流动种类
     * @return FlowForecastConfig 结果
     * @see
     * @since 1.0
     */
    List<FlowForecastConfig> getConfigsByDate(@Param("queryDate") String queryDate,
                                              @Param("flowType") String flowType);

    /**
     * 流动性预测表格数据查询
     * 
     * @param params 参数
     * @return 流动性预测数据List
     * @see
     * @since 1.0
     */
    List<FlowCommonDto> findForcastPage(Map<String, Object> params);

    /**
     * 流动性储备情况表格数据查询
     * 
     * @param params 参数
     * @return 流动性储备情况数据List
     * @see
     * @since 1.0
     */
    List<FlowCommonDto> findReservePage(Map<String, Object> params);

    /**
     * 获取金融产品代码
     * 
     * @param params 参数
     * @return 金融产品代码List
     * @see
     * @since 1.0
     */
    List<String> getFinprodIds(Map<String, Object> params);
    
    /**
     * 获取金融产品代码
     * 
     * @param params 参数
     * @return 金融产品代码List
     * @see
     * @since 1.0
     */
    List<String> getAllFinprodIds(@Param("finprodType") String finprodType);

    /**
     * 一级流动性备付-现金
     * 
     * @param params 参数
     * @return 一级流动性备付-现金
     * @see
     * @since 1.0
     */
    List<FlowQueryDataDto> getFlowReserveCash(Map<String, Object> params);

    /**
     * 一级流动性备付-回购
     * 
     * @param params 参数
     * @return 一级流动性备付-回购
     * @see
     * @since 1.0
     */
    List<FlowQueryDataDto> getFlowReserveRepo(Map<String, Object> params);

    /**
     * 二级流动性备付-债券
     * 
     * @param params 参数
     * @return 二级流动性备付-债券
     * @see
     * @since 1.0
     */
    List<FlowQueryDataDto> getSecondFlowReserveSec(Map<String, Object> params);

    /**
     * 产品规模
     * 
     * @param params 参数
     * @return 产品规模
     * @see
     * @since 1.0
     */
    List<FlowQueryDataDto> getFinproductScale(Map<String, Object> params);

    /**
     * 查询资金总流入-资产到期回款-逆回购到期、资金总流出-正回购到期
     * 
     * @param params 参数
     * @return 查询资金总流入-资产到期回款-逆回购到期、资金总流出-正回购到期
     * @see
     * @since 1.0
     */
    List<FlowQueryDataDto> getRepoMaturityFunds(Map<String, Object> params);
    
    /**
     * 资金总流入-资产到期回款-自主投资债券
     * 
     * @param params 参数
     * @return 资金总流入-资产到期回款-自主投资债券
     * @see
     * @since 1.0
     */
    List<FlowQueryDataDto> getSelfInvSecPredict(Map<String, Object> params);
    
    /**
     * 资金总流出-封闭式产品到期
     * 
     * @param params 参数
     * @return 资金总流出-封闭式产品到期
     * @see
     * @since 1.0
     */
    List<FlowQueryDataDto> getClosedProMaturity(Map<String, Object> params);
    
}
