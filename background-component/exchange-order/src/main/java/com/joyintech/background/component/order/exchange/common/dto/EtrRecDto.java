/*
 * 文件名：EtrRecDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2020年7月8日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.background.component.order.exchange.common.dto;

import com.joyintech.background.component.common.pack.common.validation.annotation.NotEmpty;

/**
 * 投资指令接收信息
 * 
 * @author zhangrui1
 * @version 1.0 2020年7月8日
 * @see EtrRecDto
 * @since 1.0
 */
public class EtrRecDto {

    /**
     * 指令编号
     */
    private String entrustId;

    /**
     * 接收人
     */
    private String trader;

    /**
     * 接收状态
     */
    private String recStatus;

    /**
     * 获取 指令编号
     * 
     * @return 指令编号.
     */
    @NotEmpty(fieldName = "EtrComponent.entrustId")
    public String getEntrustId() {
        return entrustId;
    }

    /**
     * 设置 指令编号
     * 
     * @param entrustId 指令编号
     */
    public void setEntrustId(String entrustId) {
        this.entrustId = entrustId;
    }

    /**
     * 获取 接收人
     * 
     * @return 接收人.
     */
    public String getTrader() {
        return trader;
    }

    /**
     * 设置 接收人
     * 
     * @param trader 接收人
     */
    public void setTrader(String trader) {
        this.trader = trader;
    }

    /**
     * 获取 接收状态
     * 
     * @return 接收状态.
     */
    public String getRecStatus() {
        return recStatus;
    }

    /**
     * 设置 接收状态
     * 
     * @param recStatus 接收状态
     */
    public void setRecStatus(String recStatus) {
        this.recStatus = recStatus;
    }

}
