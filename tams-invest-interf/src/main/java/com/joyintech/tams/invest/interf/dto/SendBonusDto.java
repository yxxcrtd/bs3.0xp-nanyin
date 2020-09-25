/*
 * 文件名：SendBonusDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年1月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.interf.dto;

import com.joyintech.tams.common.base.entity.InterfBonusInfo;

/**
 * 净值分红信息查询接口（190011）查询DTO
 * 
 * @author zhangrui1
 * @version 1.0 2019年1月18日
 * @see SendBonusDto
 * @since 1.0
 */
public class SendBonusDto extends InterfBonusInfo {

    /**
     * 序列号
     */
    private static final long serialVersionUID = -8121357467425080234L;

    /**
     * 数据总量
     */
    private Integer dataNum;

    /**
     * 处理结果
     */
    private boolean handleOK;

    /**
     * 错误信息
     */
    private String errMsg;

    /**
     * 获取 数据总量
     * 
     * @return 数据总量.
     */
    public Integer getDataNum() {
        return dataNum;
    }

    /**
     * 设置 数据总量
     * 
     * @param dataNum 数据总量
     */
    public void setDataNum(Integer dataNum) {
        this.dataNum = dataNum;
    }

    /**
     * 获取 处理结果
     * 
     * @return 处理结果.
     */
    public boolean isHandleOK() {
        return handleOK;
    }

    /**
     * 设置 处理结果
     * 
     * @param handleOK 处理结果
     */
    public void setHandleOK(boolean handleOK) {
        this.handleOK = handleOK;
    }

    /**
     * 获取 错误信息
     * 
     * @return 错误信息.
     */
    public String getErrMsg() {
        return errMsg;
    }

    /**
     * 设置 错误信息
     * 
     * @param errMsg 错误信息
     */
    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
