package com.joyintech.tams.batchcommon.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @author wj
 * @version 1.0.0
 * @ClassName MstHolidayMapper.java
 * @Description 跳过节假日
 * @createTime 2021年06月22日 14:56:00
 */
@Repository
public interface BatchMstHolidayMapper {
    /* 是否节假日
     * @param cdate 查询日期
     * @param calendarId 交易日历
     * @return 是否节假日（0：工作日；其他：节假日）
     * @see
     * @since 1.0
     */
    String getIsHoliday(@Param("cdate") Date cdate, @Param("calendarId") String calendarId);
}
