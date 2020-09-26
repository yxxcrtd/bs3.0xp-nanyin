/*
 * 文件名：InstanceOfClass.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：王波
 * 修改时间：2018年6月1日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.common.service;

/**
 * 生成泛型类 
 * 
 * @author 王波
 * @version 1.0 2018年6月1日
 * @see InstanceOfClass
 * @since 1.0
 */
public class InstanceOfClass {
    /**
     * 生成泛型类
     * @param cls class
     * @param <T> 生成类的泛型
     * @return 生成的类
     * @see
     * @since 1.0
     */
    public static <T> T createInstance(Class<T> cls) {
        T obj=null;
        try {
            obj=cls.newInstance();
        } 
        catch (Exception e) {
            throw new RuntimeException("参数出现异常");
        }
        return obj;
    }
}
