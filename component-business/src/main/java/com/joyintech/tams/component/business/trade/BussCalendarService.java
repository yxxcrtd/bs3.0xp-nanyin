/*
 * 文件名：BussCalendarService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiaominghui
 * 修改时间：2019年7月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.trade;

import java.util.Map;

/**
 * 交易日历
 * 〈功能详细描述〉
 * @author qiaominghui
 * @version 1.0 2019年7月11日
 * @see BussCalendarService
 * @since 1.0
 */
public interface BussCalendarService {


    String selectCalendarIdByCurPair(Map<String, Object> pageParam);
}
