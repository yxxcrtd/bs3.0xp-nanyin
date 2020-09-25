/*
 * 文件名：EtrInvestPersonInfo002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年9月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.trade;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.component.business.trade.dto.BussEtrInvestPersonInfoDto;

/**
 * 投资委托人员信息接口
 * 〈功能详细描述〉
 * @author xiexinting
 * @version 1.0 2018年9月19日
 * @see BussEtrInvestPersonInfoService
 * @since 1.0
 */
public interface BussEtrInvestPersonInfoService {

    /**
     * 默认entity名称
     */
    String DEFAULT_ENTITY_NAME = "etrInvestPersonInfo";
    /**
     * 
     * 通过主键查询人员信息
     * <功能详细描述>
     * @param key
     * @return
     * @see
     * @since 1.0
     */
//    BussEtrInvestPersonInfoDto selectByKey(String key);
    
    /**
     * 
     * 本方交易员
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    Page<SelectOptionDto> getTrader(Map<String, Object> map);
    /**
     * 
     * 交易对手
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    Page<SelectOptionDto> getCounterId(Map<String, Object> map);
    /**
     * 
     * 获取用户信息
     * <功能详细描述>
     * @param userName
     * @return
     * @see
     * @since 1.0
     */
    Map<String, Object> getUser(String userName);
    
}
