/*
 * 文件名：Setting.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2020年9月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.riskrds.dto;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.util.CollectionUtils;

/**
 * 联动配置信息
 * 
 * @author zhangrui1
 * @version 1.0 2020年9月22日
 * @see Setting
 * @since 1.0
 */
public class Setting<T> {

    /**
     * 固定值
     */
    public static final int TYPE_VAL = 0;

    /**
     * SQL
     */
    public static final int TYPE_SQL = 1;

    /**
     * 现有函数
     */
    public static final int TYPE_MTD = 2;

    /**
     * 类型
     */
    private int type = TYPE_VAL;

    /**
     * 是否结果
     */
    private boolean isResult;

    private T express;

    protected Setting(int type, boolean isResult, T express) {
        this.type = type;
        this.isResult = isResult;
        this.express = express;
    }

    protected T getExpress() {
        return express;
    }

    protected void setExpress(T express) {
        this.express = express;
    }

    /**
     * 类型
     * 
     * @return 类型
     */
    public int getType() {
        return this.type;
    }

    /**
     * 是否结果
     * 
     * @return true : 结果
     */
    public boolean isResult() {
        return isResult;
    }

    public Object execute(Function<T, Object> func) {
        if (null != func) {
            return func.apply(express);
        }
        return null;
    }

    /**
     * parameter
     */
    private Map<String, Object> paramSet;

    /**
     * 设置查询条件
     * 
     * @param key 条件名
     * @param val 条件值
     */
    public void put(String key, Object val) {
        if (CollectionUtils.isEmpty(paramSet)) {
            paramSet = new HashMap<>();
        }
        paramSet.put(key, val);
    }

    public void setParamSet(Map<String, Object> paramSet) {
        this.paramSet = paramSet;
    }

    protected Map<String, Object> getParamSet() {
        return this.paramSet;
    }
}
