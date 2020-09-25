/*
 * 文件名：bussCalendarImplService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：admin
 * 修改时间：2019年7月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.trade.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.JoyinRestTemplate;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.component.business.trade.BussCalendarService;
import com.joyintech.tams.invest.common.constant.ServiceApplication;

/**
 * 交易日历
 * 〈功能详细描述〉
 * @author qiaominghui
 * @version 1.0 2019年7月11日
 * @see bussCalendarImplService
 * @since 1.0
 */
@Service
public class bussCalendarImplService  extends BaseService implements BussCalendarService{
	
	@Autowired
    private JoyinRestTemplate restTemplate;

    @Override
    public String selectCalendarIdByCurPair(Map<String, Object> pageParam) {
        return restTemplate.postForObject(ServiceApplication.TRADE_SERVICE_NAME + "/common/BussCalendarService/selectCalendarIdByCurPair",
            pageParam, String.class);
    }
	
}
