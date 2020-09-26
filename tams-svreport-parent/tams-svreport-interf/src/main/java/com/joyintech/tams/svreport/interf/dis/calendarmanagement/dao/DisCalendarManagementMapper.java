package com.joyintech.tams.svreport.interf.dis.calendarmanagement.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.svreport.interf.dis.calendarmanagement.dto.DisCalendarManagement;
@Repository
public interface DisCalendarManagementMapper {
	    
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param dtoList
     * @see
     * @since 1.0
     */
    int mergeByModel(@Param("list")List<DisCalendarManagement> dtoList);

}