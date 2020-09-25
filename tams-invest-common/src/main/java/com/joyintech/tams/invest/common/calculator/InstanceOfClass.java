/*
 * 文件名：InstanceOfClass.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月16日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.common.calculator;


/**
 *  生成泛型类
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月16日
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
