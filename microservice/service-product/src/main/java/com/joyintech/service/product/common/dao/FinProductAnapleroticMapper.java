/*
 * 文件名：FinProductAnapleroticMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：LiJun
 * 修改时间：2019年4月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.product.common.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.common.dto.FinProductAnapleroticInfoDto;
import com.joyintech.service.product.common.dto.FinProductAnapleroticPlanDto;
import com.joyintech.service.product.common.dto.FinProductCommonInfoDto;
import com.joyintech.tams.common.base.entity.FinBaseRuleInfo;
import com.joyintech.tams.common.base.entity.FinFloatAccrualSche;
import com.joyintech.tams.common.base.entity.FinFloatCalcRuleDetail;

/**
 * 业绩回补mapper
 * 〈功能详细描述〉
 * @author LiJun
 * @version 1.0 2019年4月25日
 * @see FinProductAnapleroticMapper
 * @since 1.0
 */
@Repository
public interface FinProductAnapleroticMapper {

    /**
     * 条件查询业绩回补信息
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<FinProductAnapleroticInfoDto> queryAnapleroticInfoById(Map<String, Object> param);
    /**
     * 分页查询业绩回补信息
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<FinProductAnapleroticInfoDto> selectForPage(Map<String, Object> param);
    /**
     * 产品代码查询业绩回补现金流id
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<FinProductAnapleroticInfoDto> queryAnapleroticInfoByFinprodId(Map<String, Object> param);
    
    /**
     * 下拉控件查询
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> queryProdIdInfo(Map<String, Object> param);
    
    int deleteScheByCashId(String cashId);
    /**
     * 根据cashId查询回补计划
     * @param anapleroticId
     * @return
     */
    List<FinProductAnapleroticPlanDto> selectAnapleroticPlan(@Param("anapleroticId") String anapleroticId);
    /**
     * 查询浮动管理费信息
     * @param finprodId
     * @param value
     * @return
     */
    FinFloatCalcRuleDetail selectFloatFeeInfo(@Param("finprodId") String finprodId, @Param("value") String value);
    /**
     * 条件查询分档产品业绩回补信息
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<FinProductAnapleroticInfoDto> queryAnapleroticInfoByProdStyle(Map<String, Object> param);
    
    /**
     * 根据cashId查询回补计划
     * @param anapleroticId
     * @return
     */
    FinProductCommonInfoDto getProdInfoByProdStyle(Map<String, Object> param);
    

    /**
     * 条件查询业绩回补信息 --双击详情页面
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    List<FinProductAnapleroticInfoDto> queryAnapleroticInfoByIdForDetail(Map<String, Object> param);
    /**
     * 根据产品代码与生效日删除业绩回补信息
     * @param finprodId
     * @param effDate
     * @return
     */
    int deleteAnapleroticBaseByFinprodId(@Param("finprodId") String finprodId, @Param("effDate") Date effDate);
	/**
	 * @param info
	 */
	int deleteFinCashInfo(FinBaseRuleInfo info);
	
	/**
	 * 
	 * <插入回补计划>
	 * <功能详细描述>
	 * @param finFloatAccrualSche
	 * @return
	 * @see
	 * @since 1.0
	 */
	int insertFinFloatAccrualSche(FinFloatAccrualSche finFloatAccrualSche);
	
	List<FinProductAnapleroticPlanDto> selectAnapleroticPlanByFinprodId(@Param("finprodId") String finprodId);

    String getOpenMode(@Param("finprodId") String finprodId);
}