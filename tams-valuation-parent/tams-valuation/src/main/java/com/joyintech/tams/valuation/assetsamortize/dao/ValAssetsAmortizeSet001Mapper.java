/*
 * 文件名：ValAssetsAmortizeSet001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：HuangJian
 * 修改时间：2018年12月4日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.assetsamortize.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.valuation.assetsamortize.dto.ValAssetsAmortizeSet001Dto;

/**
 * 资产摊销
 * 
 * @author HuangJian
 * @version 1.0 2018年12月4日
 * @see ValAssetsAmortizeSet001Mapper
 * @since 1.0
 */
@Repository
public interface ValAssetsAmortizeSet001Mapper {

    /**
     * 资产代码下拉框加载
     * 
     * @param map Map
     * @return FinProduct
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getFinprodId(Map<String, Object> map);

    /**
     * 账套代码下拉框加载
     * 
     * @param map Map
     * @return List
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getBooksetId(Map<String, Object> map);

    /**
     * 表格数据加载
     * 
     * @param map Map
     * @return List
     * @see
     * @since 1.0
     */
    List<ValAssetsAmortizeSet001Dto> findPage(Map<String, Object> map);

    /**
     * 得到比较的账套日期
     * 
     * @param booksetId 账套代码
     * @return getCdate
     * @see
     * @since 1.0
     */
    String getCdate(@Param("booksetId") String booksetId);

    /**
     * 资产代码下拉框获取
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
	List<SelectOptionDto> getFinprodId2(Map<String, Object> map);
}
