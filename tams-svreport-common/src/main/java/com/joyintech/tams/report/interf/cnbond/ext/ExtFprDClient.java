/*
 * 文件名：ExtFprDClient.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：何阳阳
 * 修改时间：2017年11月21日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */

package com.joyintech.tams.report.interf.cnbond.ext;

import java.lang.reflect.Field;
import java.util.Map;

import ds.dc.api.app.DClient;
import fpr.dmsg.client.FprDClient;

/**
 * 扩展中债FprDClient
 * 
 * @author 何阳阳
 * @version 1.0 2017年11月21日
 * @see ExtFprDClient
 * @since 1.0
 */
public class ExtFprDClient extends FprDClient {

    /**
     * 直连机的服务器地址列表
     */
    private static Map<String, String> dimServer;

    /**
     * 登记银行代码
     */
    private static String regBankCode;

    /**
     * 设置 c
     * 
     * @param c c
     * @throws IllegalAccessException 
     */
    public void setC(DClient c) throws  IllegalAccessException{
        Field[] ff = this.getClass().getSuperclass().getDeclaredFields();
        for (Field f : ff) {
            f.setAccessible(true);
            f.set(this, c);
        }
    }

    /**
     * 获取 dimServer
     * 
     * @param regBankCode 登记银行代码
     * @return dimServer.
     */
    public static Map<String, String> getDimServer() {
        return dimServer;
    }

    /**
     * 获取中债直连机地址
     * 
     * @param regBankCode 登记银行代码
     * @return dimServer.
     */
    public static String getDimServerUrl() {
        if (null != ExtFprDClient.dimServer) {
            return ExtFprDClient.dimServer.get(ExtFprDClient.regBankCode);
        }
        return null;
    }

    /**
     * 设置 dimServer
     * 
     * @param dimServer dimServer
     */
    public void setDimServer(Map<String, String> dimServer) {
        ExtFprDClient.dimServer = dimServer;
    }

    /**
     * 获取 regBankCode
     * 
     * @return regBankCode.
     */
    public static String getRegBankCode() {
        return regBankCode;
    }

    /**
     * 设置 regBankCode
     * 
     * @param regBankCode regBankCode
     */
    public static void setRegBankCode(String regBankCode) {
        ExtFprDClient.regBankCode = regBankCode;
    }

}
