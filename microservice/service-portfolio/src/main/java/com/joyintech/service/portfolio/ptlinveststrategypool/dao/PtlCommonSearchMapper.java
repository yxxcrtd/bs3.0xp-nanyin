/*
 * 文件名：CommonSearchMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangJin
 * 修改时间：2019年10月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.portfolio.ptlinveststrategypool.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.portfolio.ptlinveststrategypool.dto.PtlInvStrategyDetailDto;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author zhangJin
 * @version 1.0 2019年10月14日
 * @see PtlCommonSearchMapper
 * @since 1.0
 */
@Repository
public interface PtlCommonSearchMapper {

    /**
     * <一句话功能简述> <功能详细描述>
     * 
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> queryMulType2(Map<String, Object> pageParam);

    /**
     * <一句话功能简述> <功能详细描述>
     * 
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> queryMulType(Map<String, Object> pageParam);

    /**
     * <一句话功能简述> <功能详细描述>
     * 
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> queryMulType3(Map<String, Object> pageParam);

    /**
     * <一句话功能简述> <功能详细描述>
     * 
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> queryMulType4(Map<String, Object> pageParam);

    /**
     * <一句话功能简述> <功能详细描述>
     * 
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> queryMulType5(Map<String, Object> pageParam);

    /**
     * <一句话功能简述> <功能详细描述>
     * 
     * @return
     * @see
     * @since 1.0
     */
    List<PtlInvStrategyDetailDto> queryUniteTypes();

}
