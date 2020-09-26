/*
 * 文件名：InterfBaseProcessor.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：王波
 * 修改时间：2018年6月11日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.interf.core;

import java.util.Map;

import org.springframework.batch.item.ItemProcessor;

import com.joyintech.framework.base.BaseEntity;

public abstract class InterfBaseProcessor<I, O extends BaseEntity> implements ItemProcessor<I, O>  {
    

    /**
     * 参数
     */
    private Map<String, Object> params;
    
    /**
     * 获取 params
     * @return params.
     */
    public Map<String, Object> getParams() {
        return params;
    }

    
    /**
     * 设置 params
     * @param params params
     */
    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
    
}
