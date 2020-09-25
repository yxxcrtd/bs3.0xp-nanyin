/*
 * 文件名：ActualTimeAssetMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：yanya
 * 修改时间：2019年10月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.portfolio.actualtimecapital.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.portfolio.actualtimeasset.dto.ActualTimeAssetDto;
import com.joyintech.service.portfolio.actualtimecapital.dto.CashArtDealDto;
import com.joyintech.tams.invest.common.dto.PtlCashPositionFlowDto;
import com.joyintech.tams.invest.common.dto.etr.EtrCashPositionFlowDto;

/**
 * 实时资金头寸查询Mapper
 * 〈功能详细描述〉
 * @author yanya
 * @version 1.0 2019年10月16日
 * @see ActualTimeCapitalMapper
 * @since 1.0
 */
@Repository
public interface ActualTimeCapitalMapper {
    /**
     * 画面查询方法
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<ActualTimeAssetDto> findPage(Map<String,Object> map);

    /**
     * 资金账户下拉框
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getAcctInfo(Map<String, Object> map);
    
    /**
     * 查询现金头寸调整
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    List<CashArtDealDto> getCashArtDealInfo(CashArtDealDto dto);
    
    /**
     * 现金头寸调整 以组合代码 + 账号 + 日期为主键，存在的场合则为更新处理
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    int insertCashArtDealInfoData(CashArtDealDto dto);
    
    /**
     * 组合现金流水 以组合代码 + 账号 + 日期为主键，存在的场合则为更新处理
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    int insertPtlCashPositionFlowInfoData(PtlCashPositionFlowDto dto);
    
    /**
     * 指令现金流水 以组合代码 + 账号 + 日期为主键，存在的场合则为更新处理
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    int insertEtrCashPositionFlowInfoData(EtrCashPositionFlowDto dto);
}
