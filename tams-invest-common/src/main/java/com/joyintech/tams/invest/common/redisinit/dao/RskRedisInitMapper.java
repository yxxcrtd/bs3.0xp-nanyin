/*
 * 文件名：RskRedisInitMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2019年9月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.redisinit.dao;

import com.joyintech.tams.common.base.entity.RskIndividAssetDel;
import com.joyintech.tams.common.base.entity.RskIndividSpecialStage;
import com.joyintech.tams.common.base.entity.RskIndividUnite;
import com.joyintech.tams.invest.common.redisinit.dto.*;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * redis初始化逻辑
 * 〈功能详细描述〉
 * @author 裴家俊
 * @version 1.0 2018年11月21日
 * @see RskRedisInitMapper
 * @since 1.0
 */
@Repository
public interface RskRedisInitMapper {
    /**
     * 获取组合基本信息
     * @return ImplPortf
     * @since 1.0
     */
    List<ImplPortf> getPtlPortfolioData(Map<String, Object> map);
    /**
     * 获取资产基本信息
     * @return ImplEvent
     * @since 1.0
     */
    List<ImplAsset> getPtlAssetData(Map<String, Object> map);
    /**
     * 获取日历基本信息
     * @return ImplCalendar
     * @since 1.0
     */
    List<ImplCalendar> getCalendarData(Map<String, Object> map);
    /**
     * 获取预占实占信息
     * @return ImplOccupyRel
     * @since 1.0
     */
    List<ImplOccupyRel> getOccupyRelData();
    /**
     * 获取风险事件
     * @return ImplEvent
     * @since 1.0
     */
    List<ImplEvent> getRskEventData();

    /**
     * 获取风险事件与参数之间的关系
     * @return ImplEventItem
     * @since 1.0
     */
    List<ImplEventItem> getRskEventItemData();

    /**
     * 获取风险事件与参数之间的关系
     * @return ImplEventParaRel
     * @since 1.0
     */
    List<ImplEventParaRel> getRskEventParaResData();

    /**
     * 获取风险参数明细值
     * @return ImplParam
     * @since 1.0
     */
    List<ImplParam> getRskIndParamDtlData(Map<String, Object> map);

    /**
     * 获取风险指标
     * @return ImplParam
     * @since 1.0
     */
    List<ImplIndicator> getRskIndicatorData(Map<String, Object> map);

    /**
     * 获取指标联合设置
     * @return ImplParam
     * @since 1.0
     */
    List<RskIndividUnite> getRskIndUniteData(Map<String, Object> map);

    /**
     * 获取指标特殊阈值
     * @return RskIndividSpecialStage
     * @since 1.0
     */
    List<RskIndividSpecialStage> getRskIndIndivLimit(Map<String, Object> map);
    /**
     * 获取指标特殊资产阈值
     * @return RskIndividSpecialStage
     * @since 1.0
     */
    List<RskIndividSpecialStage> getRskIndIndivAssetLimit(Map<String, Object> map);
    /**
     * 获取指标剔除资产
     * @return RskIndividAssetDel
     * @since 1.0
     */
    List<RskIndividAssetDel> getRskRemoveAssetData(Map<String, Object> map);

    /**
     * 获取每个组合被绑定的组合包
     * @return ImplPtlIndicator
     * @since 1.0
     */
    List<ImplPtlIndicator> getRskPtlRelateBagData(Map<String, Object> map);

    /**
     * 获取每个组合或组合包关联的指标
     * @return ImplPtlIndicator
     * @since 1.0
     */
    List<ImplPtlIndicator> getRskPtlIndicatorData(Map<String, Object> map);

    /**
     * 获取组合包下的所有组合
     * @return ImplPtlIndicator
     * @since 1.0
     */
    List<String> getAllPortfIdWithPortfBag(Map<String, Object> map);
    
    /**
     * 查询 市场代码:交易场所与资产代码的关系
     * 
     * @param map 查询条件
     * @return 市场代码:交易场所与资产代码的关系
     */
    List<ImplAsset> selectAssetIndex(Map<String, Object> map);

    /**
     * 查询 基金估值信息
     * 
     * @param map 查询条件
     * @return 基金估值信息
     */
    List<ImplFundValuation> selectFundVal(Map<String, Object> map);

    /**
     * 查询 汇率行情信息
     * 
     * @param map 查询条件
     * @return 汇率行情信息
     */
    List<ImplExcharge> selectExchargeInfo(Map<String, Object> map);

    /**
     * 查询 参数值的变化值
     * 
     * @param map 查询条件
     * @return 参数值的变化值
     */
    List<Map<String,Object>> selectParamValOffset(Map<String, Object> map);

    // add by zhangrui1 start
    /**
     * 查询资产的规模信息
     * 
     * @param map 查询条件
     * @return 资产的规模信息
     */
    List<ImplProdAssetAmount> selectProdAssetAmount(Map<String, Object> map);
    // add by zhangrui1 end
}
