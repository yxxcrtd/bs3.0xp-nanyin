/*
 * 文件名：IConverter.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：何阳阳
 * 修改时间：2018年1月31日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.background.component.common.pack.common.grid;


/**
 * @param <T>
 * @author 何阳阳
 * @version 1.0 2018年1月31日
 * @see IConverter
 * @since 1.0
 */
public interface IConverter<T> {

    /**
     * @param src Object
     * @return T
     */
    T doConverter(Object src);
}
