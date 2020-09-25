/*
 * 文件名：SysParamInfService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年10月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.cache.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.interf.cache.dao.SysParaMapper;
import com.joyintech.service.interf.cache.entity.SysPara;
import com.joyintech.tams.invest.common.component.base.core.impl.PlatformDateImpl;

/**
 * 查询接口系统参数Service
 * 
 * @author zhangrui1
 * @version 1.0 2018年10月26日
 * @see SysParamInfService
 * @since 1.0
 */
@Service
public class SysParamInfService {

    /**
     * 查询接口系统参数Mapper
     */
    @Autowired
    private SysParaMapper sysParaMapper;

    /**
     * 平台日期组件对象
     */
    @Autowired
    private PlatformDateImpl platformDtImpl;

    /**
     * 加载系统参数
     * 
     * @return 系统参数
     * @see
     * @since 1.0
     */
    @DataSource(DataSourceType.OTHER)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Map<String, Object> interfCache() {
        // 加载系统参数
        List<SysPara> list = sysParaMapper.selectInfAll();
        Map<String, Object> resultMap = new HashMap<>();
        for (SysPara para : list) {
            resultMap.put(para.getParaCode(), para.getParamValue());
        }
        return resultMap;
    }

    /**
     * 加载平台日期
     * 
     * @return 平台日期 yyyyMMdd
     * @see
     * @since 1.0
     */
    @DataSource(DataSourceType.MASERT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String getSysDate() {
        // 加载平台日期
        Date dt = platformDtImpl.getSysDate();
        if (dt != null) {
            return DateUtil.format(dt, DateUtil.DEFAULT_OTHER_FORMAT);
        }
        return StringUtil.EMPTY;
    }
}
