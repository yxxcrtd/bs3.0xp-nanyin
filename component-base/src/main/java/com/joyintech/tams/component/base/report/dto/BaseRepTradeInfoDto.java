/*
 * 文件名：BaseRepTradeInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：刘二通
 * 修改时间：2018年11月4日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.base.report.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.RepTrade;

/**
 * 交易报送信息
 *
 * @author 刘二通
 * @version 1.0 2018年11月4日
 * @see BaseRepTradeInfoDto
 * @since 1.0
 */
public class BaseRepTradeInfoDto {

    /**
     * 交易报送DTO
     */
    private RepTrade repTrade;

    /**
     * List<行内交易编码>
     */
    private List<String> bankTradeCodeList;

    /**
     * 获取 repTrade
     *
     * @return repTrade.
     */
    public RepTrade getRepTrade() {
        return repTrade;
    }

    /**
     * 设置 repTrade
     *
     * @param repTrade repTrade
     */
    public void setRepTrade(RepTrade repTrade) {
        this.repTrade = repTrade;
    }

    /**
     * 获取 bankTradeCodeList
     *
     * @return bankTradeCodeList.
     */
    public List<String> getBankTradeCodeList() {
        return bankTradeCodeList;
    }

    /**
     * 设置 bankTradeCodeList
     *
     * @param bankTradeCodeList bankTradeCodeList
     */
    public void setBankTradeCodeList(List<String> bankTradeCodeList) {
        this.bankTradeCodeList = bankTradeCodeList;
    }

}
