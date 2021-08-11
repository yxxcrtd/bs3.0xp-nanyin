/*
 * 文件名：IntFrequency.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统
 * 修改人：何阳阳
 * 修改时间：2017年1月23日
 * 修改内容：新建
 * 系统名称：中信银行资管系统
 */
 
package com.joyintech.tams.model.valuation.batch.calculation.constant;


/**
 * 付息频率
 * @author zznn
 */
public enum IntFrequency {
    月付(12),
    季付(4),
    半年付(2),
    年付(1);
    
    private int value;

    /**
     * 获取 value
     * @return value.
     */
    public int getValue() {
        return value;
    }

    /**
     * 设置 value
     * @param value value
     */
//    public void setValue(int value) {
//        this.value = value;
//    }

    /**
     * @param value
     */
    private IntFrequency(int value) {
        this.value = value;
    }
    
}
