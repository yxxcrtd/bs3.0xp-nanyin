/*
 * 文件名：RiskDispatcher.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2020年9月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.riskrds.filter;

import java.util.List;
import java.util.Map;

/**
 * 风控委托交易模块过滤器
 * 
 * @author zhangrui1
 * @version 1.0 2020年9月9日
 * @see IRiskModelFilter
 * @since 1.0
 */
public interface IRiskModelFilter {

    /**
     * 过滤生成目标报文体
     * 
     * @param pageId 画面ID
     * @param dto 画面数据实体
     * @return 目标报文体
     */
    <T> List<Map<String, Object>> filter(String pageId, T dto);
}
