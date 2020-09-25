/*
 * 文件名：EngineContext.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：何阳阳
 * 修改时间：2017年1月20日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */

package com.joyintech.tams.disclosure.busicommon.common.util;
import com.joyintech.tams.disclosure.busicommon.common.config.DefaultConfig;
import com.joyintech.tams.disclosure.busicommon.common.util.Configuration;

/**
 * 引擎执行环境
 * 
 * @author 何阳阳
 * @version 1.0 2017年1月20日
 * @see EngineContext
 * @since 1.0
 */
public class EngineContext {
    
    /**
     * EngineContext
     */
    private static EngineContext instance;

    /**
     * 配置
     */
    private Configuration config = new DefaultConfig();

    /**
     * 
     */
    private EngineContext() {
        super();
    }

    /**
     * 获取实例
     * @return EngineContext
     */
    public static EngineContext getInstance() {
        if(null == instance) {
            instance = new EngineContext();
        }
        return instance;
    }
    
    /**
     * 获取 配置
     * 
     * @return 配置.
     */
    public Configuration getConfig() {
        return config;
    }

    /**
     * 设置 配置
     * 
     * @param config 配置
     */
    public void setConfig(Configuration config) {
        this.config = config;
    }

}
