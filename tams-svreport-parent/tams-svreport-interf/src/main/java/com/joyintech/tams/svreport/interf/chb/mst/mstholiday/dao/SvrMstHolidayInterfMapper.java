package com.joyintech.tams.svreport.interf.chb.mst.mstholiday.dao;

import com.joyintech.tams.common.base.entity.MstHoliday;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SvrMstHolidayInterfMapper {

    /**
     * 查询截止上次接入时间的基础日历信息数据
     * @param batchTime
     * @return
     */
    List<MstHoliday> select(@Param("updatetime") Date updatetime);

}