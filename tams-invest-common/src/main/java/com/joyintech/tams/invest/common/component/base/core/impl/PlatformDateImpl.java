/*
 * 文件名：PlatformDateImpl.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：何阳阳
 * 修改时间：2018年7月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.common.component.base.core.impl;

import com.github.pagehelper.util.StringUtil;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.security.User;
import com.joyintech.framework.security.data.InstitutionDate;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.component.base.core.impl.dao.PlatformDateImplMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * 平台日期组件实现类
 * @author 何阳阳
 * @version 1.0 2018年7月26日
 * @see PlatformDateImpl
 * @since 1.0
 */
@Primary
@Service("platformDateService")
public class PlatformDateImpl implements IPlatformDate {
    /**
     * 系统日期工具Mapper
     */
    @Autowired
    private PlatformDateImplMapper platformDateImplMapper;
    /**
     * 获取平台日期
     * <功能详细描述>
     * @return 日期
     * @see
     * @since 1.0
     */
    @Override
    public Date getSysDate() {

        if(MultilevelDataAuthorityUtil.isEnabled()) {
            if (SecurityUtil.hasAuthenticated()) {
                return getSysDate(LoginUserUtil.getUserName());
            }else{
                return getSysDate(GlobalConstant.SYSTEM_USER);
            }
        }
        return platformDateImplMapper.getSysDate();
    }

    @Override
    public Date getSysDateByIns(String institutionCode) {
        return platformDateImplMapper.getSysDateByIns(institutionCode);
    }

    /**
     * 通过用户名 获取
     * @param userName
     * @return
     */
    public Date getSysDate(String userName){

        if(MultilevelDataAuthorityUtil.isEnabled()) {
            String orgCode = MultilevelDataAuthorityUtil.getOrganizationCode(userName);
            if (StringUtils.isNotEmpty(orgCode)) {
                Date dateByIns = platformDateImplMapper.getSysDateByIns(orgCode);
                if (dateByIns != null) {
                    return dateByIns;
                }
            }
        }
        return platformDateImplMapper.getCommonSysDate();
    }

    public Date selectCommonSysDate(){
        return platformDateImplMapper.getCommonSysDate();
    }

    /**
     * 更新平台日期
     * @param newDate 新平台日
     * @return 更新结果
     */
    @Override
    public int updateSysDate(Date newDate) {

        int result = 0;
        if(MultilevelDataAuthorityUtil.isEnabled()){
            User user = SecurityUtil.getLoginUser();
            result = platformDateImplMapper.updateSysDateByIns(newDate,user.getOrgCode());

            if(result > 0) {
                // 更新缓存
                synchronized (this) {
                    CacheUtil.clearAppCache(GlobalConstant.NOW_SYS_DATE+user.getOrgCode());
                    CacheUtil.putAppCache(GlobalConstant.NOW_SYS_DATE+user.getOrgCode(), newDate);
                }
            }

        }else {
            result = platformDateImplMapper.updateSysDate(newDate);
            if(result > 0) {
                // 更新缓存
                synchronized (this) {
                    CacheUtil.clearAppCache(GlobalConstant.NOW_SYS_DATE);
                    CacheUtil.putAppCache(GlobalConstant.NOW_SYS_DATE, newDate);
                }
            }
        }

        return result;
    }

    @Override
    public int updateSysDateByIns(Date newDate,String institutionCode){

        int result = 0;
        if(MultilevelDataAuthorityUtil.isEnabled()){
            result = platformDateImplMapper.updateSysDateByIns(newDate,institutionCode);
            if(result > 0) {
                // 更新缓存
                synchronized (this) {
                    CacheUtil.clearAppCache(GlobalConstant.NOW_SYS_DATE+institutionCode);
                    CacheUtil.putAppCache(GlobalConstant.NOW_SYS_DATE+institutionCode, newDate);
                }
            }

        }
        return result;

    }



    public int updateAllSysDate(String institutionCode) {

        //if(StringUtils.isEmpty(institutionCode) || StringUtils.equals("ALL",institutionCode)){
            // 全部更新 + 1
         return   platformDateImplMapper.updateAllSysDate(institutionCode);

        //}

    }

    @Override
    public List<InstitutionDate> getSysDateList(String institutionCode) {

//        if(StringUtils.isEmpty(institutionCode) || StringUtils.equals("ALL",institutionCode)){

           return platformDateImplMapper.getAllSysDate(institutionCode);
//        }else{
//
//        }


    }

    /**
     * 获取数据库日期
     * <功能详细描述>
     * @return 日期
     * @see
     * @since 1.0
     */
    @Override
    public Date getDbDate() {
        return platformDateImplMapper.getDbDate();
    }
    
    /**
     * 获取平台日期字符串格式
     * <功能详细描述>
     * @return 日期
     * @see
     * @since 1.0
     */
    @Override
    public String getSysDateStr() {
        return DateUtil.format(getSysDate());
    }
}
