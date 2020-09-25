/*
 * 文件名：ValBondassetquery001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：qiangchengxi
 * 修改时间：2018年7月20日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.service.valuation.assets.bondasset.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectDto;
import com.joyintech.service.valuation.assets.bondasset.dto.ValBondassetBJDto;
import com.joyintech.service.valuation.assets.bondasset.dto.ValBondassetFXDto;
import com.joyintech.service.valuation.assets.bondasset.dto.ValBondassetHBDto;
import com.joyintech.service.valuation.assets.bondasset.dto.ValBondassetXQDto;
import com.joyintech.service.valuation.assets.bondasset.dto.ValBondassetquery002Dto;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author zhangzhao
 * @version 1.0 2018年7月20日
 * @see ValBondassetquery002Mapper
 * @since 1.0
 */
@Repository(value="serviceValBondassetquery002Mapper")
public interface ValBondassetquery002Mapper {
    

    /**
     * 根据产品findprodid获取债券详细信息
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    ValBondassetquery002Dto queryBondasetByfinprodId(Map<String, Object> map);

    /**
     * 详情页表单数据的获取-行权信息
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<ValBondassetquery002Dto> queryXqinfo(Map<String, Object> map);

    /**
     * 详情页表单数据的获取-债券利率报价信息
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<ValBondassetquery002Dto> queryintrate(Map<String, Object> map);

    /**
     * 详情页表单数据的获取-付息计划列表
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<ValBondassetquery002Dto> queryintpay(Map<String, Object> map);

    /**
     * 详情页表单数据的获取-还本计划列表
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<ValBondassetquery002Dto> queryrepay(Map<String, Object> map);
    
    /**
     * 详情页表单数据的获取-现金流列表
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<ValBondassetquery002Dto> querycashflow(Map<String, Object> map);

    /**
     * 根据金融资产代码查询资产信息
     * <功能详细描述>
     * @param bondid
     * @return
     * @see
     * @since 1.0
     */
    int selectByPrimaryKey(String finprodid);

    /**
     * 查询资产是否有交易信息
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    int selectDealCountByFinprodid(Map<String, Object> map);

    /**
     * 根据金融产品代码删标的类金融产品表
     * <功能详细描述>
     * @param cashId
     * @return
     * @see
     * @since 1.0
     */
    int deleteFinProductByFinProdId(@Param("finprodId")String finprodId);
    
    /**
     * 更新标的类金融产品表
     * <功能详细描述>
     * @param entity
     * @see
     * @since 1.0
     */
    int updateFinProduct(ValBondassetquery002Dto entity);
    
    /**
     * 根据金融产品代码删标的类金融产品扩展表
     * <功能详细描述>
     * @param cashId
     * @return
     * @see
     * @since 1.0
     */
    int deleteFinProductExtByFinProdId(@Param("finprodId")String finprodId);
    
    /**
     * 更新标的类金融产品扩展表
     * <功能详细描述>
     * @param entity
     * @return
     * @see
     * @since 1.0
     */
    int updateFinProductExt(ValBondassetquery002Dto entity);
    
    /**
     * 根据金融产品代码删金融产品分类表
     * <功能详细描述>
     * @param cashId
     * @return
     * @see
     * @since 1.0
     */
    int deleteFinProductTypeByFinProdId(@Param("finprodId")String finprodId);
    
    /**
     * 更新金融产品分类表
     * <功能详细描述>
     * @param entity
     * @return
     * @see
     * @since 1.0
     */
    int updateFinProductType(Map<String,Object> map);
    
    /**
     * 根据金融产品代码删除行权计划
     * <功能详细描述>
     * @param cashId
     * @return
     * @see
     * @since 1.0
     */
    int deleteFinExercisePlanByFinProdId(@Param("finprodId")String finprodId);
    
    /**
     * 批量插入行权计划
     * <功能详细描述>
     * @param xqList
     * @param cashId 
     * @return
     * @see
     * @since 1.0
     */
    int insertFinExercisePlan(@Param("list")List<ValBondassetXQDto> xqList, 
                              @Param("cashId") String cashId,
                              @Param("finprodId") String finprodId);
    
    /**
     * 根据金融产品代码删除现金流信息表
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    int deleteFinCashInfoByFinProdId(@Param("finprodId")String finprodId);
    
    /**
     * 根据金融产品代码删除现金流计算规则
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    int deleteFinCashCalcRuleByFinProdId(@Param("finprodId")String finprodId);
    
    /**
     * 批量插入现金流计算规则
     * <功能详细描述>
     * @param intRateList
     * @param basis 
     * @param ratetype 
     * @return
     * @see
     * @since 1.0
     */
    int insertFinCashCalcRule(@Param("list")List<ValBondassetBJDto> intRateList, 
                              @Param("cashId") String cashId,
                              @Param("finprodId") String finprodId, 
                              @Param("ratetype")String ratetype, 
                              @Param("basis")String basis);
    
    /**
     * 根据金融产品代码删除现金流支付规则
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    int deleteFinCashPayRuleByFinProdId(@Param("finprodId")String finprodId);
    
    /**
     * 根据金融产品代码更新现金流支付规则
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    int updateFinCashPayRuleByFinProdId(ValBondassetquery002Dto dto);
    
    /**
     * 根据金融产品代码删除现金流计划
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    int deleteFinCashPlanByFinProdId(@Param("finprodId")String finprodId);
    
    /**
     * 批量插入现金流计划（付息）
     * <功能详细描述>
     * @param cashPlanList 付息计划List
     * @param cashId 现金流代码
     * @param finprodId 金融产品代码
     * @return
     * @see
     * @since 1.0
     */
    int insertFinCashPlanFX(@Param("list")List<ValBondassetFXDto> cashPlanList,
                            @Param("cashId") String cashId,
                            @Param("finprodId") String finprodId);
    
    /**
     * 批量插入现金流计划（还本）
     * <功能详细描述>
     * @param retList 还本计划List
     * @param cashId 现金流代码
     * @param finprodId 金融产品代码
     * @return
     * @see
     * @since 1.0
     */
    int insertFinCashPlanHB(@Param("list")List<ValBondassetHBDto> cashPlanList,
                            @Param("cashId") String cashId,
                            @Param("finprodId") String finprodId);

    /**
     * 查询交易日历下拉框
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    List<SelectDto> selectTradeCalendar();
    
    /**
     * 根据查询日期、交易场所代码查询下一工作日
     * 
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    Date selectNextWorkDay(Map<String,Object> map);

    /**
     * 查询发行人下拉框
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    List<SelectDto> selectIssure();
}
