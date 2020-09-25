/*
 * 文件名：FinProductSchedulingMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年9月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.product.productelement.scheduling.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.PaPrdIssueSchedule;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;

/**
 * 理财产品排期信息管理
 * 
 * @author zhangliang
 * @version 1.0 2018年9月13日
 * @see FinProductSchedulingMapper
 * @since 1.0
 */
@Repository
public interface FinProductSchedulingMapper {

    /**
     * 查询排期所有数据
     * 
     * @param map 参数map
     * @return 排期所有数据
     * @see
     * @since 1.0
     */
    List<PaPrdIssueSchedule> queryAllData(Map<String, Object> map);

    /**
     * 根据产品代码模糊查询联系下拉框
     * 
     * @param param 参数map
     * @return 产品代码和简称
     * @see
     * @since 1.0
     */
    List<SuggestDto> querySchedulingProdInfo(@Param("param") String param);

    /**
     * 查询产品登记编码是否已存在
     * 
     * @param finprodId 产品登记编码
     * @return 是否已存在
     * @see
     * @since 1.0
     */
    int checkRegistCodeExists(@Param("finprodId") String finprodId);

    /**
     * 查询产品模板下拉框
     * 
     * @param param 参数map
     * @return 产品模板
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> queryProdTempleteInfo(Map<String, Object> param);

    /**
     * 查询产品申报方案
     * 
     * @param param 参数map
     * @return 产品申报方案
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> queryDeclareSchemeInfo(Map<String, Object> param);

    /**
     * 根据模板代码查询模板信息
     * 
     * @param param 参数map
     * @return 模板信息
     * @see
     * @since 1.0
     */
    FinProduct queryTempleteInfoById(Map<String, Object> param);

    /**
     * 查询产品基本信息是否存在
     * 
     * @param finprodMarketId 产品代码
     * @param finprodAbbr 产品简称
     * @param finprodName 产品名称
     * @param finprodId 金融产品代码
     * @param finprodType2 金融产品类型
     * @return 是否存在
     * @see
     * @since 1.0
     */
    int checkProdInfoExist(@Param("finprodMarketId") String finprodMarketId,
                           @Param("finprodAbbr") String finprodAbbr,
                           @Param("finprodName") String finprodName,
                           @Param("finprodId") String finprodId,
                           @Param("finprodType2") String finprodType2);
}
