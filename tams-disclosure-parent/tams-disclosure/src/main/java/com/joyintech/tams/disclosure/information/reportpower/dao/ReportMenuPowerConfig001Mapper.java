/*
 * 文件名：ReportTreeMenuConfiguration001Mapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：huwei
 * 修改时间：2018年11月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.disclosure.information.reportpower.dao;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.system.base.entity.SysRole;
import com.joyintech.tams.common.base.entity.RepPower;
import com.joyintech.tams.disclosure.information.reportpower.dto.ReportMenuPowerConfig001Dto;

/**
 * 
 * @author huwei
 * @version 1.0 2018年11月10日
 * @see ReportMenuPowerConfig001Mapper
 * @since 1.0
 */
@Repository
public interface ReportMenuPowerConfig001Mapper {
    
    /**
     * 查询按钮的查询方法
     * @param params 参数    
     * @return List<ValLoanMarkquery001Dto>
     * @see
     * @since 1.0
     */
    List<ReportMenuPowerConfig001Dto> queryPageList(Map<String, Object> params);
    
    /**
     * 查询所有角色
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    List<SysRole> select();
    
    /**
     * 查询报表所对应的角色
     * <一句话功能简述>
     * <功能详细描述>
     * @param params
     * @return
     * @see
     * @since 1.0
     */
    List<SysRole> findUserRole(Map<String, Object> params);
    
    /**
     * 保存
     * <一句话功能简述>
     * <功能详细描述>
     * @param repPower
     * @see
     * @since 1.0
     */
    void save(RepPower repPower);
    
    /**
     * 根据报表ID删除
     * <一句话功能简述>
     * <功能详细描述>
     * @param repId
     * @return
     * @see
     * @since 1.0
     */
    int deleteByKey(String repId);
    
}
