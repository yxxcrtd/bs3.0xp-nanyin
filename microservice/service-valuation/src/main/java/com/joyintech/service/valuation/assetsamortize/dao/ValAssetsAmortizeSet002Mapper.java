/*
 * 文件名：ValAssetsAmortizeSet001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：HuangJian
 * 修改时间：2018年12月4日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.assetsamortize.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.assetsamortize.dto.ValAssetsAmortizeSet001Dto;

/**
 * 资产摊销
 * 
 * @author HuangJian
 * @version 1.0 2018年12月4日
 * @see ValAssetsAmortizeSet002Mapper
 * @since 1.0
 */
@Repository(value="serviceValAssetsAmortizeSet002Mapper")
public interface ValAssetsAmortizeSet002Mapper {

    /**
     * 新建 表格加载
     * 
     * @param map Map
     * @return List
     * @see
     * @since 1.0
     */
    List<ValAssetsAmortizeSet001Dto> findPage(Map<String, Object> map);

    /**
     * 表格 下拉框加载
     * 
     * @param map Map<String, Object>
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getRecalType(Map<String, Object> map);

    /**
     * check 数据是否重复
     * 
     * @param dto ValAssetsAmortizeSet001Dto
     * @return boolean
     * @see
     * @since 1.0
     */
    boolean checkBokAmoConfig(ValAssetsAmortizeSet001Dto dto);

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
