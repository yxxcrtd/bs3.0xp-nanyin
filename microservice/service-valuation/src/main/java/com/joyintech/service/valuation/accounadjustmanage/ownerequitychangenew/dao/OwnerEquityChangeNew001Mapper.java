/*
 * 文件名：OwnerEquityChange001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：zhangzhao
 * 修改时间：2018年9月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.service.valuation.accounadjustmanage.ownerequitychangenew.dao;

import com.joyintech.service.valuation.accounadjustmanage.ownerequitychangenew.dto.OwnerEquityChangeNew001Dto;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author lby
 * @version 1.0 2020年11月24日
 * @see OwnerEquityChangeNew001Mapper
 * @since 1.0
 */
@Repository(value="serviceOwnerEquityChangeNew001Mapper")
public interface OwnerEquityChangeNew001Mapper {


    /**
     * 查询数据
     * <功能详细描述>
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    List<OwnerEquityChangeNew001Dto> selectAll(Map<String, Object> pageParam);

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
    OwnerEquityChangeNew001Dto getDateInfo1(Map<String, Object> pageParam);

    /**
     * 根据账套组ID获取默认日期信息
     *
     * @param pageParam
     * @return OwnerEquityChange001Dto
     */
    List<OwnerEquityChangeNew001Dto> getDateInfo2(Map<String, Object> pageParam);

    /***
     * 获取当前时间区间的最早的跑批日期中的期初净值
     * @param pageParam
     * @return
     */
	List<OwnerEquityChangeNew001Dto> selectBegin(Map<String, Object> pageParam);
	
    /***
     * 获取当前时间区间内最晚的跑批日期中的期末净值
     * @param pageParam
     * @return
     */
	List<OwnerEquityChangeNew001Dto> selectEnd(Map<String, Object> pageParam);

}
