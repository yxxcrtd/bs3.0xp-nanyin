/*
 * 文件名：SecCalRequestQuoteMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2020年5月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.bankuplink.calc.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.service.bankuplink.calc.dto.SecInfoModelDto;
import com.joyintech.tams.common.base.entity.FinCashPlan;

/**
 * 算法查询债券基本信息
 * 
 * @author zhangliang
 * @version 1.0 2020年5月19日
 * @see SecCalRequestQuoteMapper
 * @since 1.0
 */
@Repository
public interface SecCalRequestQuoteMapper {

    /**
     * 查询债券基本信息
     * 
     * @param map 入参
     * @return
     * @see
     * @since 1.0
     */
    List<SecInfoModelDto> getSecInfoModel(Map<String, Object> map);
    
    /**
     * 查询债券付息计划
     * 
     * @param map 入参
     * @return
     * @see
     * @since 1.0
     */
    List<FinCashPlan> getFinCashPlan(Map<String, Object> map);
    
    /**
     * a.当前债券面额取得（根据还本计划计算）
     *
     * @param param 参数
     * @return a.当前债券面额取得（根据还本计划计算）
     * @see
     * @since 1.0
     */
    BigDecimal getCurSecAmt(Map<String, Object> param);
}
