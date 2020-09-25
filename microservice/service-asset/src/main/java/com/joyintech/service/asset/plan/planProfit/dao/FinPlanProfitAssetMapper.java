/*
 * 文件名：FinPlanAssetRMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：liuwei
 * 修改时间：2018年9月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.asset.plan.planProfit.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.service.asset.plan.planProfit.dto.FinPlanProfitAssetDto;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.asset.plan.planNet.dao.FinPlanNetAssetMapper;

/**
 * 计划类资产（利率型）Mapper
 * 
 * @author liuwei
 * @version 1.0 2018年9月10日
 * @see FinPlanNetAssetMapper
 * @since 1.0
 */
@Repository
public interface FinPlanProfitAssetMapper {
    /**
     * 资产代码的联想文本框事件
     * 
     * @param map 查询参数
     * @return 查询的资产代码和资产名称列表
     * @see
     * @since 1.0
     */
    List<SuggestDto> getFinProdMarketId(Map<String, Object> map);

 /*<!--增加快捷录入 add by zhuyongkang 2019/10/12 start  -->*/
    String getFinprodId(String finprodMarketId);
    /*<!--增加快捷录入 add by zhuyongkang 2019/10/12 end  -->*/
    /**
     * 查询界面查询事件
     * 
     * @param pageParam 页面参数
     * @return 查询的资产代码和资产名称列表
     * @see
     * @since 1.0
     */
    List<FinPlanProfitAssetDto> select(Map<String, Object> pageParam);

    /**
     * 出账编号的联想文本框事件
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
    FinPlanProfitAssetDto getChange(String cmsId);

    /**
     * 管理人的联想文本框事件
     * 
     * @param map 查询参数
     * @return 查询的管理人代码和管理人名称列表
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getIssuer(Map<String, Object> map);

    /**
     * 托管人的联想文本框事件
     * 
     * @param map 查询参数
     * @return 查询的托管人代码和托管人名称列表
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getTrusteeId(Map<String, Object> map);

    /**
     * 交易日历的联想文本框事件
     * 
     * @param map 查询参数
     * @return 查询的交易日历代码和交易日历名称列表
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getCalendarId(Map<String, Object> map);

    /**
     * 放款分行的联想文本框事件
     * 
     * @param map 查询参数
     * @return 查询结果
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getBank(Map<String, Object> map);

    /**
     * 资产提供行的多选下拉框事件
     * 
     * @param map 查询参数
     * @return 查询结果
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getBankList(Map<String, Object> map);
    /**
     * 修改或双击查看界面
     *
     * @param map 查询参数
     * @return 查询结果
     * @see
     * @since 1.0
     */
    List<FinPlanProfitAssetDto> getBasicInfoByFinprodId(Map<String,Object> map);
    /**
     * 查询市场代码
     *
     * @param finprodMarketId 市场代码
     * @return 市场代码
     * @see
     * @since 1.0
     */
    int selectFinprodMarketId(String finprodMarketId);
    /**
     * 查询客户名称
     *
     * @param custormerId 客户代码
     * @return 客户名称
     * @see
     * @since 1.0
     */
    String getName(String custormerId);

    /**
     * 资产是否存在交易
     *
     * @param finprodId 金融产品代码
     * @return 交易数
     * @see
     * @since 1.0
     */
//    int isTransInAsset(String finprodId);
    /**
     * 查询母计划下的子计划
     *
     * @param custormerId 客户代码
     * @return 客户名称
     * @see
     * @since 1.0
     */
    List<String> getFinprodIdByPFid(String finprodId);
     
}
