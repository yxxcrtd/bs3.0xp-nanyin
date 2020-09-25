/*
 * 文件名：JixuDate001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangkang
 * 修改时间：2019年11月15日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.stresstestmanage.jixudate.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.CacheUtil;
import com.joyintech.service.risk.stresstestmanage.jixudate.dao.JixuDate001Mapper;
import com.joyintech.tams.common.base.dao.SttDataReadyMapper;
import com.joyintech.tams.common.base.entity.SttDataReady;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangkang
 * @version 1.0 2019年11月15日
 * @see JixuDate001Service
 * @since 1.0
 */
@Service
public class JixuDate001Service {
    
    @Autowired(required=false)
    private JixuDate001Mapper jixuDate001Mapper;
    
    @Autowired
    private SttDataReadyMapper sttDataReadyMapper;
    
    
    public Date getJixuDate() {
        // TODO Auto-generated method stub
        return jixuDate001Mapper.getJixuDate();
    }
    /**
     * 更新平台日期
     * @param newDate 新平台日
     * @return 更新结果
     */
    public int updateJixuDate(Date newDate) {
        // TODO Auto-generated method stub
//        int result =  jixuDate001Mapper.updateJixuDate(newDate);
//        if(result > 0) {
//            // 更新缓存
//            synchronized (this) {
//                CacheUtil.clearAppCache(GlobalConstant.NOW_SYS_DATE);
//                CacheUtil.putAppCache(GlobalConstant.NOW_SYS_DATE, newDate);
//            }
//        }
        
        SttDataReady sttDataReady  = new SttDataReady();
        sttDataReady.setSys("ZG");
        sttDataReady.setHostNm("1");
        sttDataReady.setBatchNum("ALL");
        sttDataReady.setDataDate(newDate);
        sttDataReady.setReadyTime(newDate);
        int result = sttDataReadyMapper.insert(sttDataReady);
        return result;
    }
}
