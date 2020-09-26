/*
 * 文件名：ValSysParaService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangwei
 * 修改时间：2019年2月1日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.common.interf.val.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.datasource.DynamicDataSourceHolder;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.tams.valuation.common.interf.val.dao.ValSysParaMapper;

/**
 * 提供系统参数
 * @author zhangwei
 * @version 1.0 2019年2月1日
 * @see ValSysParaService
 * @since 1.0
 */
@Service
public class ValSysParaService {

    /**
     * 系统参数
     */
    @Autowired
    private ValSysParaMapper valSysParaMapper;
    
    /**
     * 获取系统参数
     * @param paraCode
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @DataSource(DataSourceType.OTHER)
    public String findParamValue(String paraCode) {
        String paramValue = valSysParaMapper.selectParamValue(paraCode);
        return paramValue;
    }
    
}
