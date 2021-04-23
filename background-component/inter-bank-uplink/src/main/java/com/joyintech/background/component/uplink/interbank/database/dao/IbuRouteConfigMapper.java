/*
 * 文件名：IbuRouteConfigMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Jiaoxujin
 * 修改时间：2020年5月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.background.component.uplink.interbank.database.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.background.component.uplink.interbank.business.dto.RouteConfigSubDto;
import com.joyintech.background.component.uplink.interbank.database.entity.IbuIntfAttrConfig;
import com.joyintech.background.component.uplink.interbank.database.entity.IbuIntfScheme;

/**
 * 路由配置接口
 * @author 曹国
 * @version 1.0 2020年5月9日
 * @see IbuRouteConfigMapper
 * @since 1.0
 */
@Repository
public interface IbuRouteConfigMapper {
    
    /**
     * 
     * 获取默认配置方案
     * <功能详细描述>
     * @return 默认配置方案
     * @see
     * @since 1.0
     */
    IbuIntfScheme getDefaultScheme(String yes);
    
    /**
     * 查询所有配置方案及对应属性信息
     * 
     * <功能详细描述>
     * @param isDefault
     * @return
     * @see
     * @since 1.0
     */
    List<RouteConfigSubDto> queryALLScheme();
    
    /**
     * 查询所有配置方案及对应属性信息
     * 
     * <功能详细描述>
     * @param isDefault
     * @return
     * @see
     * @since 1.0
     */
    int updateIsdefaultToNo(Map<String, String> condition);
    
    /**
     * 查询所有配置方案及对应属性信息
     * 
     * <功能详细描述>
     * @param isDefault
     * @return
     * @see
     * @since 1.0
     */
    int batchInsertAttrTable(List<IbuIntfAttrConfig> ibuIntfAttrConfigList);
}
