/*
 * 文件名：ReconcileSchemeInfo001Mapper.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算POC
 * 修改人：jiangchuanzhi
 * 修改时间：2018年1月22日
 * 修改内容：新建
 * 系统名称：估值核算POC
 */

package com.joyintech.service.valuation.valuationparametermanage.reconcilescheme.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.valuationparametermanage.reconcilescheme.dto.ValReconcileSchemeAssetConfigDto;
import com.joyintech.service.valuation.valuationparametermanage.reconcilescheme.dto.ValReconcileSchemeInfo001Dto;
import com.joyintech.service.valuation.valuationparametermanage.reconcilescheme.dto.ValReconcileSchemeNatureConfigDto;
import com.joyintech.service.valuation.valuationparametermanage.reconcilescheme.dto.ValReconcileSchemeStructConfigDto;
import com.joyintech.service.valuation.valuationparametermanage.reconcilescheme.dto.ValReconcileSchemeSubjectConfigDto;
import com.joyintech.service.valuation.valuationparametermanage.valuationmethodscheme.dto.ValuationMethodScheme001Dto;
import com.joyintech.tams.common.base.entity.BokSubject;


/**
 * 对账规则配置Mapper
 * 
 * @author weipeng
 * @version 1.0 2018年11月13日
 * @see ValReconcileSchemeInfo001Mapper
 * @since 1.0
 */
@Repository(value="serviceValReconcileSchemeInfo001Mapper")
public interface ValReconcileSchemeInfo001Mapper {

    /**
     * 检索方案名称下拉框
     * 
     * @param schemeTypeList 参数
     * @return 方案信息List
     * @since 1.0
     */
    List<SelectOptionDto> getSchemeInfoList(Map<String, Object> params);
    
    /**
     * 检索方案名称左侧列表
     * 
     * @param schemeTypeList 参数
     * @return 方案信息List
     * @since 1.0
     */
    List<ValReconcileSchemeInfo001Dto> findSchemeList(Map<String, Object> params);

    /**
     * 获取所有科目信息
     * 
     * @return 科目信息
     * @since 1.0
     */
    List<BokSubject> getAllBokSubject(@Param("booksetId") String booksetId);

    /**
     * 页面初始化所属账套下拉框数据
     * 
     * @return 账套数据
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getBokBookSet();
    
    /**
     * 通过方案代码查询对应方案信息
     * @param schId 方案代码
     * @return 方案信息
     * @see
     * @since 1.0
     */
    ValReconcileSchemeInfo001Dto getSchemeInfo(@Param("booksetSchemeId") String booksetSchemeId);

    /**
     * 插入结构映射明细
     * 
     * @param structList 结构映射明细List
     * @since 1.0
     */
    void insertStructConfigBatch(@Param("structList") List<ValReconcileSchemeStructConfigDto> structList);

    /**
     * 插入科目映射明细
     * 
     * @param subjectList 科目映射明细List
     * @since 1.0
     */
    void insertSubjectConfigBatch(@Param("subjectList") List<ValReconcileSchemeSubjectConfigDto> subjectList);
 
    /**
     * 插入科目性质映射明细
     * 
     * @param subjectList 科目映射明细List
     * @since 1.0
     */
    void insertNatureConfigBatch(@Param("subjectList") List<ValReconcileSchemeNatureConfigDto> natureList);

    /**
     * 插入资产映射明细
     * 
     * @param assetList 资产映射明细List
     * @since 1.0
     */
    void insertAssetConfigBatch(@Param("assetList") List<ValReconcileSchemeAssetConfigDto> assetList);

    /**
     * 根据方案代码删除结构映射明细
     * 
     * @param schemeId 方案代码
     * @return 删除件数
     * @since 1.0
     */
    int deleteStructBySchemeId(@Param("schemeId") String schemeId);

    /**
     * 根据方案代码删除科目映射明细
     * 
     * @param schemeId 方案代码
     * @return 删除件数
     * @since 1.0
     */
    int deleteSubjectBySchemeId(@Param("schemeId") String schemeId);
    
    /**
     * 根据方案代码删除科目性质映射明细
     * 
     * @param schemeId 方案代码
     * @return 删除件数
     * @since 1.0
     */
    int deleteNatureBySchemeId(@Param("schemeId") String schemeId);

    /**
     * 根据方案代码删除资产映射明细
     * 
     * @param schemeId 方案代码
     * @return 删除件数
     * @since 1.0
     */
    int deleteAssetBySchemeId(@Param("schemeId") String schemeId);
    
    /**
     * 根据方案代码和账套代码删除 账套方案配置
     * @param schemeId
     * @param booksetId
     * @return
     * @see
     * @since 1.0
     */
    int deleteBooksetPlanBySchemeId(@Param("schemeId") String schemeId,
                                    @Param("booksetId") String booksetId);

    /**
     * 初始化结构映射
     * 
     * @param params 方案代码、账套代码
     * @return 结构映射明细
     * @since 1.0
     */
    List<ValReconcileSchemeStructConfigDto> initStructGrid(Map<String, Object> params);

    /**
     * 初始化科目映射
     * 
     * @param params 方案代码、账套代码
     * @return 科目映射明细
     * @since 1.0
     */
    List<ValReconcileSchemeSubjectConfigDto> initSubjectGrid(Map<String, Object> params);
    
    /**
     * 初始化科目性质映射
     * 
     * @param params 方案代码、账套代码
     * @return 科目性质映射明细
     * @since 1.0
     */
    List<ValReconcileSchemeNatureConfigDto> initNatureGrid(Map<String, Object> params);

    /**
     * 初始化资产映射
     * 
     * @param params 方案代码、账套代码
     * @return 资产映射明细
     * @since 1.0
     */
    List<ValReconcileSchemeAssetConfigDto> initAssetGrid(Map<String, Object> params);

}
