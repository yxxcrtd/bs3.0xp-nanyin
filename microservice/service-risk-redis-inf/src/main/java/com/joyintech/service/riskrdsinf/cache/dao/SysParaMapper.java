/*
 * 文件名：SysParaMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年10月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.riskrdsinf.cache.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.joyintech.service.riskrdsinf.cache.entity.SysPara;
import com.joyintech.service.riskrdsinf.cache.entity.SysParaKey;

/**
 * 查询接口系统参数Mapper
 * 
 * @author zhangrui1
 * @version 1.0 2018年10月26日
 * @see SysParaMapper
 * @since 1.0
 */
@Repository("InterfSysParaMapper")
public interface SysParaMapper {

    /**
     * 获取接口所有系统参数
     * 
     * @return 接口系统参数列表
     * @see
     * @since 1.0
     */
    List<SysPara> selectInfAll();

    /**
     * 检索特定的系统参数
     * 
     * @param key 查询条件
     * @return 系统参数
     */
    SysPara selectParaOne(SysParaKey key);
}
