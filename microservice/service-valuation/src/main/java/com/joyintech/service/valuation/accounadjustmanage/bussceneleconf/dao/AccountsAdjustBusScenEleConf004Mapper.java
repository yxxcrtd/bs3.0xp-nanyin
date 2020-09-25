/*
 * 文件名：AccountsAdjustBusScenEleConf004Mapper.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：ruanhngming
 * 修改时间：2018年5月25日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.service.valuation.accounadjustmanage.bussceneleconf.dao;

import org.springframework.stereotype.Repository;

import com.joyintech.service.valuation.accounadjustmanage.bussceneleconf.dto.AccountsAdjustBusScenEleConf002Dto;


/**
 * 业务要素新建 mapper
 * 〈功能详细描述〉
 * @author ruanhongming
 * @version 1.0 2017年5月25日
 * @see AccountsAdjustBusScenEleConf004Mapper
 * @since 1.0
 */
@Repository(value="serviceAccountsAdjustBusScenEleConf004Mapper")
public interface AccountsAdjustBusScenEleConf004Mapper {
    
    /**
     * 修改和查看模式页面初始化，查询出相应的业务要素信息
     * <功能详细描述>
     * @param dictId 业务要素代码
     * @return 业务要素信息
     * @see
     * @since 1.0
     */
    AccountsAdjustBusScenEleConf002Dto selectBokTableDictInfo(String dictId);
    
    /**
     * 查看数据项代码是否已经存在
     * <功能详细描述>
     * @param dto 页面参数dto
     * @return 1：已存在；0：不存在
     * @see
     * @since 1.0
     */
    int checkDataRepeat4DictCode(AccountsAdjustBusScenEleConf002Dto dto);
    
    /**
     * 查看数据项名称是否已经存在
     * <功能详细描述>
     * @param dto 页面参数dto
     * @return 1：已存在；0：不出在
     * @see
     * @since 1.0
     */
    int checkDataRepeat4DictName(AccountsAdjustBusScenEleConf002Dto dto);
    
    /**
     * 已被使用过的会计元素不可修改数据项代码、数据类型以及数据来源！
     * 查询是否这几项是否
     * <功能详细描述>
     * @param dto 页面参数dto
     * @return 1：已存在；0：不出在
     * @see
     * @since 1.0
     */
    int checkDataUsed(AccountsAdjustBusScenEleConf002Dto dto);
}
