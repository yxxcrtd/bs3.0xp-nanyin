/*
 * 文件名：FinPlanAssetNet001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：liuwei
 * 修改时间：2018年9月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.asset.plan.planNet.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.asset.plan.planNet.dto.BussRepAssetMngProdInfo;
import com.joyintech.service.asset.plan.planNet.dto.BussRepOutInvestAgrInfo;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.asset.plan.planNet.dto.FinPlanNetAssetDto;

/**
 * 计划类资产（净值型）Mapper
 * 
 * @author liuwei
 * @version 1.0 2018年9月10日
 * @see FinPlanNetAssetMapper
 * @since 1.0
 */
@Repository
public interface FinPlanNetAssetMapper {

    /**
     * 资产代码的联想文本框事件
     * 
     * @param map 查询参数
     * @return 查询的资产代码和资产名称列表
     * @see
     * @since 1.0
     */
    List<SuggestDto> getFinProdMarketId(Map<String, Object> map);

    /**
     * 查询界面查询事件
     * 
     * @param pageParam 页面参数
     * @return 查询的资产代码和资产名称列表
     * @see
     * @since 1.0
     */
    List<FinPlanNetAssetDto> select(Map<String, Object> pageParam);

    /**
     * 出账编号的联想下拉框事件
     *
     * @param map 查询参数
     * @return 查询结果
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getCmsId(Map<String, Object> map);

    /**
     * 出账编号的联动事件
     *
     * @param cmsId 出账编号
     * @return 查询结果
     * @see
     * @since 1.0
     */
    FinPlanNetAssetDto getChange(String cmsId);

    /**
     * 管理人的联想下拉框事件
     * 
     * @param map 查询参数
     * @return 查询的管理人代码和管理人名称列表
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getIssuer(Map<String, Object> map);

    /**
     * 托管人的联想下拉框事件
     * 
     * @param map 查询参数
     * @return 查询的托管人代码和托管人名称列表
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getTrusteeId(Map<String, Object> map);

    /**
     * 交易日历的联想下拉框事件
     * 
     * @param map 查询参数
     * @return 查询的交易日历代码和交易日历名称列表
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getCalendarId(Map<String, Object> map);

    /**
     * 修改或双击查看界面
     *
     * @param map 查询参数
     * @return 查询结果
     * @see
     * @since 1.0
     */
    FinPlanNetAssetDto getBasicInfoByFinprodId(Map<String,Object> map);
    /**
     * 资产管理计划中债报送信息组件
     *
     * @param finprodId 金融产品代码
     * @return 查询结果
     * @see
     * @since 1.0
     */
    BussRepAssetMngProdInfo getRepAssetMngProdInfoByFinprodId(String finprodId);
    /**
     * 委外投资-协议方式中债报送信息组件
     *
     * @param finprodId 金融产品代码
     * @return 查询结果
     * @see
     * @since 1.0
     */
    BussRepOutInvestAgrInfo getRepOutInvestAgrInfoByFinprodId(String finprodId);
    /**
     * 查询市场代码
     *
     * @param finprodMarketId 市场代码
     * @return 市场代码
     * @see
     * @since 1.0
     */
    int selectFinprodMarketId(Map<String,Object> paraMap);
    /**
     * 查询客户名称
     *
     * @param custormId 客户代码
     * @return 客户名称
     * @see
     * @since 1.0
     */
    String getName(String custormId);
    /**
     * 查询资产代码
     *
     * @param finprodMarketId 市场代码
     * @return 资产代码
     * @see
     * @since 1.0
     */
    String getFinprodId(String finprodMarketId);
}
