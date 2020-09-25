/*
 * 文件名：SecPosAutoGenerationMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：刘二通
 * 修改时间：2018年11月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.batch.autogeneration.secpos.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.tams.busicommon.productRating.dto.CommonProductRatingDto;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.invest.batch.autogeneration.secpos.dto.AssetTargetDbDto;
import org.springframework.stereotype.Repository;

/**
 * 组合持仓自动生成批处理Mapper
 *
 * @author 刘二通
 * @version 1.0 2018年11月20日
 * @see SecPosAutoGenerationMapper
 * @since 1.0
 */
@Repository
public interface SecPosAutoGenerationMapper {

    /**
     * 删除当日持仓数据
     *
     * @param paramMap 参数
     * @return 执行结果
     * @see
     * @since 1.0
     */
    int deleteTodaySecPosData(Map<String, Object> paramMap);

    /**
     * 生成当日持仓数据
     *
     * @param paramMap 参数
     * @return 执行结果
     * @see
     * @since 1.0
     */
    int insertTodaySecPosData(Map<String, Object> paramMap);

    /**
     * 删除当日持仓数据->组合持仓分析报表
     *
     * @param paramMap 参数
     * @return 执行结果
     * @see
     * @since 1.0
     */
    int deleteReportTodaySecPosData(Map<String, Object> paramMap);

    /**
     * 获取当前跑平日汇总数据->组合持仓分析报表
     *
     * @param paramMap 参数
     * @return 执行结果
     * @see
     * @since 1.0
     */
    List<AssetTargetDbDto> getSecPosData(Map<String, Object> paramMap);



    /**
     * 根据资产代码获取相关交易信息列表
     * @param map
     * @return
     */
    List<TrdProductDeal> getTradeListByFinprodId(Map<String, Object> map);

    /**
     * 根据资产代码、起息日、分支序号获取计息基础
     * @param map
     * @return
     */
    FinCashCalcRule getBasisByFinprodId(Map<String, Object> map);


    /**
     * 根据资产代码、查询日、行权方式、资产类型查询下一行权日
     * @param map
     * @return
     */
    List<FinExercisePlan> getExerciseDate(Map<String, Object> map);

    /**
     * 根据资产代码查询昨日指标信息的持仓数量和综合成本收益率
     * @param map
     * @return
     */
    AssetTargetDb  getYesterdayTarget(Map<String, Object> map);

    /**
     * 查询债券相关评级结果
     * @paramFAIRE_PRICE
     * @return
     */
    List<CommonProductRatingDto> getBestProductRating(Map<String, Object> map);
    
    /**
     * 获取底层资产数据
     * <一句话功能简述>
     * <功能详细描述>
     * @param paramMap
     * @return
     * @see
     * @since 1.0
     */
    List<AssetTargetDbDto> getUnderlyingAssetsData(Map<String, Object> paramMap);
    
    /**
     * 
     * 获取资产风险等级
     * <功能详细描述>
     * @param paramMap
     * @return
     * @see
     * @since 1.0
     */
    List<String> getAssetRiskTypeList(Map<String, Object> paramMap);
}
