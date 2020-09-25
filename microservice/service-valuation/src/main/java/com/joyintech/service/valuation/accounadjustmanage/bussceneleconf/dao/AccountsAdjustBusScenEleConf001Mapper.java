/*
 * 文件名：AccountsAdjustBusScenEleConf001Mapper.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：ruanhngming
 * 修改时间：2018年5月25日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.service.valuation.accounadjustmanage.bussceneleconf.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.service.valuation.accounadjustmanage.accountentry.dto.ExportEntity;
import com.joyintech.service.valuation.accounadjustmanage.bussceneleconf.dto.AccountsAdjustBusScenEleConf001Dto;
import com.joyintech.tams.common.base.entity.BokTablelist;

/**
 * 业务场景配置查询mapper
 * 〈功能详细描述〉
 * @author ruanhongming
 * @version 1.0 2017年5月25日
 * @see AccountsAdjustBusScenEleConf001Mapper
 * @since 1.0
 */
@Repository(value="serviceAccountsAdjustBusScenEleConf001Mapper")
public interface AccountsAdjustBusScenEleConf001Mapper {

    /**
     * 页面查询业务场景配置信息
     * <功能详细描述>
     * @param paraMap 页面参数map
     * @return 查询到的业务场景配置信息
     * @see
     * @since 1.0
     */
	List<AccountsAdjustBusScenEleConf001Dto> findBokTableList(Map<String, Object> params);
    
    /**
     * 页面导出时，查询符合条件的业务场景配置信息数量
     * <功能详细描述>
     * @param paraMap 页面参数map
     * @return 符合条件的业务场景配置信息数量
     * @see
     * @since 1.0
     */
    int findBokTableListCount(Map<String, Object> paraMap);
    
    /**
     * 查询导出业务场景配置信息
     * <功能详细描述>
     * @param paraMap 页面参数map
     * @return 查询到的业务场景配置信息模板列表
     * @see
     * @since 1.0
     */
    List<ExportEntity> exportBokTableList(Map<String, Object> paraMap);
    
    /**
     * 查询行数据的更新时间
     * <功能详细描述>
     * @param dto 行数据
     * @return 更新时间
     * @see
     * @since 1.0
     */
    Date findBokTabListUpdateDate(AccountsAdjustBusScenEleConf001Dto dto);
    
    /**
     * 查询业务场景代码是否已被使用
     * <功能详细描述>
     * @param tableId 业务场景代码
     * @return 1：已使用，0：未使用
     * @see
     * @since 1.0
     */
    int tableIdIsUsed(String tableId);
    
    /**
     * 将行内删除的数据状态更新成D
     * <功能详细描述>
     * @param entity 行删除entity
     * @see
     * @since 1.0
     */
    void updateBokTableList2D(BokTablelist entity);
    
}
