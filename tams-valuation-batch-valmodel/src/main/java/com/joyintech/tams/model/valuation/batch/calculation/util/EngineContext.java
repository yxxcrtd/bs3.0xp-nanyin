package com.joyintech.tams.model.valuation.batch.calculation.util;

import com.joyintech.tams.model.valuation.batch.calculation.Configuration;
import com.joyintech.tams.model.valuation.batch.calculation.config.DefaultConfig;

/**
 * @author zznn
 * @date 2021/7/1
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

