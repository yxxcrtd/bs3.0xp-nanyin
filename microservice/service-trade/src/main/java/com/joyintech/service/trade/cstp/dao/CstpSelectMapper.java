/*
 * 文件名：CSTPSelectMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年12月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.trade.cstp.dao;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.trade.cstp.dto.CstpSelectDto;

/**
 * CSTP下行交易查询Mapper
 * @author fanshuanghu
 * @version 1.0 2018年12月3日
 * @see CstpSelectMapper
 * @since 1.0
 */
@Repository
public interface CstpSelectMapper {
    /**
     * 分页查询结果
     * @param map 查询参数
     * @return 分页查询结果
     * @see
     * @since 1.0
     */
    List<CstpSelectDto> findPage(Map<String,Object> map);
    /**
     * 获取组合代码下拉框实体类
     * @param map 查询参数
     * @return 下拉框集合
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getPortfolioId(Map<String,Object> map);
    /**
     * 获取成交编号下拉框实体类
     * @param map 查询参数
     * @return 下拉框集合
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getExecid(Map<String,Object> map);
    /**
     * 获取资产代码下拉框
     * @return 下拉框集合
     * @see
     * @since 1.0
     */
    List<SuggestDto> getFinprodId(Map<String,Object> map);

    BigDecimal getAmountByFtradeId(Map<String,Object> map);
}
