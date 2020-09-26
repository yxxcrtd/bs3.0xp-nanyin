/*
 * 文件名：PlatformDateImpl.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：何阳阳
 * 修改时间：2018年7月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.common.svreport.palatformDate.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.web.PlatformDate;
import com.joyintech.tams.common.svreport.palatformDate.dao.SvrPlatformDateImplMapper;

/**
 * 平台日期组件实现类
 * @author 何阳阳
 * @version 1.0 2018年7月26日
 * @see SvrPlatformDateImpl
 * @since 1.0
 */
@Service("svrPlatformDateImpl")
public class SvrPlatformDateImpl implements PlatformDate {
    /**
     * 系统日期工具Mapper
     */
    @Autowired
    private SvrPlatformDateImplMapper platformDateImplMapper;
    /**
     * 获取平台日期
     * <功能详细描述>
     * @return 日期
     * @see
     * @since 1.0
     */
    @Override
    public Date getSysDate() {
        return platformDateImplMapper.getSysDate();
    }

    /**
     * 更新平台日期
     * @param newDate 新平台日
     * @return 更新结果
     */
    @Override
    public int updateSysDate(Date newDate) {
        int result =  platformDateImplMapper.updateSysDate(newDate);
        if(result > 0) {
            // 更新缓存
            synchronized (this) {
                CacheUtil.clearAppCache(GlobalConstant.NOW_SYS_DATE);
                CacheUtil.putAppCache(GlobalConstant.NOW_SYS_DATE, newDate);
            }
        }
        return result;
    }
}
