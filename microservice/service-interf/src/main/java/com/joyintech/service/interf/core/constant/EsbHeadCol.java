/*
 * 文件名：EsbHead.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年10月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.core.constant;

/**
 * ESB报文头字段标准定义接口
 * 
 * @author zhangrui1
 * @version 1.0 2018年10月25日
 * @see EsbHeadCol
 * @since 1.0
 */
public interface EsbHeadCol {

    /**
     * 取得字段名
     * 
     * @return 字段名
     * @see
     * @since 1.0
     */
    String colName();

    /**
     * 取得数据类型
     * 
     * @return 数据类型
     * @see
     * @since 1.0
     */
    String colType();

    /**
     * 取得数据长度
     * 
     * @return 数据长度
     * @see
     * @since 1.0
     */
    int colLength();
}
