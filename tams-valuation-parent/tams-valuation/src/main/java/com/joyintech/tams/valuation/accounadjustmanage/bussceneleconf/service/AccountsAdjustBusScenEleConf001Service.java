/*
 * 文件名：AccountsAdjustBusScenEleConf001Service.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：ruanhngming
 * 修改时间：2018年5月25日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 

package com.joyintech.tams.valuation.accounadjustmanage.bussceneleconf.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.tams.valuation.accounadjustmanage.bussceneleconf.dao.AccountsAdjustBusScenEleConf001Mapper;
import com.joyintech.tams.valuation.accounadjustmanage.bussceneleconf.dto.AccountsAdjustBusScenEleConf001Dto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.common.base.dao.BokTablelistMapper;
import com.joyintech.tams.common.base.entity.BokTablelist;

/**
 * 业务场景配置查询 service
 * 
 * @author ruanhongming
 * @version 1.0 2018年5月25日
 * @see AccountsAdjustBusScenEleConf001Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AccountsAdjustBusScenEleConf001Service extends BaseService<AccountsAdjustBusScenEleConf001Dto> {

    /**
     * 业务场景配置查询mapper
     */
    @Autowired
    private AccountsAdjustBusScenEleConf001Mapper accountsAdjustBusScenEleConf001Mapper;

    
    /**
     * 会计场景表  mapper
     */
    @Autowired
    private BokTablelistMapper bokTablelistMapper;

    /**
     * 页面查询业务场景配置信息
     * 
     * @param paraMap 页面参数map
     * @return 查询到的业务场景配置信息
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @DataSource(DataSourceType.MASERT)
    public Page<AccountsAdjustBusScenEleConf001Dto> findPage(Map<String, Object> pageParam)
        throws Exception {
        return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME+"/AccountsAdjustBusScenEleConf001/findPage", pageParam, AccountsAdjustBusScenEleConf001Dto.class);
//    	pageParam.put("tableConfig", "业务场景要素配置");
//    	return super.queryForPage(()-> accountsAdjustBusScenEleConf001Mapper.findBokTableList(pageParam));
    }

    /**
     * 查询业务场景代码是否已被使用 
     * 
     * @param tableId 业务场景代码
     * @return 1：已使用，0：未使用
     * @see
     * @since 1.0
     */
    public boolean tableIdIsUsed(String tableId) {
        return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME+"/AccountsAdjustBusScenEleConf001/tableIdIsUsed", tableId, boolean.class);
//        return accountsAdjustBusScenEleConf001Mapper.tableIdIsUsed(tableId) > 0;
    }

    /**
     * 行内删除业务场景信息 
     * 
     * @param tableId 业务场景代码
     * @see
     * @since 1.0
     */
    public void deleteBokTableListInfo(String tableId) {
        
        restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME+"/AccountsAdjustBusScenEleConf001/deleteBokTableListInfo", tableId, Integer.class);
        
//        ValBokTablelistKey key = new ValBokTablelistKey();
//        key.setTableId(tableId);
//        key.setStatus(DictCodeList.数据状态_删除.getValue());
        //删除历史逻辑删除数据
//     TODO
//        this.valbokTablelistMapper.deleteByPrimaryKey(tableId,null);

//        BokTablelist entity = new BokTablelist();
//        entity.setTableId(tableId);
//        // 将源数据状态更新成D
//        this.accountsAdjustBusScenEleConf001Mapper.updateBokTableList2D(entity);
    }

}
