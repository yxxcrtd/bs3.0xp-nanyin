/*
 * 文件名：BussCalendarController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：admin
 * 修改时间：2019年7月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.trade.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.component.business.trade.BussCalendarService;

/**
 *  交易日历
 * 〈功能详细描述〉
 * @author qiaominghui
 * @version 1.0 2019年7月11日
 * @see BussCalendarController
 * @since 1.0
 */
@Controller
@RequestMapping("/component/trade/BussCalendar")
public class BussCalendarController {
	
	@Autowired
	private BussCalendarService bussCalendarService;
	
	/**
	 * 根据货币对获取复合交易日历
	 * <功能详细描述>
	 * @param param
	 * @return
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
    @RequestMapping("selectCalendarIdByCurPair")
    public Map<String,Object> selectCalendarIdByCurPair(@RequestBody Map<String, Object> param) {
        // TODO 需要调接口
		String calendarId = null;
        if (bussCalendarService != null && param.get("curPair") != null) {
        	calendarId = bussCalendarService.selectCalendarIdByCurPair(param);
        }
        param.put("calendarId", calendarId);
        return param;
    }
	
	
}
