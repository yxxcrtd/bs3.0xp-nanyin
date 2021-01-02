/*
 * 文件名：SvrStartupConfiguration.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：Administrator
 * 修改时间：2019年3月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.common.svreport.base.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.CacheUtil;
import com.joyintech.system.config.StartupConfiguration;
import com.joyintech.tams.common.base.dao.ChbTypeMappingExMapper;
import com.joyintech.tams.common.base.entity.ChbTypeMappingEx;

/**
 * 监管报送系统启动定制化类
 * 〈功能详细描述〉
 * @author chenxc
 * @version 1.0 2019年3月11日
 * @see SvrStartupConfiguration
 * @since 1.0
 */
@Component
public class SvrStartupConfiguration extends StartupConfiguration{
    private final Logger logger = LoggerFactory.getLogger(SvrStartupConfiguration.class);

    public final static String CACHEKEY="SVR_TYPEMAPPING"; 
    
    @Autowired
    private ChbTypeMappingExMapper chbTypeMappingExMapper;
    
    
    @Override
    public void afterStartup(ApplicationContext applicationContext) {
        
        super.afterStartup(applicationContext);
        
        List<ChbTypeMappingEx> typeList = chbTypeMappingExMapper.select();
        Map<String,ChbTypeMappingEx> typeMap = typeList.stream().collect(Collectors.toMap(ChbTypeMappingEx::getMappingkey, a -> a,(k1,k2)->k1));
        typeMap.putAll(typeList.stream().filter(a->a.getSrcfield() != null).collect(Collectors.toMap(ChbTypeMappingEx::getMappingkeyAll, a -> a,(k1,k2)->k1)));
//        for ( Entry<String,ChbTypeMappingEx> e:typeMap.entrySet() ){
//            System.out.println(e.getKey()+"----"+e.getValue());
//        }
        CacheUtil.putAppCache(CACHEKEY, typeMap);
        
    }

    @Override
    public void run() {
        logger.info("TAMSSVR服务不重新加载缓存222222");
    }
}
