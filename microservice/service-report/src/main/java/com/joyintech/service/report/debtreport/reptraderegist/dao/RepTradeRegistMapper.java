/*
 * 文件名：RepTradeRegistServiceMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年12月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.report.debtreport.reptraderegist.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.report.debtreport.reptraderegist.dto.RepTradeRegistDto;

/**
 * 交易要素登记
 * @author fanshuanghu
 * @version 1.0 2018年12月28日
 * @see RepTradeRegistMapper
 * @since 1.0
 */
@Repository
public interface RepTradeRegistMapper {
    /**
     * 分页查询
     * @param map 分页查询参数
     * @return 分页查询结果
     * @see
     * @since 1.0
     */
    List<RepTradeRegistDto> findPage(Map<String,Object> map);
    /**
     * 获取金融产品代码下拉框集合
     * @return 金融产品代码下拉框集合
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getProductCode(Map<String,Object> map);
    /**
     * 获取金融产品代码下拉框集合
     * @return 金融产品代码下拉框集合
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getRepFinprodId(Map<String,Object> map);
    /**
     * 获取行内交易编号下拉框集合
     * @return 行内交易编号下拉框集合
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getRepBankTradeCode(Map<String,Object> map);
    /**
     * 获取资产代码下拉框集合
     * @return 资产代码下拉框集合
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getRepBankAssetCode(Map<String,Object> map);
    /**
     * 获取金融产品登记编码下拉框集合
     * @return 金融产品登记编码下拉框集合
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getRepFinprodRegId(Map<String,Object> map);
}
