package com.joyintech.tams.invest.common.autocoupon.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author zhangnian
 * @ClassName DateCalForBatchMapper
 * @description TODO
 * @date 2020/10/14 0014 13:39
 **/
@Repository
public interface DateCalForBatchMapper {

    /**
     * 交易日历的节假日取得
     *
     * @param paraMap 参数
     * @return 节假日列表
     */
    List<String> getDealCalendarHoliday(Map<String, Object> paraMap);

    /**
     * 交易日历的节假日取得
     *
     * @param calendarId 交易日历ID
     * @return 节假日列表
     */
    List<String> getHolidayDealCalendarId(String calendarId);

    /**
     * 查询日期参数是否节假日
     *
     * @param dateStr 日期字符串
     * @param calId 日历ID
     * @return 是否节假日
     */
    int selectDealCalendarHolidayCount(@Param("dateStr")String dateStr, @Param("calId")String calId);

    /**
     * 市场的节假日取得
     *
     * @param paraMap 查询参数
     * @return 市场的节假日列表
     */
    List<String> getMarketHoliday(Map<String, Object> paraMap);
} 

