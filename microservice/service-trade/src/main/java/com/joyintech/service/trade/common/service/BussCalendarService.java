/*
 * 文件名：BussCalendarService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：admin
 * 修改时间：2019年7月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.trade.common.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.service.trade.common.dao.BussCalendarMapper;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author admin
 * @version 1.0 2019年7月11日
 * @see BussCalendarService
 * @since 1.0
 */
@Service
public class BussCalendarService {


    @Autowired
    private BussCalendarMapper bussCalendarMapper;
    
	/**
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param param
	 * @return
	 * @see
	 * @since 1.0
	 */
	public String selectCalendarIdByCurPair(Map<String, Object> param) {
        List<String> calendarIdList = bussCalendarMapper.selectCalendarIdByCurPair(param);
        String ret = null;
        if(calendarIdList!=null&&calendarIdList.size()>0) {
        	ret = calendarIdList.get(0);
        }
        return ret;
    }
	
	
	
}
