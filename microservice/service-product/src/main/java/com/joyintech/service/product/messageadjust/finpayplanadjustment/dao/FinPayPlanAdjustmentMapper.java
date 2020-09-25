/*
 * 文件名：FinPayPlanAdjustmentMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年10月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.product.messageadjust.finpayplanadjustment.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.FinCashCalcRuleKey;
import com.joyintech.tams.common.base.entity.FinCashPayRuleKey;
import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.common.base.entity.FinFloatCountingRuleKey;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.FinRateInfoKey;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.common.dto.BussFinProductFeeInfoDto;
import com.joyintech.service.product.messageadjust.finpayplanadjustment.dto.FinPayPlanAdjustmentDto;
import com.joyintech.service.product.messageadjust.finpayplanadjustment.dto.FinPayRuleAdjustmentDto;

/**
 * 信息调整管理_付费计划调整
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年10月18日
 * @see FinPayPlanAdjustmentMapper
 * @since 1.0
 */
@Repository
public interface FinPayPlanAdjustmentMapper {
    
    /**
     * 
     * 产品代码下拉框
     * <功能详细描述>
     * @param param Map<String, Object>
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getFinprodId(Map<String, Object> param);
    
    /**
     * 
     * 产品款型下拉框
     * <功能详细描述>
     * @param param Map<String, Object>
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getProdType(Map<String, Object> param);
    
    /**
     * 
     * 费用名称下拉框
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getFeeName(Map<String, Object> param);
    
    /**
     * 
     * 根据产品代码和费用名称，联动带出产品对应费用的付费计划 
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<FinPayPlanAdjustmentDto> selectForPage(Map<String, Object> param);
    
    /**
     * 
     * 获取到期日
     * <功能详细描述>
     * @param Map<String, Object>
     * @return FinPayPlanAdjustmentDto
     * @see
     * @since 1.0
     */
    FinPayPlanAdjustmentDto getMdate(Map<String, Object> param);
    
    /**
     * 
     * 获取现金流计划
     * <功能详细描述>
     * @param cashId
     * @return
     * @see
     * @since 1.0
     */
    List<FinCashPlan> selectByCashId(String cashId);
    
    /**
     * 
     * 付费规则调整产品代码下拉框 yzg 20191126
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getSelectFinprodId(Map<String, Object> param);

    /**
	 * 查询产品费用列表信息
	 * @param map 参数
	 * @return 费用列表信息
	 */
	List<FinPayRuleAdjustmentDto> queryFeeInfoByFinprodId(Map<String, Object> map);
	
	/**
	 * 产品款型下拉框
	 * @param param
	 * @return
	 */
	List<SelectOptionDto> getSelectProdStyle(Map<String, Object> param);
	
	/**
	 * 根据CashId删除付费计划
	 */
	void deleteCashPlanByCashId(String cashId);
	
	/**
	 * 根据金融产品代码查询子产品
	 * @param finprodId
	 * @return
	 */
	List<FinProduct> selectLayProductByFinproductId(String finprodId);
	
	/**
	 * 删除生效日大于等于下一付费区间开始日的现金流支付规则
	 * @param key
	 */
	void deleteFinCashPayByEffdate(FinCashPayRuleKey key);
	
	/**
	 * 删除生效日大于等于下一付费区间开始日的现金流计算规则
	 * @param key
	 */
	void deleteFinCashCalcRuleByEffdate(FinCashCalcRuleKey key);
	
	/**
	 * 删除生效日大于等于下一付费区间开始日的现金流计提规则
	 * @param key
	 */
	void deleteFinFloatCountingRuleByEffdate(FinFloatCountingRuleKey key);
	
	/**
	 * 删除生效日大于等于下一付费区间开始日的现金流计提规则
	 * @param key
	 */
	void deleteFinRateInfoByEffdate(FinRateInfoKey key);
}
