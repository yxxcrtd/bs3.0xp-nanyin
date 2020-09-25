/*
 * 文件名：FinNCProductBuild002Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：tanxuan
 * 修改时间：2020年02月26日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.service.valuation.finncproductbuild.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.valuation.finncproductbuild.dto.FinNCProductBuild001Dto;

/**
 * 产品信息新建、修改
 * 
 * @author tanxuan
 * @version 1.0 2020年02月26日
 * @see FinNCProductBuild002Mapper
 * @since 1.0
 */
@Repository(value="serviceFinNCProductBuild002Mapper")
public interface FinNCProductBuild002Mapper {
    
    /**
     * 母产品代码下拉框获取值
     * 
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getpFinprodId(Map<String, Object> map);
    
    /**
     * 获取机构信息
     * 
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getCustomerId(Map<String, Object> map);
    
    /**
     * 唯一性验证
     * @param finprodId
     * @return FinNCProductBuild001Dto
     * @see
     * @since 1.0
     */
    FinNCProductBuild001Dto findByFinprodId(@Param("finprodId") String finprodId);
    
}
