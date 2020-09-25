/*
 * 文件名：InterfInfo.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2018年10月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.core.constant;

/**
 * 接口服务代码服务场景接口ID等信息Interface
 * 
 * @author zhangrui1
 * @version 1.0 2018年10月23日
 * @see EsbServiceInfo
 * @since 1.0
 */
public interface EsbServiceInfo {

    /**
     * 取得服务代码
     * 
     * @return 服务代码
     * @see
     * @since 1.0
     */
    String getServiceCode();

    /**
     * 取得服务场景
     * 
     * @return 服务场景
     * @see
     * @since 1.0
     */
    String getServiceScene();

    /**
     * 取得服务代码
     * 
     * @return 服务代码
     * @see
     * @since 1.0
     */
    String getTransId();

    /**
     * 取得接口ID
     * 
     * @return 接口ID
     * @see
     * @since 1.0
     */
    String getInterfId();

    /**
     * 取得交易码
     * 
     * @return 交易码
     * @see
     * @since 1.0
     */
    String getTranCode();
}
