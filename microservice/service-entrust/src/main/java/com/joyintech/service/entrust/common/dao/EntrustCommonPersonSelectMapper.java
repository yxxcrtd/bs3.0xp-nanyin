/*
 * 文件名：EntrustCommonPersonSelectMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年11月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.entrust.common.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author xiexinting
 * @version 1.0 2018年11月9日
 * @see EntrustCommonPersonSelectMapper
 * @since 1.0
 */
@Repository
public interface EntrustCommonPersonSelectMapper {
    /**
     * 
     * 查询交易对手
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getCounterId(Map<String, Object> map);

    List<SelectOptionDto> getCounterIdDepat(Map<String, Object> param);
    /**
     * 
     * 查询本方交易员
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getTrader(Map<String, Object> map);
    
    /**
     * 
     * 根据委托代码查询交易员集合
     * <功能详细描述>
     * @param map Map<String, Object>
     * @return List<String>
     * @see
     * @since 1.0
     */
    List<String> getTraderList(Map<String, Object> map);
    /**
     * 
     * 查询用户信息
     * <功能详细描述>
     * @param userName
     * @return
     * @see
     * @since 1.0
     */
    Map<String, Object> getUser(@Param("userName") String userName );
    /**
     * 
     * 查询真实姓名
     * <功能详细描述>
     * @param userName
     * @return
     * @see
     * @since 1.0
     */
    String getRealName(@Param("userName") String userName);
}
