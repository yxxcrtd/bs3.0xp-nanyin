package com.joyintech.invest.interf.common.core.contants;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;

import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.invest.interf.common.core.annotation.INF;
import com.joyintech.invest.interf.common.core.service.InterfRecvBaseService;
import com.joyintech.invest.interf.common.core.service.InterfSendBaseService;

/**
 * @Description: 接口枚举
 * @create: 2020/1/16 10:23
 * @return: 
 */
public class InterfList {
    private InterfList() {
        // private the constructor
    }

    private static Map<String, InterfSendBaseService<?>> beanSendMap = new HashMap<>();
    
    private static Map<String, InterfRecvBaseService<?>> beanRecvMap = new HashMap<>();

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
            if (obj instanceof InterfSendBaseService) {
                InterfSendBaseService<?> svc = (InterfSendBaseService<?>)obj;
                String infId = svc.getInfId();
                beanSendMap.put(infId, svc);
            }  else if (obj instanceof InterfRecvBaseService) {
            	InterfRecvBaseService<?> svc = (InterfRecvBaseService<?>)obj;
                String infId = svc.getInfId();
                beanRecvMap.put(infId, svc);
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
    public static final InterfSendBaseService<?> getSendBean(String id) {
        if (beanSendMap == null || beanSendMap.isEmpty()) {
            init();
        }
        return beanSendMap.get(id);
    }
    
    /**
     * 根据接口ID取得接口模块Service
     *
     * @param id 接口ID
     * @return 接口模块Service
     * @see
     * @since 1.0
     */
    public static final InterfRecvBaseService<?> getRecvBean(String id) {
        if (beanRecvMap == null || beanRecvMap.isEmpty()) {
            init();
        }
        return beanRecvMap.get(id);
    }
}
