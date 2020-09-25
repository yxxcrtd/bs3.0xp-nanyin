/*
 * 文件名：InfList.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年7月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.riskrdsinf.core.constant;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;

import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.service.riskrdsinf.core.annotation.INF;
import com.joyintech.service.riskrdsinf.core.service.InfBaseService;

/**
 * 接口枚举
 * 
 * @author zhangrui1
 * @version 1.0 2019年7月26日
 * @see InfList
 * @since 1.0
 */
public class InfList {

    private InfList() {
        // private the constructor
    }

    private static Map<String, InfBaseService<?>> beanMap = new HashMap<>();

    /**
     * 初始化接口模块Service列表
     * 
     * @see
     * @since 1.0
     */
    private static void init() {
        ApplicationContext context = SpringContextUtil.getApplicationContext();
        if (context == null) {
            return;
        }
        Map<String, Object> beans = context.getBeansWithAnnotation(INF.class);
        if (beans == null || beans.isEmpty()) {
            return;
        }
        beans.entrySet().forEach(en -> {
            Object obj = en.getValue();
            if (obj instanceof InfBaseService) {
                InfBaseService<?> svc = (InfBaseService<?>)obj;
                String infId = svc.getInfId();
                beanMap.put(infId, svc);
            }
        });
    }

    /**
     * 根据接口ID取得接口模块Service
     * 
     * @param id 接口ID
     * @return 接口模块Service
     * @see
     * @since 1.0
     */
    public static final InfBaseService<?> getBean(String id) {
        if (beanMap == null || beanMap.isEmpty()) {
            init();
        }
        return beanMap.get(id);
    }
}
