/*
 * 文件名：AccountsAdjustBusScenEleConf002Mapper.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：ruanhngming
 * 修改时间：2018年5月25日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.service.valuation.accounadjustmanage.bussceneleconf.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.service.valuation.accounadjustmanage.bussceneleconf.dto.AccountsAdjustBusScenEleConf001Dto;

/**
 * 业务场景配置查询mapper
 * 〈功能详细描述〉
 * @author ruanhongming
 * @version 1.0 2017年5月25日
 * @see AccountsAdjustBusScenEleConf002Mapper
 * @since 1.0
 */
@Repository(value="serviceAccountsAdjustBusScenEleConf002Mapper")
public interface AccountsAdjustBusScenEleConf002Mapper {

    /**
     * 修改或新建时check业务场景名称是否已存在
     * <功能详细描述>
     * @param dto 页面dto
     * @return 1：已存在；0：不存在
     * @see
     * @since 1.0
     */
    int tableNameIsExists(AccountsAdjustBusScenEleConf001Dto dto);
    
    /**
     * 新建时check业务场景代码是否已存在
     * <功能详细描述>
     * @param tableId 业务场景代码
     * @return 1：已存在；0：不存在
     * @see
     * @since 1.0
     */
    int tableIdIsExists(String tableId);
    
    /**
     * 页面初始化，根据业务场景代码查询业务场景配置信息
     * <功能详细描述>
     * @param paraMap 参数map
     * @return 业务场景配置信息
     * @see
     * @since 1.0
     */
    AccountsAdjustBusScenEleConf001Dto selectBokTableListByTableId(Map<String, Object> paraMap);
    
    /**
     * 新建业务场景要素配置信息
     * 
     * @param tableId 业务场景代码
     * @return 1：已存在；0：不存在
     * @see
     * @since 1.0
     */
    int insertValBokTablelist(AccountsAdjustBusScenEleConf001Dto dto);
}
