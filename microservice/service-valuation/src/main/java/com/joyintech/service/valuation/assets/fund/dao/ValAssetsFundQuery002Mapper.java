/*
 * 文件名：ValAssetsFundQuery001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangwei
 * 修改时间：2018年7月19日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.assets.fund.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.service.valuation.assets.fund.dto.ValAssetsFundQuery002Dto;
import com.joyintech.service.valuation.assets.fund.dto.ValCashPlanDto;
import com.joyintech.tams.common.base.entity.FinCashInfo;
import com.joyintech.tams.common.base.entity.FinCashPayRule;
import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.FinProductAdd;
import com.joyintech.tams.common.base.entity.FinProductType;

/**
 * 基金类资产查询
 * 
 * @author zhangwei
 * @version 1.0 2018年7月19日
 * @see ValAssetsFundQuery002Mapper
 * @since 1.0
 */
@Repository(value="serviceValAssetsFundQuery002Mapper")
public interface ValAssetsFundQuery002Mapper {

    /**
     * 根据查询finprodId单个详细信息
     * 
     * @param finprodId
     * @return 单个详细信息
     * @see
     * @since 1.0
     */
    ValAssetsFundQuery002Dto selectByfinprodId(String finprodId);

    /**
     * 根据查询finprodId 付息计划列表
     * 
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    List<ValCashPlanDto> selectCashPlanByFinprodId(String finprodId);

    /**
     * 校验输入的资产代码是否唯一
     * 
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    boolean checkFindprodId(@Param("finprodId") String finprodId);

    /**
     * 更新标的类金融产品表信息
     * 
     * @param finProduct
     * @return
     * @see
     * @since 1.0
     */
    int updateFinProduct(FinProduct finProduct);

    /**
     * 更新标的类金融产品扩展表
     * 
     * @param finProductExt
     * @return
     * @see
     * @since 1.0
     */
    int updateFinProductExt(FinProductAdd finProductAdd);

    /**
     * 更新金融产品分类表
     * 
     * @param finProductType
     * @return
     * @see
     * @since 1.0
     */
    int updateFinProductType(FinProductType finProductType);

    /**
     * 更新现金流信息表（货币基金）
     * 
     * @param finCashInfo
     * @return
     * @see
     * @since 1.0
     */
    int updateFinCashInfo(FinCashInfo finCashInfo);

    /**
     * 更新现金流支付规则表（货币基金）
     * 
     * @param finCashPayRule
     * @return
     * @see
     * @since 1.0
     */
    int updateFinCashPayRule(FinCashPayRule finCashPayRule);

    /**
     * 更新现金流计划表（货币基金）
     * 
     * @param finCashPlan
     * @return
     * @see
     * @since 1.0
     */
    int updateFinCashPlan(FinCashPlan finCashPlan);

    /**
     * 存在交易时，更新标的类金融产品表信息
     * 
     * @param finProduct
     * @return
     * @see
     * @since 1.0
     */
    int updateFinProduct2(FinProduct finProduct);
}
