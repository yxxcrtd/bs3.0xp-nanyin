/*
 * 文件名：CommonSearch.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangJin
 * 修改时间：2019年10月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.evaluation.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.PtlInvStrategyDetailClass;
import com.joyintech.tams.component.business.evaluation.dto.LevelProdDto;
import com.joyintech.tams.component.business.evaluation.dto.PtlAssetValDto;
import com.joyintech.tams.component.business.evaluation.dto.PtlLatestStrategyDto;
import com.joyintech.tams.component.business.evaluation.dto.SubPortfolioDto;

/**
 * 共通查询方法
 * 
 * @author zhangJin
 * @version 1.0 2019年10月9日
 * @see CommonSearchMapper
 * @since 1.0
 */
@Repository
public interface CommonSearchMapper {

    /**
     * 1.子组合查询
     * 
     * @param finprodId 金融产品代码
     * @return 子组合
     * @see
     * @since 1.0
     */
    List<SubPortfolioDto> querySubPortfolio(@Param("finprodId") String finprodId);

    /**
     * 2.分档产品查询
     * 
     * @param finprodId 金融产品代码
     * @return 分档产品
     * @see
     * @since 1.0
     */
    List<LevelProdDto> queryLevelProd(@Param("finprodId") String finprodId);

    /**
     * 3.投资策略明细分类查询
     * 
     * @param batchId 参数
     * @return 明细分类
     * @see
     * @since 1.0
     */
    List<PtlInvStrategyDetailClass> queryDetailClass(@Param("batchId") String batchId);

    /**
     * 4.组合资产估值查询
     * 
     * @param paramMap 参数
     * @see
     * @since 1.0
     */
    List<PtlAssetValDto> queryPtlAssetVal(Map<String, Object> paramMap);

    /**
     * 5.组合最新策略查询
     * 
     * @param date 日期
     * @param portfolioId 组合代码
     * @return 组合最新策略
     * @see
     * @since 1.0
     */
    List<PtlLatestStrategyDto> queryPtlLatestStrategy(@Param("date") Date date,
                                                      @Param("portfolioId") String portfolioId);

}
