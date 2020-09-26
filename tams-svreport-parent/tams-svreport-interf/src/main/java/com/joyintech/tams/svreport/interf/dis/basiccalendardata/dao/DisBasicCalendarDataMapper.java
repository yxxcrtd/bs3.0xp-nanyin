package com.joyintech.tams.svreport.interf.dis.basiccalendardata.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.svreport.interf.dis.basiccalendardata.dto.DisBasicCalendarData;
@Repository
public interface DisBasicCalendarDataMapper {
	    
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param dtoList
     * @see
     * @since 1.0
     */
    int mergeByModel(@Param("list")List<DisBasicCalendarData> dtoList);

}