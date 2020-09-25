/*
 * 文件名：AccountsAdjustBusScenEleConf003Service.java
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.service.valuation.accounadjustmanage.bussceneleconf.dao.AccountsAdjustBusScenEleConf003Mapper;
import com.joyintech.service.valuation.accounadjustmanage.bussceneleconf.dto.AccountsAdjustBusScenEleConf002Dto;
import com.joyintech.tams.common.base.entity.BokTabledict;

/**
 * 业务要素配置  service 
 * 
 * @author ruanhongming
 * @version 1.0 2016年5月25日
 * @see AccountsAdjustBusScenEleConf003Service
 * @since 1.0
 */
@Service(value="serviceAccountsAdjustBusScenEleConf003Service")
@Transactional(rollbackFor = Exception.class)
public class AccountsAdjustBusScenEleConf003Service extends BaseService<AccountsAdjustBusScenEleConf002Dto> {

    /**
     * 业务要素配置查询mapper
     */
    @Autowired
    private AccountsAdjustBusScenEleConf003Mapper accountsAdjustBusScenEleConf003Mapper;
    
    public String qryTableNameByTableId(@RequestBody String tableId){
        return accountsAdjustBusScenEleConf003Mapper.qryTableNameByTableId(tableId);
    }
    
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
    public Page<AccountsAdjustBusScenEleConf002Dto> findPage(int page,int length,Map<String, Object> pageParam)
        throws Exception {
    	return super.queryForPage(page,length,()-> accountsAdjustBusScenEleConf003Mapper.findValBokTableDictList(pageParam));
    }

    /**
     * 行内删除业务场景信息
     * 
     * @param dto 行数据
     * @see
     * @since 1.0
     */
    public void deleteBokTableDict(AccountsAdjustBusScenEleConf002Dto dto) {
        BokTabledict entity = new BokTabledict();
        entity.setDictId(dto.getDictId());
        //将数据状态更新成D
        this.accountsAdjustBusScenEleConf003Mapper.updateBokTableDict2D(entity);
    }
    
}
