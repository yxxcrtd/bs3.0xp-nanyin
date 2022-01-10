package com.joyintech.tams.svreport.interf.dis.calendarmanagement.dao;

import com.joyintech.tams.common.base.entity.MstCalendarInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SvrMstCalendarInfoInterfMapper {

    /**
     * 查询截止上次接入时间的基础日历信息数据
     * @param batchTime
     */
    List<MstCalendarInfo> select(@Param("updatetime") Date updatetime);

}