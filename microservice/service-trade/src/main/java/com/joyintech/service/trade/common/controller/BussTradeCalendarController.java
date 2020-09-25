/*
 * 文件名：BussCalendarController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：admin
 * 修改时间：2019年7月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.trade.common.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.service.trade.common.service.BussCalendarService;

@RestController
@RequestMapping("/common/BussCalendarService")
public class BussTradeCalendarController {
	

    @Autowired
	private BussCalendarService bussCalendarService;
    
    /**
     * 根据货币对得到复合日历
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @RequestMapping("selectCalendarIdByCurPair")
    public String selectCalendarIdByCurPair(@RequestBody Map<String, Object> param) {
        return bussCalendarService.selectCalendarIdByCurPair(param);
    }
}
