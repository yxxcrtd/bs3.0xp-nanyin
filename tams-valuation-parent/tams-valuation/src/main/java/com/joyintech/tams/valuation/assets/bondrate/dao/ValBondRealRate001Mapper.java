/*
 * 文件名：ValBondRealRate001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：HuangJian
 * 修改时间：2019年1月19日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.assets.bondrate.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.valuation.assets.bondrate.dto.ValBondRealRate001Dto;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author HuangJian
 * @version 1.0 2019年1月19日
 * @see ValBondRealRate001Mapper
 * @since 1.0
 */
@Repository
public interface ValBondRealRate001Mapper {

    /**
     * 数据查询
     * 
     * @param params Map<String, Object>
     * @return List<ValBondRealRate001Dto>
     * @see
     * @since 1.0
     */
    List<ValBondRealRate001Dto> findPage(Map<String, Object> params);

    /**
     * 账套代码下拉框
     * 
     * @param params Map<String, Object>
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getBooksetId(Map<String, Object> params);

    /**
     * 债券代码下拉框
     * 
     * @param params Map<String, Object>
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getBondId(Map<String, Object> params);

}
