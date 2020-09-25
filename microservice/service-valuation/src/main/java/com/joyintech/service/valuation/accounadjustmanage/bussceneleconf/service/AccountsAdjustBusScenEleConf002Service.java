/*
 * 文件名：AccountsAdjustBusScenEleConf002Service.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：ruanhngming
 * 修改时间：2018年5月25日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.accounadjustmanage.bussceneleconf.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.service.valuation.accounadjustmanage.bussceneleconf.dao.AccountsAdjustBusScenEleConf002Mapper;
import com.joyintech.service.valuation.accounadjustmanage.bussceneleconf.dto.AccountsAdjustBusScenEleConf001Dto;

/**
 * 业务场景配置新建 service
 * 
 * @author ruanhongming
 * @version 1.0 2016年5月25日
 * @see AccountsAdjustBusScenEleConf002Service
 * @since 1.0
 */
@Service(value="serviceAccountsAdjustBusScenEleConf002Service")
@Transactional(rollbackFor = Exception.class)
public class AccountsAdjustBusScenEleConf002Service extends BaseService <AccountsAdjustBusScenEleConf001Dto> {

    /**
     * 业务场景配置查询mapper
     */
    @Autowired
    private AccountsAdjustBusScenEleConf002Mapper accountsAdjustBusScenEleConf002Mapper;
    

    /**
     * 新建保存时，查询业务场景代码是否已经存在
     * 
     * @param tableId 业务场景代码
     * @return true:已存在；false：不存在
     * @see
     * @since 1.0
     */
    public boolean tableIdIsExists(String tableId) {
        return accountsAdjustBusScenEleConf002Mapper.tableIdIsExists(tableId) > 0;
    }

    /**
     * 修改或新建时check业务场景名称是否已存在
     * 
     * @param dto 页面dto
     * @return 1：已存在；0：不存在
     * @see
     * @since 1.0
     */
    public boolean tableNameIsExists(AccountsAdjustBusScenEleConf001Dto dto) {
        return accountsAdjustBusScenEleConf002Mapper.tableNameIsExists(dto) > 0;
    }

    /**
     * 页面初始化，根据业务场景代码查询业务场景配置信息
     * 
     * @param paraMap 参数map
     * @return 业务场景配置信息
     * @see
     * @since 1.0
     */
    public AccountsAdjustBusScenEleConf001Dto selectBokTableListByTableId(Map<String, Object> paraMap) {
        return accountsAdjustBusScenEleConf002Mapper.selectBokTableListByTableId(paraMap);
    }

    /**
     * 保存
     * 
     * @param dto 页面参数dto
     * @return 是否保存成功
     * @see
     * @since 1.0
     */
    public boolean saveData(AccountsAdjustBusScenEleConf001Dto dto) {
    	int num = accountsAdjustBusScenEleConf002Mapper.insertValBokTablelist(dto);
    	return num>0;
    }
}
