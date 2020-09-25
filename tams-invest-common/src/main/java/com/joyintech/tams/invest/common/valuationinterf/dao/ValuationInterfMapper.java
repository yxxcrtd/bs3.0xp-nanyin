/*
 * 文件名：ValuationInterfMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2019年1月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.common.valuationinterf.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.PtlCapacctInfo;

/**
 * 估值接口Mapper
 *
 * @author etliu
 * @version 1.0 2019年1月12日
 * @see ValuationInterfMapper
 * @since 1.0
 */
@Repository
public interface ValuationInterfMapper {

    /**
     * 查询金融产品账户信息
     *
     * @param paramMap 参数
     * @return 金融产品账户信息
     * @see
     * @since 1.0
     */
    List<PtlCapacctInfo> selectProdAcctData(Map<String, Object> paramMap);
    
    /**
     * 根据组合代码，查询该组合下的产品
     *
     * @param portfolioId 组合代码
     * @return 该组合下的产品
     * @see
     * @since 1.0
     */
    String selectFinprodIdByPortfolioId(@Param ("portfolioId") String portfolioId);
    
    /**
     * 根据母产品查询分层子产品代码
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    List<String> selectLayerFinprodIdByPfinprodId(@Param ("finprodId") String finprodId);
    
    /**
     * 查询金融产品账户信息Ex
     * @param paramMap 参数
     * @return 金融产品账户信息
     * @see
     * @since 1.0
     */
    PtlCapacctInfo selectProdAcctDataEx(Map<String, Object> paramMap);
}
