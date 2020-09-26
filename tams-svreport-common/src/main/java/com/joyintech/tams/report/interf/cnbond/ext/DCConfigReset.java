package com.joyintech.tams.report.interf.cnbond.ext;

import java.io.File;
import java.lang.reflect.Field;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.joyintech.framework.util.StringUtil;

import AF.po.AFComCfgObj;
import ds.dc.api.app.DCconfig;
import ds.dc.api.app.DClient;


/**
 * 中债直连切面<br>
 * 多直连机地址动态切换
 * 
 * @author 何阳阳
 * @version 1.0 2017年11月21日
 * @see DCConfigReset
 * @since 1.0
 */
@Component
@Aspect
public class DCConfigReset {

    /**
     * 日志记录
     */
    private final Logger logger = LoggerFactory.getLogger(DCConfigReset.class);

    /**
     * AppCfg/DC/cfg 中默认配置的DIM_SERVER
     */
    private static String defaultUrl = null;

    /**
     * @param joinPoint JoinPoint
     * @throws IllegalAccessException 
     */
    @Before("execution(* ds.dc.api.app.DClient.*(..))")
    private void beforeDClientMethod(JoinPoint joinPoint) throws IllegalAccessException{
        // 获取直连机地址
        String url = getServerUrl();
        // 如果有配置不同的直连机地址，则根据对应的登记银行代码进行设置
        if (StringUtil.isNotEmpty(url)) {
            Object target = joinPoint.getTarget();
            setServerUrl(url);
            if (target instanceof DClient) {
                Field[] fields = target.getClass().getDeclaredFields();
                for (Field field : fields) {
                    if (field.getName().equals("serverUrl")) {
                        field.setAccessible(true);
                        field.set(target, url);
                    }
                }
            }
        }
    }

    /**
     * 设置直连机地址
     * 
     * @param serverUrl 直连机地址
     */
    public void setServerUrl(String serverUrl){
        File file = new File("./AppCfg/DC/cfg");
        if (!file.exists()) {
            logger.error("中债直连配置文件不存在！[" + file.getAbsolutePath() + "]");
            return;
        }
        AFComCfgObj cfg = DCconfig.getDcCfg();
        cfg.set("DIM_SERVER", serverUrl);
    }

    /**
     * 获取直连机服务器地址
     * 
     * @return 直连机服务器地址
     */
    private String getServerUrl() {
        if (null == defaultUrl) {
            defaultUrl = DCconfig.getServerUrl();
        }
        String serverUrl = ExtFprDClient.getDimServerUrl();
        return StringUtil.defaultString(serverUrl, defaultUrl);
    }
}
