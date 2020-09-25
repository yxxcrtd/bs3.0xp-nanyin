/*
 * 文件名：InterfOPDL03OutEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨静敏
 * 修改时间：2018年11月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.iopdl03.entity;

import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbName;

/**
 * opdl03接口输出
 * 
 * @author 杨静敏
 * @version 1.0 2018年11月19日
 * @see InterfOPDL03OutEntity
 * @since 1.0
 */

public class InterfOPDL03OutEntity {

    /**
     * 交易返回码
     */
    private String retCode;

    /**
     * 交易返回信息
     */
    private String retMsg;

    /**
     * 获取交易返回码
     * 
     * @return 交易返回码
     */
    public String getRetCode() {
        return retCode;
    }

    /**
     * 设置交易返回码
     * 
     * @param retCode 交易返回码
     */
    @EsbName(value = "RET_CODE")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    /**
     * 获取交易返回信息
     * 
     * @return 交易返回信息
     */
    public String getRetMsg() {
        return retMsg;
    }

    /**
     * 设置交易返回信息
     * 
     * @param retMsg 交易返回信息
     */
    @EsbName(value = "RET_MSG")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

}
