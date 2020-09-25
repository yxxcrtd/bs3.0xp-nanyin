/*
 * 文件名：ProductLedger001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2019年9月24日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.report.poc.riskmonitorindicators.dao;

import java.util.List;
import java.util.Map; 
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.report.poc.riskmonitorindicators.dto.RiskMonitorIndicatorsDto;
import com.joyintech.service.report.poc.riskmonitorindicators.dto.RiskTemporaryDataDto;

/**
 * 风险监测指标Mapper
 * 〈功能详细描述〉
 * @author liupeilin
 * @version 1.0 2019年11月11日
 * @see RiskMonitorIndicatorsMapper
 * @since 1.0
 */

public interface RiskMonitorIndicatorsMapper {
    
    /**
     * 查询产品所有数据
     *
     * @param map 参数map
     * @return 产品所有数据
     * @see
     * @since 1.0
     */
    List<RiskMonitorIndicatorsDto> selectForPage(Map<String, Object> map);
    
    /**
     * 产品代码联想下拉框
     *
     * @param param 参数map
     * @return 产品信息
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> queryProdIdInfo(Map<String, Object> map);
    
    /**
     * 理财经理联想下拉框
     *
     * @param param 参数map
     * @return 理财经理信息
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> queryManagerInfo(Map<String, Object> map);
    
    /**
     * 查询产品的总资产、净资产
     *
     * @param param 参数map
     * @return 产品的总资产、净资产
     * @see
     * @since 1.0
     */
    List<RiskTemporaryDataDto> queryTotalAsset(Map<String, Object> map);
 
    /**
     * <穿透后  查询产品的总资产、净资产>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<RiskTemporaryDataDto> queryTotalAssetPierce(Map<String, Object> map);
 
    /**
     * 查询开放式高流动性资产
     *
     * @param param 参数map
     * @return 开放式高流动性资产
     * @see
     * @since 1.0
     */
    List<RiskTemporaryDataDto> queryOpenHighMobilityAsset(Map<String, Object> map);

    /**
     * <穿透后 开放式高流动性资产>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<RiskTemporaryDataDto> queryOpenHighMobilityAssetPierce(Map<String, Object> map);

    /**
     * 查询现金
     *
     * @param param 参数map
     * @return 现金
     * @see
     * @since 1.0
     */
    List<RiskTemporaryDataDto> queryCash(Map<String, Object> map);

    /**
     * 查询-穿透后 现金
     *
     * @param param 参数map
     * @return-穿透后 现金
     * @see
     * @since 1.0
     */
    List<RiskTemporaryDataDto> queryCashPierce(Map<String, Object> map);
    
    /**
     * 查询-高流动性资产
     *
     * @param param 参数map
     * @return-高流动性资产
     * @see
     * @since 1.0
     */
    List<RiskTemporaryDataDto> queryHighMobilityAsset(Map<String, Object> map);
    
    /**
     * 查询-穿透后 高流动性资产
     *
     * @param param 参数map
     * @return-穿透后 高流动性资产
     * @see
     * @since 1.0
     */
    List<RiskTemporaryDataDto> queryHighMobilityAssetPierce(Map<String, Object> map);
     
    /**
     * 查询-单一最大资产集中度 
     *
     * @param param 参数map
     * @return-单一最大资产集中度 
     * @see
     * @since 1.0
     */
    List<RiskTemporaryDataDto> queryMaxAssetsCD(Map<String, Object> map);
    
    /**
     * 查询-穿透后 单一最大资产集中度 
     *
     * @param param 参数map
     * @return-单一最大资产集中度 
     * @see
     * @since 1.0
     */
    List<RiskTemporaryDataDto> queryMaxAssetsCDPierce(Map<String, Object> map);

    
    /**
     * 查询-单一最大融资人集中度
     *
     * @param param 参数map
     * @return-单一最大融资人集中度
     * @see
     * @since 1.0
     */
    List<RiskTemporaryDataDto> queryLargestFinancierCD(Map<String, Object> map);

    /**
     * 查询-穿透后  单一最大融资人集中度
     *
     * @param param 参数map
     * @return-单一最大融资人集中度
     * @see
     * @since 1.0
     */
    List<RiskTemporaryDataDto> queryLargestFinancierCDPierce(Map<String, Object> map);

    /**
     * 查询-汇总  单一最大融资人集中度
     *
     * @param param 参数map
     * @return-单一最大融资人集中度
     * @see
     * @since 1.0
     */
    RiskTemporaryDataDto queryLargestFinancierCDSummary(Map<String, Object> map);
   
    RiskTemporaryDataDto queryLargestFinancierCDSummaryPierce(Map<String, Object> map);
     
    /**
     * 查询-非公开债券集中度
     *
     * @param param 参数map
     * @return-非公开债券集中度
     * @see
     * @since 1.0
     */
    List<RiskTemporaryDataDto> queryNonPublicBondCD(Map<String, Object> map);

    /**
     * 查询-单一关联方单笔交易占资本净额比例
     *
     * @param param 参数map
     * @return-单一关联方单笔交易占资本净额比例
     * @see
     * @since 1.0
     */
    List<RiskTemporaryDataDto> querySinglejoinratio(Map<String, Object> map);
    /**
     * <穿透后  非公开债券集中度>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<RiskTemporaryDataDto> queryNonPublicBondCDPierce(Map<String, Object> map);
    
    
    /**
     * 查询-非标资产占产品净资产比例 - 非标资产占全行总资产比例
     *
     * @param param 参数map
     * @return-非标资产占产品净资产比例 - 非标资产占全行总资产比例
     * @see
     * @since 1.0
     */
    List<RiskTemporaryDataDto> queryAssetsRatio(Map<String, Object> map);
    
    /**
     * 查询 穿透后-非标资产占产品净资产比例 - 非标资产占全行总资产比例
     *
     * @param param 参数map
     * @return-非标资产占产品净资产比例 - 非标资产占全行总资产比例
     * @see
     * @since 1.0
     */
    List<RiskTemporaryDataDto> queryAssetsRatioPierce(Map<String, Object> map);
    
    
    /**
     * 查询-浮动盈亏金额
     *
     * @param param 参数map
     * @return-浮动盈亏金额
     * @see
     * @since 1.0
     */
    List<RiskTemporaryDataDto> queryTdyFloatIngpl(Map<String, Object> map);
  
    /**
     * <穿透后  浮动盈亏金额 >
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<RiskTemporaryDataDto> queryTdyFloatIngplPierce(Map<String, Object> map);
    
    /**
     * <债券久期 >
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<RiskTemporaryDataDto> querySecDuration(Map<String, Object> map);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<RiskTemporaryDataDto> querySecDurationPierce(Map<String, Object> map);
    /**
     * <产品久期 >
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<RiskTemporaryDataDto> queryProductDuration(Map<String, Object> map);

    /**
     * <穿透后  产品久期>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<RiskTemporaryDataDto> queryProductDurationPierce(Map<String, Object> map);

    /**
     * <资产久期 >
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<RiskTemporaryDataDto> queryAssetDuration(Map<String, Object> map);
  
    /**
     * <资产久期 >
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<RiskTemporaryDataDto> queryAssetDurationPierce(Map<String, Object> map);

    
    List<RiskTemporaryDataDto> queryMobilityAssetSummary(Map<String, Object> map);
    
    
    List<RiskTemporaryDataDto> queryMobilityAssetSummaryPierce(Map<String, Object> map);
    
    RiskTemporaryDataDto querySingleNonStandardrRatio(Map<String, Object> map);

    RiskTemporaryDataDto querySingleNonStandardrRatioPierce(Map<String, Object> map);

}
