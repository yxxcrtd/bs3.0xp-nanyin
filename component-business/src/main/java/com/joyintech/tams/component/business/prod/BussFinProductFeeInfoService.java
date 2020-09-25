/*
 * 文件名：BussFinProductFeeInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：何阳阳
 * 修改时间：2018年9月30日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.prod;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.common.base.entity.StlCapacctInfo;
import com.joyintech.tams.component.business.prod.dto.BussFinProductFeeInfoDto;

/**
 * 产品费用业务组件
 * @author 何阳阳
 * @version 1.0 2018年9月30日
 * @see BussFinProductFeeInfoService
 * @since 1.0
 */
public interface BussFinProductFeeInfoService {

    /**
     * 默认实体名
     */
    String DEFAULT_ENTITY_NAME = "productFeeInfoDto";

    /**
     * 查询费用品种信息
     *
     * @return 费用品种信息
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> queryFeeInfoList(Map<String, Object> map);

    /**
     * 根据费用代码查询费用基本信息
     *
     * @param feeId 费用代码
     * @param finprodMarketId 市场代码
     * @return 费用基本信息
     * @see
     * @since 1.0
     */
    BussFinProductFeeInfoDto queryMstFeeinfoByFeeId(String feeId, String finprodMarketId);

    /**
     * 根据费用信息生成付费计划
     *
     * @param finProductFeeInfoDto 费用信息
     * @return 付费计划
     * @see
     * @since 1.0
     */
    List<FinCashPlan> generatePayFeePlan(BussFinProductFeeInfoDto finProductFeeInfoDto);

    /**
     * 根据金融产品代码查询费用列表信息
     *
     * @param finprodId 金融产品代码
     * @return 费用列表信息
     * @see
     * @since 1.0
     */
    List<BussFinProductFeeInfoDto> queryFeeInfoByFinprodId(String finprodId,String finprodType2);

    /**
     * 根据金融产品代码和费用代码查询费用计划
     *
     * @param finprodId 金融产品代码
     * @param feeId 费用代码
     * @return 费用计划
     * @see
     * @since 1.0
     */
    List<FinCashPlan> queryFeePlanListByFinprodId(String finprodId, String feeId);

    /**
     * 根据组合代码查询费用列表信息
     *
     * @param ptlId 组合代码
     * @return 费用列表信息
     * @see
     * @since 1.0
     */
    List<BussFinProductFeeInfoDto> queryFeeInfoByPtlId(String ptlId);

    /**
     * 根据费用代码查询费用基本信息--利率调整
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    BussFinProductFeeInfoDto queryMstFeeinfoForRateAdjByFeeId(Map<String,Object> param);

    /**
     * 根据金融产品代码查询费用列表信息包含浮动管理费
     *
     * @param finprodId 金融产品代码
     * @return 费用列表信息
     * @see
     * @since 1.0
     */
    List<BussFinProductFeeInfoDto> queryFeeInfoByFinprodIdEx(String finprodId);

    /**
     * 查询资金账户费用品种信息
     * 
     * @return 费用品种信息
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> queryFundFeeInfoList(Map<String, Object> map);
    
    /**
     * 获取账号信息
     * @return 清算账户表信息
     * @see
     * @since 1.0
     */
    public StlCapacctInfo getCctInfo(String acctNo);
    
    /**
     * 付费计划调整
     * @param finProductFeeInfoDto
     * @param planStartDay
     * @param planEndDay
     * @return
     */
    public List<FinCashPlan> finCashPlanAdjustment(BussFinProductFeeInfoDto finProductFeeInfoDto,Date planStartDay,Date planEndDay);
}
