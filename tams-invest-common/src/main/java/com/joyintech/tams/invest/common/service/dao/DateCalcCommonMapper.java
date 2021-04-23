/*
 * 文件名：DateCalcCommonMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2021年1月5日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.common.service.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author etliu
 * @version 1.0 2021年1月5日
 * @see DateCalcCommonMapper
 * @since 1.0
 */
public interface DateCalcCommonMapper {

    /**
     * 查询日期参数是否节假日
     * 
     * @param dateStr 日期字符串
     * @param calId 日历ID
     * @return 是否节假日
     */
    int selectDealCalendarHolidayCount(@Param("dateStr")String dateStr, @Param("calId")String calId);
    
    /**
     * 交易日历的节假日取得
     * 
     * @param paraMap 参数
     * @return 节假日列表
     */
    List<String> getDealCalendarHoliday(Map<String, Object> paraMap);
}
