/*
 * 文件名：RskEventService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年8月14日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.riskinfrds.event.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.constantrds.RedisKeyEnum;
import com.joyintech.tams.invest.common.riskinfrds.base.service.RedisBaseService;
import com.joyintech.tams.invest.common.riskinfrds.calendar.entity.ICalendar;
import com.joyintech.tams.invest.common.riskinfrds.event.entity.EventMap;
import com.joyintech.tams.invest.common.riskinfrds.event.entity.IEvent;

/**
 * 风险前置服务
 * 
 * @author zhangrui1
 * @version 1.0 2019年8月14日
 * @see RskEventService
 * @since 1.0
 */
@Service
public class RskEventService extends RedisBaseService<EventMap> {
	
	/**
	 * 后台日志
	 */
	private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 匹配风险事件
     * 
     * @param mapData 匹配对象
     * @param calendar 日历信息
     * @return 风险事件列表
     * @see
     * @since 1.0
     */
    public List<IEvent> matchEvent(Map<String, Object> mapData, ICalendar calendar) {
        List<String> fldList = new ArrayList<>();
        fldList.add(EventMap.ALL);
        // 从Redis中获取所有的风险事件信息
        Map<String, EventMap> eMap = getMapFromRedis(RedisKeyEnum.风险事件配置.key(), fldList);
        // 存在风险事件信息时
        List<IEvent> resList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(mapData) && !CollectionUtils.isEmpty(eMap)) {
            EventMap eventMap = eMap.get(EventMap.ALL);
            Collection<IEvent> eList = eventMap.values();
            // 循环所有风险事件信息匹配特征值
            eList.forEach(event -> {
                // 匹配成功则将风险事件加入返回列表
                if (event.match(mapData, calendar)) {
                    event.setRelValue(mapData);
                    resList.add(event);
                }
            });
        }
        log(resList);
        // 返回风险事件列表
        return resList;
    }

    /**
     * 后台打印触发的风险事件
     * 
     * @param resList 风险事件列表
     */
    protected void log(List<IEvent> resList){
    	if (!StringUtil.isEmptyList(resList)) {
    		StringBuilder builder = new StringBuilder();
    		resList.forEach(event->builder.append(event.getEventName()).append(GlobalConstant.COMMA));
    		String logStr = builder.toString();
    		logger.info("触发的风控事项是:【{}】", logStr);
    	}
    }
}
