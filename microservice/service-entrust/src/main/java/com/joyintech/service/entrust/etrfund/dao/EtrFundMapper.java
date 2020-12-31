/*
 * 文件名：EtrFundMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年10月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.entrust.etrfund.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.entrust.etrbrowdepoinfo.dto.EtrBrowDepoInfoDto;
import com.joyintech.service.entrust.etrfund.dto.EtrFundDto;

/**
 * 基金委托
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年10月8日
 * @see EtrFundMapper
 * @since 1.0
 */
@Repository
public interface EtrFundMapper {
    
    /**
     * 
     * 组合代码联想文本框 
     * <功能详细描述>
     * @param map Map<String, Object>
     * @return List<SuggestDto>
     * @see
     * @since 1.0
     */
    List<SuggestDto> getPortfolioId(Map<String, Object> map);
    
    /**
     * 
     * 资产代码联想文本框
     * <功能详细描述>
     * @param map Map<String, Object>
     * @return List<SuggestDto>
     * @see
     * @since 1.0
     */
    List<SuggestDto> getFinprodMarketId(Map<String, Object> map);
    
    /**
     * 
     * 页面查询
     * <功能详细描述>
     * @param map 查询参数
     * @return List<EtrBrowDepoInfoDto>
     * @see
     * @since 1.0
     */
    List<EtrFundDto> select(Map<String,Object> map);
    
    /**
     * 
     * 新建页面资产代码联想下拉框
     * <功能详细描述>
     * @param map Map<String, Object>
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getSelectFinprodMarketId(Map<String, Object> map);
    
    /**
     * 
     * 新建页面组合代码联想下拉框
     * <功能详细描述>
     * @param map Map<String, Object>
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getSelectPortfolioId(Map<String, Object> map);
    
    /**
     * 
     * 编辑、双击打开页面详情
     * <功能详细描述>
     * @param entrustId String
     * @param finprodId String
     * @param portfolioId String
     * @return EtrBrowDepoInfoDto
     * @see
     * @since 1.0
     */
    EtrFundDto selectByKey(@Param("entrustId") String entrustId,@Param("finprodId") String finprodId);
    
    /**
     * 
     * 查询委托金额
     * <功能详细描述>
     * @param map Map<String,Object>
     * @return BigDecimal
     * @see
     * @since 1.0
     */
    BigDecimal selectEntrustAmt(Map<String,Object> map);
    
    /**
     * 
     * 查询发行规模
     * <功能详细描述>
     * @param map Map<String,Object>
     * @return BigDecimal
     * @see
     * @since 1.0
     */
    BigDecimal selectIssueAmt(Map<String,Object> map);
    
    /**
     * 
     * 查询单位净值
     * <功能详细描述>
     * @param map Map<String,Object>
     * @return BigDecimal
     * @see
     * @since 1.0
     */
    BigDecimal getPrice(Map<String,Object> map);
    
    /**
     * 
     * 新建页面通道下拉框
     * <功能详细描述>
     * @param map Map<String, Object>
     * @return Page<SelectOptionDto>
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getSelectTongDao(Map<String, Object> map);
    /**
     * 通道可用现金查询
     * @param map
     * @return
     */
    BigDecimal getTongDaoCash(Map<String, Object> map);

    /**
     * 
     * 查询资产管理人
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    String selectManeger(@Param("issuer") String issuer);


    List<String> findSameNameCounterId(Map<String, Object> map);
}
