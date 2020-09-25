/*
 * 文件名：OwnerEquityChange001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangzhao
 * 修改时间：2018年9月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.service.valuation.accounadjustmanage.ownerequitychange.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.service.valuation.accounadjustmanage.ownerequitychange.dto.OwnerEquityChange001Dto;
import com.joyintech.tams.common.base.entity.BokBalance;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author zhangzhao
 * @version 1.0 2018年9月18日
 * @see OwnerEquityChange001Mapper
 * @since 1.0
 */
@Repository(value="serviceOwnerEquityChange001Mapper")
public interface OwnerEquityChange001Mapper {


    /**
     * 查询数据
     * <功能详细描述>
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    List<OwnerEquityChange001Dto> selectAll(Map<String, Object> pageParam);

    /**
     * 获取当前系统平台日期
     *
     * @return 当前系统平台日期
     * @see
     * @since 1.0
     */
    Date getNowSysDate();
    
    /**
     * 根据账套代码获取默认日期信息
     * 
     * @param pageParam
     * @return OwnerEquityChange001Dto
     */
    OwnerEquityChange001Dto getDateInfo1 (Map<String, Object> pageParam);
    
    /**
     * 根据账套组ID获取默认日期信息
     * 
     * @param pageParam
     * @return OwnerEquityChange001Dto
     */
    List<OwnerEquityChange001Dto> getDateInfo2 (Map<String, Object> pageParam);
}
