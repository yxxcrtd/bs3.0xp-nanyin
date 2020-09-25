/*
 * 文件名：AccountsAdjustBusScenEleConf003Mapper.java
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

import com.joyintech.service.valuation.accounadjustmanage.bussceneleconf.dto.AccountsAdjustBusScenEleConf002Dto;
import com.joyintech.tams.common.base.entity.BokTabledict;

/**
 * 业务要素管理查询 mapper
 * 〈功能详细描述〉
 * @author ruanhongming
 * @version 1.0 2017年5月25日
 * @see AccountsAdjustBusScenEleConf003Mapper
 * @since 1.0
 */
@Repository(value="serviceAccountsAdjustBusScenEleConf003Mapper")
public interface AccountsAdjustBusScenEleConf003Mapper {
    
    /**
     * 查询业务要素配置列表
     * <功能详细描述>
     * @param paraMap 页面参数map
     * @return 业务要素配置列表
     * @see
     * @since 1.0
     */
    List<AccountsAdjustBusScenEleConf002Dto> findValBokTableDictList(Map<String, Object> params);
    
    /**
     * 导出时查询符合条件的业务要素数量
     * <功能详细描述>
     * @param paraMap 页面参数map
     * @return 符合导出条件的业务要素数量
     * @see
     * @since 1.0
     */
    int findBokTableDictCount(Map<String, Object> paraMap);
    
    /**
     * 查询符合导出条件的业务要素
     * <功能详细描述>
     * @param paraMap 页面参数map
     * @return 符合导出条件的业务要素列表
     * @see
     * @since 1.0
     */
    
    /**
     * 行内修改或删除时，check业务要素是否已被使用
     * <功能详细描述>
     * @param dictId 业务要素代码
     * @return 1：已被使用；0：未被使用
     * @see
     * @since 1.0
     */
    int rowEditOrDeleteCheck(String dictId);
    
    /**
     * 查询行数据的更新时间
     * <功能详细描述>
     * @param dictId 业务要素代码
     * @return 更新时间
     * @see
     * @since 1.0
     */
    Date findBokTableDictUpdateTime(String dictId);
    
    /**
     * 行内删除结束后，将数据状态更新成D
     * <功能详细描述>
     * @param entity 删除实体
     * @see
     * @since 1.0
     */
    void updateBokTableDict2D(BokTabledict entity);
    
     String qryTableNameByTableId(String tableId);
}
