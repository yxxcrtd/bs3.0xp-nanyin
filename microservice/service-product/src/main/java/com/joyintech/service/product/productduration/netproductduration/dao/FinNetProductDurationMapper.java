/*
 * 文件名：FinNetProductDurationMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zqz
 * 修改时间：2019年1月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.product.productduration.netproductduration.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.productduration.netproductduration.dto.FinCashPlanDto;
import com.joyintech.service.product.productduration.netproductduration.dto.FinNetProductDurationDto;
import com.joyintech.service.product.productduration.netproductduration.dto.FinNetProductDurationLayerDto;
import com.joyintech.tams.common.base.entity.BokPositionPrd;
import com.joyintech.tams.common.base.entity.FinBaseRuleInfo;
import com.joyintech.tams.common.base.entity.FinCashCalcRule;
import com.joyintech.tams.common.base.entity.FinCashInfo;
import com.joyintech.tams.common.base.entity.FinFloatCalcRule;
import com.joyintech.tams.common.base.entity.FinFloatCalcRuleDetail;
import com.joyintech.tams.common.base.entity.FinTradeProduct;
import com.joyintech.tams.common.base.entity.TrdFinProductDealTotal;
import com.joyintech.tams.common.base.entity.TrdProductDeal;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author zqz
 * @version 1.0 2019年1月15日
 * @see FinNetProductDurationMapper
 * @since 1.0
 */
public interface FinNetProductDurationMapper {

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getFinprodMarketIdList(Map<String, Object> map);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    FinNetProductDurationDto getNetValueInfo(Map<String, Object> map);
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    BigDecimal getTdyBuyShareAmt(Map<String, Object> map);
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    BigDecimal getAfterTdySellShareAmt(Map<String, Object> map);
    /**
     * 查询净值化存续期信息
     * @param map
     * @return
     */
	FinNetProductDurationDto selectInfo(Map<String, Object> map);

	FinCashPlanDto queryBonusRule(Map<String, Object> map);

	List<TrdFinProductDealTotal> queryAfterTrdFinProductDealTotal(FinNetProductDurationDto dto);

	BokPositionPrd queryMaxDateBokPositionPrd(FinNetProductDurationDto dto);

	List<TrdFinProductDealTotal> queryAfterAssetTrdFinProductDealTotal(FinNetProductDurationDto dto);
	/**
	 * add by dingjunfeng 产品在做提前终止和产品到期之前 需要判断资产有没有全部处置
	 * @param dto
	 * @return
	 */
	Integer queryMaxDatePtlSecPosition(FinNetProductDurationDto dto);
	/**
	 * 查询是否存在认购之外的交易
	 * @param map 输入条件
	 * @return 
	 */
	Integer queryIsExistSubTrade(Map<String, Object> map);
	/**
     * 根据金融产品代码查询分层金额明细
     * add by wm 2019-06-24
     * @param map 参数
     * @return 分层金额明细
     */
	List<FinNetProductDurationLayerDto> queryLayAmtDetailByFinprodId(Map<String, Object> map);
	
	 /**
     * 根据金融产品代码查询分层交易明细
     * add by wm 2019-06-24
     * @param bussId 交易编号
     * @return 分层金额明细
     */
    List<FinNetProductDurationLayerDto> queryLayAmtDetailByBussId(Map<String, Object> map);
    /**
     * 查询产品分红方式
     * add by wm 
     * @param bussId 交易编号
     * @return 分层金额明细
     */
    List<String> getFinprodPayType(Map<String, Object> map);
    /**
     * 查询现金流信息
     * @param map 
     * @return 现金流信息数据
     */
    List<FinCashInfo> getFinCashInfo(Map<String, Object> map);
    /**
     * 查询现金流计算规则
     * @param map 
     * @return 现金流计算规则
     */
    List<FinCashCalcRule> getFinCashCalcRule(Map<String, Object> map);
    /**
     * 查询业绩报酬计算规则
     * @param map 
     * @return 业绩报酬计算规则
     */
    List<FinFloatCalcRule> getFinFloatCalcRule(Map<String, Object> map);
    /**
     * 查询业绩报酬计算明细
     * @param map 
     * @return 业绩报酬计算明细
     */
    List<FinFloatCalcRuleDetail> getFinFloatCalcRuleDetail(Map<String, Object> map);
    /**
     * 查询业绩基准表
     * @param map 
     * @return 业绩基准表
     */
    List<FinBaseRuleInfo> getFinBaseRuleInfo(Map<String, Object> map);
    /**
     * 删除金融产品标的拓展表
     * @param map 
     * @return 
     */
    int deleteFinTradeProductAddByFinprodId(Map<String, Object> map);
    /**
     * 删除业绩报酬计算规则
     * @param map 
     * @return 
     */
    int deleteFinFloatCalcRuleByFinprodId(Map<String, Object> map);
    /**
     * 删除业绩报酬计算规则明细
     * @param map 
     * @return 
     */
    int deleteFinFloatCalcRuleDetailByFinprodId(Map<String, Object> map);
    /**
     * 删除业绩基准表数据(FIN_BASE_RULE_INFO)
     * @param map 
     * @return 
     */
    int deleteFinBaseRuleInfoByFinprodId(Map<String, Object> map);
    /**
     * 获取期限值
     * @param map 
     * @return 
     */
    List<SelectOptionDto> getTermDaysList(Map<String, Object> map);
    /**
     * 获取期次信息
     * @param map 
     * @return 
     */
    List<SelectOptionDto> getPeriodsList(Map<String, Object> map);
    /**
     * 获取期次子产品
     * @param map 
     * @return 
     */
    List<FinNetProductDurationDto> getPeriodInfoList(Map<String, Object> map);
    /**
     * 获取期次子产品最新净值日、单位净值
     * @param map 
     * @return 
     */
    BokPositionPrd getBokPositionPrd(Map<String, Object> map);
    /**
     * 获取已完成交易期次子产品
     * @param map 
     * @return 
     */
    List<FinNetProductDurationDto> getPeriodInfoListIntrade(Map<String, Object> map);
    /**
     * 获取期次子产品浮动管理费
     * @param map 	finprodId branch busiType totalTradeId
     * @return 
     */
    TrdProductDeal getManeTrdProductDeal(Map<String, Object> map);
    /**
     * 通过交易编号获取分支序号
     * @param 
     * @return 
     */
    TrdProductDeal getTrdProductDealByTradeId(Map<String, Object> map);
    /**
     * 通过交易编号获取分支序号
     * @param 
     * @return 
     */
    List<FinTradeProduct> getFinTradeProductByPeriodId(Map<String, Object> map);
    /**
     * 通过交易编号获取分支序号
     * @param 
     * @return 
     */
    BokPositionPrd getTrdValueByCdate(Map<String, Object> map);

	long checkId(String tradeId);
	/**
     * 获取货币型产品交易当天现金分红和红利下发金额
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    BigDecimal queryBounsAmtForHB(Map<String, Object> map);
}
